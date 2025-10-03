package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.model.Cuenta;
import com.challange.api.rest.banco.dominio.ports.in.ModificarCuentaUseCase;
import com.challange.api.rest.banco.dominio.ports.out.CuentaRepositoryPort;

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