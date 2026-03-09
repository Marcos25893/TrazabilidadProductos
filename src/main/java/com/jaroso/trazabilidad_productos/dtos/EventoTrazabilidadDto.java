package com.jaroso.trazabilidad_productos.dtos;

import java.time.LocalDateTime;

public record EventoTrazabilidadDto(Long id, LocalDateTime timestamp, String tipoEvento, String ubicacion, String observaciones, Long loteId) {
}
