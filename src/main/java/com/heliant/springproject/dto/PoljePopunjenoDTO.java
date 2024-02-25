package com.heliant.springproject.dto;

import com.heliant.springproject.entiteti.PoljePopunjeno;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PoljePopunjenoDTO(
        @NotNull(message = "Ukoliko je polje popunjeno mora se naznaciti ID polja (template-a) za koje se vezuje")
        Long idPolje,

        @NotNull(message = "Ukoliko je polje popunjeno mora se naznaciti ID popunjenog formulara (template-a) za koje se vezuje")
        Long idFormularPopunjen,

        String vrednostTekst,

        Double vrednostBroj
) {

    public static PoljePopunjeno dtoUOriginal(PoljePopunjenoDTO poljePopunjenoDTO, PoljePopunjeno poljePopunjeno) {
        poljePopunjeno.setVrednostBroj(poljePopunjenoDTO.vrednostBroj());
        poljePopunjeno.setVrednostTekst(poljePopunjenoDTO.vrednostTekst());
        poljePopunjeno.setVremeKreiranja(LocalDateTime.now());
        poljePopunjeno.setVremeIzmene(LocalDateTime.now());
        return poljePopunjeno;
    }
}
