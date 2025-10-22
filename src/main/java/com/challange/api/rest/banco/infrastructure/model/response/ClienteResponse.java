package com.challange.api.rest.banco.infrastructure.model.response;

import com.challange.api.rest.banco.domain.model.Genero;
import com.challange.api.rest.banco.domain.model.TipoDocumento;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponse {

    private String nombres;
    private String apellidos;
    private String telefono;
    private LocalDateTime fechaNacimiento;
    private String email;
    private Genero genero;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private Boolean activo;

}
