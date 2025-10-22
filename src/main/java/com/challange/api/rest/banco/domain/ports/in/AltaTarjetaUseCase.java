package com.challange.api.rest.banco.domain.ports.in;

import com.challange.api.rest.banco.domain.model.Tarjeta;

public interface AltaTarjetaUseCase {

    Tarjeta alta(Tarjeta tarjeta);
}