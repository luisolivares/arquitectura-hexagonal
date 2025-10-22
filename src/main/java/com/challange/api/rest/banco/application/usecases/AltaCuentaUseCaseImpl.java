package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.domain.model.Cuenta;
import com.challange.api.rest.banco.domain.ports.in.AltaCuentaUseCase;
import com.challange.api.rest.banco.domain.ports.out.CuentaRepositoryPort;

public class AltaCuentaUseCaseImpl implements AltaCuentaUseCase {

    private final CuentaRepositoryPort port;

    public AltaCuentaUseCaseImpl(CuentaRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Cuenta alta(Cuenta cuenta) {
        return port.alta(cuenta);
    }
}
