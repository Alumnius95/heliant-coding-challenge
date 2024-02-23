package com.heliant.springproject.kontroleri;

import com.heliant.springproject.entiteti.Korisnik;
import com.heliant.springproject.servisi.KorisnikServis;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/korisnici")
@AllArgsConstructor
public class KorisnikKontroler {

    private final KorisnikServis korisnikServis;
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
    public ResponseEntity<Korisnik> kreirajKorisnika(@RequestBody Korisnik korisnik) {
        Korisnik sacuvanKorisnik = korisnikServis.sacuvaj(korisnik);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvanKorisnik);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Korisnik> azurirajKorisnika(@PathVariable Long id, @RequestBody Korisnik korisnik) {
        if (korisnikServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        korisnik.setId(id);
        Korisnik azuriranKorisnik = korisnikServis.azuriraj(korisnik);
        return ResponseEntity.ok(azuriranKorisnik);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisiKorisnika(@PathVariable Long id) {
        if (korisnikServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        korisnikServis.obrisi(id);
        return ResponseEntity.noContent().build();
    }
}
