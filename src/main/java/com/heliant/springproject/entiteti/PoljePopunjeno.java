package com.heliant.springproject.entiteti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Polje_Popunjeno")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PoljePopunjeno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vrednost_tekst")
    private String vrednostTekst;

    @Column(name = "vrednost_broj")
    private Double vrednostBroj;

    @Column(name = "vreme_kreiranja", nullable = false)
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene", nullable = false)
    private LocalDateTime vremeIzmene;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formular_popunjen", nullable = false)
    private FormularPopunjen formularPopunjen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_polje", nullable = false)
    private Polje polje;
}
