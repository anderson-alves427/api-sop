package br.com.andersonalves.sop_api.modules.empenho.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andersonalves.sop_api.modules.empenho.dtos.CreateEmpenhoDTO;
import br.com.andersonalves.sop_api.modules.empenho.dtos.EditEmpenhoDTO;
import br.com.andersonalves.sop_api.modules.empenho.dtos.ListEmpenhoByDespesaIdOutputDTO;
import br.com.andersonalves.sop_api.modules.empenho.entities.EmpenhoEntity;
import br.com.andersonalves.sop_api.modules.empenho.services.CreateEmpenhoService;
import br.com.andersonalves.sop_api.modules.empenho.services.DeleteEmpenhoService;
import br.com.andersonalves.sop_api.modules.empenho.services.EditEmpenhoService;
import br.com.andersonalves.sop_api.modules.empenho.services.ListEmpenhoByIdService;
import br.com.andersonalves.sop_api.modules.pagamento.dtos.EditPagamentoDTO;
import br.com.andersonalves.sop_api.modules.pagamento.entity.PagamentoEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/empenho")
public class EmpenhoController {

    @Autowired
    private CreateEmpenhoService createEmpenhoService;

    @Autowired
    private DeleteEmpenhoService deleteEmpenhoService;

    @Autowired
    private ListEmpenhoByIdService listEmpenhoByIdService;

    @Autowired
    private EditEmpenhoService editEmpenhoService;

    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateEmpenhoDTO dto) {
        try {
            var result = createEmpenhoService.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<List<ListEmpenhoByDespesaIdOutputDTO>> listEmpenhoByDespesaId(@PathVariable UUID id) {
        List<ListEmpenhoByDespesaIdOutputDTO> empenhos = this.listEmpenhoByIdService.execute(id);
        return ResponseEntity.ok(empenhos);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> edit(@PathVariable UUID id, @RequestBody @Valid EditEmpenhoDTO dto) {
        try {
            EmpenhoEntity empenho = editEmpenhoService.execute(id, dto);
            return ResponseEntity.ok(empenho);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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
