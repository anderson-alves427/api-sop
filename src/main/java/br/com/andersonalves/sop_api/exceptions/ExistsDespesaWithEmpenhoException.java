package br.com.andersonalves.sop_api.exceptions;

public class ExistsDespesaWithEmpenhoException extends RuntimeException {

    public ExistsDespesaWithEmpenhoException() {
        super("Não é possível excluir uma despesa com empenhos associados.");
    }
}
