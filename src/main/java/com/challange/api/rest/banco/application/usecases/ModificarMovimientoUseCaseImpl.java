package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.model.Movimiento;
import com.challange.api.rest.banco.dominio.ports.in.ModificarMovimientoUseCase;
import com.challange.api.rest.banco.dominio.ports.out.MovimientoRepositoryPort;

public class ModificarMovimientoUseCaseImpl implements ModificarMovimientoUseCase {

    private final MovimientoRepositoryPort port;

    public ModificarMovimientoUseCaseImpl(MovimientoRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Movimiento modificarMovimiento(Movimiento movimiento, long idMovimiento) {
        return port.modificarMovimiento(movimiento, idMovimiento);
    }

}
