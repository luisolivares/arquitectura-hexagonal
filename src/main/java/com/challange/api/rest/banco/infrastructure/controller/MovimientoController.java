package com.challange.api.rest.banco.infrastructure.controller;

import com.challange.api.rest.banco.application.services.ServiceMovimiento;
import com.challange.api.rest.banco.domain.model.Movimiento;
import com.challange.api.rest.banco.domain.model.TipoCanal;
import com.challange.api.rest.banco.domain.model.TipoDocumento;
import com.challange.api.rest.banco.domain.model.TipoMovimiento;
import com.challange.api.rest.banco.infrastructure.model.request.MovimientoRequest;
import com.challange.api.rest.banco.infrastructure.utils.ObjectMapperUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api/v1/movimientos")
@RequiredArgsConstructor
@Tag(
        name = "Gestión de Movimientos Bancarios",
        description = "Operaciones para la administración de movimientos bancarios asociados a las tarjetas y cuentas del cliente"
)
public class MovimientoController {

    private final ServiceMovimiento serviceMovimiento;

    @Operation(summary = "Asociar movimiento bancario con cuenta bancaria", description = "Asociar un movimiento bancario con una cuenta bancaria.")
    @PostMapping(value = "/numero_cuenta/{numeroCuenta}/tipo_documento/{tipoDocumento}/numero_documento_cliente/{numeroDocumentoCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movimiento> altaConCuenta(
            @Valid @RequestBody MovimientoRequest request,
            @Parameter(description = "Número único de cuenta", required = true, example = "1234567890")
            @PathVariable @NotBlank @Pattern(regexp = "\\d{10,20}") String numeroCuenta,
            @PathVariable @NotNull(message = "El tipo de documento es obligatorio") TipoDocumento tipoDocumento,
            @Parameter(description = "Número de documento (6 a 10 dígitos)", required = true, example = "19758123")
            @PathVariable @NotBlank @Size(min = 6, max = 10) @Pattern(regexp = "\\d+") String numeroDocumentoCliente) {
        Movimiento movimiento = ObjectMapperUtils.map(request, Movimiento.class);
        return new ResponseEntity<>(serviceMovimiento.asociarMovimientoClienteCuentaUseCase(movimiento, tipoDocumento, numeroDocumentoCliente, numeroCuenta), HttpStatus.CREATED);
    }


    @Operation(summary = "Asociar movimiento bancario con tarjeta bancaria", description = "Asociar un movimiento bancario con una tarjeta bancaria.")
    @PostMapping(value = "/numero_tarjeta/{numeroTarjeta}/tipo_documento/{tipoDocumento}/numero_documento_cliente/{numeroDocumentoCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movimiento> altaConTarjeta(
            @Valid @RequestBody MovimientoRequest request,
            @Parameter(description = "Número de tarjeta (16 dígitos)", required = true, example = "4111111111111111")
            @PathVariable("numeroTarjeta")
            @NotBlank
            @Pattern(regexp = "\\d+", message = "El número de tarjeta debe tener exactamente solo dígitos")
            String numeroTarjeta,
            @PathVariable @NotNull(message = "El tipo de documento es obligatorio") TipoDocumento tipoDocumento,
            @Parameter(description = "Número de documento (6 a 10 dígitos)", required = true, example = "19758123")
            @PathVariable @NotBlank @Size(min = 6, max = 10) @Pattern(regexp = "\\d+") String numeroDocumentoCliente) {
        Movimiento movimiento = ObjectMapperUtils.map(request, Movimiento.class);
        return new ResponseEntity<>(serviceMovimiento.asociarMovimientoClienteTarjetaUseCase(movimiento, tipoDocumento, numeroDocumentoCliente, numeroTarjeta), HttpStatus.CREATED);
    }

    @Operation(summary = "Listado de movimientos bancarios", description = "Listado de movimientos bancarios en base a criterios de búsqueda con paginado.")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movimiento>> buscarMovimientoPor(@RequestParam(required = false) TipoMovimiento tipoMovimiento,
                                                                @RequestParam(required = false) TipoCanal tipoCanal,
                                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
                                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(serviceMovimiento.buscarMovimientoPor(tipoMovimiento, tipoCanal, fechaDesde, fechaHasta, page, size), HttpStatus.OK);
    }


    @Operation(summary = "Modificar movimiento bancario", description = "Se modifica un movimiento bancario tomando en cuenta la referencia bancaria de la misma.")
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movimiento> modificar(@Valid @RequestBody MovimientoRequest request) {
        Movimiento movimiento = ObjectMapperUtils.map(request, Movimiento.class);
        return new ResponseEntity<>(serviceMovimiento.modificarMovimiento(movimiento), HttpStatus.OK);
    }

}
