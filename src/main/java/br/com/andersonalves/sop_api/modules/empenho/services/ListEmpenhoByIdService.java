package br.com.andersonalves.sop_api.modules.empenho.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.modules.empenho.dtos.ListEmpenhoByDespesaIdOutputDTO;
import br.com.andersonalves.sop_api.modules.empenho.entities.EmpenhoEntity;
import br.com.andersonalves.sop_api.modules.empenho.repository.EmpenhoRepository;

@Service
public class ListEmpenhoByIdService {

    @Autowired
    private EmpenhoRepository empenhoRepository;

    public List<ListEmpenhoByDespesaIdOutputDTO> execute(UUID despesaId) {
        List<EmpenhoEntity> empenhos = this.empenhoRepository.findAllByDespesaId(despesaId);

        return empenhos.stream()
                .map(emp -> {
                    ListEmpenhoByDespesaIdOutputDTO dto = new ListEmpenhoByDespesaIdOutputDTO();
                    dto.setId(emp.getId());
                    dto.setNumeroEmpenho(emp.getNumeroEmpenho());
                    dto.setDataEmpenho(emp.getDataEmpenho());
                    dto.setValorEmpenho(emp.getValorEmpenho());
                    dto.setObservacao(emp.getObservacao());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
