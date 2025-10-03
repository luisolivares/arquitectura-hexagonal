package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Cliente;

public interface AsociarClienteCuentaUseCase {

    Cliente asociarClienteCuentaUseCase(String numeroDocumento, String numeroCuenta);

}
