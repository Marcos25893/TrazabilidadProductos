package com.jaroso.trazabilidad_productos.services;

import com.jaroso.trazabilidad_productos.dtos.ProductoCreateDto;
import com.jaroso.trazabilidad_productos.dtos.ProductoDto;
import com.jaroso.trazabilidad_productos.dtos.ProductoResumenDto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    ProductoDto create(ProductoCreateDto dto);
    List<ProductoResumenDto> findAll();
    Optional<ProductoDto> findById(Long id);
    ProductoDto update(Long id, ProductoCreateDto dto);
    boolean delete(Long id);
}
