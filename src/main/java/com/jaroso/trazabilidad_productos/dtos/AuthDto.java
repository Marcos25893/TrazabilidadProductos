package com.jaroso.trazabilidad_productos.dtos;

import java.util.List;

public record AuthDto(String username, List<String> authorities, String token) {
}
