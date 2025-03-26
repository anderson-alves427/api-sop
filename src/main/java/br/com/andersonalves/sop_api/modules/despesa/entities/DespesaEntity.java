package br.com.andersonalves.sop_api.modules.despesa.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.andersonalves.sop_api.modules.despesa.enums.StatusDespesa;
import br.com.andersonalves.sop_api.modules.despesa.enums.TipoDespesa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "despesa", uniqueConstraints = {
        @UniqueConstraint(columnNames = "numeroProtocolo", name = "uniqueNumeroProtocolo")
})
public class DespesaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String numeroProtocolo;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Tipo de despesa é obrigatório")
    private TipoDespesa tipoDespesa;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataProtocolo;

    @NotNull(message = "Data de vencimento é obrigatória")
    private LocalDate dataVencimento;

    @NotNull(message = "Credor da despesa é obrigatório")
    private String credor;

    @NotNull(message = "Descrição da despesa é obrigatória")
    private String descricao;

    @NotNull(message = "Valor da despesa é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor da despesa deve ser maior que 0")
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDespesa status = StatusDespesa.AGUARDANDO_EMPENHO;

}
