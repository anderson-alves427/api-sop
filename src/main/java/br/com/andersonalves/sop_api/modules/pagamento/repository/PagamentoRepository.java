package br.com.andersonalves.sop_api.modules.pagamento.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.andersonalves.sop_api.modules.pagamento.entity.PagamentoEntity;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, UUID> {

    @Query("SELECT e.numeroPagamento FROM PagamentoEntity e WHERE e.numeroPagamento LIKE :ano% ORDER BY e.numeroPagamento DESC LIMIT 1")
    Optional<String> findUltimoNumeroPagamento(@Param("ano") String ano);

    boolean existsByEmpenhoId(UUID empenhoId);

    @Query("SELECT COALESCE(SUM(p.valorPagamento), 0) FROM PagamentoEntity p WHERE p.empenhoId = :empenhoId")
    BigDecimal sumPagamentoValuesByEmpenho(@Param("empenhoId") UUID empenhoId);

    List<PagamentoEntity> findAllByEmpenhoId(UUID empenhoId);
}
