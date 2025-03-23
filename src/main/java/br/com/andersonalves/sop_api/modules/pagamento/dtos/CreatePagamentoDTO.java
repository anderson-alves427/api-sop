package br.com.andersonalves.sop_api.modules.pagamento.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePagamentoDTO(

        @NotNull(message = "A data do Pagamento é obrigatória") LocalDate dataPagamento,

        @NotNull(message = "O valor do Pagamento é obrigatório") @DecimalMin(value = "0.01", message = "O valor do Pagamento deve ser maior que zero") BigDecimal valorPagamento,

        @Size(max = 500, message = "A observação pode ter no máximo 500 caracteres") String observacao,

        @NotNull(message = "O id do empenho é obrigatório") UUID empenhoId

) {
}
