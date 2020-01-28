Delete from patient;
Delete from appointment;

INSERT INTO patient
(`name`, `cpf`, `phone`, `birthday`, `gender`)
VALUES
("Ricardo de Melo", "12345678910", "12345678910", "1989-04-29", 0),
("Bruno", "10123456789", "10123456789", "1986-02-10", 0),
("Jonas", "10987654321", "10987654321", "1987-12-03", 0),
("Washington", "98765432101", "98765432101", "1988-07-14", 0),
("Rinaldo", "12312312312", "12312312312", "1982-10-30", 0);

INSERT INTO appointment
(`date`, `remarked`, `specialty`, `value`, `patient_id`)
VALUES
("2020-03-20", false, "Ortodontista", 300, (select id from `odontoSystem`.`patient` where name = "Ricardo de Melo")),
("2020-02-22", false, "Dentista", 500, (select id from `odontoSystem`.`patient` where name = "Bruno")),
("2020-03-15", false, "Ortodontista", 50, (select id from `odontoSystem`.`patient` where name = "Jonas")),
("2020-04-01", false, "Dentista", 1000, (select id from `odontoSystem`.`patient` where name = "Washington")),
("2020-03-02", false, "Ortodontista", 850, (select id from `odontoSystem`.`patient` where name = "Rinaldo"));
