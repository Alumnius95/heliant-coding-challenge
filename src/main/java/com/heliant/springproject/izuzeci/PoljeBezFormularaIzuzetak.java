package com.heliant.springproject.izuzeci;

public class PoljeBezFormularaIzuzetak extends RuntimeException{

    public PoljeBezFormularaIzuzetak() {
    }

    public PoljeBezFormularaIzuzetak(String message) {
        super(message);
    }

    public PoljeBezFormularaIzuzetak(String message, Throwable cause) {
        super(message, cause);
    }

    public PoljeBezFormularaIzuzetak(Throwable cause) {
        super(cause);
    }

    public PoljeBezFormularaIzuzetak(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
