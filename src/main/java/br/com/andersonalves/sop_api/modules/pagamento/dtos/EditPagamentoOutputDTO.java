package br.com.andersonalves.sop_api.modules.pagamento.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditPagamentoOutputDTO {
    private UUID id;
    private LocalDate dataPagamento;
    private BigDecimal valorPagamento;
    private String observacao;
    private String numeroPagamento;

}
