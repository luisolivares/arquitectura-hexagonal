package com.challange.api.rest.banco.infrastructure.repository.adapter;

import com.challange.api.rest.banco.dominio.model.Cuenta;
import com.challange.api.rest.banco.dominio.ports.out.CuentaRepositoryPort;
import com.challange.api.rest.banco.infrastructure.entity.CuentaEntity;
import com.challange.api.rest.banco.infrastructure.repository.CuentaRepository;
import com.challange.api.rest.banco.infrastructure.utils.ObjectMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class JPACuentaRepositoryAdapter implements CuentaRepositoryPort {

    private final CuentaRepository cuentaRepository;

    @Transactional
    @Override
    public Cuenta alta(Cuenta cuenta) {
//        CuentaEntity entity = CuentaEntity
//                .builder()
//                .tipoCuenta(cuenta.getTipoCuenta())
//                .numeroCuenta(cuenta.getNumeroCuenta())
//                .saldoInicial(cuenta.getSaldoInicial())
//                .saldoMovimiento(cuenta.getSaldoMovimiento())
//                .saldoDisponible(cuenta.getSaldoDisponible())
//                .activo(cuenta.isActivo())
//                .fechaAlta(cuenta.getFechaAlta())
//                .build();

        CuentaEntity entity = ObjectMapperUtils.map(cuenta, CuentaEntity.class);

        return ObjectMapperUtils.map(cuentaRepository.saveAndFlush(entity), Cuenta.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Cuenta buscarCuentaPorNumeroCuenta(String numeroCuenta) {

        Optional<CuentaEntity> cuentaEntity = cuentaRepository.buscarPorNumero(numeroCuenta);
        Cuenta response = null;

        if (cuentaEntity.isPresent()) {
            response = ObjectMapperUtils.map(cuentaEntity, Cuenta.class);
        }

        return response;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cuenta> buscarTodasCuentas(int page, int size) {
        List<Cuenta> cuentas = new ArrayList<>();
        Pageable paging = PageRequest.of(page, size);
        Page<CuentaEntity> response = cuentaRepository.findAll(paging);
        if (response.hasContent()) {
            cuentas = ObjectMapperUtils.mapAll(response.getContent(), Cuenta.class);
            return cuentas;
        } else {
            return cuentas;
        }
    }

    @Transactional
    @Override
    public Cuenta modificarCuenta(Cuenta cuenta) {
        CuentaEntity cuentaEntity = ObjectMapperUtils.map(cuenta, CuentaEntity.class);
        return ObjectMapperUtils.map(cuentaRepository.saveAndFlush(cuentaEntity), Cuenta.class);
    }

    @Transactional
    @Override
    public Cuenta baja(String numeroCuenta) {
        Optional<CuentaEntity> cuentaEntity = cuentaRepository.buscarPorNumero(numeroCuenta);
        return ObjectMapperUtils.map(cuentaRepository.saveAndFlush(cuentaEntity.get()), Cuenta.class);
    }

}
