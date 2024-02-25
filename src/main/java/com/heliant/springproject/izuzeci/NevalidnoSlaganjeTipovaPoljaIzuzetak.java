package com.heliant.springproject.izuzeci;

public class NevalidnoSlaganjeTipovaPoljaIzuzetak extends RuntimeException{

    public NevalidnoSlaganjeTipovaPoljaIzuzetak() {
    }

    public NevalidnoSlaganjeTipovaPoljaIzuzetak(String message) {
        super(message);
    }

    public NevalidnoSlaganjeTipovaPoljaIzuzetak(String message, Throwable cause) {
        super(message, cause);
    }

    public NevalidnoSlaganjeTipovaPoljaIzuzetak(Throwable cause) {
        super(cause);
    }

    public NevalidnoSlaganjeTipovaPoljaIzuzetak(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
