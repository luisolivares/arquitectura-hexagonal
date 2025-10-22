package com.challange.api.rest.banco.domain.ports.in;

import com.challange.api.rest.banco.domain.model.Movimiento;
import com.challange.api.rest.banco.domain.model.TipoCanal;
import com.challange.api.rest.banco.domain.model.TipoMovimiento;

import java.time.LocalDate;
import java.util.List;

public interface BuscarMovimientoUseCase {

    List<Movimiento> buscarMovimientoPor(TipoMovimiento tipoMovimiento, TipoCanal tipoCanal, LocalDate fechaDesde, LocalDate fechaHasta, int page, int size);

    List<Movimiento> buscarTodo(int page, int size);

    Movimiento buscarMovimientoPorId(long idMovimiento);

    Movimiento buscarMovimientoPorReferenciaOperacion(String referenciaOperacion);

}
