package com.heliant.springproject.izuzeci;

public class PoljePopunjenoBezPoljaIzuzetak extends RuntimeException{

    public PoljePopunjenoBezPoljaIzuzetak() {
    }

    public PoljePopunjenoBezPoljaIzuzetak(String message) {
        super(message);
    }

    public PoljePopunjenoBezPoljaIzuzetak(String message, Throwable cause) {
        super(message, cause);
    }

    public PoljePopunjenoBezPoljaIzuzetak(Throwable cause) {
        super(cause);
    }

    public PoljePopunjenoBezPoljaIzuzetak(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
