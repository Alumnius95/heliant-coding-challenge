package com.heliant.springproject.kontroleri;

import com.heliant.springproject.dto.FormularPopunjenDTO;
import com.heliant.springproject.entiteti.Formular;
import com.heliant.springproject.entiteti.FormularPopunjen;
import com.heliant.springproject.servisi.FormularPopunjenServis;
import com.heliant.springproject.servisi.FormularServis;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/formularipopunjeni")
@AllArgsConstructor
public class FormularPopunjenKontroler {

    private final FormularPopunjenServis formularPopunjenServis;
    private final FormularServis formularServis;

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
    public ResponseEntity<FormularPopunjen> kreirajFormular(@Valid @RequestBody FormularPopunjenDTO formularPopunjenDTO) {
        Optional<Formular> formular = formularServis.nadjiKrozId(formularPopunjenDTO.formularId());
        FormularPopunjen sacuvaniPopunjeniFormular = formularPopunjenServis.sacuvaj(formularPopunjenDTO, formular);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvaniPopunjeniFormular);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormularPopunjen> azurirajFormular(@PathVariable Long id, @Valid @RequestBody FormularPopunjenDTO formularPopunjenDTO) {
        Optional<Formular> formular = formularServis.nadjiKrozId(formularPopunjenDTO.formularId());
        if (formularPopunjenServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        FormularPopunjen azuriranFormular = formularPopunjenServis.azuriraj(formularPopunjenDTO, formular, id);
        return ResponseEntity.ok(azuriranFormular);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisiPopunjenFormular(@PathVariable Long id) {
        Optional<FormularPopunjen> formularPopunjen = formularPopunjenServis.nadjiKrozId(id);
        if (formularPopunjen.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        formularPopunjenServis.obrisi(formularPopunjen.get(),id);
        return ResponseEntity.noContent().build();
    }
}
