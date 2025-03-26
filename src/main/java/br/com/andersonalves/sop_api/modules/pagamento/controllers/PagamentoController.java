package br.com.andersonalves.sop_api.modules.pagamento.controllers;

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

import br.com.andersonalves.sop_api.modules.despesa.dtos.EditDespesaDTO;
import br.com.andersonalves.sop_api.modules.despesa.entities.DespesaEntity;
import br.com.andersonalves.sop_api.modules.empenho.dtos.ListEmpenhoByDespesaIdOutputDTO;
import br.com.andersonalves.sop_api.modules.pagamento.dtos.CreatePagamentoDTO;
import br.com.andersonalves.sop_api.modules.pagamento.dtos.EditPagamentoDTO;
import br.com.andersonalves.sop_api.modules.pagamento.dtos.ListPagamentoByEmpenhoIdOutputDTO;
import br.com.andersonalves.sop_api.modules.pagamento.entity.PagamentoEntity;
import br.com.andersonalves.sop_api.modules.pagamento.services.CreatePagamentoService;
import br.com.andersonalves.sop_api.modules.pagamento.services.DeletePagamentoService;
import br.com.andersonalves.sop_api.modules.pagamento.services.EditPagamentoService;
import br.com.andersonalves.sop_api.modules.pagamento.services.ListPagamentoByEmpenhoIdService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private CreatePagamentoService createPagamentoService;

    @Autowired
    private DeletePagamentoService deletePagamentoService;

    @Autowired
    private ListPagamentoByEmpenhoIdService listPagamentoByEmpenhoIdService;

    @Autowired
    private EditPagamentoService editPagamentoService;

    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody CreatePagamentoDTO dto) {
        try {
            var result = createPagamentoService.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<List<ListPagamentoByEmpenhoIdOutputDTO>> listPagamentoByEmpenhoID(@PathVariable UUID id) {
        List<ListPagamentoByEmpenhoIdOutputDTO> pagamentos = this.listPagamentoByEmpenhoIdService.execute(id);
        return ResponseEntity.ok(pagamentos);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> edit(@PathVariable UUID id, @RequestBody @Valid EditPagamentoDTO dto) {
        try {
            PagamentoEntity pagamento = editPagamentoService.execute(id, dto);
            return ResponseEntity.ok(pagamento);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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
