package com.heliant.springproject.dto;

import com.heliant.springproject.entiteti.Formular;
import com.heliant.springproject.entiteti.Polje;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormularDTO {

    @NotBlank(message = "Naziv je neophodan")
    @Size(max = 256, message = "Naziv moze imati max 256 karaktera")
    private String naziv;

    @Valid
    @NotEmpty(message = "Makar jedno polje je potrebno za formular")
    private Set<PoljeDTO>poljaDto;

    public static Formular dtoUOriginal(FormularDTO formularDTO, Formular formular) {
        formular.setNaziv(formularDTO.getNaziv());
        formular.setVremeKreiranja(LocalDateTime.now());
        formular.setVremeIzmene(LocalDateTime.now());
        Set<Polje> polja = formularDTO.getPoljaDto().stream()
                .map(poljeDTO -> PoljeDTO.dtoUOriginal(poljeDTO, new Polje()))
                .collect(Collectors.toSet());
        polja.forEach(polje -> polje.setFormular(formular));
        formular.setPolja(polja);
        return formular;
    }
}
