package br.com.andersonalves.sop_api.modules.empenho.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateEmpenhoDTO(

                @NotNull(message = "A data do empenho é obrigatória") LocalDate dataEmpenho,

                @NotNull(message = "O valor do empenho é obrigatório") @DecimalMin(value = "0.01", message = "O valor do empenho deve ser maior que zero") BigDecimal valorEmpenho,

                @Size(max = 500, message = "A observação pode ter no máximo 500 caracteres") String observacao

) {
}
