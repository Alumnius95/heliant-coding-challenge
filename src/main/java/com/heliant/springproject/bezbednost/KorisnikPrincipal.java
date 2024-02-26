package com.heliant.springproject.bezbednost;

import com.heliant.springproject.entiteti.Korisnik;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class KorisnikPrincipal implements UserDetails {

    private Long id;
    private String korisnickoIme;
    private String lozinka;
    private Collection<? extends GrantedAuthority> role;

    public static KorisnikPrincipal kreirajPrincipala(Korisnik korisnik) {
        GrantedAuthority rola = new SimpleGrantedAuthority(korisnik.getRola().name());
        return new KorisnikPrincipal(korisnik.getId(),
                korisnik.getKorisnickoIme(),
                korisnik.getLozinka(),
                Collections.singletonList(rola));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return lozinka;
    }

    @Override
    public String getUsername() {
        return korisnickoIme;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
