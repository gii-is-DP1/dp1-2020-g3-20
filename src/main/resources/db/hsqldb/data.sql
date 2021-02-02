--USUARIOS Y AUTHORITIES--
INSERT INTO users(username,password,enabled) VALUES ('prince','12345',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'prince','propietario');

INSERT INTO users(username,password,enabled) VALUES ('marcos','12345',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'marcos','manager');

INSERT INTO users(username,password,enabled) VALUES ('carlos','12345',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'carlos','camarero');

INSERT INTO users(username,password,enabled) VALUES ('carmen','12345',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'carmen','camarero');

INSERT INTO users(username,password,enabled) VALUES ('coral','12345',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'coral','cocinero');

INSERT INTO users(username,password,enabled) VALUES ('cobi','12345',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'cobi','cocinero');

--PERSONAS--
INSERT INTO propietario(id,name,apellido,gmail,telefono,usuario,contrasena) VALUES (1,'Prince','Presley','princePR@gmail.com', 616161616,'prince','12345');
INSERT INTO manager(id,name,apellido,gmail,telefono,usuario,contrasena) VALUES (1,'Marcos','Martín','marcosMA@gmail.com',626262626,'marcos','12345');
INSERT INTO camarero(id,name,apellido,gmail,telefono,usuario,contrasena) VALUES (1,'Carlos','Catalán','carlosCA@gmail.com',636363636,'carlos','12345');
INSERT INTO camarero(id,name,apellido,gmail,telefono,usuario,contrasena) VALUES (2,'Carmen','Casio','carmenCA@gmail.com',646464646,'carmen','12345');
INSERT INTO cocinero(id,name,apellido,gmail,telefono,usuario,contrasena) VALUES (1,'Coral','Cohen','coralCO@gmail.com',656565656,'coral','12345');
INSERT INTO cocinero(id,name,apellido,gmail,telefono,usuario,contrasena) VALUES (2,'Cobi','Connor','cobiCO@gmail.com',666666666,'coral','12345');

--PROVEEDORES--
INSERT INTO proveedor(id,name,gmail,telefono) VALUES (1, 'Makro', 'foorder.dp@gmail.com', 666123456);
INSERT INTO proveedor(id,name,gmail,telefono) VALUES (2, 'CashFresh', 'foorder.dp@gmail.com',666234567);
INSERT INTO proveedor(id,name,gmail,telefono) VALUES (3, 'Frutas Manolo', 'foorder.dp@gmail.com',666345678);
INSERT INTO proveedor(id,name,gmail,telefono) VALUES (4, 'PepsiCo', 'foorder.dp@gmail.com',666345678);

--TIPOS DE PRODUCTO--
INSERT INTO tipoproducto VALUES (1, 'Carne');
INSERT INTO tipoproducto VALUES (2, 'Pescado');
INSERT INTO tipoproducto VALUES (3, 'Frutas y Verduras');
INSERT INTO tipoproducto VALUES (4, 'Lácteos');
INSERT INTO tipoproducto VALUES (5, 'Bebidas');
INSERT INTO tipoproducto VALUES (6, 'Otros');

--PRODUCTOS--
			--Makro--
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (1,6,'Pan', 10.0, 5.0, 30.0, 1);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (2,1,'Solomillo', 3.0, 1.0, 10.0, 1);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (3,2,'Bacalao', 2.0, 6.0, 10.0, 1);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (4,4,'Roquefort', 1.0, 4.0, 4.0, 1);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (5,6,'Huevos', 12.0, 18.0, 36.0, 1);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (6,6,'Arroz', 1.0, 5.5, 10.0, 1);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (7,1,'Carne Picada', 1.0, 1.5, 4.0, 1);
			--CashFresh--
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (8,4,'Leche', 6.0, 20.0, 25.0, 2);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (9,4,'Mantequilla', 5.0, 15.0, 20.0, 2);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (10,6,'Nata', 4.0, 9.0, 15.0, 2);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (11,6,'Tomate Frito', 5.0, 18.0, 20.0, 2);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (12,6,'Sal', 0.5, 0.8, 3.0, 2);
			--Frutas Manolo--
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (13,3,'Lechuga', 5.0, 10.0, 15.0, 3);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (14,3,'Cebolla', 2.0, 1.5, 10.0, 3);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (15,3,'Tomate', 1.0, 2.0, 4.0, 3);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (16,3,'Patatas', 1.0, 3.0, 5.0, 3);
			--PepsiCo--
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (17,5,'Pepsi', 10.0, 20.0, 50.0, 4);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (18,5,'7UP', 5.0, 10.0, 25.0, 4);
INSERT INTO producto(id,tipo_producto,name,cantidad_minima,cantidad_actual,cantidad_maxima,proveedor_id) VALUES (19,5,'Lipton', 5.0, 10.0, 25.0, 4);

--PLATOS--
INSERT INTO platos(id,name,precio,disponible) VALUES (1, 'Albóndigas',5.0, TRUE);
INSERT INTO platos(id,name,precio,disponible) VALUES (2, 'Revuelto de bacalao',7.8, TRUE);
INSERT INTO platos(id,name,precio,disponible) VALUES (3, 'Solomillo al roque',9.0, FALSE);
INSERT INTO platos(id,name,precio,disponible) VALUES (4, 'Arroz a la cubana',4.5, TRUE);
INSERT INTO platos(id,name,precio,disponible) VALUES (5, 'Tortilla de patatas',5.0, TRUE);
INSERT INTO platos(id,name,precio,disponible) VALUES (6, 'Ensalada',3.0, TRUE);
INSERT INTO platos(id,name,precio,disponible) VALUES (7, 'Arroz con leche',18.0, TRUE);
INSERT INTO platos(id,name,precio,disponible) VALUES (8, 'Pepsi',1.5, TRUE);
INSERT INTO platos(id,name,precio,disponible) VALUES (9, '7UP',1.5, TRUE);
INSERT INTO platos(id,name,precio,disponible) VALUES (10, 'Lipton',1.5, TRUE);
INSERT INTO platos(id,name,precio,disponible) VALUES (11, 'Cesta de pan',1.0, TRUE);

--INGREDIENTES--
				--Albóndigas--
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (1 , 0.5 , 7 , 1);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (2 , 0.25, 14, 1);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (3 , 1.0 , 11, 1);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (4 , 0.01, 12, 1);
				--Revuelto de bacalao--
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (5 , 1.0 , 3 , 2);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (6 , 2.0 , 5 , 2);
				--Solomillo al roque--
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (7 , 1.0 , 2 , 3);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (8 , 0.5 , 4 , 3);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (9 , 1.0 , 10, 3);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (10, 0.2 , 9 , 3);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (11, 0.01, 12, 3);
				--Arroz a la cubana--
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (12 , 0.25, 6 , 4);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (13 , 0.5 , 11, 4);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (14 , 1.0 , 5 , 4);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (15 , 0.01, 12, 4);
				--Tortilla de patatas--
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (16 , 3.0 , 5 , 5);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (17 , 0.25, 16, 5);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (18 , 0.02, 12, 5);
				--Ensalada--
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (19 , 1.0 , 13, 6);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (20 , 0.3 , 15, 6);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (21 , 0.01, 12, 6);
				--Arroz con leche--
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (22 , 0.5 , 6 , 7);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (23 , 2.0 , 8 , 7);
				--Bebidas y Pan--
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (24 , 1.0 , 17, 8);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (25 , 1.0 , 18, 9);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (26 , 1.0 , 19, 10);
INSERT INTO ingrediente(id,cantidad,producto_id,plato_id) VALUES (27 , 1.0 , 1 , 11);

--COMANDA--
INSERT INTO comanda(id, mesa, fecha_creado, fecha_finalizado, precio_total, camarero_id) VALUES (1,2,'2021-02-08 14:56:17','2021-02-08 15:23:02',15.5,1);
INSERT INTO comanda(id, mesa, fecha_creado, fecha_finalizado, precio_total, camarero_id) VALUES (2,3,'2021-02-08 14:58:25','2021-02-08 15:37:17',7.5,2);

--ESTADOS DEL PLATO--
INSERT INTO estadoplato VALUES (1, 'ENCOLA');
INSERT INTO estadoplato VALUES (2, 'ENPROCESO');
INSERT INTO estadoplato VALUES (3, 'FINALIZADO');

--PLATOS PEDIDOS--
INSERT INTO platopedido(id,comanda_id,estadoplato,plato_id) VALUES (1,1,3,3);
INSERT INTO platopedido(id,comanda_id,estadoplato,plato_id) VALUES (2,1,2,5);
INSERT INTO platopedido(id,comanda_id,estadoplato,plato_id) VALUES (3,1,1,8);
INSERT INTO platopedido(id,comanda_id,estadoplato,plato_id) VALUES (4,2,1,1);
INSERT INTO platopedido(id,comanda_id,estadoplato,plato_id) VALUES (5,2,1,10);
INSERT INTO platopedido(id,comanda_id,estadoplato,plato_id) VALUES (6,2,3,11);

--INGREDIENTES PEDIDOS--
				--COMANDA 1--
							--Solomillo al roque (3)--
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (7 , 1.0 , 1);
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (8 , 0.5 , 1);
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (9 , 1.0 , 1);
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (10, 0.2 , 1);
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (11, 0.01, 1);
							--Tortilla de patatas (5)--
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (16 , 3.0 , 2);
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (17 , 0.25, 2);
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (18 , 0.02, 2);
							--Pepsi (8)--
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (25 , 1.0 , 3);
				--COMANDA 2--
							--Albóndigas (1)--
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (1 , 0.5 , 4);
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (2 , 0.25, 4);
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (3 , 1.0 , 4);
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (4 , 0.01, 4);
							--Lipton (10)--
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (26 , 1.0 , 5);
							--Cesta de pan (11)--
INSERT INTO ingediente_pedido(ingrediente_id,cant_pedida,pp_id) VALUES (27 , 1.0 , 6);

--PEDIDO--
INSERT INTO pedido(id,fechapedido,hallegado,proveedor_id) VALUES (1, '2020-01-04', FALSE, 1);
INSERT INTO pedido(id,fechapedido,fechaentrega,hallegado,proveedor_id) VALUES (2, '2020-01-05', '2020-01-06', TRUE, 2);

--LINEA PEDIDO--
INSERT INTO lineapedido(id,producto_id,cantidad,pedido_id) VALUES (1, 1, 10.0, 1);
INSERT INTO lineapedido(id,producto_id,cantidad,pedido_id) VALUES (2, 8, 20.0, 2);


--INSERT INTO users(username,password,enabled) VALUES ('f3rangmor','f3rpass',TRUE);
--INSERT INTO authorities(id,username,authority) VALUES (4,'f3rangmor','owner');
--INSERT INTO owners VALUES (11, 'Fernando', 'Angulo', 'Av. Reina Mercede sn', 'Sevilla', '657356473', 'f3rangmor');
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'boxi', '2012-06-08', 2, 11);
--
--
--INSERT INTO users(username,password,enabled) VALUES ('alisanhos','Pet_ASH.23',TRUE);
--INSERT INTO authorities(id,username,authority) VALUES (5,'alisanhos','owner');
--INSERT INTO owners VALUES (12, 'Alex', 'Hossdorf', 'Calle Tarfia', 'Sevilla', '695199919', 'alisanhos');
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Keka', '2019-05-23', 2, 12);
--
--
--INSERT INTO users(username,password,enabled) VALUES ('horgarler','Tarfia75B',TRUE);
--INSERT INTO authorities(id,username,authority) VALUES (6,'horgarler','owner');
--INSERT INTO owners VALUES (13, 'Horacio', 'Garcia', 'Calle Tarfia', 'Sevilla', '670219587', 'horgarler');
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'Geko', '1997-01-06', 3, 13);
--
--
--INSERT INTO users(username,password,enabled) VALUES ('vicmonpui','Flores54A',TRUE);
--INSERT INTO authorities(id,username,authority) VALUES (7,'vicmonpui','owner');
--INSERT INTO owners VALUES (14, 'Victor', 'Monteseirin', 'Calle Nogales', 'Sevilla', '678543534', 'vicmonpui');
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (17, 'Pogo', '2018-04-15', 4, 14);
--
--
--INSERT INTO users(username,password,enabled) VALUES ('jostabrod','vEvRvOL123',TRUE);
--INSERT INTO authorities(id,username,authority) VALUES (8,'jostabrod','owner');
--INSERT INTO owners VALUES (15, 'Jose', 'Tabares', 'Calle Franco', 'Sevilla', '647235916', 'jostabrod');
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (18, 'Curro', '2015-01-18', 2, 15);

--INSERT INTO vets VALUES (1, 'James', 'Carter');
--INSERT INTO vets VALUES (2, 'Helen', 'Leary');
--INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
--INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
--INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
--INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');
--
--INSERT INTO specialties VALUES (1, 'radiology');
--INSERT INTO specialties VALUES (2, 'surgery');
--INSERT INTO specialties VALUES (3, 'dentistry');
--
--INSERT INTO vet_specialties VALUES (2, 1);
--INSERT INTO vet_specialties VALUES (3, 2);
--INSERT INTO vet_specialties VALUES (3, 3);
--INSERT INTO vet_specialties VALUES (4, 2);
--INSERT INTO vet_specialties VALUES (5, 1);
--
--INSERT INTO types VALUES (1, 'cat');
--INSERT INTO types VALUES (2, 'dog');
--INSERT INTO types VALUES (3, 'lizard');
--INSERT INTO types VALUES (4, 'snake');
--INSERT INTO types VALUES (5, 'bird');
--INSERT INTO types VALUES (6, 'hamster');
--
--INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
--INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
--INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
--INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
--INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
--INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
--INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
--INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
--INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
--INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
--
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
--INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
--
--INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
--INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
--INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
--INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');


