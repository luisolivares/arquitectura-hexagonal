package com.challange.api.rest.banco.infrastructure.repository;

import com.challange.api.rest.banco.infrastructure.entity.TarjetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarjetaRepository extends JpaRepository<TarjetaEntity, Long> {

    @Query("SELECT t FROM Tarjeta t WHERE t.numeroTarjeta = :numeroTarjeta")
    Optional<TarjetaEntity> buscarPorNumeroTarjeta(@Param("numeroTarjeta") String numeroTarjeta);


}
