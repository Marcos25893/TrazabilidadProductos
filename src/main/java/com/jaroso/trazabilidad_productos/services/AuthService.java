package com.jaroso.trazabilidad_productos.service;


import com.jaroso.trazabilidad_productos.dtos.AuthDto;
import com.jaroso.trazabilidad_productos.dtos.UserCreateDto;
import com.jaroso.trazabilidad_productos.dtos.UserDto;
import com.jaroso.trazabilidad_productos.dtos.UserLoginDto;
import com.jaroso.trazabilidad_productos.entities.Usuario;
import com.jaroso.trazabilidad_productos.repository.UserRepository;
import com.jaroso.trazabilidad_productos.security.JwtService;
import com.jaroso.trazabilidad_productos.security.UserAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    /**
     * Guardar un nuevo usuario en la bbdd
     * @param userDTO
     * @return
     */
    public UserDto save(UserCreateDto userDTO) {
        Usuario user = new Usuario(
                null,
                userDTO.userName(),
                passwordEncoder.encode(userDTO.password()),
                userDTO.email(),
                List.of(UserAuthority.READ)
        );

        //Comprobar que el username no este ya en la bbdd
        if (this.repository.findByUserName(user.getUsername()).isPresent()) {
            log.error("El usuario ya existe");
            throw new RuntimeException("El usuario ya existe");
        }
        this.repository.save(user);

        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }

    /**
     * Login del usuario
     * @param user
     * @return
     */
    public ResponseEntity<AuthDto> login (UserLoginDto user){
        //1.Buscar el usuario en la bbdd

        Optional<Usuario> userOptional = this.repository.findByUserName(user.userName());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        //2.Comprobar la contraseña
        Authentication authDto = new UsernamePasswordAuthenticationToken(user.userName(), user.password());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.userName(),
                        user.password()
                )

        );


        //3.Generar el token JWT
        String token = jwtService.generateToken(authentication);

        //4.Generar el objeto AuthDto y devolverlo
        Usuario userEntity = (Usuario) authentication.getPrincipal();
        AuthDto auth = new AuthDto(userEntity.getUsername(),
                userEntity.getAuthorities().stream().map(Object::toString).toList(),
                token);
        return ResponseEntity.ok(auth);
    }



}
