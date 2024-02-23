package com.heliant.springproject.repozitorijumi;

import com.heliant.springproject.entiteti.Polje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoljeRepozitorijum extends JpaRepository<Polje, Long> {

}
