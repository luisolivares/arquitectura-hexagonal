package com.challange.api.rest.banco.application.services;

import com.challange.api.rest.banco.dominio.model.Tarjeta;
import com.challange.api.rest.banco.dominio.ports.in.AltaTarjetaUseCase;
import com.challange.api.rest.banco.dominio.ports.in.BajaTarjetaUseCase;
import com.challange.api.rest.banco.dominio.ports.in.BuscarTarjetaUseCase;
import com.challange.api.rest.banco.dominio.ports.in.ModificarTarjetaUseCase;

import java.util.List;

public class ServiceTarjeta implements AltaTarjetaUseCase, BajaTarjetaUseCase, BuscarTarjetaUseCase, ModificarTarjetaUseCase {

    private final AltaTarjetaUseCase altaTarjetaUseCase;
    private final BajaTarjetaUseCase bajaTarjetaUseCase;
    private final BuscarTarjetaUseCase buscarTarjetaUseCase;
    private final ModificarTarjetaUseCase modificarTarjetaUseCase;

    public ServiceTarjeta(AltaTarjetaUseCase altaTarjetaUseCase, BajaTarjetaUseCase bajaTarjetaUseCase, BuscarTarjetaUseCase buscarTarjetaUseCase, ModificarTarjetaUseCase modificarTarjetaUseCase) {
        this.altaTarjetaUseCase = altaTarjetaUseCase;
        this.bajaTarjetaUseCase = bajaTarjetaUseCase;
        this.buscarTarjetaUseCase = buscarTarjetaUseCase;
        this.modificarTarjetaUseCase = modificarTarjetaUseCase;
    }

    @Override
    public Tarjeta alta(Tarjeta tarjeta) {
        return altaTarjetaUseCase.alta(tarjeta);
    }

    @Override
    public Tarjeta buscarPorNumeroTarjeta(String numeroTarjeta) {
        return buscarTarjetaUseCase.buscarPorNumeroTarjeta(numeroTarjeta);
    }

    @Override
    public List<Tarjeta> buscarTodasTarjetas(int page, int size) {
        return buscarTarjetaUseCase.buscarTodasTarjetas(page, size);
    }

    @Override
    public Tarjeta modificarTarjeta(Tarjeta tarjeta) {
        return modificarTarjetaUseCase.modificarTarjeta(tarjeta);
    }

    @Override
    public Tarjeta baja(String numero) {
        return bajaTarjetaUseCase.baja(numero);
    }
}
