package com.jaroso.trazabilidad_productos.mapper;



import com.jaroso.trazabilidad_productos.dtos.UserCreateDto;
import com.jaroso.trazabilidad_productos.dtos.UserDto;
import com.jaroso.trazabilidad_productos.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(Usuario user);
    Usuario toEntity(UserCreateDto userDto);
}