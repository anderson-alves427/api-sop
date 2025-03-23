package br.com.andersonalves.sop_api.exceptions;

public class NumeroProtocoloFoundException extends RuntimeException {
    public NumeroProtocoloFoundException() {
        super("Número de protocolo já cadastrado!");
    }
}
