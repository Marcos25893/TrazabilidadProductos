package com.jaroso.trazabilidad_productos.dtos;

import java.time.LocalDate;

public record LoteDto(Long id, String numeroLote, LocalDate fechaProduccion, Integer cantidad, String estado, Long productoId) {
}
