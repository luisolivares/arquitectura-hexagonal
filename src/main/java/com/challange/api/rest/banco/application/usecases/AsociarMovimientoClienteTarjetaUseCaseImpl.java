package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.exceptions.MovimientoException;
import com.challange.api.rest.banco.dominio.model.*;
import com.challange.api.rest.banco.dominio.ports.in.AsociarMovimientoClienteTarjetaUseCase;
import com.challange.api.rest.banco.dominio.ports.out.ClienteRepositoryPort;
import com.challange.api.rest.banco.dominio.ports.out.MovimientoRepositoryPort;
import com.challange.api.rest.banco.dominio.ports.out.TarjetaRepositoryPort;

import java.util.Optional;

public class AsociarMovimientoClienteTarjetaUseCaseImpl implements AsociarMovimientoClienteTarjetaUseCase {

    private static String errorAsociarMovimientoClienteTarjeta;

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final TarjetaRepositoryPort tarjetaRepositoryPort;
    private final MovimientoRepositoryPort movimientoRepositoryPort;

    public AsociarMovimientoClienteTarjetaUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort, TarjetaRepositoryPort tarjetaRepositoryPort, MovimientoRepositoryPort movimientoRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.tarjetaRepositoryPort = tarjetaRepositoryPort;
        this.movimientoRepositoryPort = movimientoRepositoryPort;
    }

    @Override
    public Movimiento asociarMovimientoClienteTarjetaUseCase(Movimiento movimiento, TipoDocumento tipoDocumento, String numeroDocumentoCliente, String numeroTarjeta) {

        Optional<Cliente> cliente = Optional.ofNullable(clienteRepositoryPort.buscarPorTipoYNumeroDocumento(tipoDocumento, numeroDocumentoCliente));
        Optional<Tarjeta> cuenta = Optional.ofNullable(tarjetaRepositoryPort.buscarPorNumeroTarjeta(numeroTarjeta));

        if (cliente.isEmpty()) {
            errorAsociarMovimientoClienteTarjeta = String.format("No existe el cliente %s %s", tipoDocumento, numeroDocumentoCliente);
            throw new MovimientoException(errorAsociarMovimientoClienteTarjeta, 404);
        }

        if (cuenta.isEmpty()) {
            errorAsociarMovimientoClienteTarjeta = String.format("No existe la tarjeta %s", numeroTarjeta);
            throw new MovimientoException(errorAsociarMovimientoClienteTarjeta, 404);
        }

        return movimientoRepositoryPort.asociarClienteTarjetaMovimiento(movimiento, cliente.get(), cuenta.get());
    }
}
