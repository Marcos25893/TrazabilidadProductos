package com.jaroso.trazabilidad_productos.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity(name = "eventos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EventoTrazabilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private String tipoEvento;
    private String ubicacion;
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id")
    private Lote lote;
}