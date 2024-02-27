# heliant-coding-challenge
Repozitorijum kreiran za potrebe izrade zadatka u procesu selekcije kompanije Heliant.

# Napomena
Sve stavke koje se tiču samog zadatka nalaze se na grani feature/form-management-system.

Tokom izrade klasa u javi korišćena je srpska terminologija, kako bi bila u skladu sa nazivima tabela (iako je naravno moguće podesiti da se ne podudaraju).

Zbog lakše preglednosti komitova i dobre prakse, otvoren je Pull request na kome se mogu videti zasebni komitovi.

Od pomenutih zahteva zadatka u komitovima se nalaze:
+ inicijalizacija aplikacije koristeći Spring Boot
+ Korićenje MySQLa (odnosno MySQL drivera uz liquibase)
+ omogućena je autentifikacija korišćenjem JWT tokena
+ implementeriane su CRUD operacije za definisane entitete
+ implementirana je validacija (uz anotacije i definisane izuzetke za specijalne slučajeve)
+ kreiran je task koji svakog dana u ponoć prebrojava popunjene formulare prethodnog dana
+ ubačena su dodatna polja i podešeno njihovo automatsko setovanje
+ korišćenje liquibase-a
+ implementirana je autorizacija korisnika (i definisane role i ograničenja kroz njih: ADMIN i RADNIK)
+ implementirana je paginacija pri dohvatanju svih formulara

Detaljniji opis koda je u README fajlu feature/form-management-system grane.

