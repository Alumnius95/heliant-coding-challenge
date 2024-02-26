package com.heliant.springproject.dto;

import com.heliant.springproject.entiteti.Formular;
import com.heliant.springproject.entiteti.Polje;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record FormularDTO(
        @NotBlank(message = "Naziv je neophodan")
        @Size(max = 256, message = "Naziv moze imati max 256 karaktera")
        String naziv,

        @Valid
        @NotEmpty(message = "Makar jedno polje je potrebno za formular")
        Set<PoljeDTO> poljaDto
) {

    public static Formular dtoUOriginal(FormularDTO formularDTO) {
        Formular formular = new Formular();
        formular.setNaziv(formularDTO.naziv());
        formular.setVremeKreiranja(LocalDateTime.now());
        formular.setVremeIzmene(LocalDateTime.now());
        Set<Polje> polja = formularDTO.poljaDto().stream()
                .map(PoljeDTO::dtoUOriginal)
                .collect(Collectors.toSet());
        polja.forEach(polje -> polje.setFormular(formular));
        formular.setPolja(polja);
        return formular;
    }
}
