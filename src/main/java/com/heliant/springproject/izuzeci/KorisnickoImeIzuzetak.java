package com.heliant.springproject.izuzeci;

public class KorisnickoImeIzuzetak extends RuntimeException{

    public KorisnickoImeIzuzetak() {
    }

    public KorisnickoImeIzuzetak(String message) {
        super(message);
    }

    public KorisnickoImeIzuzetak(String message, Throwable cause) {
        super(message, cause);
    }

    public KorisnickoImeIzuzetak(Throwable cause) {
        super(cause);
    }

    public KorisnickoImeIzuzetak(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
