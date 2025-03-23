package br.com.andersonalves.sop_api.modules.empenho.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.modules.empenho.dtos.CreateEmpenhoDTO;
import br.com.andersonalves.sop_api.modules.empenho.entities.EmpenhoEntity;
import br.com.andersonalves.sop_api.modules.empenho.repository.EmpenhoRepository;

@Service
public class CreateEmpenhoService {

    @Autowired
    private EmpenhoRepository empenhoRepository;

    public EmpenhoEntity execute(CreateEmpenhoDTO empenhoDto) {

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
