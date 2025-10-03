package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.model.Cuenta;
import com.challange.api.rest.banco.dominio.ports.in.BuscarCuentaUseCase;
import com.challange.api.rest.banco.dominio.ports.out.CuentaRepositoryPort;

import java.util.List;

public class BuscarCuentaUseCaseImpl implements BuscarCuentaUseCase {

    private final CuentaRepositoryPort port;

    public BuscarCuentaUseCaseImpl(CuentaRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Cuenta buscarCuentaPor(String numeroCuenta) {
        return port.buscarCuentaPorNumeroCuenta(numeroCuenta);
    }

    @Override
    public List<Cuenta> buscarTodasCuentas(int page, int size) {
        return port.buscarTodasCuentas(page, size);
    }
}
