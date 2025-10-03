package com.challange.api.rest.banco.application.services;

import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.Genero;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;
import com.challange.api.rest.banco.dominio.ports.in.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ServiceClienteTest {

    @Mock
    private AltaClienteUseCase altaClienteUseCase;
    @Mock
    private BajaClienteUseCase bajaClienteUseCase;
    @Mock
    private BuscarClienteUseCase buscarClienteUseCase;
    @Mock
    private ModificarClienteUseCase modificarClienteUseCase;
    @Mock
    private AsociarClienteTarjetaUseCase asociarClienteTarjetaUseCase;
    @Mock
    private AsociarClienteCuentaUseCase asociarClienteCuentaUseCase;

    @InjectMocks
    private ServiceCliente serviceCliente;

    @Test
    @Order(1)
    void altaToAltaClienteUseCaseTest() {
        Cliente cliente = Cliente
                .builder()
                .idCliente(1)
                .email("email@email.com")
                .nombres("Luis")
                .apellidos("Gonzalez")
                .genero(Genero.MASCULINO)
                .telefono("1176289602")
                .fechaNacimiento(LocalDate.now())
                .tipoDocumento(TipoDocumento.CEDULA)
                .numeroDocumento("98956366")
                .estado(true)
                .build();
        when(altaClienteUseCase.alta(cliente)).thenReturn(cliente);

        Cliente result = serviceCliente.alta(cliente);

        assertEquals(cliente, result);
        verify(altaClienteUseCase, times(1)).alta(cliente);
    }

    @Test
    @Order(2)
    void bajaToBajaClienteUseCaseTest() {
        TipoDocumento tipoDocumento = TipoDocumento.CEDULA;
        String numeroDocumento = "98956366";
        Cliente cliente = Cliente
                .builder()
                .idCliente(1)
                .email("email@email.com")
                .nombres("Luis")
                .apellidos("Gonzalez")
                .genero(Genero.MASCULINO)
                .telefono("1176289602")
                .fechaNacimiento(LocalDate.now())
                .tipoDocumento(TipoDocumento.CEDULA)
                .numeroDocumento("98956366")
                .estado(true)
                .build();

        when(bajaClienteUseCase.baja(tipoDocumento, numeroDocumento)).thenReturn(cliente);

        Cliente result = serviceCliente.baja(tipoDocumento, numeroDocumento);

        assertEquals(cliente, result);
        verify(bajaClienteUseCase).baja(tipoDocumento, numeroDocumento);
    }

    @Test
    @Order(3)
    void buscarPorDocumentoTest() {
        TipoDocumento tipoDocumento = TipoDocumento.CEDULA;
        String numeroDocumento = "98956366";
        Cliente cliente = Cliente
                .builder()
                .idCliente(1)
                .email("email@email.com")
                .nombres("Luis")
                .apellidos("Gonzalez")
                .genero(Genero.MASCULINO)
                .telefono("1176289602")
                .fechaNacimiento(LocalDate.now())
                .tipoDocumento(TipoDocumento.CEDULA)
                .numeroDocumento("98956366")
                .estado(true)
                .build();

        when(buscarClienteUseCase.buscarPorDocumento(tipoDocumento, numeroDocumento)).thenReturn(cliente);

        Cliente result = serviceCliente.buscarPorDocumento(tipoDocumento, numeroDocumento);

        assertEquals(cliente, result);
        verify(buscarClienteUseCase).buscarPorDocumento(tipoDocumento, numeroDocumento);
    }

    @Test
    @Order(4)
    void listadoTest() {

        Cliente cliente = Cliente
                .builder()
                .idCliente(1)
                .email("email@email.com")
                .nombres("Luis")
                .apellidos("Gonzalez")
                .genero(Genero.MASCULINO)
                .telefono("1176289602")
                .fechaNacimiento(LocalDate.now())
                .tipoDocumento(TipoDocumento.CEDULA)
                .numeroDocumento("98956366")
                .estado(true)
                .build();

        List<Cliente> clientes = List.of(cliente);
        when(buscarClienteUseCase.listado(0, 10)).thenReturn(clientes);

        List<Cliente> result = serviceCliente.listado(0, 10);

        assertEquals(clientes, result);
        verify(buscarClienteUseCase).listado(0, 10);
    }

    @Test
    @Order(5)
    void delegateModificarTest() {
        Cliente cliente = Cliente
                .builder()
                .idCliente(1)
                .email("email@email.com")
                .nombres("Luis")
                .apellidos("Gonzalez")
                .genero(Genero.MASCULINO)
                .telefono("1176289602")
                .fechaNacimiento(LocalDate.now())
                .tipoDocumento(TipoDocumento.CEDULA)
                .numeroDocumento("98956366")
                .estado(true)
                .build();
        when(modificarClienteUseCase.modificar(cliente)).thenReturn(cliente);

        Cliente result = serviceCliente.modificar(cliente);

        assertEquals(cliente, result);
        verify(modificarClienteUseCase).modificar(cliente);
    }

    @Test
    @Order(6)
    void asociarClienteTarjetaTest() {
        String tarjeta = "9999";
        String numeroDocumento = "98956366";
        Cliente cliente = Cliente
                .builder()
                .idCliente(1)
                .email("email@email.com")
                .nombres("Luis")
                .apellidos("Gonzalez")
                .genero(Genero.MASCULINO)
                .telefono("1176289602")
                .fechaNacimiento(LocalDate.now())
                .tipoDocumento(TipoDocumento.CEDULA)
                .numeroDocumento("98956366")
                .estado(true)
                .build();

        when(asociarClienteTarjetaUseCase.asociarClienteTarjetaUseCase(numeroDocumento, tarjeta))
                .thenReturn(cliente);

        Cliente result = serviceCliente.asociarClienteTarjetaUseCase(numeroDocumento, tarjeta);

        assertEquals(cliente, result);
        verify(asociarClienteTarjetaUseCase).asociarClienteTarjetaUseCase(numeroDocumento, tarjeta);
    }

    @Test
    @Order(7)
    void asociarClienteCuentaTest() {
        String documento = "98956366";
        String cuenta = "1111";
        Cliente cliente = Cliente
                .builder()
                .idCliente(1)
                .email("email@email.com")
                .nombres("Luis")
                .apellidos("Gonzalez")
                .genero(Genero.MASCULINO)
                .telefono("1176289602")
                .fechaNacimiento(LocalDate.now())
                .tipoDocumento(TipoDocumento.CEDULA)
                .numeroDocumento("98956366")
                .estado(true)
                .build();

        when(asociarClienteCuentaUseCase.asociarClienteCuentaUseCase(documento, cuenta))
                .thenReturn(cliente);

        Cliente result = serviceCliente.asociarClienteCuentaUseCase(documento, cuenta);

        assertEquals(cliente, result);
        verify(asociarClienteCuentaUseCase).asociarClienteCuentaUseCase(documento, cuenta);
    }
}
