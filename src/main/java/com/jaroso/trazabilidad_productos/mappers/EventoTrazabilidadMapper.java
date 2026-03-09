package com.jaroso.trazabilidad_productos.mappers;


import com.jaroso.trazabilidad_productos.dtos.EventoTrazabilidadCreateDto;
import com.jaroso.trazabilidad_productos.dtos.EventoTrazabilidadDto;
import com.jaroso.trazabilidad_productos.dtos.EventoTrazabilidadResumenDto;
import com.jaroso.trazabilidad_productos.entities.EventoTrazabilidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventoTrazabilidadMapper {
    @Mapping(source = "lote.id", target = "loteId")
    EventoTrazabilidadDto toDto(EventoTrazabilidad evento);
    EventoTrazabilidadResumenDto toResumenDto(EventoTrazabilidad evento);
    EventoTrazabilidad toEntity(EventoTrazabilidadCreateDto dto);
}
