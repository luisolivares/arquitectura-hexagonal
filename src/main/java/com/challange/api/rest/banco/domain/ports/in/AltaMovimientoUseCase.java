package com.challange.api.rest.banco.domain.ports.in;

import com.challange.api.rest.banco.domain.model.Movimiento;


public interface AltaMovimientoUseCase {

    Movimiento alta(Movimiento movimiento);

}
