package com.challange.api.rest.banco.infrastructure.repository;

import com.challange.api.rest.banco.infrastructure.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long>, JpaSpecificationExecutor<MovimientoEntity> {

    @Query("SELECT m FROM Movimiento m WHERE m.idMovimiento = :idMovimiento")
    Optional<MovimientoEntity> buscarPorId(@Param("idMovimiento") Long idMovimiento);

    @Query("SELECT m FROM Movimiento m WHERE m.referenciaOperacion = :referenciaOperacion")
    Optional<MovimientoEntity> buscarPorReferencia(@Param("referenciaOperacion") String referenciaOperacion);

}
