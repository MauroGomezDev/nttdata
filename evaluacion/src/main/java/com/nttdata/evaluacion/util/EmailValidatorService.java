package com.nttdata.evaluacion.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailValidatorService {

    /**
     * Se crea esta clase para dejar la expresion regular de validacion de email configurable
     * desde el archivo application.properties
     */
    @Value("${email.regex}")
    private String emailRegex;

    public boolean isValidEmail(String email) {
        return email.matches(emailRegex);
    }
}
