DROP TABLE Job CASCADE;
CREATE TABLE Job
(
  idJob SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  price FLOAT NOT NULL,
  hourPrice FLOAT NOT NULL,
  description TEXT
);

DROP TABLE Location CASCADE;
CREATE TABLE Location
(
  idLocation SERIAL PRIMARY KEY,
  address VARCHAR(100),
  placeName VARCHAR(50),
  latitude DECIMAL(8, 6) NOT NULL,
  longitude DECIMAL(9, 6) NOT NULL
);

DROP TABLE Role CASCADE;
CREATE TABLE Role
(
  idRole SERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

DROP TABLE Employee CASCADE;
CREATE TABLE Employee
(
  pid CHAR(11) PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(256) NOT NULL,
  username VARCHAR(50) NOT NULL,
  idRole INT NOT NULL,
  FOREIGN KEY (idRole) REFERENCES Role(idRole)
);

DROP TABLE "group" CASCADE;
CREATE TABLE "group"
(
  idGroup SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  idLeader CHAR(11) NOT NULL,
  idJob INT,
  FOREIGN KEY (idLeader) REFERENCES Employee(pid),
  FOREIGN KEY (idJob) REFERENCES Job(idJob)
);

DROP TABLE Task CASCADE;
CREATE TABLE Task
(
  idTask SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description TEXT,
  dateTimeStart TIMESTAMP NOT NULL,
  dateTimeEnd TIMESTAMP NOT NULL,
  hoursNeededEstimate SMALLINT,
  idJob INT,
  idLocation INT,
  FOREIGN KEY (idJob) REFERENCES Job(idJob),
  FOREIGN KEY (idLocation) REFERENCES Location(idLocation),
  CONSTRAINT chkStartEnd CHECK (
      dateTimeStart <= dateTimeEnd
  )
);

DROP TABLE WorkHoursInput CASCADE;
CREATE TABLE WorkHoursInput
(
  idWorkHoursInput SERIAL PRIMARY KEY,
  date DATE NOT NULL,
  workHoursDone SMALLINT NOT NULL,
  idEmployee CHAR(11) NOT NULL,
  idTask INT NOT NULL,
  FOREIGN KEY (idEmployee) REFERENCES Employee(pid),
  FOREIGN KEY (idTask) REFERENCES Task(idTask)
);

DROP TABLE EmployeeGroup CASCADE;
CREATE TABLE EmployeeGroup
(
  idEmployee CHAR(11) NOT NULL REFERENCES Employee ON DELETE CASCADE ON UPDATE CASCADE,
  idGroup INT NOT NULL REFERENCES "group" ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (idEmployee, idGroup),
  FOREIGN KEY (idEmployee) REFERENCES Employee(pid),
  FOREIGN KEY (idGroup) REFERENCES "group"(idGroup)
);

DROP TABLE EmployeeTask CASCADE;
CREATE TABLE EmployeeTask
(
  idEmployee CHAR(11) NOT NULL REFERENCES Employee ON DELETE CASCADE ON UPDATE CASCADE,
  idTask INT NOT NULL REFERENCES Task ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (idEmployee, idTask),
  FOREIGN KEY (idEmployee) REFERENCES Employee(pid),
  FOREIGN KEY (idTask) REFERENCES Task(idTask)
);

INSERT INTO Role (name)
VALUES 
('employee'),
('leader'),
('owner');

INSERT INTO Employee(pid, name, surname, email, password, username, idRole)
VALUES
('00000000001', 'Heena', 'Wang', 'heena.wang@email.com', '$2a$12$n5lr.aSBHk1w9KVJfNMMXumkLjXbGW1Ht3Yjo87zY5JkNeD1udIlS', 'hWang', 2),
('00000000002', 'Amirah', 'Bevan', 'amirah.bevan@email.com', '$2a$12$I/37/Nr6UKNx4P/SzsIbUep7AspbvxqUQQNEd3KjbUkPW8IqT6eX6', 'aBevan', 1),
('00000000003', 'Nolan', 'Salgado', 'nolan.salgado@email.com', '$2a$12$DB172kCc3RhMJjSF9KymXe5I1SU7EAw4VFQu3Vwjoz411XmNRKHDq', 'nSalgado', 1),
('00000000004', 'Eamon', 'Donald', 'eamon.donald@email.com', '$2a$12$kYUdzCfzSd8vtlXtE4JftOTpCQ.nxfT36i44fGZTN6qzXxs/Mcwia', 'eDonald', 1),
('00000000005', 'Burhan', 'Whittle', 'burhan.whittle@email.com', '$2a$12$1PjCK54js8h.ARcccwohZO9PEiG3ybpy6TK2ycUk7z5A7FA9b/ZH6', 'bWhittle', 2),
('00000000006', 'Juan', 'Crane', 'juan.crane@email.com', '$2a$12$VeCUwyYGTxG2Pi1MLtU1GuO38jNqPXsrAOFarub.Bjp6wSc00ApSW', 'jCrane', 1),
('00000000007', 'Ameera', 'Thatcher', 'ameera.thatcher@email.com', '$2a$12$fMhqZ3lQfY0ZKwx0rhsHEeOelMvhGR3tij1dFBXZMmoYiEoTV8C5W', 'aThatcher', 1),
('00000000008', 'Akbar', 'Francis', 'akbar.francis@email.com', '$2a$12$WN6gmV9WMWN1zGHdmnIE5eK.mm01ai5JGVOFTNHl0wLhZa//9yOjq', 'aFrancis', 1),
('00000000009', 'Maxwell', 'Keeling', 'maxwell.keeling@email.com', '$2a$12$JfBIQXtl6exAksQeQEaXXOcy8SaAACxGFBW3xCUfJfqGByiOFDO..', 'mKeeling', 2),
('00000000010', 'Andreea', 'Ballard', 'andreea.ballard@email.com', '$2a$12$ob.GC2tEFhMH8/c.4vUDXOlFSv/ugQD.P4h9W.nXJitcKiuy3LwZu', 'aBallard', 1),
('00000000011', 'Irene', 'Mckee', 'irene.mckee@email.com', '$2a$12$DRSEILIqwO6hRxr.FtgxbuVpK66oBJzPDUpRGUnSFe7mcsYuR8Kea', 'iMckee', 1),
('00000000012', 'Anwen', 'Figueroa', 'anwen.figueroa@email.com', '$2a$12$l.WwyTL5hdGmEjim1PXaS.bDYH5dsE3T4QEof7LyVuXiXSFn6pkJq', 'aFigueroa', 1),
('00000000013', 'Kunal', 'Rowland', 'kunal.rowland@email.com', '$2a$12$bgnzQ8JhqI.kZ.BMq08CLOP.IbLmbcvv/g/9aPxgKNrOxj78vsUgO', 'kRowland', 2),
('00000000014', 'Cooper', 'Benitez', 'cooper.benitez@email.com', '$2a$12$op2BlRHaFjPIhpO2uLCPgu3U8pNiZXuA42xQp.4DV8z3cUguyteSK', 'cBenitez', 1),
('00000000015', 'Farrah', 'Roach', 'farrah.roach@email.com', '$2a$12$GwM6SUSxj/j3gEeLskAQcO04Js/7RLylzILTS6xqk29nGGQzaG.fq', 'fRoach', 1),
('00000000016', 'Maureen', 'Webb', 'maureen.webb@email.com', '$2a$12$pL8DJrdCPec26L7laUH.3u8vu7ptqrVhlhmdiE3VjiDDCBVuT1TPW', 'mWebb', 1),
('00000000017', 'Renesmee', 'Harper', 'renesmee.harper@email.com', '$2a$12$r5kKsmf3rf6S39UhoTadu.UWND73fvNSN6SwPPts0mswsWgQ61j8q', 'rHarper', 2),
('00000000018', 'Maliha', 'Howe', 'maliha.howe@email.com', '$2a$12$b2dcOa92QZ.6rwCnkFkdYO2XDnJ96q7weyWtOHe8UkrsK4M5z6Dti', 'mHowe', 1),
('00000000019', 'Ted', 'Savage', 'ted.savage@email.com', '$2a$12$UMYpABGXLefrUhgwycSWyeDDnuaBGVJ8RXMSsPmvTqBaMpvd3.1GW', 'tSavage', 1),
('00000000020', 'Inayah', 'Goulding', 'inayah.goulding@email.com', '$2a$12$tVOC6dQoekYWqrPeg6h22eDps2GB2vECfckLOqEG6AJh46R7eybL2', 'iGoulding', 1),
('00000000021', 'Ciaron', 'Talley', 'ciaron.talley@email.com', '$2a$12$Vu1AwhWq1Pht8Y9zgH558eLU8DNWqh2XFYxM8XjW3MUJeGEQ9ZCd6', 'cTalley', 3);

INSERT INTO Job (name, price, hourPrice, description)
VALUES
('Sustav za navodnjavanje', 120000, 55, 'Napraviti sustav za automatsko navodnjavanje koji u odredenim periodima navodnjava.'),
('Web aplikacija za online trgovinu', 60000, 40, 'Napraviti web aplikaciju za online trgovinu racunalnih komponenti.'),
('Sustav za pracenje kvalitete zraka', 100000, 50, 'Napraviti sustav za pracenje kvalitete zraka.'),
('Sustav za automatsko parkiranje automobila', 400000, 65, 'Napraviti sustav koji omogucava automobilima da se sami parkiraju.'),
('Web aplikacija za pracenje radnih sati', 60000, 40, 'Napraviti web aplikaciju za pracenje radnih sati zaposlenika firme.'),
('Web stranica za restoran', 10000, 40, 'Napraviti web stranicu za restoran.');

INSERT INTO "group" (name, idLeader, idJob)
VALUES
('Group 1', '00000000001', 1),
('Group 2', '00000000005', 2),
('Group 3', '00000000010', 3),
('Group 4', '00000000015', 4),
('Group 5', '00000000020', 5),
('Group 6', '00000000001', 6);

INSERT INTO EmployeeGroup (idEmployee, idGroup)
VALUES
('00000000002', 1),
('00000000003', 1),
('00000000004', 1),
('00000000006', 2),
('00000000007', 2),
('00000000008', 2),
('00000000010', 3),
('00000000012', 3),
('00000000013', 3),
('00000000014', 4),
('00000000015', 4),
('00000000016', 4),
('00000000018', 5),
('00000000019', 5),
('00000000020', 5),
('00000000006', 6),
('00000000010', 6),
('00000000014', 6);

INSERT INTO Location (address, placeName, latitude, longitude)
VALUES
('Mlinksa ulica 71', 'Pozega', 45.358930, 17.682673);

INSERT INTO Task (name, description, dateTimeStart, dateTimeEnd, hoursNeededEstimate, idJob, idLocation)
VALUES
('Nabava', 'Nabava materijala i komponenti potrebnih za sustav.', '2020-02-10 09:00:00', '2020-02-20 17:00:00', 20, 1, null),
('Pregled terena', 'Pregled i mjerenje terena na kojemu se ugraduje sustav.', '2020-02-10 09:00:00', '2020-02-20 17:00:00', 20, 1, 1),
('Izrada programske potpore', 'Izrada programske potpore za sustav.', '2020-02-21 09:00:00', '2020-05-15 17:00:00', 640, 1, null),
('Izrada dokumentacije', 'Pisanje dokumentacije sustava.', '2020-02-10 09:00:00', '2020-05-20 17:00:00', 80, 1, null),
('Izrada korisnickih uputa', 'Pisanje korisnickih uputa za koristenje sustava.', '2020-05-10 09:00:00', '2020-05-20 17:00:00', 20, 1, null),
('Izrada plana rada', 'Izrada plana rada projekta.', '2020-02-10 09:00:00', '2020-02-20 17:00:00', 20, 1, null),
('Izrada plana rada', 'Izrada plana rada projekta.', '2021-10-04 09:00:00', '2021-11-03 17:00:00', 20, 5, null),
('Izrada dokumentacije', 'Pisanje dokumentacije sustava.', '2021-10-04 09:00:00', '2022-01-21 17:00:00', 80, 5, null),
('Razrada arhitekture sustava', 'Razrada arhitekture sustava.', '2021-11-04 09:00:00', '2021-11-17 17:00:00', 20, 5, null),
('Implementacija generickih funkcionalnosti', 'Implementacija registriranja i prijave korisnika.', '2021-11-18 09:00:00', '2021-12-10 17:00:00', 126, 5, null),
('Implementacija projekta', 'Implementacija trazenih funkcionalnosti sustava.', '2021-12-11 09:00:00', '2022-01-07 17:00:00', 168, 5, null),
('Ispitivanje aplikacije', 'Ispitivanje funckionalnosti sustava.', '2022-01-08 09:00:00', '2022-01-14 17:00:00', 21, 5, null),
('Dovrsavanje implementacije i pustanje u pogon', 'Popravljanje funckionalnosti i pustanje u pogon.', '2022-01-15 09:00:00', '2022-01-21 17:00:00', 42, 5, null);

INSERT INTO EmployeeTask (idEmployee, idTask)
VALUES
('00000000001', 1),
('00000000001', 2),
('00000000001', 3),
('00000000001', 6),
('00000000002', 2),
('00000000002', 3),
('00000000002', 4),
('00000000003', 3),
('00000000003', 5),
('00000000004', 3),
('00000000004', 5),
('00000000020', 7),
('00000000020', 8),
('00000000020', 9),
('00000000018', 10),
('00000000018', 11),
('00000000019', 12),
('00000000019', 13);

