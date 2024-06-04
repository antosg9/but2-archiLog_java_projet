drop database if exists media ;
create database media;
use media;

CREATE TABLE document
(
numDoc INTEGER,
titre VARCHAR(30) NOT NULL,
typeDoc VARCHAR(10) NOT NULL,
numAbo INTEGER,
stateDoc VARCHAR(30) NOT NULL,
adulte BOOLEAN DEFAULT false,
CONSTRAINT pk_document PRIMARY KEY (numDoc)
);

CREATE TABLE abonne
(
numAbo INTEGER,
nom VARCHAR(25) NOT NULL,
dateNaiss DATE NOT NULL,
CONSTRAINT pk_abonne PRIMARY KEY (numAbo)
);

ALTER TABLE document
ADD CONSTRAINT fk_document
	FOREIGN KEY(numAbo)
	REFERENCES abonne(numAbo)
	ON DELETE CASCADE;

insert into abonne(numAbo, nom, dateNaiss) values (1,'Rick', '2019-12-19');
insert into abonne(numAbo, nom, dateNaiss) values (2,'Shane','2004-09-27');
insert into abonne(numAbo, nom, dateNaiss) values (3,'Laurie','2014-02-24');
insert into abonne(numAbo, nom, dateNaiss) values (4,'T-Dog','1996-08-17');
insert into abonne(numAbo, nom, dateNaiss) values (5,'Carl','2010-06-22');

insert into document(numDoc, titre, typeDoc, numAbo, stateDoc, adulte) values (1,'Indiana Jones','DVD',NULL,'Disponible',false);
insert into document(numDoc, titre, typeDoc, numAbo, stateDoc, adulte) values (2,'The Walking Dead','DVD',NULL,'Disponible',true);
insert into document(numDoc, titre, typeDoc, numAbo, stateDoc, adulte) values (3,'-18 boy','DVD',NULL,'Disponible',true);
insert into document(numDoc, titre, typeDoc, numAbo, stateDoc, adulte) values (4,'Star Wars','DVD',NULL,'Disponible',false);
insert into document(numDoc, titre, typeDoc, numAbo, stateDoc, adulte) values (5,'Find a titlke','DVD',NULL,'Disponible',true);

commit;