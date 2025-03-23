package br.com.andersonalves.sop_api.modules.empenho.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andersonalves.sop_api.modules.empenho.entities.EmpenhoEntity;
import br.com.andersonalves.sop_api.modules.empenho.services.CreateEmpenhoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/empenho")
public class EmpenhoController {

    @Autowired
    private CreateEmpenhoService createEmpenhoService;

    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody EmpenhoEntity empenhoEntity) {
        try {
            var result = this.createEmpenhoService.execute(empenhoEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
