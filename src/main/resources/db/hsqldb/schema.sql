DROP TABLE Student IF EXISTS;

CREATE TABLE students (
  id         VARCHAR(30) PRIMARY KEY,
  name  VARCHAR(30),
  gender  VARCHAR(8),
  birthday VARCHAR(30),
  phonenumber    VARCHAR(255),
  academy       VARCHAR(80)
);
CREATE INDEX students_name ON students (name);
