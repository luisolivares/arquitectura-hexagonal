package com.challange.api.rest.banco.domain.ports.in;

import com.challange.api.rest.banco.domain.model.Cliente;

public interface ModificarClienteUseCase {

    Cliente modificar(Cliente cliente);

}