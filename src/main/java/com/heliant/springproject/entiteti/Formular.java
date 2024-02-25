package com.heliant.springproject.entiteti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Formular")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Formular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "naziv", nullable = false, length = 256)
    private String naziv;

    @Column(name = "vreme_kreiranja", nullable = false)
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene", nullable = false)
    private LocalDateTime vremeIzmene;

    @OneToMany(mappedBy = "formular", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Polje> polja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_korisnik_kreirao", nullable = false)
    private Korisnik korisnikKreirao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_korisnik_poslednji_azurirao", nullable = false)
    private Korisnik korisnikAzurirao;

    @OneToMany(mappedBy = "formular", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<FormularPopunjen> popunjeniFormulari;
}
