package br.com.andersonalves.sop_api.modules.empenho.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record EditEmpenhoDTO(
                @NotNull(message = "Data obrigatória no formato YYYY-MM-DD") LocalDate dataEmpenho,
                String observacao) {
}
