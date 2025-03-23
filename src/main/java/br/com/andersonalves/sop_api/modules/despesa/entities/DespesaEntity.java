package br.com.andersonalves.sop_api.modules.despesa.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.andersonalves.sop_api.modules.despesa.enums.StatusDespesa;
import br.com.andersonalves.sop_api.modules.despesa.enums.TipoDespesa;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "despesa")
public class DespesaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Pattern(regexp = "\\d{5}\\.\\d{6}/\\d{4}-\\d{2}", message = "Número de protocolo inválido. Exemplo: #####.######/####-##")
    private String numeroProtocolo;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Tipo de despesa é obrigatório")
    private TipoDespesa tipoDespesa;

    @NotNull(message = "Data do protocolo é obrigatória")
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
    private StatusDespesa status;

}
