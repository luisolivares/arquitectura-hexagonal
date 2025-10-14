package com.challange.api.rest.banco.application.services;

import com.challange.api.rest.banco.dominio.exceptions.MovimientoException;
import com.challange.api.rest.banco.dominio.model.Movimiento;
import com.challange.api.rest.banco.dominio.model.TipoCanal;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;
import com.challange.api.rest.banco.dominio.model.TipoMovimiento;
import com.challange.api.rest.banco.dominio.ports.in.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Log4j2
public class ServiceMovimiento implements AltaMovimientoUseCase, BuscarMovimientoUseCase, ModificarMovimientoUseCase, BajaMovimientoUseCase, AsociarMovimientoClienteCuentaUseCase, AsociarMovimientoClienteTarjetaUseCase {

    private static String errorMovimiento;

    private final AltaMovimientoUseCase altaMovimientoUseCase;
    private final BuscarMovimientoUseCase buscarMovimientoUseCase;
    private final ModificarMovimientoUseCase modificarMovimientoUseCase;
    private final BajaMovimientoUseCase bajaMovimientoUseCase;
    private final AsociarMovimientoClienteCuentaUseCase asociarMovimientoClienteCuentaUseCase;
    private final AsociarMovimientoClienteTarjetaUseCase asociarMovimientoClienteTarjetaUseCase;

    public ServiceMovimiento(AltaMovimientoUseCase altaMovimientoUseCase, BuscarMovimientoUseCase buscarMovimientoUseCase, ModificarMovimientoUseCase modificarMovimientoUseCase, BajaMovimientoUseCase bajaMovimientoUseCase, AsociarMovimientoClienteCuentaUseCase asociarMovimientoClienteCuentaUseCase, AsociarMovimientoClienteTarjetaUseCase asociarMovimientoClienteTarjetaUseCase) {
        this.altaMovimientoUseCase = altaMovimientoUseCase;
        this.buscarMovimientoUseCase = buscarMovimientoUseCase;
        this.modificarMovimientoUseCase = modificarMovimientoUseCase;
        this.bajaMovimientoUseCase = bajaMovimientoUseCase;
        this.asociarMovimientoClienteCuentaUseCase = asociarMovimientoClienteCuentaUseCase;
        this.asociarMovimientoClienteTarjetaUseCase = asociarMovimientoClienteTarjetaUseCase;
    }

    @Override
    public Movimiento alta(Movimiento movimiento) {
        return altaMovimientoUseCase.alta(movimiento);
    }

    @Override
    public void baja(long idMovimiento) {

        Optional<Movimiento> movimiento = Optional.ofNullable(buscarMovimientoUseCase.buscarMovimientoPorId(idMovimiento));

        if (movimiento.isEmpty()) {
            errorMovimiento = String.format("No se encontro el movimiento bancario por lo cual no se le puede dar de baja.");
            throw new MovimientoException(errorMovimiento, 404);
        }

        bajaMovimientoUseCase.baja(idMovimiento);
    }

    @Override
    public List<Movimiento> buscarMovimientoPor(TipoMovimiento tipoMovimiento, TipoCanal tipoCanal, LocalDate fechaDesde, LocalDate fechaHasta, int page, int size) {

        if (page < 0 || size < 0) {
            errorMovimiento = String.format("No se envio el numero de pagina o la cantidad de registros para el listado");
            log.error(errorMovimiento);
            throw new MovimientoException(errorMovimiento, 404);
        }

        return buscarMovimientoUseCase.buscarMovimientoPor(tipoMovimiento, tipoCanal, fechaDesde, fechaHasta, page, size);
    }

    @Override
    public List<Movimiento> buscarTodo(int page, int size) {

        if (page < 0 || size < 0) {
            errorMovimiento = String.format("No se envio el numero de pagina o la cantidad de registros para el listado");
            log.error(errorMovimiento);
            throw new MovimientoException(errorMovimiento, 404);
        }

        return buscarMovimientoUseCase.buscarTodo(page, size);
    }

    @Override
    public Movimiento buscarMovimientoPorId(long idMovimiento) {
        return buscarMovimientoUseCase.buscarMovimientoPorId(idMovimiento);
    }

    @Override
    public Movimiento buscarMovimientoPorReferenciaOperacion(String referenciaOperacion) {
        return buscarMovimientoUseCase.buscarMovimientoPorReferenciaOperacion(referenciaOperacion);
    }

    @Override
    public Movimiento modificarMovimiento(Movimiento movimiento) {

        Optional<Movimiento> movimientoOptional = Optional.ofNullable(buscarMovimientoUseCase.buscarMovimientoPorReferenciaOperacion(movimiento.getReferenciaOperacion()));

        if (movimientoOptional.isEmpty()) {
            errorMovimiento = String.format("No se encuentra el movimiento bancario en sistema");
            log.error(errorMovimiento);
            throw new MovimientoException(errorMovimiento, 404);
        }

        movimiento.setIdMovimiento(movimientoOptional.get().getIdMovimiento());
        return modificarMovimientoUseCase.modificarMovimiento(movimiento);
    }


    @Override
    public Movimiento asociarMovimientoClienteCuentaUseCase(Movimiento movimiento, TipoDocumento tipoDocumento, String numeroDocumentoCliente, String numeroCuenta) {

        boolean errorTipoDocumento = false;
        boolean errorNumeroDocumento = false;
        boolean errorNumeroCuenta = false;

        StringBuilder erroresAsociarMovimientoClienteCuenta = new StringBuilder();

        if (Optional.ofNullable(tipoDocumento).isEmpty()) {
            errorTipoDocumento = true;
            erroresAsociarMovimientoClienteCuenta.append("No se envio el tipo de documento del cliente");
        }

        if (Optional.ofNullable(numeroDocumentoCliente).isEmpty()) {
            errorNumeroDocumento = true;
            erroresAsociarMovimientoClienteCuenta.append("No se envio el numero de documento del cliente");
        }

        if (Optional.ofNullable(numeroCuenta).isEmpty()) {
            errorNumeroCuenta = true;
            erroresAsociarMovimientoClienteCuenta.append("No se envio el numero de cuenta del cliente");
        }

        if (errorTipoDocumento || errorNumeroDocumento || errorNumeroCuenta) {
            log.error(erroresAsociarMovimientoClienteCuenta.toString());
            throw new MovimientoException(erroresAsociarMovimientoClienteCuenta.toString(), 404);
        }

        return asociarMovimientoClienteCuentaUseCase.asociarMovimientoClienteCuentaUseCase(movimiento, tipoDocumento, numeroDocumentoCliente, numeroCuenta);
    }

    @Override
    public Movimiento asociarMovimientoClienteTarjetaUseCase(Movimiento movimiento, TipoDocumento tipoDocumento, String numeroDocumentoCliente, String numeroTarjeta) {

        boolean errorTipoDocumento = false;
        boolean errorNumeroDocumento = false;
        boolean errorNumeroTarjeta = false;

        StringBuilder erroresAsociarMovimientoClienteTarjeta = new StringBuilder();

        if (Optional.ofNullable(tipoDocumento).isEmpty()) {
            errorTipoDocumento = true;
            erroresAsociarMovimientoClienteTarjeta.append("No se envio el tipo de documento del cliente");
        }

        if (Optional.ofNullable(numeroDocumentoCliente).isEmpty()) {
            errorNumeroDocumento = true;
            erroresAsociarMovimientoClienteTarjeta.append("No se envio el numero de documento del cliente");
        }

        if (Optional.ofNullable(numeroTarjeta).isEmpty()) {
            errorNumeroTarjeta = true;
            erroresAsociarMovimientoClienteTarjeta.append("No se envio el numero de tarjeta del cliente");
        }

        if (errorTipoDocumento || errorNumeroDocumento || errorNumeroTarjeta) {
            log.error(erroresAsociarMovimientoClienteTarjeta.toString());
            throw new MovimientoException(erroresAsociarMovimientoClienteTarjeta.toString(), 404);
        }

        return asociarMovimientoClienteTarjetaUseCase.asociarMovimientoClienteTarjetaUseCase(movimiento, tipoDocumento, numeroDocumentoCliente, numeroTarjeta);

    }
}
