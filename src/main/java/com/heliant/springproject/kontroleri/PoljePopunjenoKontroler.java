package com.heliant.springproject.kontroleri;

import com.heliant.springproject.entiteti.PoljePopunjeno;
import com.heliant.springproject.servisi.PoljePopunjenoServis;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/popunjenapolja")
@AllArgsConstructor
public class PoljePopunjenoKontroler {

    private final PoljePopunjenoServis poljePopunjenoServis;

    @GetMapping
    public ResponseEntity<List<PoljePopunjeno>> dohvatiSvaPopunjenaPolja() {
        return ResponseEntity.ok(poljePopunjenoServis.nadjiSve());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoljePopunjeno> dohvatiPoljeKrozId(@PathVariable Long id) {
        return poljePopunjenoServis.nadjiKrozId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PoljePopunjeno> kreirajPolje(@RequestBody PoljePopunjeno poljePopunjeno) {
        PoljePopunjeno sacuvanoPopunjenoPolje = poljePopunjenoServis.sacuvaj(poljePopunjeno);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvanoPopunjenoPolje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoljePopunjeno> azurirajPolje(@PathVariable Long id, @RequestBody PoljePopunjeno poljePopunjeno) {
        if (poljePopunjenoServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        poljePopunjeno.setId(id);
        PoljePopunjeno azuriranoPopunjenoPolje = poljePopunjenoServis.azuriraj(poljePopunjeno);
        return ResponseEntity.ok(azuriranoPopunjenoPolje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisiPolje(@PathVariable Long id) {
        if (poljePopunjenoServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        poljePopunjenoServis.obrisi(id);
        return ResponseEntity.noContent().build();
    }
}
