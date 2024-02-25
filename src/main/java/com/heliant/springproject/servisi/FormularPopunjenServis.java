package com.heliant.springproject.servisi;

import com.heliant.springproject.dto.FormularPopunjenDTO;
import com.heliant.springproject.dto.PoljePopunjenoDTO;
import com.heliant.springproject.entiteti.Formular;
import com.heliant.springproject.entiteti.FormularPopunjen;
import com.heliant.springproject.entiteti.PoljePopunjeno;
import com.heliant.springproject.izuzeci.FormularPopunjenIzuzetak;
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
        FormularPopunjen formularPopunjen = FormularPopunjenDTO.
                dtoUOriginal(formularPopunjenDTO, new FormularPopunjen());
        formularPopunjen.setFormular(formular.get());
        return formularPopunjenRepozitorijum.save(formularPopunjen);
    }

    @Transactional
    public FormularPopunjen azuriraj(FormularPopunjenDTO formularPopunjenDTO, Optional<Formular> formular, Long id) {
        FormularPopunjen formularPopunjen = FormularPopunjenDTO.dtoUOriginal(formularPopunjenDTO, new FormularPopunjen());
        formularPopunjen.setId(id);
        formularPopunjen.setVremeIzmene(LocalDateTime.now());
        if (formular.isPresent() && !Objects.equals(formular.get().getId(), id)) {
            formularPopunjen.setPopunjenaPolja(new HashSet<>()); //setujemo ga na neki drugi formular, time radimo reset polja
        }
        formularPopunjen.setId(id);
        return formularPopunjenRepozitorijum.save(formularPopunjen);
    }

    @Transactional
    public void obrisi(FormularPopunjen formularPopunjen, Long id) {
        Formular formular = formularPopunjen.getFormular();
        formular.getPopunjeniFormulari().remove(formularPopunjen);
        formularPopunjenRepozitorijum.deleteById(id);
    }
}
