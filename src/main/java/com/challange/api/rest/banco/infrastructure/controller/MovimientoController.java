package com.challange.api.rest.banco.infrastructure.controller;

import com.challange.api.rest.banco.application.services.ServiceMovimiento;
import com.challange.api.rest.banco.dominio.model.Movimiento;
import com.challange.api.rest.banco.dominio.model.TipoCanal;
import com.challange.api.rest.banco.dominio.model.TipoMovimiento;
import com.challange.api.rest.banco.infrastructure.model.request.MovimientoRequest;
import com.challange.api.rest.banco.infrastructure.utils.ObjectMapperUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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


    @PostMapping(value = "/numero_cuenta/{numeroCuenta}/numero_documento_cliente/{numeroDocumentoCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movimiento> alta(
            @Valid @RequestBody MovimientoRequest request,
            @Parameter(description = "Número único de cuenta", required = true, example = "1234567890")
            @PathVariable @NotBlank @Pattern(regexp = "\\d{10,20}") String numeroCuenta,
            @Parameter(description = "Número de documento (6 a 10 dígitos)", required = true, example = "19758123")
            @PathVariable @NotBlank @Size(min = 6, max = 10) @Pattern(regexp = "\\d+") String numeroDocumentoCliente) {
        Movimiento movimiento = ObjectMapperUtils.map(request, Movimiento.class);
        return new ResponseEntity<>(serviceMovimiento.asociarMovimientoClienteCuentaUseCase(movimiento, numeroDocumentoCliente, numeroCuenta), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movimiento>> buscarTodasTarjetas(
            @Parameter(description = "Número de página (0-index)", required = true, example = "0") @PathVariable int page,
            @Parameter(description = "Cantidad de resultados por página (máx. 100)", required = true, example = "10") @PathVariable int size) {
        return new ResponseEntity<>(serviceMovimiento.buscarTodo(page, size), HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movimiento>> buscarMovimientoPor(@RequestParam(required = false) TipoMovimiento tipoMovimiento,
                                                                @RequestParam(required = false) TipoCanal tipoCanal,
                                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
                                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(serviceMovimiento.buscarMovimientoPor(tipoMovimiento, tipoCanal, fechaDesde, fechaHasta, page, size), HttpStatus.OK);
    }


    @PutMapping(value = "/id_movimiento/{idMovimiento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movimiento> modificar(@Valid @RequestBody MovimientoRequest request,
                                                @Parameter(description = "Número de movimiento", required = true) long idMovimiento) {
        Movimiento movimiento = ObjectMapperUtils.map(request, Movimiento.class);
        return new ResponseEntity<>(serviceMovimiento.modificarMovimiento(movimiento, idMovimiento), HttpStatus.OK);
    }


}
