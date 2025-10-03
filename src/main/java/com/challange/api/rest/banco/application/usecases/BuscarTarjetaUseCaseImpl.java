package com.challange.api.rest.banco.application.usecases;

import com.challange.api.rest.banco.dominio.model.Tarjeta;
import com.challange.api.rest.banco.dominio.ports.in.BuscarTarjetaUseCase;
import com.challange.api.rest.banco.dominio.ports.out.TarjetaRepositoryPort;

import java.util.List;

public class BuscarTarjetaUseCaseImpl implements BuscarTarjetaUseCase {

    private final TarjetaRepositoryPort port;

    public BuscarTarjetaUseCaseImpl(TarjetaRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Tarjeta buscarPorNumeroTarjeta(String numeroTarjeta) {
        return port.buscarPorNumeroTarjeta(numeroTarjeta);
    }

    @Override
    public List<Tarjeta> buscarTodasTarjetas(int page, int size) {
        return port.buscarTodasTarjetas(page, size);
    }
}
