package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.model.Cuenta;
import com.challange.api.rest.banco.dominio.ports.in.BajaCuentaUseCase;
import com.challange.api.rest.banco.dominio.ports.out.CuentaRepositoryPort;

public class BajaCuentaUseCaseImpl implements BajaCuentaUseCase {

    private final CuentaRepositoryPort port;

    public BajaCuentaUseCaseImpl(CuentaRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Cuenta baja(String numeroCuenta, boolean estado) {
        return port.baja(numeroCuenta, estado);
    }
}
