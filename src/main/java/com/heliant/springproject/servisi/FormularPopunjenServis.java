package com.heliant.springproject.servisi;

import com.heliant.springproject.entiteti.Formular;
import com.heliant.springproject.entiteti.FormularPopunjen;
import com.heliant.springproject.repozitorijumi.FormularPopunjenRepozitorijum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FormularPopunjenServis {

    private final FormularPopunjenRepozitorijum formularPopunjenRepozitorijum;

    @Transactional(readOnly = true)
    public List<FormularPopunjen> nadjiSve() {
        return formularPopunjenRepozitorijum.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<FormularPopunjen> nadjiKrozId(Long id) {
        return formularPopunjenRepozitorijum.findById(id);
    }

    @Transactional
    public FormularPopunjen sacuvaj(FormularPopunjen formularPopunjen) {
        return formularPopunjenRepozitorijum.save(formularPopunjen);
    }

    @Transactional
    public FormularPopunjen azuriraj(FormularPopunjen formularPopunjen) {
        return formularPopunjenRepozitorijum.save(formularPopunjen);
    }

    @Transactional
    public void obrisi(Long id) {
        formularPopunjenRepozitorijum.deleteById(id);
    }
}
