package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.domain.model.Cliente;
import com.challange.api.rest.banco.domain.model.TipoDocumento;
import com.challange.api.rest.banco.domain.ports.in.BuscarClienteUseCase;
import com.challange.api.rest.banco.domain.ports.out.ClienteRepositoryPort;

import java.util.List;

public class BuscarClienteUseCaseImpl implements BuscarClienteUseCase {

    private final ClienteRepositoryPort port;

    public BuscarClienteUseCaseImpl(ClienteRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Cliente buscarPorDocumento(TipoDocumento tipoDocumento, String documento) {
        return port.buscarPorTipoYNumeroDocumento(tipoDocumento, documento);
    }

    @Override
    public Cliente buscarPorNumeroDocumento(String documento) {
        return port.buscarPorNumeroDocumento(documento);
    }

    @Override
    public List<Cliente> listado(int pagina, int tamanio) {
        return port.listado(pagina, tamanio);
    }
}
