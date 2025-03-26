package br.com.andersonalves.sop_api.modules.empenho.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.modules.empenho.dtos.EditEmpenhoDTO;
import br.com.andersonalves.sop_api.modules.empenho.entities.EmpenhoEntity;
import br.com.andersonalves.sop_api.modules.empenho.repository.EmpenhoRepository;

@Service
public class EditEmpenhoService {

    @Autowired
    private EmpenhoRepository empenhoRepository;

    public EmpenhoEntity execute(UUID id, EditEmpenhoDTO dto) {
        EmpenhoEntity empenho = empenhoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Empenho não encontrado"));

        empenho.setDataEmpenho(dto.dataEmpenho());
        empenho.setObservacao(dto.observacao());

        empenhoRepository.save(empenho);
        return empenho;
    }
}
