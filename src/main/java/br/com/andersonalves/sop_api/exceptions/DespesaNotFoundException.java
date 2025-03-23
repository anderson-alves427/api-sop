package br.com.andersonalves.sop_api.exceptions;

public class DespesaNotFoundException extends RuntimeException {

    public DespesaNotFoundException() {
        super("Despesa não encontrada");
    }
}
