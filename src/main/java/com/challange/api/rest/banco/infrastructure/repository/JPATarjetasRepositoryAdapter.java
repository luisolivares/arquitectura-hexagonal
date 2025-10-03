package com.challange.api.rest.banco.infrastructure.repository;

import com.challange.api.rest.banco.dominio.model.Tarjeta;
import com.challange.api.rest.banco.dominio.ports.out.TarjetaRepositoryPort;
import com.challange.api.rest.banco.infrastructure.entity.ClienteEntity;
import com.challange.api.rest.banco.infrastructure.entity.TarjetaEntity;
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
public class JPATarjetasRepositoryAdapter implements TarjetaRepositoryPort {

    private final TarjetaRepository tarjetaRepository;

    private final ClienteRepository clienteRepository;

    @Transactional
    @Override
    public Tarjeta alta(Tarjeta tarjeta) {

        Optional<TarjetaEntity> tarjetaEntity = tarjetaRepository.buscarPorNumeroTarjeta(tarjeta.getNumeroTarjeta());

        if (tarjetaEntity.isPresent()) {
            return ObjectMapperUtils.map(tarjetaEntity, Tarjeta.class);
        } else {
            TarjetaEntity entity = ObjectMapperUtils.map(tarjeta, TarjetaEntity.class);
            return ObjectMapperUtils.map(tarjetaRepository.saveAndFlush(entity), Tarjeta.class);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Tarjeta buscarPorNumeroTarjeta(String numeroTarjeta) {

        Optional<TarjetaEntity> tarjetaEntity = tarjetaRepository.buscarPorNumeroTarjeta(numeroTarjeta);

        if (tarjetaEntity.isPresent()) {
            return ObjectMapperUtils.map(tarjetaEntity, Tarjeta.class);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tarjeta> buscarTodasTarjetas(int page, int size) {

        List<Tarjeta> tarjetas = new ArrayList<>();
        Pageable paging = PageRequest.of(page, size);
        Page<TarjetaEntity> response = tarjetaRepository.findAll(paging);

        if (response.hasContent()) {
            tarjetas = ObjectMapperUtils.mapAll(response.getContent(), Tarjeta.class);
            return tarjetas;
        } else {
            return tarjetas;
        }
    }

    @Transactional
    @Override
    public Tarjeta modificarTarjeta(Tarjeta tarjeta) {

        Optional<TarjetaEntity> tarjetaEntity = tarjetaRepository.buscarPorNumeroTarjeta(tarjeta.getNumeroTarjeta());

        if (tarjetaEntity.isPresent()) {
            tarjetaEntity.get().setNumeroTarjeta(tarjeta.getNumeroTarjeta());
            tarjetaEntity.get().setFechaVencimiento(tarjeta.getFechaVencimiento());
            tarjetaEntity.get().setCvv(tarjeta.getCvv());
            tarjetaEntity.get().setTipoTarjeta(tarjeta.getTipoTarjeta());
            tarjetaEntity.get().setEmisorTarjeta(tarjeta.getEmisorTarjeta());
            tarjetaEntity.get().setActivo(tarjeta.isActivo());
            return ObjectMapperUtils.map(tarjetaRepository.saveAndFlush(tarjetaEntity.get()), Tarjeta.class);
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public Tarjeta baja(String numeroTarjeta) {
        Optional<TarjetaEntity> tarjetaEntity = tarjetaRepository.buscarPorNumeroTarjeta(numeroTarjeta);
        Tarjeta tarjeta = new Tarjeta();

        if (tarjetaEntity.isPresent()) {
            tarjetaEntity.get().setActivo(false);
            tarjeta = ObjectMapperUtils.map(tarjetaRepository.saveAndFlush(tarjetaEntity.get()), Tarjeta.class);
        }

        return tarjeta;
    }

    @Transactional
    @Override
    public Tarjeta asociarCliente(String numeroTarjeta, String numeroDocumento) {

        Tarjeta tarjeta = new Tarjeta();
        Optional<ClienteEntity> cliente = clienteRepository.buscarNumeroDocumento(numeroDocumento);
        Optional<TarjetaEntity> tarjetaEntity = tarjetaRepository.buscarPorNumeroTarjeta(numeroTarjeta);

        if (tarjetaEntity.isPresent() && cliente.isPresent()) {
            tarjetaEntity.get().setCliente(cliente.get());
            tarjetaRepository.saveAndFlush(tarjetaEntity.get());
            tarjeta = ObjectMapperUtils.map(tarjetaRepository.saveAndFlush(tarjetaEntity.get()), Tarjeta.class);
        }

        return tarjeta;

    }

}
