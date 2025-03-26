package br.com.andersonalves.sop_api.modules.despesa.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.andersonalves.sop_api.modules.despesa.entities.DespesaEntity;

public interface DespesaRepository extends JpaRepository<DespesaEntity, UUID> {

    @Query("SELECT d.numeroProtocolo FROM DespesaEntity d WHERE d.numeroProtocolo LIKE %:yearMonth ORDER BY d.numeroProtocolo DESC LIMIT 1")
    Optional<String> findLastNumeroProtocolo(@Param("yearMonth") String yearMonth);

}
