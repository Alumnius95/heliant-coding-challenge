package com.heliant.springproject.entiteti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Polje_Popunjeno")
@NoArgsConstructor
@AllArgsConstructor
public class PoljePopunjeno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formular_popunjen")
    private FormularPopunjen formularPopunjen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_polje")
    private Polje polje;

    @Column(name = "vrednost_tekst", nullable = false)
    private String vrednostTekst;

    @Column(name = "vrednost_broj", nullable = false)
    private Double vrednostBroj;

    @Column(name = "vreme_kreiranja", nullable = false)
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene", nullable = false)
    private LocalDateTime vremeIzmene;
}
