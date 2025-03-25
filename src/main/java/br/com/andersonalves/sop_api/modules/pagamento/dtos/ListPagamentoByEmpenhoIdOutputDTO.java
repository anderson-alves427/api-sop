package br.com.andersonalves.sop_api.modules.pagamento.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListPagamentoByEmpenhoIdOutputDTO {
    private UUID id;
    private String numeroPagamento;
    private LocalDate dataPagamento;
    private BigDecimal valorPagamento;
    private String observacao;

}
