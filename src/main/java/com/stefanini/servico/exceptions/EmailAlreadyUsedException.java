package com.stefanini.servico.exceptions;

import javax.ws.rs.BadRequestException;

public class EmailAlreadyUsedException extends BadRequestException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException() {
        super("Email is already in use!");
    }
}
