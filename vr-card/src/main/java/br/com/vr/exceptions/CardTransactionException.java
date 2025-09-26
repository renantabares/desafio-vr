package br.com.vr.exceptions;

public class CardTransactionException extends RuntimeException {
    public CardTransactionException(String message) {
        super(message);
    }
}
