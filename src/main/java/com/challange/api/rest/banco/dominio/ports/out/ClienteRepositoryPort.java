package com.challange.api.rest.banco.dominio.ports.out;

import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.Cuenta;
import com.challange.api.rest.banco.dominio.model.Tarjeta;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;

import java.util.List;

public interface ClienteRepositoryPort {

    Cliente alta(Cliente cliente);

    Cliente baja(TipoDocumento tipoDocumento, String numeroDocumento);

    Cliente buscarPorTipoYNumeroDocumento(TipoDocumento tipoDocumento, String documento);

    Cliente buscarPorNumeroDocumento(String documento);

    List<Cliente> listado(int pagina, int tamanio);

    Cliente modificarCliente(Cliente cliente);

    Cliente asociarClienteTarjeta(Cliente cliente, Tarjeta tarjeta);

    Cliente asociarClienteCuenta(Cliente cliente, Cuenta cuenta);

}