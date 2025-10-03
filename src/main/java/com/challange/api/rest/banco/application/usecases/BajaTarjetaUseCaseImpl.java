package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.model.Tarjeta;
import com.challange.api.rest.banco.dominio.ports.in.BajaTarjetaUseCase;
import com.challange.api.rest.banco.dominio.ports.out.TarjetaRepositoryPort;

public class BajaTarjetaUseCaseImpl implements BajaTarjetaUseCase {

    private final TarjetaRepositoryPort port;

    public BajaTarjetaUseCaseImpl(TarjetaRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Tarjeta baja(String numero) {
        return port.baja(numero);
    }
}
