package com.challange.api.rest.banco.dominio.ports.in;

import com.challange.api.rest.banco.dominio.model.Cuenta;

import java.util.List;

public interface BuscarCuentaUseCase {

    Cuenta buscarCuentaPor(String numeroCuenta);

    List<Cuenta> buscarTodasCuentas(int page, int size);

}
