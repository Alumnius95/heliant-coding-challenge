package com.heliant.springproject.kontroleri;


import com.heliant.springproject.bezbednost.JwtAutentikacioniOdgovor;
import com.heliant.springproject.bezbednost.JwtTokenGenerator;
import com.heliant.springproject.dto.LoginDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AutentikacijaKontroler {

    private AuthenticationManager authenticationManager;
    private JwtTokenGenerator jwtTokenGenerator;

    @PostMapping("/login")
    public ResponseEntity<JwtAutentikacioniOdgovor> authenticateUser(@RequestBody LoginDTO loginZahtev) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginZahtev.getKorisnickoIme(),
                        loginZahtev.getLozinka()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenGenerator.generisiToken(authentication);
        return ResponseEntity.ok(new JwtAutentikacioniOdgovor(jwt));
    }
}
