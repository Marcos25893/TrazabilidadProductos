package com.jaroso.trazabilidad_productos.dtos;

import java.time.LocalDateTime;

public record EventoTrazabilidadResumenDto(Long id, LocalDateTime timestamp, String tipoEvento, String ubicacion) {
}
