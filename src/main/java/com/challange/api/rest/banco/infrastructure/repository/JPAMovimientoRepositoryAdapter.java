package com.challange.api.rest.banco.infrastructure.repository;

import com.challange.api.rest.banco.dominio.model.*;
import com.challange.api.rest.banco.dominio.ports.out.MovimientoRepositoryPort;
import com.challange.api.rest.banco.infrastructure.entity.ClienteEntity;
import com.challange.api.rest.banco.infrastructure.entity.CuentaEntity;
import com.challange.api.rest.banco.infrastructure.entity.MovimientoEntity;
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

    @Transactional(readOnly = true)
    @Override
    public Movimiento modificarMovimiento(Movimiento movimiento, long idMovimiento) {

        Optional<MovimientoEntity> movimientoEntity = movimientoRepository.buscarPorId(idMovimiento);

        if (movimientoEntity.isPresent()) {
            movimientoEntity.get().setSaldoInicial(movimiento.getSaldoInicial());
            movimientoEntity.get().setSaldoMovimiento(movimiento.getSaldoMovimiento());
            movimientoEntity.get().setSaldoDisponible(movimiento.getSaldoDisponible());
            movimientoEntity.get().setFechaMovimiento(movimiento.getFechaMovimiento());
            movimientoEntity.get().setTipoMovimiento(movimiento.getTipoMovimiento());
            movimientoEntity.get().setTipoCanal(movimiento.getTipoCanal());
            movimientoEntity.get().setConcepto(movimiento.getConcepto());
            movimientoEntity.get().setTipoDivisa(movimiento.getTipoDivisa());

            return ObjectMapperUtils.map(movimientoRepository.saveAndFlush(movimientoEntity.get()), Movimiento.class);

        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public Movimiento baja(int idMovimiento, boolean esBaja) {
        return null;
    }

    @Transactional
    @Override
    public Movimiento asociarClienteCuentaMovimiento(Movimiento movimiento, Cliente cliente, Cuenta cuenta) {

        Optional<Cuenta> cuentaOptional = Optional.ofNullable(cuenta);
        Optional<Cliente> clienteOptional = Optional.ofNullable(cliente);

        Optional<MovimientoEntity> movimientoEntity = movimientoRepository.buscarPorReferencia(movimiento.getReferenciaOperacion());
        if (movimientoEntity.isPresent() && clienteOptional.isPresent() && cuentaOptional.isPresent()) {
            ClienteEntity clienteEntity = ObjectMapperUtils.map(cliente, ClienteEntity.class);
            CuentaEntity cuentaEntity = ObjectMapperUtils.map(cuenta, CuentaEntity.class);

            MovimientoEntity movimientoEntityAdd = new MovimientoEntity();
            movimientoEntityAdd.setSaldoInicial(movimiento.getSaldoInicial());
            movimientoEntityAdd.setSaldoMovimiento(movimiento.getSaldoMovimiento());
            movimientoEntityAdd.setSaldoDisponible(movimiento.getSaldoDisponible());
            movimientoEntityAdd.setFechaMovimiento(movimiento.getFechaMovimiento());
            movimientoEntityAdd.setTipoMovimiento(movimiento.getTipoMovimiento());
            movimientoEntityAdd.setTipoCanal(movimiento.getTipoCanal());
            movimientoEntityAdd.setConcepto(movimiento.getConcepto());
            movimientoEntityAdd.setTipoDivisa(movimiento.getTipoDivisa());
            movimientoEntityAdd.setReferenciaOperacion(movimiento.getReferenciaOperacion());
            // Asociar cliente y cuenta ya cargados de BD
            movimientoEntityAdd.setCliente(clienteEntity);
            movimientoEntityAdd.setCuenta(cuentaEntity);

            return ObjectMapperUtils.map(movimientoRepository.saveAndFlush(movimientoEntityAdd), Movimiento.class);

        } else {
            return null;
        }
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
