package br.com.andersonalves.sop_api.modules.empenho.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

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

        String numeroEmpenho = gerarNumeroEmpenho(empenhoDto.dataEmpenho());

        EmpenhoEntity empenho = new EmpenhoEntity(numeroEmpenho, empenhoDto.dataEmpenho(),
                empenhoDto.valorEmpenho(), empenhoDto.observacao(), empenhoDto.despesaId());

        System.out.println(empenho);
        return this.empenhoRepository.save(empenho);
    }

    private String gerarNumeroEmpenho(LocalDate dataEmpenho) {
        String anoEmpenho = String.valueOf(dataEmpenho.getYear());
        String prefixo = anoEmpenho + "NE";

        Optional<String> ultimoEmpenho = empenhoRepository.findUltimoNumeroEmpenho(prefixo);

        int proximoNumero = 1;
        if (ultimoEmpenho.isPresent()) {
            String ultimo = ultimoEmpenho.get();
            String sequencialStr = ultimo.substring(6);
            proximoNumero = Integer.parseInt(sequencialStr) + 1;
        }

        String sequencialFormatado = String.format("%04d", proximoNumero);
        return prefixo + sequencialFormatado;
    }
}
