package com.heliant.springproject.entiteti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Formular_Popunjen")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FormularPopunjen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vreme_kreiranja", nullable = false)
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene", nullable = false)
    private LocalDateTime vremeIzmene;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formular", nullable = false)
    private Formular formular;

    @OneToMany(mappedBy = "formularPopunjen", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<PoljePopunjeno>popunjenaPolja;
}