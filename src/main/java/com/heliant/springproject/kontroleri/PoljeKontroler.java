package com.heliant.springproject.kontroleri;

import com.heliant.springproject.dto.PoljeDTO;
import com.heliant.springproject.entiteti.Formular;
import com.heliant.springproject.entiteti.Polje;
import com.heliant.springproject.servisi.FormularServis;
import com.heliant.springproject.servisi.PoljeServis;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/polja")
@AllArgsConstructor
public class PoljeKontroler {

    private final PoljeServis poljeServis;
    private final FormularServis formularServis;
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
    public ResponseEntity<Polje> kreirajPolje(@Valid @RequestBody PoljeDTO poljeDTO) {
        Optional<Formular> formular = formularServis.nadjiKrozId(poljeDTO.getFormularId());
        Polje sacuvanoPolje = poljeServis.sacuvaj(poljeDTO, formular);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvanoPolje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Polje> azurirajPolje(@PathVariable Long id, @Valid @RequestBody PoljeDTO poljeDTO) {
        Optional<Formular> formular = formularServis.nadjiKrozId(poljeDTO.getFormularId());
        if (poljeServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Polje azuriranoPolje = poljeServis.azuriraj(poljeDTO, formular,id);
        return ResponseEntity.ok(azuriranoPolje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisiPolje(@PathVariable Long id) {
        Optional<Polje> polje = poljeServis.nadjiKrozId(id);
        if (polje.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        poljeServis.obrisi(polje.get(),id);
        return ResponseEntity.noContent().build();
    }
}
