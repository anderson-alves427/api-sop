package br.com.andersonalves.sop_api.modules.despesa.dtos;

import java.time.LocalDate;
import java.math.BigDecimal;
import br.com.andersonalves.sop_api.modules.despesa.enums.TipoDespesa;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditDespesaDTO(
                @NotNull LocalDate dataVencimento, @NotBlank String credor,
                @NotNull @DecimalMin(value = "0.01", message = "O valor da despesa deve ser maior que zero.") BigDecimal valor,
                TipoDespesa tipoDespesa) {
}
