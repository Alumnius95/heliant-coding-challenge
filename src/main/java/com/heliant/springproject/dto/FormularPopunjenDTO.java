package com.heliant.springproject.dto;

import com.heliant.springproject.entiteti.FormularPopunjen;
import com.heliant.springproject.entiteti.PoljePopunjeno;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


public record FormularPopunjenDTO(
        @NotNull(message = "Popunjen formular mora biti vezan za konkretan formular!")
        Long formularId,

        @Valid
        @NotEmpty(message = "Makar jedno popunjeno polje je potrebno za popunjen formular")
        Set<PoljePopunjenoDTO> poljaPopunjenaDTO
) {
    public static FormularPopunjen dtoUOriginal(FormularPopunjenDTO formularPopunjenDTO, FormularPopunjen formularPopunjen) {
        formularPopunjen.setVremeKreiranja(LocalDateTime.now());
        formularPopunjen.setVremeIzmene(LocalDateTime.now());
        Set<PoljePopunjeno> popunjenaPolja = formularPopunjenDTO.poljaPopunjenaDTO().stream()
                .map(poljePopunjenoDTO -> PoljePopunjenoDTO.dtoUOriginal(poljePopunjenoDTO, new PoljePopunjeno()))
                .collect(Collectors.toSet());
        formularPopunjen.setPopunjenaPolja(popunjenaPolja);
        return formularPopunjen;
    }
}
