package com.heliant.springproject.repozitorijumi;

import com.heliant.springproject.entiteti.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KorisnikRepozitorijum extends JpaRepository<Korisnik, Long> {
    Optional<Korisnik> findByUsername(String username);
}
