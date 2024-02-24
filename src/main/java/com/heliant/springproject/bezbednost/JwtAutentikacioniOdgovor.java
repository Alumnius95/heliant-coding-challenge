package com.heliant.springproject.bezbednost;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAutentikacioniOdgovor {

    private String pristupniToken;
    private String tipTokena = "Bearer";

    public JwtAutentikacioniOdgovor(String pristupniToken) {
        this.pristupniToken = pristupniToken;
    }
}
