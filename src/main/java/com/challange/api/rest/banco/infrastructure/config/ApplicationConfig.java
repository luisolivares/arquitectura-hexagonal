package com.challange.api.rest.banco.infrastructure.config;

import com.challange.api.rest.banco.application.services.ServiceCliente;
import com.challange.api.rest.banco.application.services.ServiceCuenta;
import com.challange.api.rest.banco.application.services.ServiceMovimiento;
import com.challange.api.rest.banco.application.services.ServiceTarjeta;
import com.challange.api.rest.banco.application.usecases.*;
import com.challange.api.rest.banco.domain.ports.in.*;
import com.challange.api.rest.banco.domain.ports.out.ClienteRepositoryPort;
import com.challange.api.rest.banco.domain.ports.out.CuentaRepositoryPort;
import com.challange.api.rest.banco.domain.ports.out.MovimientoRepositoryPort;
import com.challange.api.rest.banco.domain.ports.out.TarjetaRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ServiceCuenta serviceCuenta(CuentaRepositoryPort port) {
        AltaCuentaUseCase altaCuentaUseCase = new AltaCuentaUseCaseImpl(port);
        BuscarCuentaUseCase buscarCuentaUseCase = new BuscarCuentaUseCaseImpl(port);
        ModificarCuentaUseCase modificarCuentaUseCase = new ModificarCuentaUseCaseImpl(port);
        BajaCuentaUseCase bajaCuentaUseCase = new BajaCuentaUseCaseImpl(port);
        return new ServiceCuenta(altaCuentaUseCase, buscarCuentaUseCase, modificarCuentaUseCase, bajaCuentaUseCase);
    }

    @Bean
    public ServiceCliente serviceCliente(ClienteRepositoryPort port, CuentaRepositoryPort cuentaRepositoryPort, TarjetaRepositoryPort tarjetaRepositoryPort) {
        AltaClienteUseCase altaClienteUseCase = new AltaClienteUseCaseImpl(port);
        BuscarClienteUseCase buscarClienteUseCase = new BuscarClienteUseCaseImpl(port);
        ModificarClienteUseCase modificarClienteUseCase = new ModificarClienteUseCaseImpl(port);
        BajaClienteUseCase bajaClienteUseCase = new BajaClienteUseCaseImpl(port);
        AsociarClienteTarjetaUseCase asociarClienteTarjetaUseCase = new AsociarClienteTarjetaUseCaseImpl(port, tarjetaRepositoryPort);
        AsociarClienteCuentaUseCase asociarClienteCuentaUseCase = new AsociarClienteCuentaUseCaseImpl(port, cuentaRepositoryPort);
        return new ServiceCliente(altaClienteUseCase, bajaClienteUseCase, buscarClienteUseCase, modificarClienteUseCase, asociarClienteTarjetaUseCase, asociarClienteCuentaUseCase);
    }

    @Bean
    public ServiceTarjeta serviceTarjeta(TarjetaRepositoryPort port) {
        AltaTarjetaUseCase altaTarjetaUseCase = new AltaTarjetaUseCaseImpl(port);
        BuscarTarjetaUseCase buscarTarjetaUseCase = new BuscarTarjetaUseCaseImpl(port);
        ModificarTarjetaUseCase modificarTarjetaUseCase = new ModificarTarjetaUseCaseImpl(port);
        BajaTarjetaUseCase bajaTarjetaUseCase = new BajaTarjetaUseCaseImpl(port);
        return new ServiceTarjeta(altaTarjetaUseCase, bajaTarjetaUseCase, buscarTarjetaUseCase, modificarTarjetaUseCase);
    }

    @Bean
    public ServiceMovimiento serviceMovimiento(MovimientoRepositoryPort port, ClienteRepositoryPort clienteRepositoryPort, CuentaRepositoryPort cuentaRepositoryPort, TarjetaRepositoryPort tarjetaRepositoryPort) {
        AltaMovimientoUseCase altaMovimientoUseCase = new AltaMovimientoUseCaseImpl(port);
        BuscarMovimientoUseCase buscarMovimientoUseCase = new BuscarMovimientoUseCaseImpl(port);
        ModificarMovimientoUseCase modificarMovimientoUseCase = new ModificarMovimientoUseCaseImpl(port);
        BajaMovimientoUseCase bajaMovimientoUseCase = new BajaMovimientoUseCaseImpl(port);
        AsociarMovimientoClienteCuentaUseCase asociarMovimientoClienteCuentaUseCase = new AsociarMovimientoClienteCuentaUseCaseImpl(clienteRepositoryPort, cuentaRepositoryPort, port);
        AsociarMovimientoClienteTarjetaUseCase asociarMovimientoClienteTarjetaUseCase = new AsociarMovimientoClienteTarjetaUseCaseImpl(clienteRepositoryPort, tarjetaRepositoryPort, port);
        return new ServiceMovimiento(altaMovimientoUseCase, buscarMovimientoUseCase, modificarMovimientoUseCase, bajaMovimientoUseCase, asociarMovimientoClienteCuentaUseCase, asociarMovimientoClienteTarjetaUseCase);
    }

}