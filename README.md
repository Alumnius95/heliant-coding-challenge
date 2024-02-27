# feature/form-management-system
Grana koja sadrži komitove u kojima su impelentirani zahtevi zadatka.

# Struktura
Za potrebe zadatka korišćen je Controller-Service-Repository patern. Za svaki od navedenih slojeva napravljen je zaseban paket. U svakom od paketa se za odgovarajući entitet nalazi i odgovarajuća Kontroler, Servis i Repozitorijum klasa čija je uloga da se nosi sa neophodnim CRUD operacijama za zadati entitet. Kontroler klase su napravljene kako bi prihvatale REST zahteve i iste prosleđivale do Servis sloja koji je zadužen za glavnu biznis logiku vezanu za date entitete. Repozitorijum sloj služi za komunikaciju i operacija sa bazom podataka. Nakon obrađenih zahteva, a u odnosu na ishod operacije, Kontroler sloj vraća klijentu nazad odgovor u formi ResponseEntity klase, i odgovarajućim HTTP status kodom. Za bezbednosni sloj napravljen je poseban paket kao i jedan endpoint koji inicijalno služi za ulogovanje putem korisničkom imena i lozinke, nakon toga generiše se token pomoću kog korisnik dokazuje svoj identitet i rolu i koji pruža uz svoje zahteve koje šalje na druge endpointe.

# Entiteti
Entiteti su podeljeni u dva paketa: entiteti i dto.Prilikom kreiranja entiteta, načinjena je distinkcija između običnih (template) verzija entiteta i popunjenih verzija istih. Shodno tome korišćenjem anotacija uspostavljene su relacije između njih, ali i druge ostale veze koje su neophodne (Polje <-> Formular, PoljePopunjeno <-> FormularPopunjen). Takođe manuelno je implementirana logika za situacije gde framework ne može da održi referencijalni integirtet u samoj Javinoj aplikaciji (npr brisanje PopunjeneStavke direktno preko endpointa, ali i na mnogim drugim mestima). Takođe, napravljeni su DTO entiteti kako korisnik ne bi morao da šalje sve informacije vezane za određeni entitet (gde neke nisu ni poželjne da se šalju), pogotovo prilikom uvezivanja popunjenih varijanti entiteta sa običnim verzijama (zasebno direktno preko endpointa). Za DTO entitete korišćeni su records.

# Validacija
Prilikom validacije korišćene su dve stvari: anotacije i kastomizovani izuzeci (za specifične slučaje kada anotacije nisu dovoljne). Ukoliko korisnik unese pripadajuće template verzije za koje vezujemo popunjene verzije entiteta, ali entiteta sa tim ID-em nema u bazi, anotacije u datom slučaju nisu dovoljne (@NotNull npr.) pa su kreirani kastomizovani izuzeci poput: FormularPopunjenIzuzetak. Naravno, prva linija odbrane jesu anotacije, te je u te svrhe korišćena kombinacija anotacija @Valid i @RequestBody među parametrima metoda.

# Izuzeci
Za potrebe obrade izuzezaka koristi se globalni hendler izuzetaka. Kada dođe do određenog izuzetka, on se šalje do globalnog hendlera gde se u odgovarajućoj metodi prikupljaju informacije od samog izuzetka i to se šalje kao ResponseEnitity do klijenta. ErrorOdgovor je omotač za izuzetke gde ubacujemo poruku, vreme nastanka i željenu http poruku.

# Bezbednost
Za bezbednost korišćen je JWT i OAuth2. Iako ovo nije bio direktan zahtev zadatka, kreiran je specijalni ključ koji predstavlja jedini način da neko kreira korisnika sa određenom rolom (deo neophodnih polja KorisnikDTO). Ovaj ključ se nalazi u application.properties fajlu. Takođe, kada se korisnik kreira, njegova lozinka se enkriptuje putem PasswordEncodera (BCrypt). Zarad logovanja, korisnik je dužan da dostavi korisničko ime i lozinku na login endpoint, nakon toga dolazi do autentikacije, i ukoliko je ona uspešna, generator JWT tokena generiše potpisan token. Ovaj token korisnik koristi za dalji pristup endpointima (šalje ga uz svoj zahtev). Dolazi do pregledanja tokena (autentikacija i autorizacija) kroz filter chain, i ako je JWT token ispravan, zahtev dolazi do endponta.

# Liquibase
Sve stavke vezane za shemu podataka kreiraju se putem liquibase-a gde postoje dva fajla: changelog-master i initial-schema. Master fajl je tu da prati sve izmene do kojih može doći (u shemi), a shema je tu da definiše tabele i njihove relacije. U svakom changeSet-u definisana je odgovarajuća tabela.

# Application.properties
Ovo je fajl gde se nalaze neophodne konfiguracije (url do baze, korisničko ime, lozinka, putanja do liquibase-a, specijalni kljuć, jwt secret key, trajanje jwt-a...)

# Statistika
Statistika se vadi svakog dana u ponoć i radi se prebrojavanje popunjenih formulara prethodnog dana. Ovo je implementirano u StatistikaServis putem @Scheduled anotacije (cron job).
