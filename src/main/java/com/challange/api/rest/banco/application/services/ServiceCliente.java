package com.challange.api.rest.banco.application.services;

import com.challange.api.rest.banco.dominio.exceptions.ClienteException;
import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;
import com.challange.api.rest.banco.dominio.ports.in.*;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
public class ServiceCliente implements AltaClienteUseCase, BajaClienteUseCase, BuscarClienteUseCase, ModificarClienteUseCase, AsociarClienteTarjetaUseCase, AsociarClienteCuentaUseCase {

    private static String errorCliente;

    private final AltaClienteUseCase altaClienteUseCase;
    private final BajaClienteUseCase bajaClienteUseCase;
    private final BuscarClienteUseCase buscarClienteUseCase;
    private final ModificarClienteUseCase modificarClienteUseCase;
    private final AsociarClienteTarjetaUseCase asociarClienteTarjetaUseCase;
    private final AsociarClienteCuentaUseCase asociarClienteCuentaUseCase;

    public ServiceCliente(AltaClienteUseCase altaClienteUseCase, BajaClienteUseCase bajaClienteUseCase, BuscarClienteUseCase buscarClienteUseCase, ModificarClienteUseCase modificarClienteUseCase, AsociarClienteTarjetaUseCase asociarClienteTarjetaUseCase, AsociarClienteCuentaUseCase asociarClienteCuentaUseCase) {
        this.altaClienteUseCase = altaClienteUseCase;
        this.bajaClienteUseCase = bajaClienteUseCase;
        this.buscarClienteUseCase = buscarClienteUseCase;
        this.modificarClienteUseCase = modificarClienteUseCase;
        this.asociarClienteTarjetaUseCase = asociarClienteTarjetaUseCase;
        this.asociarClienteCuentaUseCase = asociarClienteCuentaUseCase;
    }

    @Override
    public Cliente alta(Cliente cliente) {

        Optional<Cliente> usuarioOptional = Optional.ofNullable(buscarClienteUseCase.buscarPorDocumento(cliente.getTipoDocumento(), cliente.getNumeroDocumento()));

        if (usuarioOptional.isPresent()) {
            errorCliente = String.format("No se puede registra o dar alta al cliente ya existente cuyo documento es %s %s", cliente.getTipoDocumento(), cliente.getNumeroDocumento(), cliente.getNumeroDocumento());
            log.error(errorCliente);
            throw new ClienteException(errorCliente, 404);
        }

        return altaClienteUseCase.alta(cliente);
    }

    @Override
    public Cliente baja(TipoDocumento tipoDocumento, String numeroDocumento) {
        return bajaClienteUseCase.baja(tipoDocumento, numeroDocumento);
    }

    @Override
    public Cliente buscarPorDocumento(TipoDocumento tipoDocumento, String documento) {
        return buscarClienteUseCase.buscarPorDocumento(tipoDocumento, documento);
    }

    @Override
    public Cliente buscarPorNumeroDocumento(String documento) {
        return buscarClienteUseCase.buscarPorNumeroDocumento(documento);
    }

    @Override
    public List<Cliente> listado(int pagina, int tamanio) {

        if (pagina < 0 || tamanio < 0) {
            errorCliente = String.format("No se envio el numero de pagina o la cantidad de registros para el listado");
            log.error(errorCliente);
            throw new ClienteException(errorCliente, 404);
        }

        return buscarClienteUseCase.listado(pagina, tamanio);
    }

    @Override
    public Cliente modificar(Cliente cliente) {

        Optional<Cliente> usuarioOptional = Optional.ofNullable(buscarClienteUseCase.buscarPorDocumento(cliente.getTipoDocumento(), cliente.getNumeroDocumento()));

        if (usuarioOptional.isEmpty()) {
            errorCliente = String.format("El cliente no existe en sistema cuyo documento es %s %s", cliente.getTipoDocumento(), cliente.getNumeroDocumento(), cliente.getNumeroDocumento());
            log.error(errorCliente);
            throw new ClienteException(errorCliente, 404);
        }
        cliente.setIdCliente(usuarioOptional.get().getIdCliente());
        return modificarClienteUseCase.modificar(cliente);
    }

    @Override
    public Cliente asociarClienteTarjetaUseCase(TipoDocumento tipoDocumento, String numeroDocumentoCliente, String numeroTarjeta) {
        return asociarClienteTarjetaUseCase.asociarClienteTarjetaUseCase(tipoDocumento, numeroDocumentoCliente, numeroTarjeta);
    }

    @Override
    public Cliente asociarClienteCuentaUseCase(TipoDocumento tipoDocumento, String numeroDocumento, String numeroCuenta) {
        return asociarClienteCuentaUseCase.asociarClienteCuentaUseCase(tipoDocumento, numeroDocumento, numeroCuenta);
    }
}