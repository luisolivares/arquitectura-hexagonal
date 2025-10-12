package com.challange.api.rest.banco.application.usescase;

import com.challange.api.rest.banco.application.usecases.AltaClienteUseCaseImpl;
import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.Genero;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;
import com.challange.api.rest.banco.dominio.ports.out.ClienteRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AltaClienteUseCaseImplTest {

    @Mock
    private ClienteRepositoryPort clienteRepositoryPort;

    @InjectMocks
    private AltaClienteUseCaseImpl altaClienteUseCase;

    @Test
    @DisplayName("Caso de uso de alta de un cliente")
    void altaCliente() {

        Cliente cliente = Cliente
                .builder()
                //.idCliente(1)
                .email("email@email.com")
                .nombres("Luis")
                .apellidos("Gonzalez")
                .genero(Genero.MASCULINO)
                .telefono("1176289602")
                .fechaNacimiento(LocalDate.of(1990, 5, 15))
                .tipoDocumento(TipoDocumento.CEDULA)
                .numeroDocumento("98956366")
                .estado(true)
                .build();


        when(clienteRepositoryPort.alta(cliente)).thenReturn(cliente);

        // Act
        Cliente result = altaClienteUseCase.alta(cliente);

        // Assert
        assertNotNull(result);
        assertEquals("Luis", result.getNombres());
        verify(clienteRepositoryPort, times(1)).alta(cliente);
    }


}
