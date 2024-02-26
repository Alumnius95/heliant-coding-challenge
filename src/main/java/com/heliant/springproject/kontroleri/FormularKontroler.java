package com.heliant.springproject.kontroleri;

import com.heliant.springproject.dto.FormularDTO;
import com.heliant.springproject.entiteti.Formular;
import com.heliant.springproject.servisi.FormularServis;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/formulari")
@AllArgsConstructor
public class FormularKontroler {

    private final FormularServis formularServis;

    @GetMapping
    public ResponseEntity<Page<Formular>> dohvatiSveFormulare(@RequestParam(value = "page", defaultValue = "0") int page,
                                                              @RequestParam(value = "size", defaultValue = "10") int size,
                                                              @RequestParam(value = "sort", defaultValue = "id,desc") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort.split(",")));
        Page<Formular> formulari = formularServis.nadjiSve(pageable);
        return ResponseEntity.ok(formulari);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formular> dohvatiFormularKrozId(@PathVariable Long id) {
        return formularServis.nadjiKrozId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Formular> kreirajFormular(@Valid @RequestBody FormularDTO formularDTO) {
        Formular sacuvaniFormular = formularServis.sacuvaj(formularDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvaniFormular);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Formular> azurirajFormular(@PathVariable Long id, @Valid @RequestBody FormularDTO formularDTO) {
        if (formularServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Formular azuriranFormular = formularServis.azuriraj(id,formularDTO);
        return ResponseEntity.ok(azuriranFormular);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> obrisiFormular(@PathVariable Long id) {
        if (formularServis.nadjiKrozId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        formularServis.obrisi(id);
        return ResponseEntity.noContent().build();
    }
}
