package com.jaroso.trazabilidad_productos.controllers;

import com.jaroso.trazabilidad_productos.dtos.LoteCreateDto;
import com.jaroso.trazabilidad_productos.dtos.LoteDto;
import com.jaroso.trazabilidad_productos.dtos.LoteResumenDto;
import com.jaroso.trazabilidad_productos.dtos.LoteUpdateDto;
import com.jaroso.trazabilidad_productos.services.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoteController {

    @Autowired
    private LoteService service;

    @GetMapping("/productos/{id}/lotes")
    public ResponseEntity<List<LoteResumenDto>> getLotesByProducto(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByProducto(id));
    }

    @PostMapping("/productos/{id}/lotes")
    public ResponseEntity<LoteDto> createLote(@PathVariable Long id, @RequestBody LoteCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(id, dto));
    }

    @GetMapping("/lotes/{id}")
    public ResponseEntity<LoteDto> getLoteById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/lotes/{id}/estado")
    public ResponseEntity<LoteDto> updateEstado(@PathVariable Long id, @RequestBody LoteUpdateDto dto) {
        return ResponseEntity.ok(service.updateEstado(id, dto.estado()));
    }
}
