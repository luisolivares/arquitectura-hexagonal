package com.challange.api.rest.banco.infrastructure.repository;

import com.challange.api.rest.banco.domain.model.TipoCuenta;
import com.challange.api.rest.banco.infrastructure.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {

    @Query("SELECT c FROM Cuenta c WHERE c.tipoCuenta = :tipoCuenta AND c.numeroCuenta = :numeroCuenta")
    Optional<CuentaEntity> buscarPorTipoYNumero(@Param("tipoCuenta") TipoCuenta tipoCuenta, @Param("numeroCuenta") String numeroCuenta);

    @Query("SELECT c FROM Cuenta c WHERE c.numeroCuenta = :numeroCuenta")
    Optional<CuentaEntity> buscarPorNumero(@Param("numeroCuenta") String numeroCuenta);

    @Query("UPDATE Cuenta c SET c.activo = :estado WHERE c.numeroCuenta = :numeroCuenta")
    Optional<CuentaEntity> bajaPorNumero(@Param("numeroCuenta") String numeroCuenta, @Param("estado") boolean estado);

}
