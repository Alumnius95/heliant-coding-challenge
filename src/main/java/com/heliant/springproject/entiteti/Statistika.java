package com.heliant.springproject.entiteti;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Statistika")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Statistika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datum", nullable = false)
    private LocalDate datum;

    @Column(name = "broj_popunjenih_formulara", nullable = false)
    private Integer brojPopunjenihFormulara;

}
