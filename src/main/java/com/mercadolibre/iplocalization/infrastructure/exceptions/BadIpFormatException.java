package com.mercadolibre.iplocalization.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadIpFormatException extends Exception{

    private static final long serialVersionUID = 1L;

    public BadIpFormatException(String ip) {
        super(ip + " IP INVALIDA");
    }

}
