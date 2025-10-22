package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.domain.model.Movimiento;
import com.challange.api.rest.banco.domain.ports.in.ModificarMovimientoUseCase;
import com.challange.api.rest.banco.domain.ports.out.MovimientoRepositoryPort;

public class ModificarMovimientoUseCaseImpl implements ModificarMovimientoUseCase {

    private final MovimientoRepositoryPort port;

    public ModificarMovimientoUseCaseImpl(MovimientoRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Movimiento modificarMovimiento(Movimiento movimiento) {
        return port.modificarMovimiento(movimiento);
    }

}
