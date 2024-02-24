package com.heliant.springproject.dto;

import com.heliant.springproject.entiteti.PoljePopunjeno;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoljePopunjenoDTO {
    @NotNull(message = "Ukoliko je polje popunjeno mora se naznaciti ID polja (template-a) za koje se vezuje")
    private Long idPolje;

    @NotNull(message = "Ukoliko je polje popunjeno mora se naznaciti ID popunjenog formulara (template-a) za koje se vezuje")
    private Long idFormularPopunjen;

    private String vrednostTekst;

    private Double vrednostBroj;

    public static PoljePopunjeno dtoUOriginal(PoljePopunjenoDTO poljePopunjenoDTO, PoljePopunjeno poljePopunjeno) {
        poljePopunjeno.setIdPolja(poljePopunjenoDTO.getIdPolje());
        poljePopunjeno.setIdFormularaPopunjenog(poljePopunjenoDTO.getIdFormularPopunjen());
        poljePopunjeno.setVrednostBroj(poljePopunjenoDTO.getVrednostBroj());
        poljePopunjeno.setVrednostTekst(poljePopunjenoDTO.getVrednostTekst());
        poljePopunjeno.setVremeKreiranja(LocalDateTime.now());
        poljePopunjeno.setVremeIzmene(LocalDateTime.now());
        return poljePopunjeno;
    }
}
