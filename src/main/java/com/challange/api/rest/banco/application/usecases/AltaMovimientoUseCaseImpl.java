package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.domain.model.Movimiento;
import com.challange.api.rest.banco.domain.ports.in.AltaMovimientoUseCase;
import com.challange.api.rest.banco.domain.ports.out.MovimientoRepositoryPort;

public class AltaMovimientoUseCaseImpl implements AltaMovimientoUseCase {

    private final MovimientoRepositoryPort port;

    public AltaMovimientoUseCaseImpl(MovimientoRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Movimiento alta(Movimiento movimiento) {
        return port.alta(movimiento);
    }
}
