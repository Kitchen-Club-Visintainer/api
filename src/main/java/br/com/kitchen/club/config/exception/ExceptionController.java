package br.com.kitchen.club.config.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handlerAll(Exception ex, WebRequest request) {
        ApiError apiError  = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex.getMessage());

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> serviceHandler(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_IMPLEMENTED, ex.getMessage(), ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(ParametroException.class)
    public ResponseEntity<?> paramHandler(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.valueOf(406), ex.getMessage(), "FALHA NOS PARÂMETROS FORNECIDOS");
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var erros = ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        var apiError = new ApiError(HttpStatus.valueOf(406), erros, "ERRO NA REQUISIÇÃO");
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

}
