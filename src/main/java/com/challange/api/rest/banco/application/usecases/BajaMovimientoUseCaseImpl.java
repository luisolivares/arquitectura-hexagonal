package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.ports.in.BajaMovimientoUseCase;
import com.challange.api.rest.banco.dominio.ports.out.MovimientoRepositoryPort;

public class BajaMovimientoUseCaseImpl implements BajaMovimientoUseCase {

    private final MovimientoRepositoryPort port;

    public BajaMovimientoUseCaseImpl(MovimientoRepositoryPort port) {
        this.port = port;
    }

    @Override
    public void baja(long idMovimiento) {
        port.baja(idMovimiento);
    }
}

