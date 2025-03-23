package br.com.andersonalves.sop_api.modules.pagamento.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.modules.pagamento.repository.PagamentoRepository;

@Service
public class DeletePagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public void execute(UUID id) {

        var pagamento = pagamentoRepository.findById(id);

        if (pagamento.isEmpty()) {
            throw new IllegalStateException("O pagamento não existe");
        }

        pagamentoRepository.deleteById(id);
    }
}
