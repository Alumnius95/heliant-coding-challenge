package com.heliant.springproject.kontroleri;

import com.heliant.springproject.entiteti.Korisnik;
import com.heliant.springproject.entiteti.Polje;
import com.heliant.springproject.servisi.PoljeServis;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/polja")
@AllArgsConstructor
public class PoljeKontroler {

    private final PoljeServis poljeServis;

    @GetMapping
    public ResponseEntity<List<Polje>> dohvatiSvaPolja() {
        return ResponseEntity.ok(poljeServis.nadjiSve());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Polje> dohvatiPoljeKrozId(@PathVariable Long id) {
        return poljeServis.nadjiKrozId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Polje> kreirajPolje(@RequestBody Polje polje) {
        Polje sacuvanoPolje = poljeServis.sacuvaj(polje);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvanoPolje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Polje> azurirajPolje(@PathVariable Long id, @RequestBody Polje polje) {
        if (poljeServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        polje.setId(id);
        Polje azuriranoPolje = poljeServis.azuriraj(polje);
        return ResponseEntity.ok(azuriranoPolje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisiPolje(@PathVariable Long id) {
        if (poljeServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        poljeServis.obrisi(id);
        return ResponseEntity.noContent().build();
    }
}
