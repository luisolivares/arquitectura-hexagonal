package com.challange.api.rest.banco.infrastructure.repository;

import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.Cuenta;
import com.challange.api.rest.banco.dominio.model.Tarjeta;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;
import com.challange.api.rest.banco.dominio.ports.out.ClienteRepositoryPort;
import com.challange.api.rest.banco.infrastructure.entity.ClienteEntity;
import com.challange.api.rest.banco.infrastructure.entity.CuentaEntity;
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
import java.util.Set;

@Log4j2
@Component
@RequiredArgsConstructor
public class JPAClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteRepository clienteRepository;

    @Transactional
    @Override
    public Cliente alta(Cliente cliente) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.buscarNumeroDocumento(cliente.getNumeroDocumento());
        if (clienteEntity.isPresent()) {
            return ObjectMapperUtils.map(clienteEntity, Cliente.class);
        } else {
            ClienteEntity entity = ObjectMapperUtils.map(cliente, ClienteEntity.class);
            return ObjectMapperUtils.map(clienteRepository.saveAndFlush(entity), Cliente.class);
        }
    }

    @Transactional
    @Override
    public Cliente baja(TipoDocumento tipoDocumento, String numeroDocumento) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.buscarTipoYNumeroDocumento(tipoDocumento, numeroDocumento);
        Cliente cliente = null;

        if (clienteEntity.isPresent()) {
            clienteEntity.get().setEstado(false);
            cliente = ObjectMapperUtils.map(clienteRepository.saveAndFlush(clienteEntity.get()), Cliente.class);
        }

        return cliente;
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente buscarPorTipoYNumeroDocumento(TipoDocumento tipoDocumento, String documento) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.buscarTipoYNumeroDocumento(tipoDocumento, documento);
        Cliente cliente = null;
        if (clienteEntity.isPresent()) {
            cliente = ObjectMapperUtils.map(clienteEntity, Cliente.class);
        }
        return cliente;
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente buscarPorNumeroDocumento(String documento) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.buscarNumeroDocumento(documento);
        Cliente cliente = null;
        if (clienteEntity.isPresent()) {
            cliente = ObjectMapperUtils.map(clienteEntity, Cliente.class);
        }
        return cliente;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cliente> listado(int pagina, int tamanio) {
        List<Cliente> clientes = new ArrayList<>();
        Pageable paging = PageRequest.of(pagina, tamanio);
        Page<ClienteEntity> response = clienteRepository.findAll(paging);

        if (response.hasContent()) {
            clientes = ObjectMapperUtils.mapAll(response.getContent(), Cliente.class);
            return clientes;
        } else {
            return clientes;
        }
    }

    @Transactional
    @Override
    public Cliente modificarCliente(Cliente cliente) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.buscarTipoYNumeroDocumento(cliente.getTipoDocumento(), cliente.getNumeroDocumento());
        if (clienteEntity.isPresent()) {
            clienteEntity.get().setNombres(cliente.getNombres());
            clienteEntity.get().setApellidos(cliente.getApellidos());
            clienteEntity.get().setEmail(cliente.getEmail());
            clienteEntity.get().setTipoDocumento(cliente.getTipoDocumento());
            clienteEntity.get().setEstado(cliente.getEstado());
            clienteEntity.get().setGenero(cliente.getGenero());
            return ObjectMapperUtils.map(clienteRepository.saveAndFlush(clienteEntity.get()), Cliente.class);
        } else {
            return null;
        }
    }

    @Override
    public Cliente asociarClienteTarjeta(Cliente cliente, Tarjeta tarjeta) {

        Optional<Cliente> clienteOptional = Optional.ofNullable(cliente);
        Optional<Tarjeta> tarjetaOptional = Optional.ofNullable(tarjeta);

        if (clienteOptional.isPresent() && tarjetaOptional.isPresent()) {

            TarjetaEntity tarjetaEntity = ObjectMapperUtils.map(tarjetaOptional.get(), TarjetaEntity.class);
            ClienteEntity clienteEntity = ObjectMapperUtils.map(cliente, ClienteEntity.class);

            Set<TarjetaEntity> tarjetas = Set.of(tarjetaEntity);
            clienteEntity.setTarjetas(tarjetas);

            return ObjectMapperUtils.map(clienteRepository.saveAndFlush(clienteEntity), Cliente.class);

        } else {
            return null;
        }

    }

    @Override
    public Cliente asociarClienteCuenta(Cliente cliente, Cuenta cuenta) {

        Optional<Cliente> clienteOptional = Optional.ofNullable(cliente);
        Optional<Cuenta> cuentaOptional = Optional.ofNullable(cuenta);

        if (clienteOptional.isPresent() && cuentaOptional.isPresent()) {

            CuentaEntity cuentaEntity = ObjectMapperUtils.map(cuentaOptional.get(), CuentaEntity.class);
            ClienteEntity clienteEntity = ObjectMapperUtils.map(cliente, ClienteEntity.class);

            Set<CuentaEntity> cuentas = Set.of(cuentaEntity);
            clienteEntity.setCuentas(cuentas);

            return ObjectMapperUtils.map(clienteRepository.saveAndFlush(clienteEntity), Cliente.class);

        } else {
            return null;

        }

    }
}
