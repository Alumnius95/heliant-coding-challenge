package com.heliant.springproject.kontroleri;

import com.heliant.springproject.entiteti.FormularPopunjen;
import com.heliant.springproject.servisi.FormularPopunjenServis;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/formularipopunjeni")
@AllArgsConstructor
public class FormularPopunjenKontroler {

    private final FormularPopunjenServis formularPopunjenServis;

    @GetMapping
    public ResponseEntity<List<FormularPopunjen>> dohvatiSveFormulare() {
        return ResponseEntity.ok(formularPopunjenServis.nadjiSve());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormularPopunjen> dohvatiPopunjenFormularKrozId(@PathVariable Long id) {
        return formularPopunjenServis.nadjiKrozId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FormularPopunjen> kreirajFormular(@RequestBody FormularPopunjen formularPopunjen) {
        FormularPopunjen sacuvaniPopunjeniFormular = formularPopunjenServis.sacuvaj(formularPopunjen);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvaniPopunjeniFormular);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormularPopunjen> azurirajFormular(@PathVariable Long id, @RequestBody FormularPopunjen formularPopunjen) {
        if (formularPopunjenServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        formularPopunjen.setId(id);
        FormularPopunjen azuriranFormular = formularPopunjenServis.azuriraj(formularPopunjen);
        return ResponseEntity.ok(azuriranFormular);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisiPopunjenFormular(@PathVariable Long id) {
        if (formularPopunjenServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        formularPopunjenServis.obrisi(id);
        return ResponseEntity.noContent().build();
    }
}
