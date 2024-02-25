package com.heliant.springproject.repozitorijumi;

import com.heliant.springproject.entiteti.FormularPopunjen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface FormularPopunjenRepozitorijum extends JpaRepository<FormularPopunjen, Long> {
    @Query("SELECT COUNT(fp) FROM FormularPopunjen fp WHERE (fp.vremeKreiranja BETWEEN :startPrethodnogDana AND :krajPrethodnogDana) OR (fp.vremeIzmene BETWEEN :startPrethodnogDana AND :krajPrethodnogDana)")
    int prebrojiPopunjenePrethodnogDana(LocalDateTime startPrethodnogDana, LocalDateTime krajPrethodnogDana);
}
