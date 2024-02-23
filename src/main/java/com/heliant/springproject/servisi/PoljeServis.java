package com.heliant.springproject.servisi;

import com.heliant.springproject.entiteti.Polje;
import com.heliant.springproject.repozitorijumi.PoljeRepozitorijum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PoljeServis {

    private final PoljeRepozitorijum poljeRepozitorijum;

    @Transactional(readOnly = true)
    public List<Polje> nadjiSve() {
        return poljeRepozitorijum.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Polje> nadjiKrozId(Long id) {
        return poljeRepozitorijum.findById(id);
    }

    @Transactional
    public Polje sacuvaj(Polje polje) {
        return poljeRepozitorijum.save(polje);
    }

    @Transactional
    public Polje azuriraj(Polje polje) {
        return poljeRepozitorijum.save(polje);
    }

    @Transactional
    public void obrisi(Long id) {
        poljeRepozitorijum.deleteById(id);
    }
}
