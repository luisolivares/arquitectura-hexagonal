package com.challange.api.rest.banco.infrastructure.model.request;

import com.challange.api.rest.banco.infrastructure.utils.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarjetaRequest {

    @NotBlank(message = "El número de tarjeta es obligatorio")
    @Pattern(
            regexp = "^[0-9]{16}$",
            message = "El número de tarjeta debe tener exactamente 16 dígitos"
    )
    private String numeroTarjeta;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Especifica el patrón de formato
    private LocalDate fechaVencimiento;

    @NotBlank(message = "El CVV es obligatorio")
    @Pattern(
            regexp = "^[0-9]{3,4}$",
            message = "El CVV debe tener 3 o 4 dígitos numéricos"
    )
    private String cvv;

    @NotBlank(message = "El tipo de tarjeta es obligatorio")
    @Pattern(
            regexp = "^(DEBITO|CREDITO)$",
            message = "El tipo de tarjeta debe ser DEBITO o CREDITO"
    )
    private String tipoTarjeta;

    @NotBlank(message = "El emisor de tarjeta es obligatorio")
    @Pattern(
            regexp = "^(VISA|MASTERCARD|AMERICAN_EXPRESS)$",
            message = "El tipo de tarjeta debe ser VISA o MASTERCARD o AMERICAN_EXPRESS"
    )
    private String emisorTarjeta;

    @NotNull(message = "El campo 'activo' es obligatorio")
    private Boolean activo;

}
