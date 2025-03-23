package br.com.andersonalves.sop_api.modules.despesa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.exceptions.NumeroProtocoloFoundException;
import br.com.andersonalves.sop_api.modules.despesa.entities.DespesaEntity;
import br.com.andersonalves.sop_api.modules.despesa.repositories.DespesaRepository;

@Service
public class CreateDespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public DespesaEntity execute(DespesaEntity despesaEntity) {
        this.despesaRepository.findByNumeroProtocolo(despesaEntity.getNumeroProtocolo()).ifPresent(user -> {
            throw new NumeroProtocoloFoundException();
        });

        return this.despesaRepository.save(despesaEntity);
    }

}
