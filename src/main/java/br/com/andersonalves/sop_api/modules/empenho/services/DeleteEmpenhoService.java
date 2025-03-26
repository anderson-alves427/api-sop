package br.com.andersonalves.sop_api.modules.empenho.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.modules.empenho.repository.EmpenhoRepository;
import br.com.andersonalves.sop_api.modules.pagamento.repository.PagamentoRepository;

@Service
public class DeleteEmpenhoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private EmpenhoRepository empenhoRepository;

    public void execute(UUID id) {

        if (pagamentoRepository.existsByEmpenhoId(id)) {
            throw new IllegalStateException("Operção não permitida! Existe pagamento associado!");

        }

        empenhoRepository.deleteById(id);
    }
}
