package com.jaroso.trazabilidad_productos.services;

import com.jaroso.trazabilidad_productos.dtos.EventoTrazabilidadCreateDto;
import com.jaroso.trazabilidad_productos.dtos.EventoTrazabilidadDto;
import com.jaroso.trazabilidad_productos.dtos.EventoTrazabilidadResumenDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoTrazabilidadService {
    EventoTrazabilidadDto registrarEvento(Long loteId, EventoTrazabilidadCreateDto dto);
    List<EventoTrazabilidadResumenDto> getHistorial(Long loteId);
    List<EventoTrazabilidadDto> filtrarPorTipo(Long loteId, String tipo);
    List<EventoTrazabilidadDto> filtrarPorFechas(Long loteId, LocalDateTime inicio, LocalDateTime fin);
}
