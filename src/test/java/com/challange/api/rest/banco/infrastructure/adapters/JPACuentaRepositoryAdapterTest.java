package com.challange.api.rest.banco.infrastructure.adapters;

import com.challange.api.rest.banco.domain.model.Cuenta;
import com.challange.api.rest.banco.domain.model.TipoCuenta;
import com.challange.api.rest.banco.domain.model.TipoMovimiento;
import com.challange.api.rest.banco.infrastructure.repository.adapter.JPACuentaRepositoryAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JPACuentaRepositoryAdapter.class)
public class JPACuentaRepositoryAdapterTest {

    @Autowired
    private JPACuentaRepositoryAdapter adapter;

    @Test
    @DisplayName("Alta o creación de una cuenta bancaria en una BD H2 en memoria.")
    void testAltaCuenta() {

        Cuenta cuenta = Cuenta
                .builder()
                .numeroCuenta("01702046600000087865")
                .tipoCuenta(TipoCuenta.AHORRO)
                .fechaAlta(LocalDate.now())
                .tipoMovimiento(TipoMovimiento.TRANSFERENCIA)
                .saldoInicial(new BigDecimal(1000000))
                .saldoMovimiento(new BigDecimal(500000))
                .saldoDisponible(new BigDecimal(500000))
                .activo(true)
                .build();

        Cuenta altaCuenta = adapter.alta(cuenta);

        assertThat(altaCuenta).isNotNull();
        assertThat(altaCuenta.getNumeroCuenta()).isEqualTo("01702046600000087865");
        assertThat(altaCuenta.isActivo()).isTrue();

    }

}
