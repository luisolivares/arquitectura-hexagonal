package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.domain.exceptions.MovimientoException;
import com.challange.api.rest.banco.domain.model.Cliente;
import com.challange.api.rest.banco.domain.model.Cuenta;
import com.challange.api.rest.banco.domain.model.Movimiento;
import com.challange.api.rest.banco.domain.model.TipoDocumento;
import com.challange.api.rest.banco.domain.ports.in.AsociarMovimientoClienteCuentaUseCase;
import com.challange.api.rest.banco.domain.ports.out.ClienteRepositoryPort;
import com.challange.api.rest.banco.domain.ports.out.CuentaRepositoryPort;
import com.challange.api.rest.banco.domain.ports.out.MovimientoRepositoryPort;

import java.util.Optional;

public class AsociarMovimientoClienteCuentaUseCaseImpl implements AsociarMovimientoClienteCuentaUseCase {

    private static String errorAsociarMovimientoClienteCuenta;

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final CuentaRepositoryPort cuentaRepositoryPort;
    private final MovimientoRepositoryPort movimientoRepositoryPort;

    public AsociarMovimientoClienteCuentaUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort, CuentaRepositoryPort cuentaRepositoryPort, MovimientoRepositoryPort movimientoRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.cuentaRepositoryPort = cuentaRepositoryPort;
        this.movimientoRepositoryPort = movimientoRepositoryPort;
    }

    @Override
    public Movimiento asociarMovimientoClienteCuentaUseCase(Movimiento movimiento, TipoDocumento tipoDocumento, String numeroDocumentoCliente, String numeroCuenta) {
        Optional<Cliente> cliente = Optional.ofNullable(clienteRepositoryPort.buscarPorTipoYNumeroDocumento(tipoDocumento, numeroDocumentoCliente));
        Optional<Cuenta> cuenta = Optional.ofNullable(cuentaRepositoryPort.buscarCuentaPorNumeroCuenta(numeroCuenta));

        if (cliente.isEmpty()) {
            errorAsociarMovimientoClienteCuenta = String.format("No existe el cliente %s %s", tipoDocumento, numeroDocumentoCliente);
            throw new MovimientoException(errorAsociarMovimientoClienteCuenta, 404);
        }

        if (cuenta.isEmpty()) {
            errorAsociarMovimientoClienteCuenta = String.format("No existe la cuenta %s", numeroCuenta);
            throw new MovimientoException(errorAsociarMovimientoClienteCuenta, 404);
        }

        return movimientoRepositoryPort.asociarClienteCuentaMovimiento(movimiento, cliente.get(), cuenta.get());
    }
}
