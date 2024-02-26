package com.heliant.springproject.izuzeci;

public class NevalidanTokenIzuzetak extends RuntimeException{

    public NevalidanTokenIzuzetak() {
    }

    public NevalidanTokenIzuzetak(String message) {
        super(message);
    }

    public NevalidanTokenIzuzetak(String message, Throwable cause) {
        super(message, cause);
    }

    public NevalidanTokenIzuzetak(Throwable cause) {
        super(cause);
    }

    public NevalidanTokenIzuzetak(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
