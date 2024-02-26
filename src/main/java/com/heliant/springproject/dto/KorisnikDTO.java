package com.heliant.springproject.dto;

import com.heliant.springproject.entiteti.Korisnik;
import com.heliant.springproject.entiteti.Rola;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record KorisnikDTO(
        @NotBlank(message = "Korisnicko ime je neophodno")
        @Size(max = 256, message = "Korisnicko ime moze imati max 256 karaktera")
        String korisnickoIme,

        @NotBlank(message = "Lozinka je neophodna")

        @Size(min = 6, max = 256, message = "Lozinka mora biti duzine izmedju 6 i 256 karaktera")
        String lozinka,

        @NotNull(message = "Rola je neophodna: Opcije -> ROLE_ADMIN ili ROLE_RADNIK")
        Rola rola,

        @NotBlank(message = "Specijalni kljuc za kreiranje korisnika je neophodan")
        String specijalniKljuc

) {
    public static Korisnik dtoUOriginal(KorisnikDTO dto) {
        Korisnik korisnik = new Korisnik();
        korisnik.setKorisnickoIme(dto.korisnickoIme());
        korisnik.setLozinka(dto.lozinka());
        LocalDateTime now = LocalDateTime.now();
        korisnik.setVremeKreiranja(korisnik.getId() == null ? now : korisnik.getVremeKreiranja());
        korisnik.setVremeIzmene(now);
        korisnik.setRola(dto.rola());
        return korisnik;
    }
}
