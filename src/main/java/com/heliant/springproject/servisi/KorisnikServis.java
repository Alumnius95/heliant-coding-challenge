package com.heliant.springproject.servisi;

import com.heliant.springproject.entiteti.Korisnik;
import com.heliant.springproject.repozitorijumi.KorisnikRepozitorijum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class KorisnikServis {

    private final KorisnikRepozitorijum korisnikRepozitorijum;
    @Transactional(readOnly = true)
    public List<Korisnik> nadjiSve() {
        return korisnikRepozitorijum.findAll();
    }
    @Transactional(readOnly = true)
    public Optional<Korisnik> nadjiKrozId(Long id) {
        return korisnikRepozitorijum.findById(id);
    }
    @Transactional
    public Korisnik sacuvaj(Korisnik korisnik) {
        return korisnikRepozitorijum.save(korisnik);
    }
    @Transactional
    public Korisnik azuriraj(Korisnik korisnik) {
        return korisnikRepozitorijum.save(korisnik);
    }
    @Transactional
    public void obrisi(Long id) {
        korisnikRepozitorijum.deleteById(id);
    }
}