package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Tarjeta;

public interface AltaTarjetaUseCase {

    Tarjeta alta(Tarjeta tarjeta);
}