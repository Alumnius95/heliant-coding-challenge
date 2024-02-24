package com.heliant.springproject.dto;

import com.heliant.springproject.entiteti.Formular;
import com.heliant.springproject.entiteti.Polje;
import com.heliant.springproject.entiteti.PoljePopunjeno;
import com.heliant.springproject.entiteti.Tip;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class PoljeDTO {

    @NotBlank(message = "Naziv je neophodan")
    @Size(max = 256, message = "Naziv moze imati max 256 karaktera")
    private String naziv;

    @NotNull(message = "Neophodan je redosled prikaza")
    private Integer prikazniRedosled;

    @NotNull(message = "Neophodno je uneti tip polja")
    private Tip tip;

    private Long formularId;

    @Valid
    private Set<PoljePopunjenoDTO>poljaPopunjenaDTO;

    public static Polje dtoUOriginal(PoljeDTO poljeDTO, Polje polje) {
        polje.setNaziv(poljeDTO.getNaziv());
        polje.setPrikazniRedosled(poljeDTO.getPrikazniRedosled());
        polje.setTip(poljeDTO.getTip());
        polje.setVremeKreiranja(LocalDateTime.now());
        polje.setVremePoslednjeIzmene(LocalDateTime.now());
        if (!poljeDTO.getPoljaPopunjenaDTO().isEmpty()) {
            Set<PoljePopunjeno> popunjenaPolja = poljeDTO.getPoljaPopunjenaDTO().stream()
                    .map(poljePopunjenoDTO -> PoljePopunjenoDTO.dtoUOriginal(poljePopunjenoDTO, new PoljePopunjeno()))
                    .collect(Collectors.toSet());
            polje.setPopunjenaPolja(popunjenaPolja);
        }
        return polje;
    }
}
