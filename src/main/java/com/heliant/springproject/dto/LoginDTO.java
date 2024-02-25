package com.heliant.springproject.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "Korisnicko ime je neophodno za login")
        String korisnickoIme,

        @NotBlank(message = "Lozinka je neophodna za login")
         String lozinka
) {}
