package com.heliant.springproject.servisi;

import com.heliant.springproject.bezbednost.KorisnikPrincipal;
import com.heliant.springproject.entiteti.Korisnik;
import com.heliant.springproject.izuzeci.KorisnickoImeIzuzetak;
import com.heliant.springproject.repozitorijumi.KorisnikRepozitorijum;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KorisnickiDetaljiServis implements UserDetailsService {

    private KorisnikRepozitorijum korisnikRepozitorijum;

    @Override
    public UserDetails loadUserByUsername(String korisnickoIme) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikRepozitorijum.findByUsername(korisnickoIme)
                .orElseThrow(() -> new KorisnickoImeIzuzetak("Korisnik sa datim korisnickim imenom nije pronadjen!"));

        return KorisnikPrincipal.kreirajPrincipala(korisnik);
    }
}
