package com.jaroso.trazabilidad_productos.services;

import com.jaroso.trazabilidad_productos.dtos.ProductoCreateDto;
import com.jaroso.trazabilidad_productos.dtos.ProductoDto;
import com.jaroso.trazabilidad_productos.dtos.ProductoResumenDto;
import com.jaroso.trazabilidad_productos.mappers.ProductoMapper;
import com.jaroso.trazabilidad_productos.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{
    @Autowired
    private ProductoRepository repo;
    @Autowired
    private ProductoMapper mapper;

    @Override
    public ProductoDto create(ProductoCreateDto dto) {
        if (repo.findByCodigo(dto.codigo()).isPresent()) {
            throw new RuntimeException("El producto ya existe");
        }
        return mapper.toDto(repo.save(mapper.toEntity(dto)));
    }

    @Override
    @Transactional
    public List<ProductoResumenDto> findAll() {
        return repo.findAll().stream().map(mapper::toResumenDto).toList();
    }

    @Override
    public Optional<ProductoDto> findById(Long id) {
        return repo.findById(id).map(mapper::toDto);
    }

    @Override
    public ProductoDto update(Long id, ProductoCreateDto dto) {
        return repo.findById(id).map(p -> {
            p.setNombre(dto.nombre());
            p.setDescripcion(dto.descripcion());
            p.setCodigo(dto.codigo());
            return mapper.toDto(repo.save(p));
        }).orElseThrow();
    }

    @Override
    public boolean delete(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
