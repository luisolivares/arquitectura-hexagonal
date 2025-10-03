package com.challange.api.rest.banco.infrastructure.entity;

import com.challange.api.rest.banco.dominio.model.Genero;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Cliente")
@Table(name = "cliente")
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idCliente;

    @NotBlank(message = "El campo nombre no debe estar vacio")
    @NotNull(message = "El campo nombre no debe estar vacio")
    @Column(name = "nombres", nullable = false)
    private String nombres;

    @NotBlank(message = "El campo apellidos no debe estar vacio")
    @NotNull(message = "El campo apellidos no debe estar vacio")
    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @NotBlank(message = "El campo telefono no debe estar vacio")
    @NotNull(message = "El campo telefono no debe estar nulo")
    @Column(name = "telefono", nullable = true)
    private String telefono;

    @NotNull(message = "El campo fechaNacimiento no debe estar nulo")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El campo email no debe estar vacio")
    @NotNull(message = "El campo email no debe ser nulo")
    @Email(message = "El formato del email es inválido")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotNull(message = "El campo estado no debe ser nulo")
    @Column(name = "estado", nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean estado;

    @NotNull(message = "El campo genero no debe ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "genero", nullable = false)
    private Genero genero;

    @NotNull(message = "El campo tipoDocumento no debe ser nulo")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoDocumento", nullable = false)
    private TipoDocumento tipoDocumento;

    @NotBlank(message = "El campo documento no debe estar vacio")
    @NotNull(message = "El campo documento no debe ser nulo")
    @Column(name = "numero_documento", unique = true, nullable = false)
    private String numeroDocumento;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<CuentaEntity> cuentas;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<TarjetaEntity> tarjetas;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<MovimientoEntity> movimientos;

}