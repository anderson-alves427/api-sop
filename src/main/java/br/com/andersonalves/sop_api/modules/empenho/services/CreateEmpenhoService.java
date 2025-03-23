package br.com.andersonalves.sop_api.modules.empenho.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.modules.empenho.entities.EmpenhoEntity;
import br.com.andersonalves.sop_api.modules.empenho.repository.EmpenhoRepository;

@Service
public class CreateEmpenhoService {

    @Autowired
    private EmpenhoRepository empenhoRepository;

    public EmpenhoEntity execute(EmpenhoEntity empenhoEntity) {

        return this.empenhoRepository.save(empenhoEntity);
    }
}
