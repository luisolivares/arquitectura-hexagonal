package com.challange.api.rest.banco.domain.ports.in;

import com.challange.api.rest.banco.domain.model.Tarjeta;

public interface ModificarTarjetaUseCase {

    Tarjeta modificarTarjeta(Tarjeta tarjeta);
}
