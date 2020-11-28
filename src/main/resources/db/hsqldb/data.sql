-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');


INSERT INTO proveedor(id,name,apellido,gmail,telefono) VALUES (1, 'taburete', 'El capo', 'eltitocapo@gmail.com',646464664);
INSERT INTO camarero(id,name,apellido,gmail,telefono,contrasena) VALUES (1, 'Victor', 'El Tito', 'eltitovictor@gmail.com',6464646464,'12345');
INSERT INTO manager(id,name,apellido,gmail,telefono,contrasena) VALUES (2, 'Alexander', 'El Tribandera', 'tribanderaAlexito@gmail.com', 678431588, '12345');
INSERT INTO propietario(id,name,apellido,gmail,telefono,contrasena) VALUES (1, 'Abdel', 'Ch', 'Abdch@gmail.com', 602354622, '12345');

INSERT INTO users(username,password,enabled) VALUES ('f3rangmor','f3rpass',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'f3rangmor','owner');
INSERT INTO owners VALUES (11, 'Fernando', 'Angulo', 'Av. Reina Mercede sn', 'Sevilla', '657356473', 'f3rangmor');
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'boxi', '2012-06-08', 2, 11);


INSERT INTO users(username,password,enabled) VALUES ('alisanhos','Pet_ASH.23',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'alisanhos','owner');
INSERT INTO owners VALUES (12, 'Alex', 'Hossdorf', 'Calle Tarfia', 'Sevilla', '695199919', 'alisanhos');
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Keka', '2019-05-23', 2, 12);


INSERT INTO users(username,password,enabled) VALUES ('horgarler','Tarfia75B',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'horgarler','owner');
INSERT INTO owners VALUES (13, 'Horacio', 'Garcia', 'Calle Tarfia', 'Sevilla', '670219587', 'horgarler');
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'Geko', '1997-01-06', 3, 13);


INSERT INTO users(username,password,enabled) VALUES ('vicmonpui','Flores54A',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'vicmonpui','owner');
INSERT INTO owners VALUES (14, 'Victor', 'Monteseirin', 'Calle Nogales', 'Sevilla', '678543534', 'vicmonpui');
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (17, 'Pogo', '2018-04-15', 4, 14);


INSERT INTO users(username,password,enabled) VALUES ('jostabrod','vEvRvOL123',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'jostabrod','owner');
INSERT INTO owners VALUES (15, 'Jose', 'Tabares', 'Calle Franco', 'Sevilla', '647235916', 'jostabrod');
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (18, 'Curro', '2015-01-18', 2, 15);
