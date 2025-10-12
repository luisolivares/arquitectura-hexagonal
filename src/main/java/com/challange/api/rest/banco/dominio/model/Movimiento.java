package com.challange.api.rest.banco.dominio.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimiento {

    private Long idMovimiento;
    private String concepto;
    private String referenciaOperacion;
    private LocalDate fechaMovimiento;
    private TipoCanal tipoCanal;
    private TipoDivisa tipoDivisa;
    private TipoMovimiento tipoMovimiento;
    private BigDecimal saldoInicial;
    private BigDecimal saldoMovimiento;
    private BigDecimal saldoDisponible;

}
