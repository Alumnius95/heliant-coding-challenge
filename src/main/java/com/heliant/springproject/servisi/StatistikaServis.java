package com.heliant.springproject.servisi;

import com.heliant.springproject.entiteti.Statistika;
import com.heliant.springproject.repozitorijumi.FormularPopunjenRepozitorijum;
import com.heliant.springproject.repozitorijumi.StatistikaRepozitorijum;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class StatistikaServis {

    private FormularPopunjenRepozitorijum formularPopunjenRepozitorijum;

    private StatistikaRepozitorijum statistikaRepozitorijum;

    @Scheduled(cron = "0 0 0 * * ?") // pokrece se svaki dan u ponoc
    public void generisiStatistiku() {
        // racunanje pocetka i kraja prethodnog dana
        LocalDateTime startPrethodnogDana = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIN);
        LocalDateTime krajPrethodnogDana = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX);

        // prebroj popunjene forme prethodnog dana
        int count = formularPopunjenRepozitorijum.prebrojiPopunjenePrethodnogDana(startPrethodnogDana, krajPrethodnogDana);

        // Napravi statistiku i sacuvaj je
        Statistika statistika = new Statistika();
        statistika.setDatum(LocalDate.now().minusDays(1));
        statistika.setBrojPopunjenihFormulara(count);
        statistikaRepozitorijum.save(statistika);
    }
}
