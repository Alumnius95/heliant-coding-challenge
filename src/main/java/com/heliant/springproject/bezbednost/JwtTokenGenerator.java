package com.heliant.springproject.bezbednost;

import com.heliant.springproject.izuzeci.NevalidanTokenIzuzetak;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenGenerator {

    @Value("${app.jwtKljuc}")
    private String jwtKljuc;

    @Value("${app.jwtTrajanjeMs}")
    private int jwtTrajanjeMs;

    public String generisiToken(Authentication authentication) {
        KorisnikPrincipal korisnikPrincipal = (KorisnikPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtTrajanjeMs);

        return Jwts.builder()
                .setSubject(korisnikPrincipal.getUsername())
                .claim("authorities", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtKljuc)
                .compact();
    }

    public boolean validirajToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtKljuc.getBytes()).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new NevalidanTokenIzuzetak("Istekao ili nevalidan token!");
        }
    }

    public String izvuciKorisnickoImeIzJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtKljuc)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
