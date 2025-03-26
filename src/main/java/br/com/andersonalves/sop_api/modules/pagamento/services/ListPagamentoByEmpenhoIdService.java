package br.com.andersonalves.sop_api.modules.pagamento.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.modules.pagamento.dtos.ListPagamentoByEmpenhoIdOutputDTO;
import br.com.andersonalves.sop_api.modules.pagamento.entity.PagamentoEntity;
import br.com.andersonalves.sop_api.modules.pagamento.repository.PagamentoRepository;

@Service
public class ListPagamentoByEmpenhoIdService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<ListPagamentoByEmpenhoIdOutputDTO> execute(UUID empenhoId) {
        List<PagamentoEntity> pagamentos = this.pagamentoRepository.findAllByEmpenhoId(empenhoId);

        return pagamentos.stream()
                .map(emp -> {
                    ListPagamentoByEmpenhoIdOutputDTO dto = new ListPagamentoByEmpenhoIdOutputDTO();
                    dto.setId(emp.getId());
                    dto.setNumeroPagamento(emp.getNumeroPagamento());
                    dto.setDataPagamento(emp.getDataPagamento());
                    dto.setValorPagamento(emp.getValorPagamento());
                    dto.setObservacao(emp.getObservacao());
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
