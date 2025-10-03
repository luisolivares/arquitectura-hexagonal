package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Tarjeta;

public interface BajaTarjetaUseCase {

    Tarjeta baja(String numero);

}
