package com.challange.api.rest.banco.application.services;

import com.challange.api.rest.banco.dominio.model.Cuenta;
import com.challange.api.rest.banco.dominio.ports.in.AltaCuentaUseCase;
import com.challange.api.rest.banco.dominio.ports.in.BajaCuentaUseCase;
import com.challange.api.rest.banco.dominio.ports.in.BuscarCuentaUseCase;
import com.challange.api.rest.banco.dominio.ports.in.ModificarCuentaUseCase;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class ServiceCuenta implements AltaCuentaUseCase, BuscarCuentaUseCase, ModificarCuentaUseCase, BajaCuentaUseCase {

    private final AltaCuentaUseCase altaCuentaUseCase;
    private final BuscarCuentaUseCase buscarCuentaUseCase;
    private final ModificarCuentaUseCase modificarCuentaUseCase;
    private final BajaCuentaUseCase bajaCuentaUseCase;

    public ServiceCuenta(AltaCuentaUseCase altaCuentaUseCase, BuscarCuentaUseCase buscarCuentaUseCase, ModificarCuentaUseCase modificarCuentaUseCase, BajaCuentaUseCase bajaCuentaUseCase) {
        this.altaCuentaUseCase = altaCuentaUseCase;
        this.buscarCuentaUseCase = buscarCuentaUseCase;
        this.modificarCuentaUseCase = modificarCuentaUseCase;
        this.bajaCuentaUseCase = bajaCuentaUseCase;
    }

    @Override
    public Cuenta alta(Cuenta cuenta) {
        return altaCuentaUseCase.alta(cuenta);
    }

    @Override
    public Cuenta baja(String numeroCuenta, boolean estado) {
        return bajaCuentaUseCase.baja(numeroCuenta, estado);
    }

    @Override
    public Cuenta buscarCuentaPor(String numeroCuenta) {
        return buscarCuentaUseCase.buscarCuentaPor(numeroCuenta);
    }

    @Override
    public List<Cuenta> buscarTodasCuentas(int page, int size) {
        return buscarCuentaUseCase.buscarTodasCuentas(page, size);
    }

    @Override
    public Cuenta modificarCuenta(Cuenta cuenta) {
        return modificarCuentaUseCase.modificarCuenta(cuenta);
    }


}
