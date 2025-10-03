package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Cliente;

public interface AltaClienteUseCase {

    Cliente alta(Cliente cliente);
}
