package br.com.andersonalves.sop_api.modules.despesa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.modules.despesa.entities.DespesaEntity;
import br.com.andersonalves.sop_api.modules.despesa.repository.DespesaRepository;

@Service
public class ListAllDespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public List<DespesaEntity> execute() {
        return this.despesaRepository.findAll();
    }
}
