package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.exceptions.ClienteException;
import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.Tarjeta;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;
import com.challange.api.rest.banco.dominio.ports.in.AsociarClienteTarjetaUseCase;
import com.challange.api.rest.banco.dominio.ports.out.ClienteRepositoryPort;
import com.challange.api.rest.banco.dominio.ports.out.TarjetaRepositoryPort;

import java.util.Optional;

public class AsociarClienteTarjetaUseCaseImpl implements AsociarClienteTarjetaUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final TarjetaRepositoryPort tarjetaRepositoryPort;

    public AsociarClienteTarjetaUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort, TarjetaRepositoryPort tarjetaRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.tarjetaRepositoryPort = tarjetaRepositoryPort;
    }

    @Override
    public Cliente asociarClienteTarjetaUseCase(TipoDocumento tipoDocumento, String numeroDocumentoCliente, String numeroTarjeta) {

        Optional<Cliente> cliente = Optional.ofNullable(clienteRepositoryPort.buscarPorTipoYNumeroDocumento(tipoDocumento, numeroDocumentoCliente));
        Optional<Tarjeta> tarjeta = Optional.ofNullable(tarjetaRepositoryPort.buscarPorNumeroTarjeta(numeroTarjeta));

        if (cliente.isPresent() || tarjeta.isPresent()) {
            return clienteRepositoryPort.asociarClienteTarjeta(cliente.get(), tarjeta.get());
        } else {
            throw new ClienteException("No existe el cliente o tarjeta para su asociación", 404);
        }

    }
}
