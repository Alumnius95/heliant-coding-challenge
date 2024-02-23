package com.heliant.springproject.servisi;

import com.heliant.springproject.entiteti.PoljePopunjeno;
import com.heliant.springproject.repozitorijumi.PoljePopunjenoRepozitorijum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PoljePopunjeno sacuvaj(PoljePopunjeno poljePopunjeno) {
        return poljePopunjenoRepozitorijum.save(poljePopunjeno);
    }

    @Transactional
    public PoljePopunjeno azuriraj(PoljePopunjeno poljePopunjeno) {
        return poljePopunjenoRepozitorijum.save(poljePopunjeno);
    }

    @Transactional
    public void obrisi(Long id) {
        poljePopunjenoRepozitorijum.deleteById(id);
    }
}
