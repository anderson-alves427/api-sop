package br.com.andersonalves.sop_api.modules.despesa.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andersonalves.sop_api.modules.despesa.entities.DespesaEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/despesa")
public class DespesaController {

    @PostMapping("")
    public void create(@Valid @RequestBody DespesaEntity despesaEntity) {
        System.out.println("Despesa:");
        System.out.println(despesaEntity.getDescricao());
    }
}
