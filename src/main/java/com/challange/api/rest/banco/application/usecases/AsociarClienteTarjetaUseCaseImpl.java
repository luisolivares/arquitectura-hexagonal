package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.Tarjeta;
import com.challange.api.rest.banco.dominio.ports.in.AsociarClienteTarjetaUseCase;
import com.challange.api.rest.banco.dominio.ports.out.ClienteRepositoryPort;
import com.challange.api.rest.banco.dominio.ports.out.TarjetaRepositoryPort;

public class AsociarClienteTarjetaUseCaseImpl implements AsociarClienteTarjetaUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final TarjetaRepositoryPort tarjetaRepositoryPort;

    public AsociarClienteTarjetaUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort, TarjetaRepositoryPort tarjetaRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.tarjetaRepositoryPort = tarjetaRepositoryPort;
    }

    @Override
    public Cliente asociarClienteTarjetaUseCase(String numeroDocumentoCliente, String numeroTarjeta) {

        Cliente cliente = clienteRepositoryPort.buscarPorNumeroDocumento(numeroDocumentoCliente);
        Tarjeta tarjeta = tarjetaRepositoryPort.buscarPorNumeroTarjeta(numeroTarjeta);
        return clienteRepositoryPort.asociarClienteTarjeta(cliente, tarjeta);
    }
}
