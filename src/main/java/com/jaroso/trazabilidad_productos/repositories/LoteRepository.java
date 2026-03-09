package com.jaroso.trazabilidad_productos.repository;

import com.jaroso.trazabilidad_productos.entities.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Long> {
    List<Lote> findByProductoId(Long productoId);
    Optional<Lote> findByNumeroLoteAndProductoId(String numeroLote, Long productoId);
}
