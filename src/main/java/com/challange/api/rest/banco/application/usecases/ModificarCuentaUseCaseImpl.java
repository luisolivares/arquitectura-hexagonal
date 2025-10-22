package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.domain.model.Cuenta;
import com.challange.api.rest.banco.domain.ports.in.ModificarCuentaUseCase;
import com.challange.api.rest.banco.domain.ports.out.CuentaRepositoryPort;

public class ModificarCuentaUseCaseImpl implements ModificarCuentaUseCase {

    private final CuentaRepositoryPort port;

    public ModificarCuentaUseCaseImpl(CuentaRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Cuenta modificarCuenta(Cuenta cuenta) {
        return port.modificarCuenta(cuenta);
    }
}