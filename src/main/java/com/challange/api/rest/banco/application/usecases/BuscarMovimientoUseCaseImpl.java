package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.domain.model.Movimiento;
import com.challange.api.rest.banco.domain.model.TipoCanal;
import com.challange.api.rest.banco.domain.model.TipoMovimiento;
import com.challange.api.rest.banco.domain.ports.in.BuscarMovimientoUseCase;
import com.challange.api.rest.banco.domain.ports.out.MovimientoRepositoryPort;

import java.time.LocalDate;
import java.util.List;

public class BuscarMovimientoUseCaseImpl implements BuscarMovimientoUseCase {

    private final MovimientoRepositoryPort port;

    public BuscarMovimientoUseCaseImpl(MovimientoRepositoryPort port) {
        this.port = port;
    }

    @Override
    public List<Movimiento> buscarMovimientoPor(TipoMovimiento tipoMovimiento, TipoCanal tipoCanal, LocalDate fechaDesde, LocalDate fechaHasta, int page, int size) {
        return port.buscarMovimientoPor(tipoMovimiento, tipoCanal, fechaDesde, fechaHasta, page, size);
    }

    @Override
    public List<Movimiento> buscarTodo(int page, int size) {
        return port.buscarTodo(page, size);
    }

    @Override
    public Movimiento buscarMovimientoPorId(long idMovimiento) {
        return port.buscarMovimientoPorId(idMovimiento);
    }

    @Override
    public Movimiento buscarMovimientoPorReferenciaOperacion(String referenciaOperacion) {
        return port.buscarMovimientoPorReferenciaOperacion(referenciaOperacion);
    }
}
