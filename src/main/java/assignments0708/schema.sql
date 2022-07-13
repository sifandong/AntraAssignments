create database dummy;
use dummy;

DROP TABLE IF EXISTS teachers;
CREATE TABLE teachers (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  salary DECIMAL(15,2),
  PRIMARY KEY(id)
);

DROP TABLE IF EXISTS students;
CREATE TABLE students (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS teacher_student;
CREATE TABLE teacher_student (
    id INT unsigned NOT NULL AUTO_INCREMENT,
    student_id INT NOT NULL,
    teacher_id INT NOT NULL,
    PRIMARY KEY(id),
    KEY student_id (student_id),
    KEY teacher_id (teacher_id),
    CONSTRAINT teacher_student_ibfk_1 FOREIGN KEY (student_id) REFERENCES students (id) ON UPDATE CASCADE,
    CONSTRAINT teacher_student_ibfk_2 FOREIGN KEY (teacher_id) REFERENCES teachers (id) ON UPDATE CASCADE
);


INSERT INTO teachers(name, salary) VALUES("clark", 5000);
INSERT INTO teachers(name, salary) VALUES("ford", 4500);
INSERT INTO teachers(name, salary) VALUES("smith", 800);

INSERT INTO students(name) VALUES("martin");
INSERT INTO students(name) VALUES("jones");
INSERT INTO students(name) VALUES("scott");

INSERT INTO teacher_student(student_id, teacher_id) VALUES(1,2);
INSERT INTO teacher_student(student_id, teacher_id) VALUES(2,3);
INSERT INTO teacher_student(student_id, teacher_id) VALUES(3,3);
INSERT INTO teacher_student(student_id, teacher_id) VALUES(2,1);

SELECT teachers.name, salary, students.name AS student
FROM teacher_student AS ts JOIN students ON ts.student_id = students.id
JOIN teachers ON ts.teacher_id = teachers.id;

SELECT teachers.name, COUNT(students.name) AS num_stu
FROM teacher_student AS ts JOIN students ON ts.student_id = students.id
JOIN teachers ON ts.teacher_id = teachers.id
GROUP BY teachers.id;

SELECT name,salary as 2ndHighSalary
FROM (
      SELECT name, salary, dense_rank() OVER (ORDER BY salary DESC) dr FROM teachers) t
WHERE dr = 2;







