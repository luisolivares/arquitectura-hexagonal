package com.challange.api.rest.banco.domain.ports.in;

import com.challange.api.rest.banco.domain.model.Cuenta;

public interface BajaCuentaUseCase {

    Cuenta baja(String numeroCuenta);

}
