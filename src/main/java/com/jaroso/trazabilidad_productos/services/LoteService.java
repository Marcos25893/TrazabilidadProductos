package com.jaroso.trazabilidad_productos.services;

import com.jaroso.trazabilidad_productos.dtos.LoteCreateDto;
import com.jaroso.trazabilidad_productos.dtos.LoteDto;
import com.jaroso.trazabilidad_productos.dtos.LoteResumenDto;

import java.util.List;
import java.util.Optional;

public interface LoteService {
    LoteDto create(Long productoId, LoteCreateDto dto);
    List<LoteResumenDto> findByProducto(Long productoId);
    Optional<LoteDto> findById(Long id);
    LoteDto updateEstado(Long id, String nuevoEstado);
}
