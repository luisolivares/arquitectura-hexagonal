package com.challange.api.rest.banco.domain.ports.out;

import com.challange.api.rest.banco.domain.model.Cuenta;

import java.util.List;

public interface CuentaRepositoryPort {

    Cuenta alta(Cuenta cuenta);

    Cuenta buscarCuentaPorNumeroCuenta(String numeroCuenta);

    List<Cuenta> buscarTodasCuentas(int page, int size);

    Cuenta modificarCuenta(Cuenta cuenta);

    Cuenta baja(String numeroCuenta);

}