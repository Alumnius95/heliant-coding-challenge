package com.heliant.springproject.repozitorijumi;

import com.heliant.springproject.entiteti.Formular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularRepozitorijum extends JpaRepository<Formular, Long> {

}
