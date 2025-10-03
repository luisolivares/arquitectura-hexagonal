package com.challange.api.rest.banco.dominio.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {

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
