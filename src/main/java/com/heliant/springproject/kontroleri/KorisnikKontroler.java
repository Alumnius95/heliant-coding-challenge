package com.heliant.springproject.kontroleri;

import com.heliant.springproject.dto.KorisnikDTO;
import com.heliant.springproject.entiteti.Korisnik;
import com.heliant.springproject.entiteti.Rola;
import com.heliant.springproject.izuzeci.NevalidnaRolaIzuzetak;
import com.heliant.springproject.servisi.KorisnikServis;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/korisnici")
@AllArgsConstructor
public class KorisnikKontroler {

    private final KorisnikServis korisnikServis;
    private final Environment env;
    @GetMapping
    public ResponseEntity<List<Korisnik>> dohvatiSveKorisnike() {
        return ResponseEntity.ok(korisnikServis.nadjiSve());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Korisnik> dohvatiKorisnikaKrozId(@PathVariable Long id) {
        return korisnikServis.nadjiKrozId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Korisnik> kreirajKorisnika(@RequestBody KorisnikDTO korisnikDTO) {
        if (validirajSpecijalniKljuc(korisnikDTO.getSpecijalniKljuc())) {
            Korisnik korisnik = dtoUOriginal(korisnikDTO, new Korisnik());
            Korisnik sacuvanKorisnik = korisnikServis.sacuvaj(korisnik);
            return ResponseEntity.status(HttpStatus.CREATED).body(sacuvanKorisnik);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Korisnik> azurirajKorisnika(@PathVariable Long id, @RequestBody KorisnikDTO korisnikDTO) {
        if (korisnikServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (validirajSpecijalniKljuc(korisnikDTO.getSpecijalniKljuc())) {
            Korisnik postojeciKorisnik = dtoUOriginal(korisnikDTO, korisnikServis.nadjiKrozId(id).get());
            Korisnik azuriranKorisnik = korisnikServis.azuriraj(postojeciKorisnik);
            return ResponseEntity.ok(azuriranKorisnik);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisiKorisnika(@PathVariable Long id) {
        if (korisnikServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        korisnikServis.obrisi(id);
        return ResponseEntity.noContent().build();
    }

    private Korisnik dtoUOriginal(KorisnikDTO dto, Korisnik korisnik) {
        korisnik.setKorisnickoIme(dto.getKorisnickoIme());
        korisnik.setLozinka(dto.getLozinka());
        LocalDateTime now = LocalDateTime.now();
        korisnik.setVremeKreiranja(korisnik.getId() == null ? now : korisnik.getVremeKreiranja());
        korisnik.setVremeIzmene(now);
        try {
            Rola rola = Rola.valueOf(dto.getRola().toUpperCase());
            korisnik.setRola(rola);
        } catch (IllegalArgumentException e) {
            throw new NevalidnaRolaIzuzetak("Rola koja je pruzena nije validna! Opcije su: ROLE_ADMIN ili ROLE_RADNIK.");
        }
        return korisnik;
    }

    private boolean validirajSpecijalniKljuc(String dostavljeniKljuc) {
        String specijalniKljuc = env.getProperty("app.special.key");
        return specijalniKljuc == null || !specijalniKljuc.equals(dostavljeniKljuc);
    }
}
