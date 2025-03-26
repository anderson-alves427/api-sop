package br.com.andersonalves.sop_api.modules.pagamento.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.modules.pagamento.dtos.EditPagamentoDTO;
import br.com.andersonalves.sop_api.modules.pagamento.entity.PagamentoEntity;
import br.com.andersonalves.sop_api.modules.pagamento.repository.PagamentoRepository;

@Service
public class EditPagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public PagamentoEntity execute(UUID id, EditPagamentoDTO dto) {
        PagamentoEntity pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        pagamento.setDataPagamento(dto.dataPagamento());
        pagamento.setObservacao(dto.observacao());

        pagamento = pagamentoRepository.save(pagamento);
        return pagamento;
    }
}
