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
  realized SMALLINT,
  PRIMARY KEY (idEmployee, idTask),
  FOREIGN KEY (idEmployee) REFERENCES Employee(pid),
  FOREIGN KEY (idTask) REFERENCES Task(idTask),
  CONSTRAINT chkRealized CHECK (
      realized BETWEEN 0 AND 100
  )
);
