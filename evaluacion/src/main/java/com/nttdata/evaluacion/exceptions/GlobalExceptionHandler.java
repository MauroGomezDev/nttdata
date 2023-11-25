package com.nttdata.evaluacion.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseException> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        log.error("Error al insertar usuario: " + ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseException(ex.getMessage()));
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ResponseException> handleInvalidDataException(InvalidDataException ex) {
        log.error("Datos Invalidos: " + ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ResponseException(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> handleGeneralException(Exception ex) {
        log.error("Error general al intentar crear un usuario: " + ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseException(ex.getMessage()));
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ResponseException> handleJsonProcessingException(JsonProcessingException ex) {
        log.error("Error interno del servidor al procesar JSON", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseException("Error interno del servidor al procesar JSON"));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseException> handleUserNotFoundException(UserNotFoundException ex) {
        log.error("Error al leer registro de usuario", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseException(ex.getMessage()));
    }
}
