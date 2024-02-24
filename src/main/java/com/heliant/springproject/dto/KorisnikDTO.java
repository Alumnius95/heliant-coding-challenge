package com.heliant.springproject.dto;

import com.heliant.springproject.entiteti.Korisnik;
import com.heliant.springproject.entiteti.Rola;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KorisnikDTO {

    @NotBlank(message = "Korisnicko ime je neophodno")
    @Size(max = 256, message = "Korisnicko ime moze imati max 256 karaktera")
    private String korisnickoIme;

    @NotBlank(message = "Lozinka je neophodna")
    @Size(min = 6, max = 256, message = "Lozinka mora biti duzine izmedju 6 i 256 karaktera")
    private String lozinka;

    @NotNull(message = "Rola je neophodna: Opcije -> ROLE_ADMIN ili ROLE_RADNIK")
    private Rola rola;

    @NotBlank(message = "Specijalni kljuc za kreiranje korisnika je neophodan")
    private String specijalniKljuc;

    public static Korisnik dtoUOriginal(KorisnikDTO dto, Korisnik korisnik) {
        korisnik.setKorisnickoIme(dto.getKorisnickoIme());
        korisnik.setLozinka(dto.getLozinka());
        LocalDateTime now = LocalDateTime.now();
        korisnik.setVremeKreiranja(korisnik.getId() == null ? now : korisnik.getVremeKreiranja());
        korisnik.setVremeIzmene(now);
        korisnik.setRola(dto.getRola());
        return korisnik;
    }
}
