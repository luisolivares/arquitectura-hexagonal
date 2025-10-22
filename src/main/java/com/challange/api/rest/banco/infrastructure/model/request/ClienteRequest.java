package com.challange.api.rest.banco.infrastructure.model.request;


import com.challange.api.rest.banco.domain.model.Genero;
import com.challange.api.rest.banco.domain.model.TipoDocumento;
import com.challange.api.rest.banco.infrastructure.utils.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
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
public class ClienteRequest {

    @NotBlank(message = "El campo nombre no debe estar vacio")
    @NotNull(message = "El campo nombre no debe estar vacio")
    private String nombres;

    @NotBlank(message = "El campo apellidos no debe estar vacio")
    @NotNull(message = "El campo apellidos no debe estar vacio")
    private String apellidos;

    @NotBlank(message = "El campo telefono no debe estar vacio")
    @NotNull(message = "El campo telefono no debe estar nulo")
    @Pattern(regexp = "\\d+", message = "El campo telefono debe contener solo dígitos") // Valida solo dígitos
    private String telefono;

    @NotNull(message = "El campo fechaNacimiento no debe estar nulo")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Especifica el patrón de formato
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El campo email no debe estar vacio")
    @NotNull(message = "El campo email no debe ser nulo")
    @Email(message = "El formato del email es inválido")
    private String email;

    @NotNull(message = "El campo estado no debe ser nulo")
    private Boolean estado;

    @NotNull(message = "El campo genero no debe ser nulo")
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @NotNull(message = "El campo tipoDocumento no debe ser nulo")
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @NotBlank(message = "El campo numeroDocumento no debe estar vacio")
    @NotNull(message = "El campo numeroDocumento no debe ser nulo")
    @Pattern(regexp = "\\d+", message = "El campo numeroDocumento debe contener solo dígitos") // Valida solo dígitos
    private String numeroDocumento;

}
