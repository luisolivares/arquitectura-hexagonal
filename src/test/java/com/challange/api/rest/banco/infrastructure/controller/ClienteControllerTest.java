package com.challange.api.rest.banco.infrastructure.controller;

import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.Genero;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;
import com.challange.api.rest.banco.infrastructure.utils.Utils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String API_URL = "/api/v1/clientes/";

    @Test
    @DisplayName("Test para probar una modificación de un cliente en el controlador CuentaController")
    @Order(2)
    void modificarClienteController() throws Exception {

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

        String json = Utils.objectJson(cliente);

        mockMvc.perform(
                        put(API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test para probar una alta de un cliente en el controlador CuentaController")
    @Order(1)
    void altaClienteController() throws Exception {

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

        String json = Utils.objectJson(cliente);

        mockMvc.perform(
                        post(API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tipoDocumento").value(TipoDocumento.CEDULA.name()))
                .andExpect(jsonPath("$.numeroDocumento").value("98956366"));
    }

    @Test
    @DisplayName("Test para probar una busqueda de un cliente dado su tipo y numero de documento en el controlador CuentaController")
    @Order(3)
    void buscarClienteController() throws Exception {

        TipoDocumento tipoDocumento = TipoDocumento.CEDULA;
        String numeroDocumento = "98956366";

        mockMvc.perform(
                        get(API_URL.concat("tipo_documento/{tipoDocumento}/numero_documento/{numeroDocumento}"), tipoDocumento, numeroDocumento)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Test para probar una eliminación lógica de un cliente dado su tipo y numero de documento en el controlador CuentaController")
    @Order(4)
    void eliminarClienteController() throws Exception {

        TipoDocumento tipoDocumento = TipoDocumento.CEDULA;
        String numeroDocumento = "98956366";

        mockMvc.perform(delete(API_URL.concat("tipo_documento/{tipoDocumento}/numero_documento/{numeroDocumento}"), tipoDocumento, numeroDocumento)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Luis"))
                .andExpect(jsonPath("$.numeroDocumento").value(numeroDocumento));
    }


}
