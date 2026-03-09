package com.jaroso.trazabilidad_productos.services;

import com.jaroso.trazabilidad_productos.dtos.LoteCreateDto;
import com.jaroso.trazabilidad_productos.dtos.LoteDto;
import com.jaroso.trazabilidad_productos.dtos.LoteResumenDto;
import com.jaroso.trazabilidad_productos.entities.Lote;
import com.jaroso.trazabilidad_productos.entities.Producto;
import com.jaroso.trazabilidad_productos.mappers.LoteMapper;
import com.jaroso.trazabilidad_productos.repositories.LoteRepository;
import com.jaroso.trazabilidad_productos.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LoteServiceImpl implements LoteService {

    @Autowired
    private LoteRepository loteRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private LoteMapper mapper;

    @Override
    @Transactional
    public LoteDto create(Long productoId, LoteCreateDto dto) {

        Producto producto = productoRepo.findById(productoId).orElseThrow();
        Lote lote = mapper.toEntity(dto);
        producto.addLote(lote);
        return mapper.toDto(loteRepo.save(lote));
    }

    @Override
    public List<LoteResumenDto> findByProducto(Long productoId) {
        return loteRepo.findByProductoId(productoId).stream()
                .map(mapper::toResumenDto).toList();
    }

    @Override
    public Optional<LoteDto> findById(Long id) {
        return loteRepo.findById(id).map(mapper::toDto);
    }

    @Override
    public LoteDto updateEstado(Long id, String nuevoEstado) {
        Lote lote = loteRepo.findById(id).orElseThrow();
        lote.setEstado(nuevoEstado);
        return mapper.toDto(loteRepo.save(lote));
    }
}
