package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Movimiento;

public interface AsociarMovimientoClienteCuentaUseCase {

    Movimiento asociarMovimientoClienteCuentaUseCase(Movimiento movimiento, String numeroDocumentoCliente, String numeroCuenta);

}
