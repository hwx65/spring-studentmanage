DROP TABLE Student IF EXISTS;

CREATE TABLE students (
  id         INTEGER IDENTITY PRIMARY KEY,
  name  VARCHAR(30),
  gender  VARCHAR(8),
  birthday DATE,
  phonenumber    VARCHAR(255),
  academy       VARCHAR(80)
);
CREATE INDEX students_stuname ON students (stuname);
