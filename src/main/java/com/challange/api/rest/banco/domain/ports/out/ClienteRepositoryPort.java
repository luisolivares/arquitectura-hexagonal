package com.challange.api.rest.banco.domain.ports.out;

import com.challange.api.rest.banco.domain.model.Cliente;
import com.challange.api.rest.banco.domain.model.Cuenta;
import com.challange.api.rest.banco.domain.model.Tarjeta;
import com.challange.api.rest.banco.domain.model.TipoDocumento;

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