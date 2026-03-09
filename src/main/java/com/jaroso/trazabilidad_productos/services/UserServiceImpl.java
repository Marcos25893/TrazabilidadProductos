package com.jaroso.trazabilidad_productos.services;



import com.jaroso.trazabilidad_productos.dtos.UserCreateDto;
import com.jaroso.trazabilidad_productos.dtos.UserDto;
import com.jaroso.trazabilidad_productos.entities.Usuario;
import com.jaroso.trazabilidad_productos.mappers.UserMapper;
import com.jaroso.trazabilidad_productos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public Optional<UserDto>  findById(Long id) {
        return userRepository.findById(id).map(mapper::toDto);
    }

    @Override
    public Optional<UserDto> findByUserName(String username) {
        return userRepository.findByUserName(username).map(mapper::toDto);
    }

    @Override
    public UserDto saveUser(UserCreateDto user) {
        Usuario userEntity = mapper.toEntity(user);
        return mapper.toDto(userRepository.save(userEntity));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    //Metodo para seguridad que dado un username devuelve el Usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException(username + " no encontrado")
        );
    }
}
