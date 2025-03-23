package br.com.andersonalves.sop_api.modules.empenho.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andersonalves.sop_api.modules.empenho.entities.EmpenhoEntity;

public interface EmpenhoRepository extends JpaRepository<EmpenhoEntity, UUID> {
}
