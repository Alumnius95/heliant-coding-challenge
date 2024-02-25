package com.heliant.springproject.dto;

import com.heliant.springproject.entiteti.Polje;
import com.heliant.springproject.entiteti.PoljePopunjeno;
import com.heliant.springproject.entiteti.Tip;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


public record PoljeDTO(
        @NotBlank(message = "Naziv je neophodan")
        @Size(max = 256, message = "Naziv moze imati max 256 karaktera")
        String naziv,

        @NotNull(message = "Neophodan je redosled prikaza")
        Integer prikazniRedosled,

        @NotNull(message = "Neophodno je uneti tip polja")
        Tip tip,

        Long formularId,

        @Valid
        Set<PoljePopunjenoDTO> poljaPopunjenaDTO
) {
    public static Polje dtoUOriginal(PoljeDTO poljeDTO, Polje polje) {
        polje.setNaziv(poljeDTO.naziv());
        polje.setPrikazniRedosled(poljeDTO.prikazniRedosled());
        polje.setTip(poljeDTO.tip());
        polje.setVremeKreiranja(LocalDateTime.now());
        polje.setVremePoslednjeIzmene(LocalDateTime.now());
        if (!poljeDTO.poljaPopunjenaDTO().isEmpty()) {
            Set<PoljePopunjeno> popunjenaPolja = poljeDTO.poljaPopunjenaDTO().stream()
                    .map(poljePopunjenoDTO -> PoljePopunjenoDTO.dtoUOriginal(poljePopunjenoDTO, new PoljePopunjeno()))
                    .collect(Collectors.toSet());
            polje.setPopunjenaPolja(popunjenaPolja);
        }
        return polje;
    }
}
