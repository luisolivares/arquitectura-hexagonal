package com.challange.api.rest.banco.domain.ports.out;

import com.challange.api.rest.banco.domain.model.Tarjeta;

import java.util.List;

public interface TarjetaRepositoryPort {

    Tarjeta alta(Tarjeta tarjeta);

    Tarjeta buscarPorNumeroTarjeta(String numeoTarjeta);

    List<Tarjeta> buscarTodasTarjetas(int page, int size);

    Tarjeta modificarTarjeta(Tarjeta tarjeta);

    Tarjeta baja(String numeroTarjeta);

    Tarjeta asociarCliente(String numeroTarjeta, String numeroDocumento);

}
