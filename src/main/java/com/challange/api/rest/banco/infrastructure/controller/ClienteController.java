package com.challange.api.rest.banco.infrastructure.controller;

import com.challange.api.rest.banco.application.services.ServiceCliente;
import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;
import com.challange.api.rest.banco.infrastructure.model.request.ClienteRequest;
import com.challange.api.rest.banco.infrastructure.utils.ObjectMapperUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(value = "/api/v1/clientes")
@RequiredArgsConstructor
@Tag(
        name = "Gestión de Clientes",
        description = "Operaciones para la administración de clientes (alta, búsqueda, listado, modificación y baja)."
)
public class ClienteController {

    private final ServiceCliente serviceCliente;

    @Operation(summary = "Alta de cliente", description = "Crea un nuevo cliente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado correctamente",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> alta(@Valid @RequestBody ClienteRequest request) {
        Cliente cliente = ObjectMapperUtils.map(request, Cliente.class);
        return new ResponseEntity<>(serviceCliente.alta(cliente), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar clientes paginados", description = "Obtiene una lista de clientes según la página y tamaño indicados")
    @GetMapping(value = "/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> listarTotalClientes(
            @Parameter(description = "Número de página", required = true, example = "0") @PathVariable int page,
            @Parameter(description = "Cantidad de resultados por página", required = true, example = "10") @PathVariable int size) {
        return new ResponseEntity<>(serviceCliente.listado(page, size), HttpStatus.OK);
    }

    @Operation(summary = "Buscar cliente por documento", description = "Busca un cliente usando su tipo y número de documento")
    @GetMapping(value = "/tipo_documento/{tipoDocumento}/numero_documento/{numeroDocumento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> buscarClientePorTipoYDocumento(
            @Parameter(description = "Tipo de documento", required = true, example = "DNI") @PathVariable TipoDocumento tipoDocumento,
            @Parameter(description = "Número de documento (6 a 10 dígitos)", required = true, example = "123456")
            @PathVariable @Size(min = 6, max = 10) @Pattern(regexp = "\\d+") String numeroDocumento) {
        return new ResponseEntity<>(serviceCliente.buscarPorDocumento(tipoDocumento, numeroDocumento), HttpStatus.OK);
    }

    @Operation(summary = "Modificar cliente", description = "Modifica los datos de un cliente existente")
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> modificarCliente(@Valid @RequestBody ClienteRequest request) {
        Cliente cliente = ObjectMapperUtils.map(request, Cliente.class);
        return new ResponseEntity<>(serviceCliente.modificar(cliente), HttpStatus.OK);
    }

    @Operation(summary = "Baja de cliente", description = "Elimina (baja lógica) un cliente por tipo y número de documento")
    @DeleteMapping(value = "/tipo_documento/{tipoDocumento}/numero_documento/{numeroDocumento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> bajaCliente(
            @Parameter(description = "Tipo de documento", required = true) @PathVariable TipoDocumento tipoDocumento,
            @Parameter(description = "Número de documento (6 a 10 dígitos)", required = true, example = "17958147")
            @PathVariable @Size(min = 6, max = 10) @Pattern(regexp = "\\d+") String numeroDocumento) {
        return new ResponseEntity<>(serviceCliente.baja(tipoDocumento, numeroDocumento), HttpStatus.OK);
    }


    @Operation(summary = "Asociar cliente cuenta", description = "Se asocia un cliente por tipo y número de documento con una cuenta dato su numero de la misma.")
    @PostMapping("/{tipoDocumento}/{numeroDocumento}/cuentas/{numeroCuenta}")
    public ResponseEntity<Cliente> asociarClienteCuenta(
            @Parameter(description = "Tipo de documento")
            @PathVariable TipoDocumento tipoDocumento,
            @Parameter(description = "Número de documento (6 a 10 dígitos)", example = "43170318")
            @PathVariable
            @Size(min = 6, max = 10, message = "El número de documento debe tener entre 6 y 10 dígitos")
            @Pattern(regexp = "\\d+", message = "El número de documento debe contener solo dígitos")
            String numeroDocumento,

            @Parameter(description = "Número de cuenta (12 a 19 dígitos)", example = "1234567812345678")
            @PathVariable
            @Size(min = 8, max = 20, message = "El número de cuenta debe tener entre 8 y 20 dígitos")
            @Pattern(regexp = "\\d+", message = "El número de cuenta debe contener solo dígitos")
            String numeroCuenta
    ) {
        return new ResponseEntity<>(serviceCliente.asociarClienteCuentaUseCase(numeroDocumento, numeroCuenta), HttpStatus.OK);
    }


    @Operation(summary = "Asociar cliente tarjeta", description = "Se asocia un cliente por tipo y número de documento con una tarjeta dato su numero de la misma.")
    @PostMapping("/{tipoDocumento}/{numeroDocumento}/tarjetas/{numeroTarjeta}")
    public ResponseEntity<Cliente> asociarClienteTarjeta(
            @Parameter(description = "Tipo de documento")
            @PathVariable TipoDocumento tipoDocumento,
            @Parameter(description = "Número de documento (6 a 10 dígitos)", example = "43170318")
            @PathVariable
            @Size(min = 6, max = 10, message = "El número de documento debe tener entre 6 y 10 dígitos")
            @Pattern(regexp = "\\d+", message = "El número de documento debe contener solo dígitos")
            String numeroDocumento,

            @Parameter(description = "Número de tarjeta (12 a 19 dígitos)", example = "1234567812345678")
            @PathVariable
            @Size(min = 12, max = 19, message = "El número de tarjeta debe tener entre 12 y 19 dígitos")
            @Pattern(regexp = "\\d+", message = "El número de tarjeta debe contener solo dígitos")
            String numeroTarjeta
    ) {
        return new ResponseEntity<>(serviceCliente.asociarClienteTarjetaUseCase(numeroDocumento, numeroTarjeta), HttpStatus.OK);
    }

}
