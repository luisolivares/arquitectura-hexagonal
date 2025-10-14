package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.exceptions.ClienteException;
import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.Cuenta;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;
import com.challange.api.rest.banco.dominio.ports.in.AsociarClienteCuentaUseCase;
import com.challange.api.rest.banco.dominio.ports.out.ClienteRepositoryPort;
import com.challange.api.rest.banco.dominio.ports.out.CuentaRepositoryPort;

import java.util.Optional;

public class AsociarClienteCuentaUseCaseImpl implements AsociarClienteCuentaUseCase {

    private String errorAsociarClienteCuenta;

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final CuentaRepositoryPort cuentaRepositoryPort;

    public AsociarClienteCuentaUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort, CuentaRepositoryPort cuentaRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.cuentaRepositoryPort = cuentaRepositoryPort;
    }

    @Override
    public Cliente asociarClienteCuentaUseCase(TipoDocumento tipoDocumento, String numeroDocumento, String numeroCuenta) {
        Optional<Cliente> cliente = Optional.ofNullable(clienteRepositoryPort.buscarPorTipoYNumeroDocumento(tipoDocumento, numeroDocumento));
        Optional<Cuenta> cuenta = Optional.ofNullable(cuentaRepositoryPort.buscarCuentaPorNumeroCuenta(numeroCuenta));

        if (cliente.isEmpty()) {
            errorAsociarClienteCuenta = String.format("No existe el cliente con el numero documento %s", numeroDocumento);
            throw new ClienteException(errorAsociarClienteCuenta, 404);
        }

        if (cuenta.isEmpty()) {
            errorAsociarClienteCuenta = String.format("No existe la cuenta con el numero de cuenta %s", numeroCuenta);
            throw new ClienteException(errorAsociarClienteCuenta, 404);
        }

        return clienteRepositoryPort.asociarClienteCuenta(cliente.get(), cuenta.get());

    }
}