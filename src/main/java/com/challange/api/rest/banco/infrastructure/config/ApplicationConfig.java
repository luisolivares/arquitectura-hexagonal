package com.challange.api.rest.banco.infrastructure.config;

import com.challange.api.rest.banco.application.services.ServiceCliente;
import com.challange.api.rest.banco.application.services.ServiceCuenta;
import com.challange.api.rest.banco.application.services.ServiceMovimiento;
import com.challange.api.rest.banco.application.services.ServiceTarjeta;
import com.challange.api.rest.banco.application.usecases.*;
import com.challange.api.rest.banco.dominio.ports.in.*;
import com.challange.api.rest.banco.dominio.ports.out.ClienteRepositoryPort;
import com.challange.api.rest.banco.dominio.ports.out.CuentaRepositoryPort;
import com.challange.api.rest.banco.dominio.ports.out.MovimientoRepositoryPort;
import com.challange.api.rest.banco.dominio.ports.out.TarjetaRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ServiceCuenta serviceCuenta(CuentaRepositoryPort port) {
        AltaCuentaUseCaseImpl altaCuentaUseCaseImpl = new AltaCuentaUseCaseImpl(port);
        BuscarCuentaUseCaseImpl buscarCuentaUseCaseImpl = new BuscarCuentaUseCaseImpl(port);
        ModificarCuentaUseCaseImpl modificarCuentaUseCaseImpl = new ModificarCuentaUseCaseImpl(port);
        BajaCuentaUseCaseImpl bajaCuentaUseCaseImpl = new BajaCuentaUseCaseImpl(port);
        return new ServiceCuenta(altaCuentaUseCaseImpl, buscarCuentaUseCaseImpl, modificarCuentaUseCaseImpl, bajaCuentaUseCaseImpl);
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
    public ServiceMovimiento serviceMovimiento(MovimientoRepositoryPort port, ClienteRepositoryPort clienteRepositoryPort, CuentaRepositoryPort cuentaRepositoryPort) {
        AltaMovimientoUseCase altaMovimientoUseCase = new AltaMovimientoUseCaseImpl(port);
        BuscarMovimientoUseCase buscarMovimientoUseCase = new BuscarMovimientoUseCaseImpl(port);
        ModificarMovimientoUseCase modificarMovimientoUseCase = new ModificarMovimientoUseCaseImpl(port);
        BajaMovimientoUseCase bajaMovimientoUseCase = new BajaMovimientoUseCaseImpl(port);
        AsociarMovimientoClienteCuentaUseCase asociarMovimientoClienteCuentaUseCase = new AsociarMovimientoClienteCuentaUseCaseImpl(clienteRepositoryPort, cuentaRepositoryPort, port);
        return new ServiceMovimiento(altaMovimientoUseCase, buscarMovimientoUseCase, modificarMovimientoUseCase, bajaMovimientoUseCase, asociarMovimientoClienteCuentaUseCase);
    }


}
