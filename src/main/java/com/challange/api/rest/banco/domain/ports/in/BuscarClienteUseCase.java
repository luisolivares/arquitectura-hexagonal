package com.challange.api.rest.banco.domain.ports.in;

import com.challange.api.rest.banco.domain.model.Cliente;
import com.challange.api.rest.banco.domain.model.TipoDocumento;

import java.util.List;

public interface BuscarClienteUseCase {

    Cliente buscarPorDocumento(TipoDocumento tipoDocumento, String documento);

    Cliente buscarPorNumeroDocumento(String documento);


    List<Cliente> listado(int pagina, int tamanio);


}
