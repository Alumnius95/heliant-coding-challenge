package com.heliant.springproject.servisi;

import com.heliant.springproject.entiteti.Formular;
import com.heliant.springproject.repozitorijumi.FormularRepozitorijum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FormularServis {

    private final FormularRepozitorijum formularRepozitorijum;

    @Transactional(readOnly = true)
    public List<Formular> nadjiSve() {
        return formularRepozitorijum.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Formular> nadjiKrozId(Long id) {
        return formularRepozitorijum.findById(id);
    }

    @Transactional
    public Formular sacuvaj(Formular formular) {
        return formularRepozitorijum.save(formular);
    }

    @Transactional
    public Formular azuriraj(Formular formular) {
        return formularRepozitorijum.save(formular);
    }

    @Transactional
    public void obrisi(Long id) {
        formularRepozitorijum.deleteById(id);
    }
}
