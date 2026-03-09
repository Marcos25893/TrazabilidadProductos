package com.jaroso.trazabilidad_productos.service;



import com.jaroso.trazabilidad_productos.dtos.UserCreateDto;
import com.jaroso.trazabilidad_productos.dtos.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll();
    Optional<UserDto> findById(Long id);
    Optional<UserDto> findByUserName(String username);
    UserDto saveUser(UserCreateDto user);
    void deleteUser(Long id);

    UserDetails loadUserByUsername(String username);
}
