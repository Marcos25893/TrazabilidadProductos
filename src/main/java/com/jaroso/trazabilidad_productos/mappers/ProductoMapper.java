package com.jaroso.trazabilidad_productos.mappers;

import com.jaroso.trazabilidad_productos.dtos.ProductoCreateDto;
import com.jaroso.trazabilidad_productos.dtos.ProductoDto;
import com.jaroso.trazabilidad_productos.dtos.ProductoResumenDto;
import com.jaroso.trazabilidad_productos.entities.Producto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoDto toDto(Producto producto);
    ProductoResumenDto toResumenDto(Producto producto);
    Producto toEntity(ProductoCreateDto dto);
}
