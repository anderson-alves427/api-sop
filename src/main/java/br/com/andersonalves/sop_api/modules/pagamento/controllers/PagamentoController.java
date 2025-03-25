package br.com.andersonalves.sop_api.modules.pagamento.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andersonalves.sop_api.modules.pagamento.dtos.CreatePagamentoDTO;
import br.com.andersonalves.sop_api.modules.pagamento.services.CreatePagamentoService;
import br.com.andersonalves.sop_api.modules.pagamento.services.DeletePagamentoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private CreatePagamentoService createPagamentoService;

    @Autowired
    private DeletePagamentoService deletePagamentoService;

    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody CreatePagamentoDTO dto) {
        try {
            var result = createPagamentoService.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> execute(@PathVariable UUID id) {
        try {
            deletePagamentoService.execute(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
