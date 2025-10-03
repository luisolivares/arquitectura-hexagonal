package com.challange.api.rest.banco.application.services;

import com.challange.api.rest.banco.dominio.model.Movimiento;
import com.challange.api.rest.banco.dominio.model.TipoCanal;
import com.challange.api.rest.banco.dominio.model.TipoMovimiento;
import com.challange.api.rest.banco.dominio.ports.in.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.List;

@Log4j2
public class ServiceMovimiento implements AltaMovimientoUseCase, BuscarMovimientoUseCase, ModificarMovimientoUseCase, BajaMovimientoUseCase, AsociarMovimientoClienteCuentaUseCase {

    private final AltaMovimientoUseCase altaMovimientoUseCase;
    private final BuscarMovimientoUseCase buscarMovimientoUseCase;
    private final ModificarMovimientoUseCase modificarMovimientoUseCase;
    private final BajaMovimientoUseCase bajaMovimientoUseCase;
    private final AsociarMovimientoClienteCuentaUseCase asociarMovimientoClienteCuentaUseCase;

    public ServiceMovimiento(AltaMovimientoUseCase altaMovimientoUseCase, BuscarMovimientoUseCase buscarMovimientoUseCase, ModificarMovimientoUseCase modificarMovimientoUseCase, BajaMovimientoUseCase bajaMovimientoUseCase, AsociarMovimientoClienteCuentaUseCase asociarMovimientoClienteCuentaUseCase) {
        this.altaMovimientoUseCase = altaMovimientoUseCase;
        this.buscarMovimientoUseCase = buscarMovimientoUseCase;
        this.modificarMovimientoUseCase = modificarMovimientoUseCase;
        this.bajaMovimientoUseCase = bajaMovimientoUseCase;
        this.asociarMovimientoClienteCuentaUseCase = asociarMovimientoClienteCuentaUseCase;
    }

    @Override
    public Movimiento alta(Movimiento movimiento) {
        return altaMovimientoUseCase.alta(movimiento);
    }

    @Override
    public Movimiento baja(int idMovimiento, boolean esBaja) {
        return bajaMovimientoUseCase.baja(idMovimiento, esBaja);
    }

    @Override
    public List<Movimiento> buscarMovimientoPor(TipoMovimiento tipoMovimiento, TipoCanal tipoCanal, LocalDate fechaDesde, LocalDate fechaHasta, int page, int size) {
        return buscarMovimientoUseCase.buscarMovimientoPor(tipoMovimiento, tipoCanal, fechaDesde, fechaHasta, page, size);
    }

    @Override
    public List<Movimiento> buscarTodo(int page, int size) {
        return buscarMovimientoUseCase.buscarTodo(page, size);
    }

    @Override
    public Movimiento modificarMovimiento(Movimiento movimiento, long idMovimiento) {
        return modificarMovimientoUseCase.modificarMovimiento(movimiento, idMovimiento);
    }


    @Override
    public Movimiento asociarMovimientoClienteCuentaUseCase(Movimiento movimiento, String numeroDocumentoCliente, String numeroCuenta) {
        return asociarMovimientoClienteCuentaUseCase.asociarMovimientoClienteCuentaUseCase(movimiento, numeroDocumentoCliente, numeroCuenta);
    }
}
