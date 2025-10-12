package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Movimiento;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;

public interface AsociarMovimientoClienteTarjetaUseCase {

    Movimiento asociarMovimientoClienteTarjetaUseCase(Movimiento movimiento, TipoDocumento tipoDocumento, String numeroDocumentoCliente, String numeroTarjeta);

}
