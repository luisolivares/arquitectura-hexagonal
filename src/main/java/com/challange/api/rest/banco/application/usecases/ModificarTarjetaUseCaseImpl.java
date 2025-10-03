package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.model.Tarjeta;
import com.challange.api.rest.banco.dominio.ports.in.ModificarTarjetaUseCase;
import com.challange.api.rest.banco.dominio.ports.out.TarjetaRepositoryPort;

public class ModificarTarjetaUseCaseImpl implements ModificarTarjetaUseCase {

    private final TarjetaRepositoryPort port;

    public ModificarTarjetaUseCaseImpl(TarjetaRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Tarjeta modificarTarjeta(Tarjeta tarjeta) {
        return port.modificarTarjeta(tarjeta);
    }
}
