package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.Cuenta;
import com.challange.api.rest.banco.dominio.model.Movimiento;
import com.challange.api.rest.banco.dominio.ports.in.AsociarMovimientoClienteCuentaUseCase;
import com.challange.api.rest.banco.dominio.ports.out.ClienteRepositoryPort;
import com.challange.api.rest.banco.dominio.ports.out.CuentaRepositoryPort;
import com.challange.api.rest.banco.dominio.ports.out.MovimientoRepositoryPort;

public class AsociarMovimientoClienteCuentaUseCaseImpl implements AsociarMovimientoClienteCuentaUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final CuentaRepositoryPort cuentaRepositoryPort;
    private final MovimientoRepositoryPort movimientoRepositoryPort;

    public AsociarMovimientoClienteCuentaUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort, CuentaRepositoryPort cuentaRepositoryPort, MovimientoRepositoryPort movimientoRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.cuentaRepositoryPort = cuentaRepositoryPort;
        this.movimientoRepositoryPort = movimientoRepositoryPort;
    }

    @Override
    public Movimiento asociarMovimientoClienteCuentaUseCase(Movimiento movimiento, String numeroDocumentoCliente, String numeroCuenta) {
        Cliente cliente = clienteRepositoryPort.buscarPorNumeroDocumento(numeroDocumentoCliente);
        Cuenta cuenta = cuentaRepositoryPort.buscarCuentaPorNumeroCuenta(numeroCuenta);
        return movimientoRepositoryPort.asociarClienteCuentaMovimiento(movimiento, cliente, cuenta);
    }
}
