package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.ports.in.AltaClienteUseCase;
import com.challange.api.rest.banco.dominio.ports.out.ClienteRepositoryPort;

public class AltaClienteUseCaseImpl implements AltaClienteUseCase {

    private final ClienteRepositoryPort port;

    public AltaClienteUseCaseImpl(ClienteRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Cliente alta(Cliente cliente) {
        return port.alta(cliente);
    }
}
