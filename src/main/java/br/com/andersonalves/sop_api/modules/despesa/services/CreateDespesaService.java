package br.com.andersonalves.sop_api.modules.despesa.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonalves.sop_api.modules.despesa.dtos.CreateDespesaDTO;
import br.com.andersonalves.sop_api.modules.despesa.entities.DespesaEntity;
import br.com.andersonalves.sop_api.modules.despesa.repository.DespesaRepository;

@Service
public class CreateDespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public DespesaEntity execute(CreateDespesaDTO despesa) {
        String numeroProtocolo = generateNumeroProtocolo();

        var newDespesa = new DespesaEntity();

        newDespesa.setNumeroProtocolo(numeroProtocolo);
        newDespesa.setTipoDespesa(despesa.getTipoDespesa());
        newDespesa.setDataVencimento(despesa.getDataVencimento());
        newDespesa.setCredor(despesa.getCredor());
        newDespesa.setDescricao(despesa.getDescricao());
        newDespesa.setValor(despesa.getValor());

        return this.despesaRepository.save(newDespesa);
    }

    private String generateNumeroProtocolo() {
        LocalDateTime dataProtocolo = LocalDateTime.now();
        String year = String.valueOf(dataProtocolo.getYear());
        String month = String.format("%02d", dataProtocolo.getMonthValue());
        String yearMonth = year + "-" + month;

        int nextNumber = 1;

        Optional<String> lastNumberProtocol = despesaRepository.findLastNumeroProtocolo(yearMonth);
        if (lastNumberProtocol.isPresent()) {
            String last = lastNumberProtocol.get();
            String sequentialStr = last.substring(6, 12);
            nextNumber = Integer.parseInt(sequentialStr) + 1;
        }

        String formated = String.format("%06d", nextNumber);
        return "11111." + formated + "/" + yearMonth;
    }

}
