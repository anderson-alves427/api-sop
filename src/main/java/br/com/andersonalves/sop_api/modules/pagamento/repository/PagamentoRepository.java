package br.com.andersonalves.sop_api.modules.pagamento.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.andersonalves.sop_api.modules.pagamento.entity.PagamentoEntity;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, UUID> {

    @Query("SELECT COUNT(p) FROM PagamentoEntity p WHERE EXTRACT(YEAR FROM p.dataPagamento) = :ano")
    long countByAno(@Param("ano") int ano);

    boolean existsByEmpenhoId(UUID empenhoId);

    @Query("SELECT COALESCE(SUM(p.valorPagamento), 0) FROM PagamentoEntity p WHERE p.empenhoId = :empenhoId")
    BigDecimal sumPagamentoValuesByEmpenho(@Param("empenhoId") UUID empenhoId);

    List<PagamentoEntity> findAllByEmpenhoId(UUID empenhoId);
}
