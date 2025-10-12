package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;

public interface AsociarClienteTarjetaUseCase {

    Cliente asociarClienteTarjetaUseCase(TipoDocumento tipoDocumento, String numeroDocumentoCliente, String numeroTarjeta);

}

