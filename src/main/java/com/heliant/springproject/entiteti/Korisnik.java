package com.heliant.springproject.entiteti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Korisnik")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "korisnicko_ime", nullable = false, unique = true, length = 256)
    private String korisnickoIme;

    @Column(nullable = false, length = 256)
    private String lozinka;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Rola rola;

    @Column(name = "vreme_kreiranja", nullable = false)
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene",nullable = false)
    private LocalDateTime vremeIzmene;

    @OneToMany(mappedBy = "korisnikKreirao", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Formular> kreiraniFormulari;

    @OneToMany(mappedBy = "korisnikAzurirao", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Formular> azuriraniFormulari;
}
