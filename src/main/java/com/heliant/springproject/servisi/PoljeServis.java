package com.heliant.springproject.servisi;

import com.heliant.springproject.dto.PoljeDTO;
import com.heliant.springproject.dto.PoljePopunjenoDTO;
import com.heliant.springproject.entiteti.Formular;
import com.heliant.springproject.entiteti.Polje;
import com.heliant.springproject.entiteti.Tip;
import com.heliant.springproject.izuzeci.NevalidnoSlaganjeTipovaPoljaIzuzetak;
import com.heliant.springproject.izuzeci.PoljeBezFormularaIzuzetak;
import com.heliant.springproject.repozitorijumi.FormularRepozitorijum;
import com.heliant.springproject.repozitorijumi.PoljeRepozitorijum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.heliant.springproject.dto.PoljeDTO.dtoUOriginal;

@Service
@AllArgsConstructor
public class PoljeServis {

    private final PoljeRepozitorijum poljeRepozitorijum;
    private final FormularRepozitorijum formularRepozitorijum;

    @Transactional(readOnly = true)
    public List<Polje> nadjiSve() {
        return poljeRepozitorijum.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Polje> nadjiKrozId(Long id) {
        return poljeRepozitorijum.findById(id);
    }

    @Transactional
    public Polje sacuvaj(PoljeDTO poljeDTO, Optional<Formular>formular) {
        if (formular.isEmpty()) {
            throw new PoljeBezFormularaIzuzetak("Polje ne moze biti kreirano/sacuvano bez postojeceg formulara!");
        }
        proveriSlaganjeTipova(poljeDTO);
        Polje polje = PoljeDTO.dtoUOriginal(poljeDTO, new Polje());
        polje.setVremeKreiranja(LocalDateTime.now());
        return poljeRepozitorijum.save(polje);
    }

    @Transactional
    public Polje azuriraj(PoljeDTO poljeDTO, Optional<Formular> formular, Long id) {
        proveriSlaganjeTipova(poljeDTO);
        Polje polje = dtoUOriginal(poljeDTO, new Polje());
        polje.setId(id);
        formular.ifPresent(polje::setFormular);
        polje.setVremePoslednjeIzmene(LocalDateTime.now());
        return poljeRepozitorijum.save(polje);
    }

    @Transactional
    public void obrisi(Polje polje, Long id) {
        Formular formular = polje.getFormular();
        formular.getPolja().remove(polje);
        poljeRepozitorijum.deleteById(id);
        formularRepozitorijum.save(formular);
    }

    private static void proveriSlaganjeTipova(PoljeDTO poljeDTO) {
        if (!poljeDTO.getPoljaPopunjenaDTO().isEmpty()) {
            if (poljeDTO.getTip() == Tip.TEXT) {
                for (PoljePopunjenoDTO poljePopunjenoDTO : poljeDTO.getPoljaPopunjenaDTO()) {
                    if (poljePopunjenoDTO.getVrednostTekst() == null || poljePopunjenoDTO.getVrednostBroj() != null) {
                        throw new NevalidnoSlaganjeTipovaPoljaIzuzetak("Kada je polje TEKST tipa onda ni " +
                                "jedno polje u setu ne sme biti null ILI broj ne sme postojati");
                    }
                }
            } else if (poljeDTO.getTip() == Tip.BROJ) {
                for (PoljePopunjenoDTO poljePopunjenoDTO : poljeDTO.getPoljaPopunjenaDTO()) {
                    if (poljePopunjenoDTO.getVrednostTekst() == null || poljePopunjenoDTO.getVrednostBroj() != null) {
                        throw new NevalidnoSlaganjeTipovaPoljaIzuzetak("Kada je polje BROJ tipa onda ni " +
                                "jedno polje u setu ne sme biti null ILI tekst ne sme postojati");
                    }
                }
            }
        }
    }
}
