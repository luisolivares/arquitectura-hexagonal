package com.challange.api.rest.banco.infrastructure.controller;

import com.challange.api.rest.banco.application.services.ServiceCuenta;
import com.challange.api.rest.banco.dominio.model.Cuenta;
import com.challange.api.rest.banco.infrastructure.model.request.CuentaRequest;
import com.challange.api.rest.banco.infrastructure.utils.ObjectMapperUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api/v1/cuentas")
@RequiredArgsConstructor
@Tag(
        name = "Gestión de Cuentas",
        description = "Operaciones relacionadas con la administración de cuentas bancarias (crear, buscar, listar, modificar y dar de baja cuentas)."
)
public class CuentaController {

    private final ServiceCuenta serviceCuenta;

    @Operation(summary = "Crear cuenta", description = "Registra una nueva cuenta en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta creada correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cuenta.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cuenta> crearCuenta(@Valid @RequestBody CuentaRequest request) {
        Cuenta cuenta = ObjectMapperUtils.map(request, Cuenta.class);
        return new ResponseEntity<>(serviceCuenta.alta(cuenta), HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar cuenta por número", description = "Obtiene los detalles de una cuenta a partir de su número único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cuenta.class))),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada", content = @Content)
    })
    @GetMapping(value = "/{numeroCuenta}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cuenta> buscarCuentaPor(
            @Parameter(description = "Número único de cuenta", required = true, example = "1234567890")
            @PathVariable String numeroCuenta) {
        return new ResponseEntity<>(serviceCuenta.buscarCuentaPor(numeroCuenta), HttpStatus.OK);
    }

    @Operation(summary = "Listar cuentas paginadas", description = "Devuelve todas las cuentas del sistema en formato paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de cuentas obtenido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cuenta.class)))
    })
    @GetMapping(value = "/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cuenta>> buscarTodasCuentas(
            @Parameter(description = "Número de página (0 en adelante)", required = true, example = "0")
            @Min(0) @PathVariable int page,
            @Parameter(description = "Cantidad de cuentas por página (máx. 100)", required = true, example = "10")
            @Min(1) @Max(100) @PathVariable int size) {
        return new ResponseEntity<>(serviceCuenta.buscarTodasCuentas(page, size), HttpStatus.OK);
    }

    @Operation(summary = "Dar de baja o activar cuenta", description = "Cambia el estado de una cuenta (activa/inactiva)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de cuenta actualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cuenta.class))),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada", content = @Content)
    })
    @DeleteMapping(value = "/{numeroCuenta}/{estado}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cuenta> baja(
            @Parameter(description = "Número único de cuenta", required = true, example = "1234567890")
            @PathVariable String numeroCuenta,
            @Parameter(description = "Estado de la cuenta (true=activa, false=inactiva)", required = true, example = "false")
            @PathVariable boolean estado) {
        return new ResponseEntity<>(serviceCuenta.baja(numeroCuenta, estado), HttpStatus.OK);
    }

    @Operation(summary = "Modificar cuenta", description = "Actualiza los datos de una cuenta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta modificada correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cuenta.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cuenta> modificarCuenta(@Valid @RequestBody CuentaRequest request) {
        Cuenta cuenta = ObjectMapperUtils.map(request, Cuenta.class);
        return new ResponseEntity<>(serviceCuenta.modificarCuenta(cuenta), HttpStatus.OK);
    }
}
