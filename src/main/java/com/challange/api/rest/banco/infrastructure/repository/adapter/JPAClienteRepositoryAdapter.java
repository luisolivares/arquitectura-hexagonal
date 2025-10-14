package com.challange.api.rest.banco.infrastructure.repository.adapter;

import com.challange.api.rest.banco.dominio.model.Cliente;
import com.challange.api.rest.banco.dominio.model.Cuenta;
import com.challange.api.rest.banco.dominio.model.Tarjeta;
import com.challange.api.rest.banco.dominio.model.TipoDocumento;
import com.challange.api.rest.banco.dominio.ports.out.ClienteRepositoryPort;
import com.challange.api.rest.banco.infrastructure.entity.ClienteEntity;
import com.challange.api.rest.banco.infrastructure.entity.CuentaEntity;
import com.challange.api.rest.banco.infrastructure.entity.TarjetaEntity;
import com.challange.api.rest.banco.infrastructure.repository.ClienteRepository;
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
public class JPAClienteRepositoryAdapter implements ClienteRepositoryPort {

    private final ClienteRepository clienteRepository;

    @Transactional
    @Override
    public Cliente alta(Cliente cliente) {
        ClienteEntity entity = ObjectMapperUtils.map(cliente, ClienteEntity.class);
        return ObjectMapperUtils.map(clienteRepository.saveAndFlush(entity), Cliente.class);
    }

    @Transactional
    @Override
    public Cliente baja(TipoDocumento tipoDocumento, String numeroDocumento) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.buscarTipoYNumeroDocumento(tipoDocumento, numeroDocumento);
        return ObjectMapperUtils.map(clienteRepository.saveAndFlush(clienteEntity.get()), Cliente.class);
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
        return response.hasContent() ? ObjectMapperUtils.mapAll(response.getContent(), Cliente.class) : clientes;
    }

    @Transactional
    @Override
    public Cliente modificarCliente(Cliente cliente) {
        ClienteEntity clienteEntity = ObjectMapperUtils.map(cliente, ClienteEntity.class);
        return ObjectMapperUtils.map(clienteRepository.saveAndFlush(clienteEntity), Cliente.class);
    }

    @Override
    public Cliente asociarClienteTarjeta(Cliente cliente, Tarjeta tarjeta) {

        TarjetaEntity tarjetaEntity = ObjectMapperUtils.map(tarjeta, TarjetaEntity.class);
        ClienteEntity clienteEntity = ObjectMapperUtils.map(cliente, ClienteEntity.class);

        // Usa el método de utilidad de la entidad
        clienteEntity.addTarjeta(tarjetaEntity);

        return ObjectMapperUtils.map(clienteRepository.saveAndFlush(clienteEntity), Cliente.class);
    }

    @Override
    public Cliente asociarClienteCuenta(Cliente cliente, Cuenta cuenta) {

        ClienteEntity clienteEntity = ObjectMapperUtils.map(cliente, ClienteEntity.class);
        CuentaEntity cuentaEntity = ObjectMapperUtils.map(cuenta, CuentaEntity.class);

        // Usa el método de utilidad de la entidad
        clienteEntity.addCuenta(cuentaEntity);

        return ObjectMapperUtils.map(clienteRepository.saveAndFlush(clienteEntity), Cliente.class);

    }
}
