package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.ports.in.ModificarClienteUseCase;
import com.challange.api.rest.banco.dominio.ports.out.ClienteRepositoryPort;

public class ModificarClienteUseCaseImpl implements ModificarClienteUseCase {

    private final ClienteRepositoryPort port;

    public ModificarClienteUseCaseImpl(ClienteRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Cliente modificar(Cliente cliente) {
        return port.modificarCliente(cliente);
    }
}
