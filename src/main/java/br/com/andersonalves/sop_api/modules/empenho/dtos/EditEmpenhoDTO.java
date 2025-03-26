package br.com.andersonalves.sop_api.modules.empenho.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record EditEmpenhoDTO(
        @NotNull LocalDate dataEmpenho,
        String observacao) {
}
