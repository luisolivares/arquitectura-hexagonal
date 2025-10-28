package com.challange.api.rest.banco.domain.ports.out;

import com.challange.api.rest.banco.domain.model.*;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoRepositoryPort {

    Movimiento alta(Movimiento movimiento);

    List<Movimiento> buscarMovimientoPor(TipoMovimiento tipoMovimiento, TipoCanal tipoCanal, LocalDate fechaDesde, LocalDate fechaHasta, int page, int size);

    List<Movimiento> buscarTodo(int page, int size);

    Movimiento buscarMovimientoPorId(long idMovimiento);

    Movimiento buscarMovimientoPorReferenciaOperacion(String referenciaOperacion);

    Movimiento modificarMovimiento(Movimiento movimiento);

    void baja(long idMovimiento);

    Movimiento asociarClienteCuentaMovimiento(Movimiento movimiento, Cliente cliente, Cuenta cuenta);

    Movimiento asociarClienteTarjetaMovimiento(Movimiento movimiento, Cliente cliente, Tarjeta tarjeta);

}
