package com.challange.api.rest.banco.infrastructure.entity;

import com.challange.api.rest.banco.domain.model.TipoCuenta;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Cuenta")
@Table(name = "cuentas")
@Builder
public class CuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuenta;

    @Column(name = "numero_cuenta", unique = true, nullable = false)
    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cuenta", nullable = false)
    private TipoCuenta tipoCuenta;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;

    @Column(name = "saldo_incial", nullable = false)
    private BigDecimal saldoInicial;

    @Column(name = "saldo_movimiento", nullable = false)
    private BigDecimal saldoMovimiento;

    @Column(name = "saldo_disponible", nullable = false)
    private BigDecimal saldoDisponible;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

}
