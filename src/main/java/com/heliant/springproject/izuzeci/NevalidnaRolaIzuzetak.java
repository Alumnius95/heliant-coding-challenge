package com.heliant.springproject.izuzeci;

public class NevalidnaRolaIzuzetak extends RuntimeException{

    public NevalidnaRolaIzuzetak() {
    }

    public NevalidnaRolaIzuzetak(String message) {
        super(message);
    }

    public NevalidnaRolaIzuzetak(String message, Throwable cause) {
        super(message, cause);
    }

    public NevalidnaRolaIzuzetak(Throwable cause) {
        super(cause);
    }

    public NevalidnaRolaIzuzetak(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
