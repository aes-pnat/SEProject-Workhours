CREATE TABLE Djelatnost
(
  idDjelatnost SERIAL PRIMARY KEY,
  naziv VARCHAR(100) NOT NULL,
  opis TEXT
);

CREATE TABLE Lokacija
(
  idLokacija SERIAL PRIMARY KEY,
  adresa VARCHAR(100),
  mjesto VARCHAR(50),
  lat DECIMAL(8, 6) NOT NULL,
  long DECIMAL(9, 6) NOT NULL
);

CREATE TABLE Uloga
(
  idUloga SERIAL PRIMARY KEY,
  naziv VARCHAR(50) NOT NULL
);

CREATE TABLE Djelatnik
(
  idDjelatnik SERIAL PRIMARY KEY,
  ime VARCHAR(50) NOT NULL,
  prezime VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  lozinka VARCHAR(50) NOT NULL,
  korisnickoIme VARCHAR(50) NOT NULL,
  oib CHAR(11) NOT NULL,
  idUloga INT NOT NULL,
  FOREIGN KEY (idUloga) REFERENCES Uloga(idUloga)
);

CREATE TABLE Grupa
(
  idGrupa SERIAL PRIMARY KEY,
  naziv VARCHAR(100) NOT NULL,
  idVoditelj INT NOT NULL,
  idDjelatnost INT,
  FOREIGN KEY (idVoditelj) REFERENCES Djelatnik(idDjelatnik),
  FOREIGN KEY (idDjelatnost) REFERENCES Djelatnost(idDjelatnost)
);

CREATE TABLE Zadatak
(
  idZadatak SERIAL PRIMARY KEY,
  naziv VARCHAR(100) NOT NULL,
  opis TEXT,
  datumVrijemePocetka TIMESTAMP NOT NULL,
  datumVrijemeZavrsetka TIMESTAMP NOT NULL,
  procjenaBrojaSati SMALLINT,
  planiranaDobit FLOAT,
  realiziranaDobit FLOAT,
  planiraniTrosak FLOAT,
  realiziraniTrosak FLOAT,
  idDjelatnost INT,
  idLokacija INT,
  FOREIGN KEY (idDjelatnost) REFERENCES Djelatnost(idDjelatnost),
  FOREIGN KEY (idLokacija) REFERENCES Lokacija(idLokacija),
  CONSTRAINT chkPocetakZavrsetak CHECK (
      datumVrijemePocetka <= datumVrijemeZavrsetka
  )
);

CREATE TABLE UnosRadnihSati
(
  idUnos SERIAL PRIMARY KEY,
  datum DATE NOT NULL,
  brRadnihSati SMALLINT NOT NULL,
  idDjelatnik INT NOT NULL,
  idZadatak INT NOT NULL,
  FOREIGN KEY (idDjelatnik) REFERENCES Djelatnik(idDjelatnik),
  FOREIGN KEY (idZadatak) REFERENCES Zadatak(idZadatak)
);

CREATE TABLE DjelatnikGrupa
(
  idDjelatnik INT NOT NULL,
  idGrupa INT NOT NULL,
  PRIMARY KEY (idDjelatnik, idGrupa),
  FOREIGN KEY (idDjelatnik) REFERENCES Djelatnik(idDjelatnik),
  FOREIGN KEY (idGrupa) REFERENCES Grupa(idGrupa)
);

CREATE TABLE DjelatnikZadatak
(
  idDjelatnik INT NOT NULL,
  idZadatak INT NOT NULL,
  realizacija SMALLINT,
  PRIMARY KEY (idDjelatnik, idZadatak),
  FOREIGN KEY (idDjelatnik) REFERENCES Djelatnik(idDjelatnik),
  FOREIGN KEY (idZadatak) REFERENCES Zadatak(idZadatak),
  CONSTRAINT chkRealizacija CHECK (
      realizacija BETWEEN 0 AND 100
  )
);