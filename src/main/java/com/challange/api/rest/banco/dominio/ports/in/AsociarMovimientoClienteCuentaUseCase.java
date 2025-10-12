package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Movimiento;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;

public interface AsociarMovimientoClienteCuentaUseCase {

    Movimiento asociarMovimientoClienteCuentaUseCase(Movimiento movimiento, TipoDocumento tipoDocumento, String numeroDocumentoCliente, String numeroCuenta);

}
