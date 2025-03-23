package br.com.andersonalves.sop_api.modules.empenho.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.andersonalves.sop_api.modules.empenho.entities.EmpenhoEntity;

public interface EmpenhoRepository extends JpaRepository<EmpenhoEntity, UUID> {

    @Query("SELECT COUNT(e) FROM EmpenhoEntity e WHERE EXTRACT(YEAR FROM e.dataEmpenho) = :ano")
    long countByAno(@Param("ano") int ano);

}
