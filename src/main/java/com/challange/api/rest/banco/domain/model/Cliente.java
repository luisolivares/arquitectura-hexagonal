package com.challange.api.rest.banco.domain.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    private Long idCliente;
    private String nombres;
    private String apellidos;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String email;
    private Genero genero;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private Boolean estado;

}
