package com.heliant.springproject.repozitorijumi;

import com.heliant.springproject.entiteti.FormularPopunjen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface FormularPopunjenRepozitorijum extends JpaRepository<FormularPopunjen, Long> {
    int prebrojiPopunjenePrethodnogDana(LocalDateTime start, LocalDateTime end);
}
