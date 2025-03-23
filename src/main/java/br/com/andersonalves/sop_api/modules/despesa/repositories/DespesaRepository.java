package br.com.andersonalves.sop_api.modules.despesa.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andersonalves.sop_api.modules.despesa.entities.DespesaEntity;

public interface DespesaRepository extends JpaRepository<DespesaEntity, UUID> {

}
