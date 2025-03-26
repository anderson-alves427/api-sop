package br.com.andersonalves.sop_api.modules.despesa.services;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.modules.despesa.dtos.EditDespesaDTO;
import br.com.andersonalves.sop_api.modules.despesa.entities.DespesaEntity;
import br.com.andersonalves.sop_api.modules.despesa.repository.DespesaRepository;
import br.com.andersonalves.sop_api.modules.empenho.repository.EmpenhoRepository;

@Service
public class EditDespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private EmpenhoRepository empenhoRepository;

    public DespesaEntity execute(UUID id, EditDespesaDTO dto) {
        DespesaEntity despesa = despesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));

        BigDecimal totalEmpenhado = empenhoRepository.somarValorEmpenhosPorDespesa(id);

        if (dto.valor().compareTo(totalEmpenhado) < 0) {
            throw new IllegalStateException(
                    "O valor da despesa não pode ser menor que o total empenhado (R$ " + totalEmpenhado + ").");
        }

        despesa.setDataVencimento(dto.dataVencimento());
        despesa.setCredor(dto.credor());
        despesa.setValor(dto.valor());
        despesa.setTipoDespesa(dto.tipoDespesa());
        despesa.setDescricao(dto.descricao());

        despesa = despesaRepository.save(despesa);
        return despesa;
    }
}
