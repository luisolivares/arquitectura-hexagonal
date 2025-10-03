package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Movimiento;
import com.challange.api.rest.banco.dominio.model.TipoCanal;
import com.challange.api.rest.banco.dominio.model.TipoMovimiento;

import java.time.LocalDate;
import java.util.List;

public interface BuscarMovimientoUseCase {

    List<Movimiento> buscarMovimientoPor(TipoMovimiento tipoMovimiento, TipoCanal tipoCanal, LocalDate fechaDesde, LocalDate fechaHasta, int page, int size);

    List<Movimiento> buscarTodo(int page, int size);

}
