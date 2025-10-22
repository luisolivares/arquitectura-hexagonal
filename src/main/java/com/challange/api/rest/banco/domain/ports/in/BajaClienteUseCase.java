package com.challange.api.rest.banco.domain.ports.in;

import com.challange.api.rest.banco.domain.model.Cliente;
import com.challange.api.rest.banco.domain.model.TipoDocumento;

public interface BajaClienteUseCase {

    Cliente baja(TipoDocumento tipoDocumento, String numeroDocumento);

}
