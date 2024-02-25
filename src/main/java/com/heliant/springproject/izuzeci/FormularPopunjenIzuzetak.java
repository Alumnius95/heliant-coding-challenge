package com.heliant.springproject.izuzeci;

public class FormularPopunjenIzuzetak extends RuntimeException{

    public FormularPopunjenIzuzetak() {
    }

    public FormularPopunjenIzuzetak(String message) {
        super(message);
    }

    public FormularPopunjenIzuzetak(String message, Throwable cause) {
        super(message, cause);
    }

    public FormularPopunjenIzuzetak(Throwable cause) {
        super(cause);
    }

    public FormularPopunjenIzuzetak(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
