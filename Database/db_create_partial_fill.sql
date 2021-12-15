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
  password VARCHAR(50) NOT NULL,
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
  idEmployee CHAR(11) NOT NULL,
  idGroup INT NOT NULL,
  PRIMARY KEY (idEmployee, idGroup),
  FOREIGN KEY (idEmployee) REFERENCES Employee(pid),
  FOREIGN KEY (idGroup) REFERENCES "group"(idGroup)
);

DROP TABLE EmployeeTask CASCADE;
CREATE TABLE EmployeeTask
(
  idEmployee CHAR(11) NOT NULL,
  idTask INT NOT NULL,
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
('00000000001', 'Heena', 'Wang', 'heena.wang@email.com', 'pass', 'hWang', 2),
('00000000002', 'Amirah', 'Bevan', 'amirah.bevan@email.com', 'pass', 'aBevan', 1),
('00000000003', 'Nolan', 'Salgado', 'nolan.salgado@email.com', 'pass', 'nSalgado', 1),
('00000000004', 'Eamon', 'Donald', 'eamon.donald@email.com', 'pass', 'eDonald', 1),
('00000000005', 'Burhan', 'Whittle', 'burhan.whittle@email.com', 'pass', 'bWhittle', 2),
('00000000006', 'Juan', 'Crane', 'juan.crane@email.com', 'pass', 'jCrane', 1),
('00000000007', 'Ameera', 'Thatcher', 'ameera.thatcher@email.com', 'pass', 'aThatcher', 1),
('00000000008', 'Akbar', 'Francis', 'akbar.francis@email.com', 'pass', 'aFrancis', 1),
('00000000009', 'Maxwell', 'Keeling', 'maxwell.keeling@email.com', 'pass', 'mKeeling', 2),
('00000000010', 'Andreea', 'Ballard', 'andreea.ballard@email.com', 'pass', 'aBallard', 1),
('00000000011', 'Irene', 'Mckee', 'irene.mckee@email.com', 'pass', 'iMckee', 1),
('00000000012', 'Anwen', 'Figueroa', 'anwen.figueroa@email.com', 'pass', 'aFigueroa', 1),
('00000000013', 'Kunal', 'Rowland', 'kunal.rowland@email.com', 'pass', 'kRowland', 2),
('00000000014', 'Cooper', 'Benitez', 'cooper.benitez@email.com', 'pass', 'cBenitez', 1),
('00000000015', 'Farrah', 'Roach', 'farrah.roach@email.com', 'pass', 'fRoach', 1),
('00000000016', 'Maureen', 'Webb', 'maureen.webb@email.com', 'pass', 'mWebb', 1),
('00000000017', 'Renesmee', 'Harper', 'renesmee.harper@email.com', 'pass', 'rHarper', 2),
('00000000018', 'Maliha', 'Howe', 'maliha.howe@email.com', 'pass', 'mHowe', 1),
('00000000019', 'Ted', 'Savage', 'ted.savage@email.com', 'pass', 'tSavage', 1),
('00000000020', 'Inayah', 'Goulding', 'inayah.goulding@email.com', 'pass', 'iGoulding', 1),
('00000000021', 'Ciaron', 'Talley', 'ciaron.talley@email.com', 'pass', 'cTalley', 3);

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