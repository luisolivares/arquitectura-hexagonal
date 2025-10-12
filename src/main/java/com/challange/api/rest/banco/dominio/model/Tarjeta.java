package com.challange.api.rest.banco.dominio.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarjeta {

    private Long idTarjeta;
    private String numeroTarjeta;
    private LocalDate fechaVencimiento;
    private String nombreTitular;
    private String cvv;
    private TipoTarjeta tipoTarjeta;
    private EmisorTarjeta emisorTarjeta;
    private boolean activo;

}