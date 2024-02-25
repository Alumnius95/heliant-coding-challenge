package com.heliant.springproject.servisi;

import com.heliant.springproject.dto.PoljePopunjenoDTO;
import com.heliant.springproject.entiteti.FormularPopunjen;
import com.heliant.springproject.entiteti.Polje;
import com.heliant.springproject.entiteti.PoljePopunjeno;
import com.heliant.springproject.entiteti.Tip;
import com.heliant.springproject.izuzeci.NevalidnoSlaganjeTipovaPoljaIzuzetak;
import com.heliant.springproject.izuzeci.PoljePopunjenoBezFormularaPopunjenogIzuzetak;
import com.heliant.springproject.izuzeci.PoljePopunjenoBezPoljaIzuzetak;
import com.heliant.springproject.repozitorijumi.PoljePopunjenoRepozitorijum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PoljePopunjenoServis {

    private final PoljePopunjenoRepozitorijum poljePopunjenoRepozitorijum;

    @Transactional(readOnly = true)
    public List<PoljePopunjeno> nadjiSve() {
        return poljePopunjenoRepozitorijum.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PoljePopunjeno> nadjiKrozId(Long id) {
        return poljePopunjenoRepozitorijum.findById(id);
    }

    @Transactional
    public PoljePopunjeno sacuvaj(PoljePopunjenoDTO poljePopunjenoDTO,
                                  Optional<FormularPopunjen> formularPopunjen, Optional<Polje> polje) {
        if (formularPopunjen.isEmpty()) {
            throw new PoljePopunjenoBezFormularaPopunjenogIzuzetak("PoljePopunjeno ne moze biti kreirano/sacuvano bez kreiranog popunjenog formulara!");
        }
        if (polje.isEmpty()) {
            throw new PoljePopunjenoBezPoljaIzuzetak("PoljePopunjeno ne moze biti kreirano/sacuvano bez polja kojem pripada!");
        }
        proveriSlaganjeTipova(poljePopunjenoDTO, polje.get());
        PoljePopunjeno poljePopunjeno = PoljePopunjenoDTO.dtoUOriginal(poljePopunjenoDTO, new PoljePopunjeno());
        return poljePopunjenoRepozitorijum.save(poljePopunjeno);
    }

    @Transactional
    public PoljePopunjeno azuriraj(PoljePopunjenoDTO poljePopunjenoDTO,
                                   Optional<FormularPopunjen> formularPopunjen, Optional<Polje> polje, Long id) {
        if (formularPopunjen.isEmpty()) {
            throw new PoljePopunjenoBezFormularaPopunjenogIzuzetak("PoljePopunjeno ne moze biti kreirano/sacuvano bez kreiranog popunjenog formulara!");
        }
        if (polje.isEmpty()) {
            throw new PoljePopunjenoBezPoljaIzuzetak("PoljePopunjeno ne moze biti kreirano/sacuvano bez polja kojem pripada!");
        }
        proveriSlaganjeTipova(poljePopunjenoDTO, polje.get());
        PoljePopunjeno poljePopunjeno = PoljePopunjenoDTO.dtoUOriginal(poljePopunjenoDTO, new PoljePopunjeno());
        poljePopunjeno.setId(id);
        poljePopunjeno.setVremeIzmene(LocalDateTime.now());
        formularPopunjen.ifPresent(poljePopunjeno::setFormularPopunjen);
        polje.ifPresent(poljePopunjeno::setPolje);
        return poljePopunjenoRepozitorijum.save(poljePopunjeno);
    }

    @Transactional
    public void obrisi(Long id) {
        poljePopunjenoRepozitorijum.deleteById(id);
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

