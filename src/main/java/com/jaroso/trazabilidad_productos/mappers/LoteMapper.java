package com.jaroso.trazabilidad_productos.mappers;

import com.jaroso.trazabilidad_productos.dtos.LoteCreateDto;
import com.jaroso.trazabilidad_productos.dtos.LoteDto;
import com.jaroso.trazabilidad_productos.dtos.LoteResumenDto;
import com.jaroso.trazabilidad_productos.entities.Lote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoteMapper {
    @Mapping(source = "producto.id", target = "productoId")
    LoteDto toDto(Lote lote);
    LoteResumenDto toResumenDto(Lote lote);
    Lote toEntity(LoteCreateDto dto);
}
