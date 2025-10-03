package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Tarjeta;

import java.util.List;

public interface BuscarTarjetaUseCase {

    Tarjeta buscarPorNumeroTarjeta(String numeroTarjeta);

    List<Tarjeta> buscarTodasTarjetas(int page, int size);

}