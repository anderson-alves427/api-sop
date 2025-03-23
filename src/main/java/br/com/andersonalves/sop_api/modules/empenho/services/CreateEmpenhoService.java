package br.com.andersonalves.sop_api.modules.empenho.services;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.exceptions.DespesaNotFoundException;
import br.com.andersonalves.sop_api.modules.despesa.repository.DespesaRepository;
import br.com.andersonalves.sop_api.modules.empenho.dtos.CreateEmpenhoDTO;
import br.com.andersonalves.sop_api.modules.empenho.entities.EmpenhoEntity;
import br.com.andersonalves.sop_api.modules.empenho.repository.EmpenhoRepository;

@Service
public class CreateEmpenhoService {

    @Autowired
    private EmpenhoRepository empenhoRepository;

    @Autowired
    private DespesaRepository despesaRepository;

    public EmpenhoEntity execute(CreateEmpenhoDTO empenhoDto) {

        var despesa = this.despesaRepository.findById(empenhoDto.despesaId());

        if (despesa.isEmpty()) {
            throw new DespesaNotFoundException();
        }

        BigDecimal totalEmpenhado = empenhoRepository.somarValorEmpenhosPorDespesa(despesa.get().getId());

        if (totalEmpenhado.add(empenhoDto.valorEmpenho()).compareTo(despesa.get().getValor()) > 0) {
            throw new IllegalStateException("O valor do empenho ultrapassa o valor da despesa associada.");
        }

        String numeroEmpenho = this.generateNumeroEmpenho(empenhoDto.dataEmpenho());

        EmpenhoEntity empenho = new EmpenhoEntity(numeroEmpenho, empenhoDto.dataEmpenho(),
                empenhoDto.valorEmpenho(), empenhoDto.observacao(), empenhoDto.despesaId());

        return this.empenhoRepository.save(empenho);
    }

    private String generateNumeroEmpenho(LocalDate dataEmpenho) {
        int ano = dataEmpenho.getYear();
        long sequencial = empenhoRepository.countByAno(ano) + 1;
        return String.format("%dNE%04d", ano, sequencial);
    }
}
