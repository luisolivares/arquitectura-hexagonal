package com.challange.api.rest.banco.infrastructure.repository.adapter;

import com.challange.api.rest.banco.domain.model.*;
import com.challange.api.rest.banco.domain.ports.out.MovimientoRepositoryPort;
import com.challange.api.rest.banco.infrastructure.entity.ClienteEntity;
import com.challange.api.rest.banco.infrastructure.entity.CuentaEntity;
import com.challange.api.rest.banco.infrastructure.entity.MovimientoEntity;
import com.challange.api.rest.banco.infrastructure.entity.TarjetaEntity;
import com.challange.api.rest.banco.infrastructure.repository.MovimientoRepository;
import com.challange.api.rest.banco.infrastructure.utils.ObjectMapperUtils;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class JPAMovimientoRepositoryAdapter implements MovimientoRepositoryPort {

    private final MovimientoRepository movimientoRepository;

    @Transactional
    @Override
    public Movimiento alta(Movimiento movimiento) {
        MovimientoEntity movimientoEntity = ObjectMapperUtils.map(movimiento, MovimientoEntity.class);
        MovimientoEntity responseEntity = movimientoRepository.saveAndFlush(movimientoEntity);
        return ObjectMapperUtils.map(responseEntity, Movimiento.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movimiento> buscarMovimientoPor(TipoMovimiento tipoMovimiento, TipoCanal tipoCanal, LocalDate fechaDesde, LocalDate fechaHasta, int page, int size) {

        Specification<MovimientoEntity> spec = filtrar(
                tipoMovimiento, tipoCanal, fechaDesde, fechaHasta
        );

        Pageable paging = PageRequest.of(page, size);

        List<Movimiento> movimientos = new ArrayList<>();

        Page<MovimientoEntity> response = movimientoRepository.findAll(spec, paging);
        if (response.hasContent()) {
            movimientos = ObjectMapperUtils.mapAll(response.getContent(), Movimiento.class);
            return movimientos;
        } else {
            return movimientos;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movimiento> buscarTodo(int page, int size) {
        List<Movimiento> movimientos = new ArrayList<>();
        Pageable paging = PageRequest.of(page, size);
        Page<MovimientoEntity> response = movimientoRepository.findAll(paging);
        if (response.hasContent()) {
            movimientos = ObjectMapperUtils.mapAll(response.getContent(), Movimiento.class);
            return movimientos;
        } else {
            return movimientos;
        }
    }

    @Override
    public Movimiento buscarMovimientoPorId(long idMovimiento) {
        Optional<MovimientoEntity> movimientoEntity = movimientoRepository.buscarPorId(idMovimiento);
        return ObjectMapperUtils.map(movimientoEntity.get(), Movimiento.class);
    }

    @Override
    public Movimiento buscarMovimientoPorReferenciaOperacion(String referenciaOperacion) {
        Optional<MovimientoEntity> movimientoEntity = movimientoRepository.buscarPorReferencia(referenciaOperacion);
        return ObjectMapperUtils.map(movimientoEntity.get(), Movimiento.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Movimiento modificarMovimiento(Movimiento movimiento) {
        MovimientoEntity movimientoEntity = ObjectMapperUtils.map(movimiento, MovimientoEntity.class);
        return ObjectMapperUtils.map(movimientoRepository.saveAndFlush(movimientoEntity), Movimiento.class);
    }

    @Transactional
    @Override
    public void baja(long idMovimiento) {
        Optional<MovimientoEntity> movimientoEntity = movimientoRepository.buscarPorId(idMovimiento);
        movimientoRepository.delete(movimientoEntity.get());
    }

    @Transactional
    @Override
    public Movimiento asociarClienteCuentaMovimiento(Movimiento movimiento, Cliente cliente, Cuenta cuenta) {

        ClienteEntity clienteEntity = ObjectMapperUtils.map(cliente, ClienteEntity.class);
        CuentaEntity cuentaEntity = ObjectMapperUtils.map(cuenta, CuentaEntity.class);

//        MovimientoEntity movimientoEntityAdd = new MovimientoEntity();
//        movimientoEntityAdd.setSaldoInicial(movimiento.getSaldoInicial());
//        movimientoEntityAdd.setSaldoMovimiento(movimiento.getSaldoMovimiento());
//        movimientoEntityAdd.setSaldoDisponible(movimiento.getSaldoDisponible());
//        movimientoEntityAdd.setFechaMovimiento(movimiento.getFechaMovimiento());
//        movimientoEntityAdd.setTipoMovimiento(movimiento.getTipoMovimiento());
//        movimientoEntityAdd.setTipoCanal(movimiento.getTipoCanal());
//        movimientoEntityAdd.setConcepto(movimiento.getConcepto());
//        movimientoEntityAdd.setTipoDivisa(movimiento.getTipoDivisa());
//        movimientoEntityAdd.setReferenciaOperacion(movimiento.getReferenciaOperacion());

        MovimientoEntity movimientoEntityAdd = ObjectMapperUtils.map(movimiento, MovimientoEntity.class);
        // Asociar cliente y cuenta ya cargados de BD
        movimientoEntityAdd.setCliente(clienteEntity);
        movimientoEntityAdd.setCuenta(cuentaEntity);

        return ObjectMapperUtils.map(movimientoRepository.saveAndFlush(movimientoEntityAdd), Movimiento.class);

    }

    @Override
    public Movimiento asociarClienteTarjetaMovimiento(Movimiento movimiento, Cliente cliente, Tarjeta tarjeta) {

        ClienteEntity clienteEntity = ObjectMapperUtils.map(cliente, ClienteEntity.class);
        TarjetaEntity tarjetaEntity = ObjectMapperUtils.map(tarjeta, TarjetaEntity.class);

        MovimientoEntity movimientoEntityAdd = ObjectMapperUtils.map(movimiento, MovimientoEntity.class);
        // Asociar cliente y cuenta ya cargados de BD
        movimientoEntityAdd.setCliente(clienteEntity);
        movimientoEntityAdd.setTarjeta(tarjetaEntity);

        return ObjectMapperUtils.map(movimientoRepository.saveAndFlush(movimientoEntityAdd), Movimiento.class);
    }

    private static Specification<MovimientoEntity> filtrar(
            TipoMovimiento tipoMovimiento,
            TipoCanal tipoCanal,
            LocalDate fechaDesde,
            LocalDate fechaHasta
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtrar por tipoMovimiento (enum)
            if (tipoMovimiento != null) {
                predicates.add(cb.equal(root.get("tipoMovimiento"), tipoMovimiento));
            }

            // Filtrar por tipoDivisa (otro enum)
            if (tipoCanal != null) {
                predicates.add(cb.equal(root.get("tipoCanal"), tipoCanal));
            }

            // Filtro por fecha entre rango
            if (fechaDesde != null && fechaHasta != null) {
                predicates.add(cb.between(root.get("fechaTransaccion"), fechaDesde, fechaHasta));
            } else if (fechaDesde != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("fechaTransaccion"), fechaDesde));
            } else if (fechaHasta != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("fechaTransaccion"), fechaHasta));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
