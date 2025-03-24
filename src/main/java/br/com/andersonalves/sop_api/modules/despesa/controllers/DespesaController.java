package br.com.andersonalves.sop_api.modules.despesa.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andersonalves.sop_api.modules.despesa.dtos.EditDespesaDTO;
import br.com.andersonalves.sop_api.modules.despesa.entities.DespesaEntity;
import br.com.andersonalves.sop_api.modules.despesa.services.CreateDespesaService;
import br.com.andersonalves.sop_api.modules.despesa.services.DeleteDespesaService;
import br.com.andersonalves.sop_api.modules.despesa.services.EditDespesaService;
import br.com.andersonalves.sop_api.modules.despesa.services.ListAllDespesaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/despesa")
public class DespesaController {

    @Autowired
    private CreateDespesaService createDespesaService;

    @Autowired
    private ListAllDespesaService listAllDespesaService;

    @Autowired
    private DeleteDespesaService deleteDespesaService;

    @Autowired
    private EditDespesaService editDespesaService;

    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody DespesaEntity despesaEntity) {
        try {
            var result = createDespesaService.execute(despesaEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> edit(@PathVariable UUID id, @RequestBody @Valid EditDespesaDTO dto) {
        try {
            DespesaEntity despesaAtualizada = editDespesaService.execute(id, dto);
            return ResponseEntity.ok(despesaAtualizada);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<DespesaEntity>> listAllDespesa() {
        List<DespesaEntity> despesas = this.listAllDespesaService.execute();
        return ResponseEntity.ok(despesas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDespesa(@PathVariable UUID id) {
        try {
            deleteDespesaService.execute(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
