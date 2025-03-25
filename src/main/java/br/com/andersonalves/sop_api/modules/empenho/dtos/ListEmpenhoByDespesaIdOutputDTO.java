package br.com.andersonalves.sop_api.modules.empenho.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListEmpenhoByDespesaIdOutputDTO {
    private UUID id;
    private String numeroEmpenho;
    private LocalDate dataEmpenho;
    private BigDecimal valorEmpenho;
    private String observacao;
}