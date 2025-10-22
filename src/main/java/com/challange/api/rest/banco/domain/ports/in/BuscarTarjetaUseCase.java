package com.challange.api.rest.banco.domain.ports.in;

import com.challange.api.rest.banco.domain.model.Tarjeta;

import java.util.List;

public interface BuscarTarjetaUseCase {

    Tarjeta buscarPorNumeroTarjeta(String numeroTarjeta);

    List<Tarjeta> buscarTodasTarjetas(int page, int size);

}