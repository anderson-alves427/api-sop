package br.com.andersonalves.sop_api.modules.despesa.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.exceptions.ExistsDespesaWithEmpenhoException;
import br.com.andersonalves.sop_api.modules.despesa.repository.DespesaRepository;
import br.com.andersonalves.sop_api.modules.empenho.repository.EmpenhoRepository;

@Service
public class DeleteDespesaService {

    @Autowired
    private EmpenhoRepository empenhoRepository;

    @Autowired
    DespesaRepository despesaRepository;

    public void execute(UUID id) {

        System.out.println(empenhoRepository.existsByDespesaId(id));

        if (empenhoRepository.existsByDespesaId(id)) {
            throw new ExistsDespesaWithEmpenhoException();
        }

        despesaRepository.deleteById(id);
    }
}