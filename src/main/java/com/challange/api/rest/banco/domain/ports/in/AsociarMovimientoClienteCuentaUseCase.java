package com.challange.api.rest.banco.domain.ports.in;

import com.challange.api.rest.banco.domain.model.Movimiento;
import com.challange.api.rest.banco.domain.model.TipoDocumento;

public interface AsociarMovimientoClienteCuentaUseCase {

    Movimiento asociarMovimientoClienteCuentaUseCase(Movimiento movimiento, TipoDocumento tipoDocumento, String numeroDocumentoCliente, String numeroCuenta);

}
