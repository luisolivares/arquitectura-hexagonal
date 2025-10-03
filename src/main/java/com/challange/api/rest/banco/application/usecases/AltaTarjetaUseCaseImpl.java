package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.model.Tarjeta;
import com.challange.api.rest.banco.dominio.ports.in.AltaTarjetaUseCase;
import com.challange.api.rest.banco.dominio.ports.out.TarjetaRepositoryPort;

public class AltaTarjetaUseCaseImpl implements AltaTarjetaUseCase {

    private final TarjetaRepositoryPort port;

    public AltaTarjetaUseCaseImpl(TarjetaRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Tarjeta alta(Tarjeta tarjeta) {
        return port.alta(tarjeta);
    }
}
