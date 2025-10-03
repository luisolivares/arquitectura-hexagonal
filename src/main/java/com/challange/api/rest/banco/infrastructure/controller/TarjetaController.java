package com.challange.api.rest.banco.infrastructure.controller;

import com.challange.api.rest.banco.application.services.ServiceTarjeta;
import com.challange.api.rest.banco.dominio.model.Tarjeta;
import com.challange.api.rest.banco.infrastructure.model.request.TarjetaRequest;
import com.challange.api.rest.banco.infrastructure.utils.ObjectMapperUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api/v1/tarjetas")
@RequiredArgsConstructor
@Tag(
        name = "Gestión de Tarjetas",
        description = "Operaciones para la administración de tarjetas: alta, búsqueda, listado, modificación y baja."
)
public class TarjetaController {

    private final ServiceTarjeta serviceTarjeta;

    @Operation(summary = "Alta de tarjeta", description = "Crea una nueva tarjeta en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarjeta creada correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tarjeta.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tarjeta> alta(@Valid @RequestBody TarjetaRequest request) {
        Tarjeta tarjeta = ObjectMapperUtils.map(request, Tarjeta.class);
        return new ResponseEntity<>(serviceTarjeta.alta(tarjeta), HttpStatus.CREATED);
    }

    @Operation(summary = "Modificar tarjeta", description = "Modifica los datos de una tarjeta existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarjeta modificada correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tarjeta.class))),
            @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada", content = @Content)
    })
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tarjeta> modificar(@Valid @RequestBody TarjetaRequest request) {
        Tarjeta tarjeta = ObjectMapperUtils.map(request, Tarjeta.class);
        return new ResponseEntity<>(serviceTarjeta.modificarTarjeta(tarjeta), HttpStatus.OK);
    }

    @Operation(summary = "Listar tarjetas", description = "Obtiene una lista de todas las tarjetas de manera paginada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de tarjetas obtenido correctamente",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Tarjeta.class))))
    })
    @GetMapping(value = "/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tarjeta>> buscarTodasTarjetas(
            @Parameter(description = "Número de página (0-index)", required = true, example = "0") @PathVariable int page,
            @Parameter(description = "Cantidad de resultados por página (máx. 100)", required = true, example = "10") @PathVariable int size) {
        return new ResponseEntity<>(serviceTarjeta.buscarTodasTarjetas(page, size), HttpStatus.OK);
    }

    @Operation(summary = "Buscar tarjeta por número", description = "Obtiene los datos de una tarjeta a partir de su número único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarjeta encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tarjeta.class))),
            @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada", content = @Content)
    })
    @GetMapping(value = "/numero_tarjeta/{numeroTarjeta}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tarjeta> buscarTarjetaPorNumero(
            @Parameter(description = "Número de la tarjeta", required = true, example = "4111111111111111")
            @PathVariable("numeroTarjeta") String numeroTarjeta) {
        return new ResponseEntity<>(serviceTarjeta.buscarPorNumeroTarjeta(numeroTarjeta), HttpStatus.OK);
    }

    @Operation(summary = "Baja de tarjeta", description = "Elimina (baja lógica) una tarjeta a partir de su número único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarjeta dada de baja correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tarjeta.class))),
            @ApiResponse(responseCode = "404", description = "Tarjeta no encontrada", content = @Content)
    })
    @DeleteMapping(value = "/numero_tarjeta/{numeroTarjeta}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tarjeta> bajaTarjetaPorNumero(
            @Parameter(description = "Número de la tarjeta", required = true, example = "4111111111111111")
            @PathVariable("numeroTarjeta") String numeroTarjeta) {
        return new ResponseEntity<>(serviceTarjeta.baja(numeroTarjeta), HttpStatus.OK);
    }
}