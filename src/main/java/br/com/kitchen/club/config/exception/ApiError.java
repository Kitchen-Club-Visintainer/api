package br.com.kitchen.club.config.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiError {

    private HttpStatus status;
    private List<String> errors;
    private String message;

    public ApiError() {
    }

    public ApiError(HttpStatus status, List<String> errors, String message) {
        super();
        this.status = status;
        this.errors = errors;
        this.message = message;
    }

    public ApiError(HttpStatus status, String error, String message) {
        super();
        this.status = status;
        this.errors = List.of(error);
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
