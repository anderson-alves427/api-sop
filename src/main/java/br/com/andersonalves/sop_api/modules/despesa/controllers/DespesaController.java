package br.com.andersonalves.sop_api.modules.despesa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andersonalves.sop_api.modules.despesa.entities.DespesaEntity;
import br.com.andersonalves.sop_api.modules.despesa.repositories.DespesaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/despesa")
public class DespesaController {

    @Autowired
    private DespesaRepository despesaRepository;

    @PostMapping("")
    public void create(@Valid @RequestBody DespesaEntity despesaEntity) {
        this.despesaRepository.save(despesaEntity);
    }
}
