package com.challange.api.rest.banco.infrastructure.model.request;

import com.challange.api.rest.banco.dominio.model.TipoCuenta;
import com.challange.api.rest.banco.infrastructure.utils.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaRequest {

    @NotBlank(message = "El número de cuenta es obligatorio")
    @Pattern(regexp = "^[0-9]+$", message = "El número de cuenta solo puede contener dígitos")
    @Size(min = 5, max = 20, message = "El número de cuenta debe tener entre 5 y 20 dígitos")
    private String numeroCuenta;

    @NotNull(message = "El tipo de cuenta es obligatorio")
    private TipoCuenta tipoCuenta;

    @NotNull(message = "La fecha de alta es obligatoria")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fechaAlta;


    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El saldo inicial debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El saldo inicial debe tener máximo 10 enteros y 2 decimales")
    private BigDecimal saldoInicial;

    @NotNull(message = "El saldo del movimiento es obligatorio")
    @Positive(message = "El saldo del movimiento debe ser positivo")
    @Digits(integer = 10, fraction = 2, message = "El saldo del movimiento debe tener máximo 10 enteros y 2 decimales")
    private BigDecimal saldoMovimiento;

    @NotNull(message = "El saldo disponible es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El saldo disponible no puede ser negativo")
    @Digits(integer = 10, fraction = 2, message = "El saldo disponible debe tener máximo 10 enteros y 2 decimales")
    private BigDecimal saldoDisponible;

    @NotNull(message = "El campo 'activo' es obligatorio")
    private Boolean activo;

}
