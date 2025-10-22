package com.challange.api.rest.banco.infrastructure.entity;

import com.challange.api.rest.banco.domain.model.TipoCanal;
import com.challange.api.rest.banco.domain.model.TipoDivisa;
import com.challange.api.rest.banco.domain.model.TipoMovimiento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Movimiento")
@Table(name = "movimientos")
public class MovimientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Long idMovimiento;

    @NotNull(message = "El campo saldoInicial no debe ser nulo")
    @Column(name = "saldo_inicial", nullable = false)
    private BigDecimal saldoInicial;

    @NotNull(message = "El campo saldoMovimiento no debe ser nulo")
    @Column(name = "saldo_movimiento", nullable = false)
    private BigDecimal saldoMovimiento;

    @NotNull(message = "El campo saldoDisponible no debe ser nulo")
    @Column(name = "saldoDisponible", nullable = false)
    private BigDecimal saldoDisponible;

    @NotNull(message = "El campo fechaMovimiento no debe estar nulo")
    @Column(name = "fecha_movimiento", nullable = false)
    private LocalDate fechaMovimiento;

    @NotNull(message = "El campo tipoMovimiento no debe ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimiento tipoMovimiento;

    @NotNull(message = "El campo tipoDivisa no debe ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_divisa", nullable = false)
    private TipoDivisa tipoDivisa;

    @NotNull(message = "El campo tipoCanal no debe ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_canal", nullable = false)
    private TipoCanal tipoCanal;

    @Column(name = "referencia_operacion", unique = true, nullable = false)
    private String referenciaOperacion;

    @Column(name = "concepto", nullable = false)
    private String concepto;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id")
    private CuentaEntity cuenta;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tarjeta_id")
    private TarjetaEntity tarjeta;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

}