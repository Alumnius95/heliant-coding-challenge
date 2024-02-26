package com.heliant.springproject.kontroleri;

import com.heliant.springproject.dto.KorisnikDTO;
import com.heliant.springproject.entiteti.Korisnik;
import com.heliant.springproject.servisi.KorisnikServis;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.heliant.springproject.dto.KorisnikDTO.dtoUOriginal;

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
    public ResponseEntity<Korisnik> kreirajKorisnika(@Valid @RequestBody KorisnikDTO korisnikDTO) {
        if (validirajSpecijalniKljuc(korisnikDTO.specijalniKljuc())) {
            Korisnik korisnik = dtoUOriginal(korisnikDTO);
            Korisnik sacuvanKorisnik = korisnikServis.sacuvaj(korisnik);
            return ResponseEntity.status(HttpStatus.CREATED).body(sacuvanKorisnik);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Korisnik> azurirajKorisnika(@PathVariable Long id, @Valid @RequestBody KorisnikDTO korisnikDTO) {
        if (korisnikServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (validirajSpecijalniKljuc(korisnikDTO.specijalniKljuc())) {
            Korisnik postojeciKorisnik = dtoUOriginal(korisnikDTO);
            postojeciKorisnik.setId(id);
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

    private boolean validirajSpecijalniKljuc(String dostavljeniKljuc) {
        String specijalniKljuc = env.getProperty("app.special.key");
        return specijalniKljuc == null || !specijalniKljuc.equals(dostavljeniKljuc);
    }
}
