package com.jaroso.trazabilidad_productos.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "lotes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numeroLote;

    private LocalDate fechaProduccion;
    private Integer cantidad;
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @OneToMany(mappedBy = "lote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoTrazabilidad> eventos = new ArrayList<>();

    public void addEvento(EventoTrazabilidad evento) {
        eventos.add(evento);
        evento.setLote(this);
    }
}

