package com.challange.api.rest.banco.application.services;

import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.Genero;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;
import com.challange.api.rest.banco.dominio.ports.in.*;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    @DisplayName("Damos alta o creación de un cliente bancarario.")
    @Order(1)
    void altaToAltaClienteUseCaseTest() {
        Cliente cliente = Cliente
                .builder()
                //.idCliente(1)
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
    @DisplayName("Damos baja o eliminación lógica a un cliente bancarario.")
    @Order(2)
    void bajaToBajaClienteUseCaseTest() {
        TipoDocumento tipoDocumento = TipoDocumento.CEDULA;
        String numeroDocumento = "98956366";
        Cliente cliente = Cliente
                .builder()
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
    @DisplayName("Busqueda de un cliente bancarario dado su tipo y numero de documento.")
    @Order(3)
    void buscarPorDocumentoTest() {
        TipoDocumento tipoDocumento = TipoDocumento.CEDULA;
        String numeroDocumento = "98956366";
        Cliente cliente = Cliente
                .builder()
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
    @DisplayName("Listamos a clientes bancararios.")
    @Order(4)
    void listadoTest() {

        Cliente cliente = Cliente
                .builder()
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
    @DisplayName("Modificamos a un cliente bancarario.")
    @Order(5)
    void delegateModificarTest() {
        Cliente cliente = Cliente
                .builder()
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

        when(buscarClienteUseCase.buscarPorDocumento(cliente.getTipoDocumento(), cliente.getNumeroDocumento()))
                .thenReturn(cliente);

        when(modificarClienteUseCase.modificar(cliente)).thenReturn(cliente);

        Cliente result = serviceCliente.modificar(cliente);

        assertEquals(cliente, result);
        verify(modificarClienteUseCase).modificar(cliente);
    }

    @Test
    @DisplayName("Asociamos a un cliente bancario con una tarjeta bancaria dado el numero de documento del cliente así como del numero de tarjeta")
    @Order(6)
    void asociarClienteTarjetaTest() {
        String tarjeta = "9999";
        String numeroDocumento = "98956366";
        Cliente cliente = Cliente
                .builder()
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

        when(asociarClienteTarjetaUseCase.asociarClienteTarjetaUseCase(TipoDocumento.CEDULA, numeroDocumento, tarjeta))
                .thenReturn(cliente);

        Cliente result = serviceCliente.asociarClienteTarjetaUseCase(TipoDocumento.CEDULA, numeroDocumento, tarjeta);

        assertEquals(cliente, result);
        verify(asociarClienteTarjetaUseCase).asociarClienteTarjetaUseCase(TipoDocumento.CEDULA, numeroDocumento, tarjeta);
    }

    @Test
    @DisplayName("Asociamos a un cliente bancario con una cuenta bancaria dado el numero de documento del cliente así como del numero de cuenta")
    @Order(7)
    void asociarClienteCuentaTest() {
        String documento = "98956366";
        String cuenta = "1111";
        Cliente cliente = Cliente
                .builder()
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

        when(asociarClienteCuentaUseCase.asociarClienteCuentaUseCase(TipoDocumento.CEDULA, documento, cuenta))
                .thenReturn(cliente);

        Cliente result = serviceCliente.asociarClienteCuentaUseCase(TipoDocumento.CEDULA, documento, cuenta);

        assertEquals(cliente, result);
        verify(asociarClienteCuentaUseCase).asociarClienteCuentaUseCase(TipoDocumento.CEDULA, documento, cuenta);
    }
}
