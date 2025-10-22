package com.challange.api.rest.banco.application.services;

import com.challange.api.rest.banco.domain.exceptions.TarjetaException;
import com.challange.api.rest.banco.domain.model.Tarjeta;
import com.challange.api.rest.banco.domain.ports.in.AltaTarjetaUseCase;
import com.challange.api.rest.banco.domain.ports.in.BajaTarjetaUseCase;
import com.challange.api.rest.banco.domain.ports.in.BuscarTarjetaUseCase;
import com.challange.api.rest.banco.domain.ports.in.ModificarTarjetaUseCase;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
public class ServiceTarjeta implements AltaTarjetaUseCase, BajaTarjetaUseCase, BuscarTarjetaUseCase, ModificarTarjetaUseCase {

    private static String errorTarjeta;

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

        Optional<Tarjeta> tarjetaOptinal = Optional.ofNullable(buscarTarjetaUseCase.buscarPorNumeroTarjeta(tarjeta.getNumeroTarjeta()));

        if (tarjetaOptinal.isPresent()) {
            errorTarjeta = String.format("La tarjeta N° %s ya registrada en sistema", tarjeta.getNumeroTarjeta());
            log.error(errorTarjeta);
            throw new TarjetaException(errorTarjeta, 404);
        }

        return altaTarjetaUseCase.alta(tarjeta);
    }

    @Override
    public Tarjeta buscarPorNumeroTarjeta(String numeroTarjeta) {
        return buscarTarjetaUseCase.buscarPorNumeroTarjeta(numeroTarjeta);
    }

    @Override
    public List<Tarjeta> buscarTodasTarjetas(int page, int size) {

        if (page < 0 || size < 0) {
            errorTarjeta = String.format("No se envio el numero de pagina o la cantidad de registros para el listado");
            log.error(errorTarjeta);
            throw new TarjetaException(errorTarjeta, 404);
        }

        return buscarTarjetaUseCase.buscarTodasTarjetas(page, size);
    }

    @Override
    public Tarjeta modificarTarjeta(Tarjeta tarjeta) {

        Optional<Tarjeta> tarjetaOptional = Optional.ofNullable(buscarTarjetaUseCase.buscarPorNumeroTarjeta(tarjeta.getNumeroTarjeta()));

        if (tarjetaOptional.isEmpty()) {
            errorTarjeta = String.format("La tarjeta N° %s no se encuenta en sistema para su modificación", tarjeta.getNumeroTarjeta());
            log.error(errorTarjeta);
            throw new TarjetaException(errorTarjeta, 404);
        }

        tarjeta.setIdTarjeta(tarjetaOptional.get().getIdTarjeta());
        return modificarTarjetaUseCase.modificarTarjeta(tarjeta);
    }

    @Override
    public Tarjeta baja(String numero) {
        Optional<Tarjeta> tarjeta = Optional.ofNullable(buscarTarjetaUseCase.buscarPorNumeroTarjeta(numero));

        if (tarjeta.isEmpty()) {
            errorTarjeta = String.format("La tarjeta N° %s no se encuenta en sistema para su baja", numero);
            log.error(errorTarjeta);
            throw new TarjetaException(errorTarjeta, 404);
        }

        tarjeta.get().setActivo(false);
        return bajaTarjetaUseCase.baja(numero);
    }
}
