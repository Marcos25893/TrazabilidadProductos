package com.jaroso.trazabilidad_productos.repositories;

import com.jaroso.trazabilidad_productos.entities.EventoTrazabilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoTrazabilidadRepository extends JpaRepository<EventoTrazabilidad, Long> {
    List<EventoTrazabilidad> findByLoteIdOrderByTimestampAsc(Long loteId);
    List<EventoTrazabilidad> findByLoteIdAndTipoEventoOrderByTimestampAsc(Long loteId, String tipo);
    List<EventoTrazabilidad> findByLoteIdAndTimestampBetweenOrderByTimestampAsc(Long loteId, LocalDateTime inicio, LocalDateTime fin);
}
