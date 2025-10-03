package com.challange.api.rest.banco.infrastructure.model.response;

import com.challange.api.rest.banco.dominio.model.TipoCuenta;
import com.challange.api.rest.banco.dominio.model.TipoMovimiento;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaResponse {
    private int idCuenta;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private LocalDate fechaAlta;
    private TipoMovimiento tipoMovimiento;
    private BigDecimal saldoInicial;
    private BigDecimal saldoMovimiento;
    private BigDecimal saldoDisponible;
    private boolean activo;

}
