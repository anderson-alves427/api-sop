package br.com.andersonalves.sop_api.modules.empenho.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andersonalves.sop_api.modules.empenho.dtos.CreateEmpenhoDTO;
import br.com.andersonalves.sop_api.modules.empenho.services.CreateEmpenhoService;
import br.com.andersonalves.sop_api.modules.empenho.services.DeleteEmpenhoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/empenho")
public class EmpenhoController {

    @Autowired
    private CreateEmpenhoService createEmpenhoService;

    @Autowired
    private DeleteEmpenhoService deleteEmpenhoService;

    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateEmpenhoDTO dto) {
        try {
            var result = createEmpenhoService.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try {
            deleteEmpenhoService.execute(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
