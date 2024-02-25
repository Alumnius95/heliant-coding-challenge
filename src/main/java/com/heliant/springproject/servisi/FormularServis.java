package com.heliant.springproject.servisi;

import com.heliant.springproject.dto.FormularDTO;
import com.heliant.springproject.entiteti.Formular;
import com.heliant.springproject.entiteti.Korisnik;
import com.heliant.springproject.izuzeci.KorisnickoImeIzuzetak;
import com.heliant.springproject.repozitorijumi.FormularRepozitorijum;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FormularServis {

    private final KorisnikServis korisnikServis;
    private final FormularRepozitorijum formularRepozitorijum;

    @Transactional(readOnly = true)
    public Page<Formular> nadjiSve(Pageable pageable) {
        return formularRepozitorijum.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Formular> nadjiKrozId(Long id) {
        return formularRepozitorijum.findById(id);
    }

    @Transactional
    public Formular sacuvaj(FormularDTO formularDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String korisnickoIme = authentication.getName();
        Korisnik kreator = korisnikServis.nadjiPremaKorisnickomImenu(korisnickoIme)
                .orElseThrow(() -> new KorisnickoImeIzuzetak("Korisnik sa ovim korisnickim imenom nije nadjen: " + korisnickoIme));
        Formular formular = FormularDTO.dtoUOriginal(formularDTO);
        formular.setKorisnikKreirao(kreator);
        formular.setKorisnikAzurirao(kreator);
        return formularRepozitorijum.save(formular);
    }

    @Transactional
    public Formular azuriraj(Long id, FormularDTO formularDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String korisnickoIme = authentication.getName();
        Korisnik korisnikKojiMenja = korisnikServis.nadjiPremaKorisnickomImenu(korisnickoIme)
                .orElseThrow(() -> new KorisnickoImeIzuzetak("Korisnik sa ovim korisnickim imenom nije nadjen: " + korisnickoIme));
        Formular formular = FormularDTO.dtoUOriginal(formularDTO);
        formular.setId(id);
        formular.setVremeIzmene(LocalDateTime.now());
        formular.setKorisnikAzurirao(korisnikKojiMenja);
        return formularRepozitorijum.save(formular);
    }

    @Transactional
    public void obrisi(Long id) {
        formularRepozitorijum.deleteById(id);
    }
}
