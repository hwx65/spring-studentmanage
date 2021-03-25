DROP TABLE Student IF EXISTS;

CREATE TABLE students (
  id         INTEGER IDENTITY PRIMARY KEY,
  stuname VARCHAR(30),
  gender  VARCHAR(8),
  birth_date DATE,
  hometown    VARCHAR(255),
  academy       VARCHAR(80),
  studentnumber  VARCHAR(20)
);
CREATE INDEX students_stuname ON students (stuname);
