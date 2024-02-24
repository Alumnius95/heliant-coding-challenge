package com.heliant.springproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KorisnikDTO {
    private String korisnickoIme;
    private String lozinka;
    private String rola;
    private String specijalniKljuc;
}
