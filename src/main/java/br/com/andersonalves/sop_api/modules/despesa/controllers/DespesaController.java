package br.com.andersonalves.sop_api.modules.despesa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andersonalves.sop_api.modules.despesa.entities.DespesaEntity;
import br.com.andersonalves.sop_api.modules.despesa.services.CreateDespesaService;
import br.com.andersonalves.sop_api.modules.despesa.services.ListAllDespesaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/despesa")
public class DespesaController {

    @Autowired
    private CreateDespesaService createDespesaService;

    @Autowired
    private ListAllDespesaService listAllDespesaService;

    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody DespesaEntity despesaEntity) {
        try {
            var result = createDespesaService.execute(despesaEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<DespesaEntity>> listAllDespesa() {
        List<DespesaEntity> despesas = this.listAllDespesaService.execute();
        return ResponseEntity.ok(despesas);
    }

}
