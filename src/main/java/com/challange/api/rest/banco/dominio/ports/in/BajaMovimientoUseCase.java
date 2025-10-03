package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Movimiento;

public interface BajaMovimientoUseCase {

    Movimiento baja(int idMovimiento, boolean esBaja);

}
