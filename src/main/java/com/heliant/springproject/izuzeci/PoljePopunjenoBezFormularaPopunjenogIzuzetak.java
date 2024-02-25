package com.heliant.springproject.izuzeci;

public class PoljePopunjenoBezFormularaPopunjenogIzuzetak extends RuntimeException{

    public PoljePopunjenoBezFormularaPopunjenogIzuzetak() {
    }

    public PoljePopunjenoBezFormularaPopunjenogIzuzetak(String message) {
        super(message);
    }

    public PoljePopunjenoBezFormularaPopunjenogIzuzetak(String message, Throwable cause) {
        super(message, cause);
    }

    public PoljePopunjenoBezFormularaPopunjenogIzuzetak(Throwable cause) {
        super(cause);
    }

    public PoljePopunjenoBezFormularaPopunjenogIzuzetak(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
