package com.challange.api.rest.banco.infrastructure.adapters;

import com.challange.api.rest.banco.domain.model.Cliente;
import com.challange.api.rest.banco.domain.model.Genero;
import com.challange.api.rest.banco.domain.model.TipoDocumento;
import com.challange.api.rest.banco.infrastructure.repository.adapter.JPAClienteRepositoryAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JPAClienteRepositoryAdapter.class)
public class JPAClienteRepositoryAdapterTest {

    @Autowired
    private JPAClienteRepositoryAdapter adapter;

    @Test
    @DisplayName("Alta o creación de un cliente nuevo en la BD de memoria H2")
    void testAltaCliente() {
        Cliente cliente = Cliente
                .builder()
                .idCliente(null)
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

        Cliente result = adapter.alta(cliente);

        // Assert
        Cliente encontrado = adapter.buscarPorNumeroDocumento("98956366");

        assertThat(result).isNotNull();
        assertThat(result.getNumeroDocumento()).isEqualTo("98956366");

        assertThat(encontrado).isNotNull();
        assertThat(encontrado.getNombres()).isEqualTo("Luis");

    }


    @Test
    @DisplayName("Debe buscar cliente por documento")
    void testBuscarPorDocumento() {
        Cliente cliente = Cliente
                .builder()
                .idCliente(null)
                .email("email@email.com")
                .nombres("Ana")
                .apellidos("Gonzalez")
                .genero(Genero.MASCULINO)
                .telefono("1176289602")
                .fechaNacimiento(LocalDate.of(1990, 5, 15))
                .tipoDocumento(TipoDocumento.CEDULA)
                .numeroDocumento("87654321")
                .estado(true)
                .build();

        adapter.alta(cliente);

        Cliente result = adapter.buscarPorNumeroDocumento("87654321");

        assertThat(result).isNotNull();
        assertThat(result.getNombres()).isEqualTo("Ana");
    }

    @Test
    @DisplayName("Debe dar de baja cliente existente")
    void testBajaCliente() {
        Cliente cliente = Cliente
                .builder()
                .idCliente(null)
                .email("pedro@email.com")
                .nombres("Pedro")
                .apellidos("Sanchez")
                .genero(Genero.MASCULINO)
                .telefono("1176289602")
                .fechaNacimiento(LocalDate.of(1990, 5, 15))
                .tipoDocumento(TipoDocumento.PASAPORTE)
                .numeroDocumento("11111111")
                .estado(false)
                .build();

        adapter.alta(cliente);

        Cliente result = adapter.baja(TipoDocumento.PASAPORTE, "11111111");

        assertThat(result).isNotNull();
        assertThat(result.getEstado()).isFalse();
    }

    @Test
    @DisplayName("Debe devolver listado paginado")
    void testListado() {
        for (int i = 1; i <= 5; i++) {
            adapter.alta(

                    Cliente
                            .builder()
                            .idCliente(null)
                            .nombres("Cliente " + i)
                            .apellidos("Apellido " + i)
                            .email("cliente" + i + "@test.com")
                            .apellidos("Sanchez")
                            .genero(Genero.MASCULINO)
                            .telefono("1176289602")
                            .fechaNacimiento(LocalDate.now())
                            .tipoDocumento(TipoDocumento.PASAPORTE)
                            .numeroDocumento("DOC" + i)
                            .estado(true)
                            .build()


            );
        }

        List<Cliente> page = adapter.listado(0, 3);

        assertThat(page).hasSize(3);
    }

    @Test
    @DisplayName("Debe modificar cliente existente")
    void testModificarCliente() {
        Cliente cliente = Cliente
                .builder()
                .idCliente(null)
                .email("lucia@hotmail.com")
                .nombres("Lucia")
                .apellidos("Martinez")
                .genero(Genero.MASCULINO)
                .telefono("1176289602")
                .fechaNacimiento(LocalDate.of(1990, 5, 15))
                .tipoDocumento(TipoDocumento.PASAPORTE)
                .numeroDocumento("123456789")
                .estado(true)
                .build();

        cliente = adapter.alta(cliente);

        cliente.setNombres("Lucia Modificada");

        Cliente result = adapter.modificarCliente(cliente);

        assertThat(result).isNotNull();
        assertThat(result.getNombres()).isEqualTo("Lucia Modificada");
    }

}
