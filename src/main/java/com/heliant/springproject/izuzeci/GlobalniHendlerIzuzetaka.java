package com.heliant.springproject.izuzeci;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalniHendlerIzuzetaka {

    @ExceptionHandler(NevalidanTokenIzuzetak.class)
    public ResponseEntity<ErrorOdgovor>hendlujNevalidanTokenIzuzetak(NevalidanTokenIzuzetak nevalidanTokenIzuzetak) {
        ErrorOdgovor errorOdgovor = new ErrorOdgovor(nevalidanTokenIzuzetak.getMessage(), HttpStatus.UNAUTHORIZED, LocalDateTime.now());
        return new ResponseEntity<>(errorOdgovor, errorOdgovor.getHttpStatus());
    }

    @ExceptionHandler(KorisnickoImeIzuzetak.class)
    public ResponseEntity<ErrorOdgovor>hendlujNevalidanTokenIzuzetak(KorisnickoImeIzuzetak korisnickoImeIzuzetak) {
        ErrorOdgovor errorOdgovor = new ErrorOdgovor(korisnickoImeIzuzetak.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(errorOdgovor, errorOdgovor.getHttpStatus());
    }

    @ExceptionHandler(NevalidnaRolaIzuzetak.class)
    public ResponseEntity<ErrorOdgovor>hendlujNevalidanTokenIzuzetak(NevalidnaRolaIzuzetak nevalidnaRolaIzuzetak) {
        ErrorOdgovor errorOdgovor = new ErrorOdgovor(nevalidnaRolaIzuzetak.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(errorOdgovor, errorOdgovor.getHttpStatus());
    }

    @ExceptionHandler(PoljeBezFormularaIzuzetak.class)
    public ResponseEntity<ErrorOdgovor>hendlujNevalidanTokenIzuzetak(PoljeBezFormularaIzuzetak poljeBezFormularaIzuzetak) {
        ErrorOdgovor errorOdgovor = new ErrorOdgovor(poljeBezFormularaIzuzetak.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(errorOdgovor, errorOdgovor.getHttpStatus());
    }

    @ExceptionHandler(NevalidnoSlaganjeTipovaPoljaIzuzetak.class)
    public ResponseEntity<ErrorOdgovor>hendlujNevalidanTokenIzuzetak(NevalidnoSlaganjeTipovaPoljaIzuzetak poljeBezFormularaIzuzetak) {
        ErrorOdgovor errorOdgovor = new ErrorOdgovor(poljeBezFormularaIzuzetak.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(errorOdgovor, errorOdgovor.getHttpStatus());
    }

    @ExceptionHandler(FormularPopunjenIzuzetak.class)
    public ResponseEntity<ErrorOdgovor>hendlujNevalidanTokenIzuzetak(FormularPopunjenIzuzetak formularPopunjenIzuzetak) {
        ErrorOdgovor errorOdgovor = new ErrorOdgovor(formularPopunjenIzuzetak.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(errorOdgovor, errorOdgovor.getHttpStatus());
    }

}
