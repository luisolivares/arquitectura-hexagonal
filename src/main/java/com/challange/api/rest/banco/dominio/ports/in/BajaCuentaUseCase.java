package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Cuenta;

public interface BajaCuentaUseCase {

    Cuenta baja(String numeroCuenta);

}
