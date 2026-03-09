package com.jaroso.trazabilidad_productos.dtos;

import java.time.LocalDate;

public record LoteCreateDto(String numeroLote, LocalDate fechaProduccion, Integer cantidad, String estado) {
}
