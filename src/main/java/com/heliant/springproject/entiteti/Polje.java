package com.heliant.springproject.entiteti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Polje")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Polje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 256)
    private String naziv;

    @Column(name = "prikazni_redosled")
    private Integer prikazniRedosled;

    @Column(name = "vreme_kreiranja", nullable = false)
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene", nullable = false)
    private LocalDateTime vremePoslednjeIzmene;

    @Enumerated(EnumType.STRING)
    private Tip tip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formular", nullable = false)
    private Formular formular;

    @OneToMany(mappedBy = "polje", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PoljePopunjeno>popunjenaPolja;
}
