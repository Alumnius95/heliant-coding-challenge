package com.heliant.springproject.kontroleri;

import com.heliant.springproject.dto.PoljePopunjenoDTO;
import com.heliant.springproject.entiteti.FormularPopunjen;
import com.heliant.springproject.entiteti.Polje;
import com.heliant.springproject.entiteti.PoljePopunjeno;
import com.heliant.springproject.servisi.FormularPopunjenServis;
import com.heliant.springproject.servisi.PoljePopunjenoServis;
import com.heliant.springproject.servisi.PoljeServis;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/popunjenapolja")
@AllArgsConstructor
public class PoljePopunjenoKontroler {

    private final PoljePopunjenoServis poljePopunjenoServis;
    private final PoljeServis poljeServis;
    private final FormularPopunjenServis formularPopunjenServisServis;

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
    public ResponseEntity<PoljePopunjeno> kreirajPoljePopunjeno(@Valid @RequestBody PoljePopunjenoDTO poljePopunjenoDTO) {
        Optional<FormularPopunjen> formularPopunjen = formularPopunjenServisServis.nadjiKrozId(poljePopunjenoDTO.idFormularPopunjen());
        Optional<Polje> polje = poljeServis.nadjiKrozId(poljePopunjenoDTO.idPolje());
        PoljePopunjeno sacuvanoPopunjenoPolje = poljePopunjenoServis.sacuvaj(poljePopunjenoDTO,formularPopunjen, polje);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvanoPopunjenoPolje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoljePopunjeno> azurirajPoljePopunjeno(@PathVariable Long id, @Valid @RequestBody PoljePopunjenoDTO poljePopunjenoDTO) {
        Optional<FormularPopunjen> formularPopunjen = formularPopunjenServisServis.nadjiKrozId(poljePopunjenoDTO.idFormularPopunjen());
        Optional<Polje> polje = poljeServis.nadjiKrozId(poljePopunjenoDTO.idPolje());
        if (poljePopunjenoServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PoljePopunjeno azuriranoPopunjenoPolje = poljePopunjenoServis.azuriraj(poljePopunjenoDTO, formularPopunjen, polje, id);
        return ResponseEntity.ok(azuriranoPopunjenoPolje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisiPoljePopunjeno(@PathVariable Long id) {
        Optional<PoljePopunjeno> poljePopunjeno = poljePopunjenoServis.nadjiKrozId(id);
        if (poljePopunjeno.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        poljePopunjenoServis.obrisi(poljePopunjeno.get(),id);
        return ResponseEntity.noContent().build();
    }
}
