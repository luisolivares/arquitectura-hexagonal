package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.domain.model.Cliente;
import com.challange.api.rest.banco.domain.model.TipoDocumento;
import com.challange.api.rest.banco.domain.ports.in.BajaClienteUseCase;
import com.challange.api.rest.banco.domain.ports.out.ClienteRepositoryPort;

public class BajaClienteUseCaseImpl implements BajaClienteUseCase {

    private final ClienteRepositoryPort port;

    public BajaClienteUseCaseImpl(ClienteRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Cliente baja(TipoDocumento tipoDocumento, String numeroDocumento) {
        return port.baja(tipoDocumento, numeroDocumento);
    }
}
