package br.com.andersonalves.sop_api.modules.pagamento.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record EditPagamentoDTO(
        @NotNull LocalDate dataPagamento,
        String observacao) {
}
