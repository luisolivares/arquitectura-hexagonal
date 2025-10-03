package com.challange.api.rest.banco.infrastructure.model.request;

import com.challange.api.rest.banco.dominio.model.TipoCanal;
import com.challange.api.rest.banco.dominio.model.TipoDivisa;
import com.challange.api.rest.banco.dominio.model.TipoMovimiento;
import com.challange.api.rest.banco.infrastructure.utils.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(
        name = "MovimientoRequest",
        description = "Objeto de solicitud para registrar un movimiento en una cuenta bancaria."
)
public class MovimientoRequest {

    @Schema(description = "Concepto del movimiento", example = "Depósito en efectivo")
    @NotBlank(message = "El concepto es obligatorio")
    @Size(min = 3, max = 150, message = "El concepto debe tener entre 3 y 150 caracteres")
    private String concepto;

    @Schema(description = "Concepto del referencia", example = "Identificador unico de cada transaccion")
    @NotBlank(message = "El referencia es obligatorio")
    @Size(min = 3, max = 150, message = "El referencia debe tener entre 3 y 150 caracteres")
    private String referenciaOperacion;

    @Schema(description = "Fecha del movimiento", example = "2025-10-01")
    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha del movimiento no puede ser futura")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Especifica el patrón de formato
    private LocalDate fechaMovimiento;

    @Schema(description = "Canal por el que se realizó el movimiento (ejemplo: CAJERO, ONLINE)", example = "ONLINE")
    @NotNull(message = "El tipo de canal es obligatorio")
    private TipoCanal tipoCanal;

    @Schema(description = "Divisa utilizada en el movimiento", example = "DOLARES")
    @NotNull(message = "La divisa es obligatoria")
    private TipoDivisa tipoDivisa;

    @Schema(description = "Número de cuenta asociada al movimiento", example = "1234567890123456")
    @NotBlank(message = "El número de cuenta es obligatorio")
    @Pattern(regexp = "\\d{10,20}", message = "El número de cuenta debe contener entre 10 y 20 dígitos")
    private String numeroCuenta;

    @Schema(description = "Tipo de movimiento (ejemplo: DEPOSITO, RETIRO, TRANSFERENCIA)", example = "DEPOSITO")
    @NotNull(message = "El tipo de movimiento es obligatorio")
    private TipoMovimiento tipoMovimiento;

    @Schema(description = "Saldo inicial antes del movimiento", example = "1000.00")
    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El saldo inicial no puede ser negativo")
    private BigDecimal saldoInicial;

    @Schema(description = "Monto del movimiento (positivo o negativo según corresponda)", example = "250.00")
    @NotNull(message = "El saldo de movimiento es obligatorio")
    @DecimalMin(value = "0.01", inclusive = true, message = "El movimiento debe ser mayor que 0")
    private BigDecimal saldoMovimiento;

    @Schema(description = "Saldo disponible después del movimiento", example = "1250.00")
    @NotNull(message = "El saldo disponible es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El saldo disponible no puede ser negativo")
    private BigDecimal saldoDisponible;

}
