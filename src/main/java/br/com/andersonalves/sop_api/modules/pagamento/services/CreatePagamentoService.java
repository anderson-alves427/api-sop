package br.com.andersonalves.sop_api.modules.pagamento.services;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.modules.empenho.repository.EmpenhoRepository;
import br.com.andersonalves.sop_api.modules.pagamento.dtos.CreatePagamentoDTO;
import br.com.andersonalves.sop_api.modules.pagamento.entity.PagamentoEntity;
import br.com.andersonalves.sop_api.modules.pagamento.repository.PagamentoRepository;

@Service
public class CreatePagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private EmpenhoRepository empenhoRepository;

    public PagamentoEntity execute(CreatePagamentoDTO pagamentoDto) {

        var empenho = this.empenhoRepository.findById(pagamentoDto.empenhoId());

        if (empenho.isEmpty()) {
            throw new IllegalStateException("Empenho não encontrado");
        }

        BigDecimal totalPagamento = pagamentoRepository.sumPagamentoValuesByEmpenho(empenho.get().getId());

        if (totalPagamento.add(pagamentoDto.valorPagamento()).compareTo(empenho.get().getValorEmpenho()) > 0) {
            throw new IllegalStateException("O valor do pagamento ultrapassa o valor do empenho associado.");
        }

        String numeroPagamento = this.generateNumeroPagamento(pagamentoDto.dataPagamento());

        PagamentoEntity pagamento = new PagamentoEntity(numeroPagamento, pagamentoDto.dataPagamento(),
                pagamentoDto.valorPagamento(), pagamentoDto.observacao(), pagamentoDto.empenhoId());

        return this.pagamentoRepository.save(pagamento);
    }

    private String generateNumeroPagamento(LocalDate dataPagamento) {
        int ano = dataPagamento.getYear();
        long sequencial = pagamentoRepository.countByAno(ano) + 1;
        return String.format("%dNP%04d", ano, sequencial);
    }
}
