package br.com.vr.exceptions;

public class CardAlreadyExistsException extends RuntimeException {
    public CardAlreadyExistsException(String message) {
        super(message);
    }
}
