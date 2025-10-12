package com.challange.api.rest.banco.infrastructure.entity;

import com.challange.api.rest.banco.dominio.model.EmisorTarjeta;
import com.challange.api.rest.banco.dominio.model.TipoTarjeta;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Tarjeta")
@Table(name = "tarjetas")
public class TarjetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarjeta")
    private Long idTarjeta;

    @Column(name = "numero_tarjeta", unique = true, nullable = false)
    private String numeroTarjeta;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "cvv")
    private String cvv;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_tarjeta", nullable = false)
    private TipoTarjeta tipoTarjeta;

    @Enumerated(EnumType.STRING)
    @Column(name = "emisor_tarjeta", nullable = false)
    private EmisorTarjeta emisorTarjeta;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

}
