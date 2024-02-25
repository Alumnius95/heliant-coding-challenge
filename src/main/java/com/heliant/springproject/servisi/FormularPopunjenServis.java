package com.heliant.springproject.servisi;

import com.heliant.springproject.dto.FormularPopunjenDTO;
import com.heliant.springproject.dto.PoljePopunjenoDTO;
import com.heliant.springproject.entiteti.*;
import com.heliant.springproject.izuzeci.FormularPopunjenIzuzetak;
import com.heliant.springproject.izuzeci.NevalidnoSlaganjeTipovaPoljaIzuzetak;
import com.heliant.springproject.izuzeci.PoljePopunjenoBezPoljaIzuzetak;
import com.heliant.springproject.repozitorijumi.FormularPopunjenRepozitorijum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FormularPopunjenServis {

    private final FormularPopunjenRepozitorijum formularPopunjenRepozitorijum;
    private final PoljeServis poljeServis;
    @Transactional(readOnly = true)
    public List<FormularPopunjen> nadjiSve() {
        return formularPopunjenRepozitorijum.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<FormularPopunjen> nadjiKrozId(Long id) {
        return formularPopunjenRepozitorijum.findById(id);
    }

    @Transactional
    public FormularPopunjen sacuvaj(FormularPopunjenDTO formularPopunjenDTO, Optional<Formular> formular) {
        if (formular.isEmpty()) {
            throw new FormularPopunjenIzuzetak("FormularPopunjen mora biti vezan za validan/postojeci formular!");
        }
        for (PoljePopunjenoDTO poljePopunjenoDTO: formularPopunjenDTO.getPoljaPopunjenaDTO()){
            Optional<Polje> polje = poljeServis.nadjiKrozId(poljePopunjenoDTO.getIdPolje());
            if (polje.isEmpty()) {
                throw new PoljePopunjenoBezPoljaIzuzetak("Popunjeno polje mora biti vezano za postojeci ID polja!");
            }
            proveriSlaganjeTipova(poljePopunjenoDTO, polje.get());
        }
        FormularPopunjen formularPopunjen = FormularPopunjenDTO.
                dtoUOriginal(formularPopunjenDTO, new FormularPopunjen());
        formularPopunjen.setFormular(formular.get());
        formular.get().getPopunjeniFormulari().add(formularPopunjen);
        return formularPopunjenRepozitorijum.save(formularPopunjen);
    }

    @Transactional
    public FormularPopunjen azuriraj(FormularPopunjenDTO formularPopunjenDTO, Optional<Formular> formular, Long id) {
        if (formular.isEmpty()) {
            throw new FormularPopunjenIzuzetak("FormularPopunjen mora biti vezan za validan/postojeci formular!");
        }
        for (PoljePopunjenoDTO poljePopunjenoDTO: formularPopunjenDTO.getPoljaPopunjenaDTO()){
            Optional<Polje> polje = poljeServis.nadjiKrozId(poljePopunjenoDTO.getIdPolje());
            if (polje.isEmpty()) {
                throw new PoljePopunjenoBezPoljaIzuzetak("Popunjeno polje mora biti vezano za postojeci ID polja!");
            }
            proveriSlaganjeTipova(poljePopunjenoDTO, polje.get());
        }
        FormularPopunjen formularPopunjen = FormularPopunjenDTO.dtoUOriginal(formularPopunjenDTO, new FormularPopunjen());
        boolean validno = formularPopunjen.getPopunjenaPolja().stream().allMatch(poljePopunjeno ->
                formular.get().getPolja().stream().anyMatch(polje ->
                        (polje.getTip() == Tip.TEXT && poljePopunjeno.getVrednostTekst() != null && poljePopunjeno.getVrednostBroj() == null) ||
                                (polje.getTip() == Tip.BROJ && poljePopunjeno.getVrednostBroj() != null && poljePopunjeno.getVrednostTekst() == null)
                )
        );
        if (!validno) {
            throw new NevalidnoSlaganjeTipovaPoljaIzuzetak("Pri azuriranju formulara podesili ste formular na tip" +
                    "koji ne sadrzi tipove polja koje vasa popunjena forma sadrzi ili ste ostavili istu formu (FK) i" +
                    "azurirali popunjena polja gde je neko od njih mismatch sa tom postojecom formom");
        }
        formularPopunjen.setId(id);
        formularPopunjen.setVremeIzmene(LocalDateTime.now());
        formularPopunjen.setFormular(formular.get());
        formular.get().getPopunjeniFormulari().add(formularPopunjen);
        return formularPopunjenRepozitorijum.save(formularPopunjen);
    }

    @Transactional
    public void obrisi(FormularPopunjen formularPopunjen, Long id) {
        Formular formular = formularPopunjen.getFormular();
        formular.getPopunjeniFormulari().remove(formularPopunjen);
        formularPopunjenRepozitorijum.deleteById(id);
    }

    private static void proveriSlaganjeTipova(PoljePopunjenoDTO poljePopunjenoDTO, Polje polje) {
        if (polje.getTip() == Tip.TEXT) {
            if (poljePopunjenoDTO.getVrednostTekst() == null || poljePopunjenoDTO.getVrednostBroj() != null) {
                throw new NevalidnoSlaganjeTipovaPoljaIzuzetak("Popunjeno polje ima unet razlicit tip vrednosti od parent polja - MORA TEXT");
            }
        } else if (polje.getTip() == Tip.BROJ) {
            if (poljePopunjenoDTO.getVrednostTekst() != null || poljePopunjenoDTO.getVrednostBroj() == null) {
                throw new NevalidnoSlaganjeTipovaPoljaIzuzetak("Popunjeno polje ima unet razlicit tip vrednosti od parent polja - MORA BROJ");
            }
        }
    }
}
