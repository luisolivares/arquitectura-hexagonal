package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.Cuenta;
import com.challange.api.rest.banco.dominio.ports.in.AsociarClienteCuentaUseCase;
import com.challange.api.rest.banco.dominio.ports.out.ClienteRepositoryPort;
import com.challange.api.rest.banco.dominio.ports.out.CuentaRepositoryPort;

public class AsociarClienteCuentaUseCaseImpl implements AsociarClienteCuentaUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final CuentaRepositoryPort cuentaRepositoryPort;

    public AsociarClienteCuentaUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort, CuentaRepositoryPort cuentaRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.cuentaRepositoryPort = cuentaRepositoryPort;
    }

    @Override
    public Cliente asociarClienteCuentaUseCase(String numeroDocumento, String numeroCuenta) {
        Cliente cliente = clienteRepositoryPort.buscarPorNumeroDocumento(numeroDocumento);
        Cuenta cuenta = cuentaRepositoryPort.buscarCuentaPorNumeroCuenta(numeroCuenta);
        return clienteRepositoryPort.asociarClienteCuenta(cliente, cuenta);
    }
}