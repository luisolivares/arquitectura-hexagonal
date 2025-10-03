package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;

import java.util.List;

public interface BuscarClienteUseCase {

    Cliente buscarPorDocumento(TipoDocumento tipoDocumento, String documento);

    Cliente buscarPorNumeroDocumento(String documento);


    List<Cliente> listado(int pagina, int tamanio);


}
