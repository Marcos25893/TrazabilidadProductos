package com.jaroso.trazabilidad_productos.security;


import com.jaroso.trazabilidad_productos.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //1. Extraer el token de la cabecera http
        String token = extractToken(request);

        //2. Validar el token
        if(this.jwtService.isValidToken(token)){
            //3.Sacar el usuario
            String username = this.jwtService.getUsernameFromToken(token);
            UserDetails user = this.userService.loadUserByUsername(username);

            //4.Generar objeto Authentication y meterlo en el SecurityContext
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        //Continuar con el siguiente filtro
        filterChain.doFilter(request, response);
    }


    //Extraer el token de la cabecera Http
    private String extractToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        //Asegurar que es un Bearer token
        if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
}
