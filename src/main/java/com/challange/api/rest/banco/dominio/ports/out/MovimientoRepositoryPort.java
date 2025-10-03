package com.challange.api.rest.banco.dominio.ports.out;

import com.challange.api.rest.banco.dominio.model.*;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoRepositoryPort {

    Movimiento alta(Movimiento movimiento);

    List<Movimiento> buscarMovimientoPor(TipoMovimiento tipoMovimiento, TipoCanal tipoCanal, LocalDate fechaDesde, LocalDate fechaHasta, int page, int size);

    List<Movimiento> buscarTodo(int page, int size);

    Movimiento modificarMovimiento(Movimiento movimiento, long idMovimiento);

    Movimiento baja(int idMovimiento, boolean esBaja);

    Movimiento asociarClienteCuentaMovimiento(Movimiento movimiento, Cliente cliente, Cuenta cuenta);

}
