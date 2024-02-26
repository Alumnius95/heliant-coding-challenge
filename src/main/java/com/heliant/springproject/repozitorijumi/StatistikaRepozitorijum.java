package com.heliant.springproject.repozitorijumi;

import com.heliant.springproject.entiteti.Statistika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatistikaRepozitorijum extends JpaRepository<Statistika, Long> {

}
