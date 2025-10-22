package com.challange.api.rest.banco.infrastructure.repository;

import com.challange.api.rest.banco.domain.model.TipoDocumento;
import com.challange.api.rest.banco.infrastructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query(value = "SELECT c Cliente FROM Cliente c WHERE c.tipoDocumento = :tipoDocumento AND c.numeroDocumento = :numeroDocumento")
    Optional<ClienteEntity> buscarTipoYNumeroDocumento(@Param("tipoDocumento") TipoDocumento tipoDocumento, @Param("numeroDocumento") String numeroDocumento);

    @Query(value = "SELECT c Cliente FROM Cliente c WHERE c.numeroDocumento = :numeroDocumento")
    Optional<ClienteEntity> buscarNumeroDocumento(@Param("numeroDocumento") String numeroDocumento);

    @Query(value = "UPDATE Cliente c SET c.estado = :estado WHERE c.numeroDocumento = :numeroDocumento AND c.tipoDocumento = :tipoDocumento")
    Optional<ClienteEntity> bajaClientePorNumeroYTipoDocumento(@Param("tipoDocumento") TipoDocumento tipoDocumento, @Param("numeroDocumento") String numeroDocumento, @Param("estado") Boolean estado);

}
