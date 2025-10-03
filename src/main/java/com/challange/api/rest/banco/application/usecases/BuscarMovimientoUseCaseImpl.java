package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.model.Movimiento;
import com.challange.api.rest.banco.dominio.model.TipoCanal;
import com.challange.api.rest.banco.dominio.model.TipoMovimiento;
import com.challange.api.rest.banco.dominio.ports.in.BuscarMovimientoUseCase;
import com.challange.api.rest.banco.dominio.ports.out.MovimientoRepositoryPort;

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
}
