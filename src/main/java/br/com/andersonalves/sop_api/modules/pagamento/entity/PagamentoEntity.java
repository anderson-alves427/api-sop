package br.com.andersonalves.sop_api.modules.pagamento.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import br.com.andersonalves.sop_api.modules.empenho.entities.EmpenhoEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "pagamento", uniqueConstraints = {
        @UniqueConstraint(columnNames = "numeroPagamento", name = "uniqueNumeroPagamento")
})
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Pattern(regexp = "^\\d{4}NE\\d{4}$", message = "O número do pagamento deve seguir o formato YYYYNE0000")
    @Column(nullable = false, unique = true, length = 12)
    private String numeroPagamento;

    @Column(nullable = false)
    private LocalDate dataPagamento;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valorPagamento;

    @Column(length = 500)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "empenho_id", insertable = false, updatable = false)
    private EmpenhoEntity empenhoEntity;

    @Column(name = "empenho_id", nullable = false)
    private UUID empenhoId;

    public PagamentoEntity(String numeroPagamento, LocalDate dataPagamento, BigDecimal valorPagamento,
            String observacao,
            UUID empenhoId) {
        this.numeroPagamento = numeroPagamento;
        this.dataPagamento = dataPagamento;
        this.valorPagamento = valorPagamento;
        this.observacao = observacao;
        this.empenhoId = empenhoId;
    }

}