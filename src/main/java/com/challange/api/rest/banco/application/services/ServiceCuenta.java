package com.challange.api.rest.banco.application.services;

import com.challange.api.rest.banco.domain.exceptions.CuentaException;
import com.challange.api.rest.banco.domain.model.Cuenta;
import com.challange.api.rest.banco.domain.ports.in.AltaCuentaUseCase;
import com.challange.api.rest.banco.domain.ports.in.BajaCuentaUseCase;
import com.challange.api.rest.banco.domain.ports.in.BuscarCuentaUseCase;
import com.challange.api.rest.banco.domain.ports.in.ModificarCuentaUseCase;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
public class ServiceCuenta implements AltaCuentaUseCase, BuscarCuentaUseCase, ModificarCuentaUseCase, BajaCuentaUseCase {

    private static String errorCuenta;

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

        Optional<Cuenta> cuentaExistente = Optional.ofNullable(buscarCuentaUseCase.buscarCuentaPor(cuenta.getNumeroCuenta()));

        if (cuentaExistente.isPresent()) {
            errorCuenta = String.format("El cuenta N° %s ya existe en el sistema.", cuenta.getNumeroCuenta());
            throw new CuentaException(errorCuenta, 404);
        }
        return altaCuentaUseCase.alta(cuenta);
    }

    @Override
    public Cuenta baja(String numeroCuenta) {

        Optional<Cuenta> cuentaExistente = Optional.ofNullable(buscarCuentaUseCase.buscarCuentaPor(numeroCuenta));

        if (cuentaExistente.isEmpty()) {
            errorCuenta = String.format("El cuenta N° %s no existe en el sistema para su baja", numeroCuenta);
            throw new CuentaException(errorCuenta, 404);
        }
        cuentaExistente.get().setActivo(false);
        return bajaCuentaUseCase.baja(numeroCuenta);
    }

    @Override
    public Cuenta buscarCuentaPor(String numeroCuenta) {
        return buscarCuentaUseCase.buscarCuentaPor(numeroCuenta);
    }

    @Override
    public List<Cuenta> buscarTodasCuentas(int page, int size) {

        if (page < 0 || size < 0) {
            errorCuenta = String.format("No se envio el numero de pagina o la cantidad de registros para el listado");
            throw new CuentaException(errorCuenta, 404);
        }

        return buscarCuentaUseCase.buscarTodasCuentas(page, size);
    }

    @Override
    public Cuenta modificarCuenta(Cuenta cuenta) {

        Optional<Cuenta> cuentaOptional = Optional.ofNullable(buscarCuentaUseCase.buscarCuentaPor(cuenta.getNumeroCuenta()));

        if (cuentaOptional.isEmpty()) {
            errorCuenta = String.format("El cuenta N° %s no existe en el sistema para su modificación.", cuenta.getNumeroCuenta());
            throw new CuentaException(errorCuenta, 404);
        }

        cuenta.setIdCuenta(cuentaOptional.get().getIdCuenta());

        return modificarCuentaUseCase.modificarCuenta(cuenta);
    }


}
