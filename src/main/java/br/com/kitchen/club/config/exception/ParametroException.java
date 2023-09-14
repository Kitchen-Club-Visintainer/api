package br.com.kitchen.club.config.exception;

public class ParametroException extends RuntimeException {

    public ParametroException() {
    }

    public ParametroException(String message) {
        super(message);
    }

    public ParametroException(Exception exception) {
        super(exception);
    }

    public ParametroException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParametroException(Throwable cause) {
        super(cause);
    }

    public ParametroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
