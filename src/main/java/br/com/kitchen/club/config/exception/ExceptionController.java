package br.com.kitchen.club.config.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handlerAll(Exception ex, WebRequest request) throws IllegalAccessException {
        ApiError apiError;

        switch (ex) {
            case ServiceException e ->
                    apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getMessage());
            case ParametroException e ->
                    apiError = new ApiError(HttpStatus.valueOf(406), e.getMessage(), "FALHA NOS PARÂMETROS FORNECIDOS");
            default -> throw new IllegalAccessException("ERRO CRÍTICO: " + ex.getMessage());
        }
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
