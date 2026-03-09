package com.jaroso.trazabilidad_productos.controllers;

import com.jaroso.trazabilidad_productos.dtos.EventoTrazabilidadCreateDto;
import com.jaroso.trazabilidad_productos.dtos.EventoTrazabilidadDto;
import com.jaroso.trazabilidad_productos.dtos.EventoTrazabilidadResumenDto;
import com.jaroso.trazabilidad_productos.services.EventoTrazabilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/lotes/{id}")
public class EventoTrazabilidadController {

    @Autowired
    private EventoTrazabilidadService service;

    @PostMapping("/eventos")
    public ResponseEntity<EventoTrazabilidadDto> registrar(@PathVariable Long id, @RequestBody EventoTrazabilidadCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarEvento(id, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<EventoTrazabilidadResumenDto>> getHistorial(@PathVariable Long id) {
        return ResponseEntity.ok(service.getHistorial(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<EventoTrazabilidadDto>> filtrarPorTipo(@PathVariable Long id, @PathVariable String tipo) {
        return ResponseEntity.ok(service.filtrarPorTipo(id, tipo));
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<EventoTrazabilidadDto>> filtrarPorFechas(
            @PathVariable Long id, @RequestParam LocalDateTime inicio, @RequestParam LocalDateTime fin)  {

        return ResponseEntity.ok(service.filtrarPorFechas(id, inicio, fin));
    }
}
