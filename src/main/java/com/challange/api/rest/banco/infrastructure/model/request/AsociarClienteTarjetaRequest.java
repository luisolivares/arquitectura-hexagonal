package com.challange.api.rest.banco.infrastructure.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsociarClienteTarjetaRequest {

    @NotBlank(message = "El campo numeroDocumento no debe estar vacio")
    @NotNull(message = "El campo numeroDocumento no debe ser nulo")
    @Pattern(regexp = "\\d+", message = "El campo numeroDocumento debe contener solo dígitos") // Valida solo dígitos
    private String numeroDocumento;

    @NotBlank(message = "El número de tarjeta es obligatorio")
    @Pattern(
            regexp = "^[0-9]{16}$",
            message = "El número de tarjeta debe tener exactamente 16 dígitos"
    )
    private String numeroTarjeta;

}
