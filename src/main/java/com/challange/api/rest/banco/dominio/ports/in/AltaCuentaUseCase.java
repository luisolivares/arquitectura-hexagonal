package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Cuenta;

public interface AltaCuentaUseCase {

    Cuenta alta(Cuenta cuenta);

}
