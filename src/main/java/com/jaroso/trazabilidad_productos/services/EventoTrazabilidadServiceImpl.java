package com.jaroso.trazabilidad_productos.services;

import com.jaroso.trazabilidad_productos.dtos.EventoTrazabilidadCreateDto;
import com.jaroso.trazabilidad_productos.dtos.EventoTrazabilidadDto;
import com.jaroso.trazabilidad_productos.dtos.EventoTrazabilidadResumenDto;
import com.jaroso.trazabilidad_productos.entities.EventoTrazabilidad;
import com.jaroso.trazabilidad_productos.entities.Lote;
import com.jaroso.trazabilidad_productos.mappers.EventoTrazabilidadMapper;
import com.jaroso.trazabilidad_productos.repositories.EventoTrazabilidadRepository;
import com.jaroso.trazabilidad_productos.repositories.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoTrazabilidadServiceImpl  implements EventoTrazabilidadService{
    @Autowired
    private EventoTrazabilidadRepository eventoRepo;
    @Autowired
    private LoteRepository loteRepo;
    @Autowired
    private EventoTrazabilidadMapper mapper;

    @Override
    @Transactional
    public EventoTrazabilidadDto registrarEvento(Long loteId, EventoTrazabilidadCreateDto dto) {
        Lote lote = loteRepo.findById(loteId).orElseThrow();
        EventoTrazabilidad evento = mapper.toEntity(dto);
        evento.setTimestamp(LocalDateTime.now());
        lote.addEvento(evento);

        return mapper.toDto(eventoRepo.save(evento));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventoTrazabilidadResumenDto> getHistorial(Long loteId) {
        return eventoRepo.findByLoteIdOrderByTimestampAsc(loteId)
                .stream().map(mapper::toResumenDto).toList();
    }

    @Override
    @Transactional
    public List<EventoTrazabilidadDto> filtrarPorTipo(Long loteId, String tipo) {
        return eventoRepo.findByLoteIdAndTipoEventoOrderByTimestampAsc(loteId, tipo)
                .stream().map(mapper::toDto).toList();
    }

    @Override
    @Transactional
    public List<EventoTrazabilidadDto> filtrarPorFechas(Long loteId, LocalDateTime inicio, LocalDateTime fin) {
        return eventoRepo.findByLoteIdAndTimestampBetweenOrderByTimestampAsc(loteId, inicio, fin)
                .stream().map(mapper::toDto).toList();
    }
}
