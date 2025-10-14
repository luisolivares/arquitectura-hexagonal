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
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Cliente")
@Table(name = "clientes")
@Builder
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

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

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<CuentaEntity> cuentas = new HashSet<>();

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<TarjetaEntity> tarjetas = new HashSet<>();

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<MovimientoEntity> movimientos = new HashSet<>();


    // Método de utilidad para asociar una cuenta y mantener la sincronización
    public void addCuenta(CuentaEntity cuenta) {
        if (this.cuentas == null) {
            this.cuentas = new HashSet<>();
        }
        this.cuentas.add(cuenta);
        cuenta.setCliente(this); // Sincroniza el otro lado de la relación
    }

    // Método de utilidad para asociar una tarjeta y mantener la sincronización
    public void addTarjeta(TarjetaEntity tarjeta) {
        if (this.tarjetas == null) {
            this.tarjetas = new HashSet<>();
        }
        this.tarjetas.add(tarjeta);
        tarjeta.setCliente(this); // Sincroniza el otro lado de la relación
    }

    // Método de utilidad para asociar una tarjeta y mantener la sincronización
    public void addMovimiento(MovimientoEntity movimiento) {
        if (this.movimientos == null) {
            this.movimientos = new HashSet<>();
        }
        this.movimientos.add(movimiento);
        movimiento.setCliente(this); // Sincroniza el otro lado de la relación
    }

}