package com.challange.api.rest.banco.application.services;

import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;
import com.challange.api.rest.banco.dominio.ports.in.*;

import java.util.List;

public class ServiceCliente implements AltaClienteUseCase, BajaClienteUseCase, BuscarClienteUseCase, ModificarClienteUseCase, AsociarClienteTarjetaUseCase, AsociarClienteCuentaUseCase {

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
        return buscarClienteUseCase.listado(pagina, tamanio);
    }

    @Override
    public Cliente modificar(Cliente cliente) {
        return modificarClienteUseCase.modificar(cliente);
    }

    @Override
    public Cliente asociarClienteTarjetaUseCase(String numeroDocumentoCliente, String numeroTarjeta) {
        return asociarClienteTarjetaUseCase.asociarClienteTarjetaUseCase(numeroDocumentoCliente, numeroTarjeta);
    }

    @Override
    public Cliente asociarClienteCuentaUseCase(String numeroDocumento, String numeroCuenta) {
        return asociarClienteCuentaUseCase.asociarClienteCuentaUseCase(numeroDocumento, numeroCuenta);
    }
}