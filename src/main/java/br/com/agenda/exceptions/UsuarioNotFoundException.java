package br.com.agenda.exceptions;

import org.springframework.stereotype.Component;

@Component
public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String message) {
        super(message);
    }
}
