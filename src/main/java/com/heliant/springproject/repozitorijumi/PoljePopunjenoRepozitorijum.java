package com.heliant.springproject.repozitorijumi;

import com.heliant.springproject.entiteti.PoljePopunjeno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoljePopunjenoRepozitorijum extends JpaRepository<PoljePopunjeno, Long> {

}
