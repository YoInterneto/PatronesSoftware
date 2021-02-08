-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.1
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.io
-- Model Author: ---


-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: ElReyDelPc_BBDD | type: DATABASE --
-- -- DROP DATABASE IF EXISTS ElReyDelPc_BBDD;
-- CREATE DATABASE ElReyDelPc_BBDD;
-- -- ddl-end --
-- 

-- object: public.Usuario | type: TABLE --
-- DROP TABLE IF EXISTS public.Usuario CASCADE;
CREATE TABLE public.Usuario(
	Nombre varchar,
	Apellido varchar,
	Email varchar NOT NULL,
	Direccion varchar,
	Telefono integer,
	Pass varchar NOT NULL,
	ID_Tienda smallint NOT NULL,
	CONSTRAINT Usuario_pk PRIMARY KEY (Email)

);
-- ddl-end --
ALTER TABLE public.Usuario OWNER TO postgres;
-- ddl-end --

-- object: public.Empleado | type: TABLE --
-- DROP TABLE IF EXISTS public.Empleado CASCADE;
CREATE TABLE public.Empleado(
	DNI varchar,
	Cargo varchar,
-- 	Nombre varchar,
-- 	Apellido varchar,
-- 	Email varchar NOT NULL,
-- 	Direccion varchar,
-- 	Telefono integer,
-- 	Pass varchar NOT NULL,
-- 	ID_Tienda smallint NOT NULL,
	CONSTRAINT Empleado_pk PRIMARY KEY (Email)

) INHERITS(public.Usuario)
;
-- ddl-end --
ALTER TABLE public.Empleado OWNER TO postgres;
-- ddl-end --

-- object: public.Cliente | type: TABLE --
-- DROP TABLE IF EXISTS public.Cliente CASCADE;
CREATE TABLE public.Cliente(
	Tarjeta varchar,
-- 	Nombre varchar,
-- 	Apellido varchar,
-- 	Email varchar NOT NULL,
-- 	Direccion varchar,
-- 	Telefono integer,
-- 	Pass varchar NOT NULL,
-- 	ID_Tienda smallint NOT NULL,
	CONSTRAINT Cliente_pk PRIMARY KEY (Email)

) INHERITS(public.Usuario)
;
-- ddl-end --
ALTER TABLE public.Cliente OWNER TO postgres;
-- ddl-end --

-- object: public.Tienda | type: TABLE --
-- DROP TABLE IF EXISTS public.Tienda CASCADE;
CREATE TABLE public.Tienda(
	ID smallint NOT NULL,
	Nombre varchar,
	Direccion varchar,
	CP smallint,
	Ciudad varchar,
	Provincia varchar,
	CONSTRAINT Tienda_pk PRIMARY KEY (ID)

);
-- ddl-end --
ALTER TABLE public.Tienda OWNER TO postgres;
-- ddl-end --

-- object: Tienda_fk | type: CONSTRAINT --
-- ALTER TABLE public.Usuario DROP CONSTRAINT IF EXISTS Tienda_fk CASCADE;
ALTER TABLE public.Usuario ADD CONSTRAINT Tienda_fk FOREIGN KEY (ID_Tienda)
REFERENCES public.Tienda (ID) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.Articulo | type: TABLE --
-- DROP TABLE IF EXISTS public.Articulo CASCADE;
CREATE TABLE public.Articulo(
	Modelo varchar,
	Codigo_ref smallint NOT NULL,
	Precio float,
	Descripcion text,
	Stock smallint,
	rutaImagen varchar,
	ID_Tienda smallint,
	CONSTRAINT Articulos_pk PRIMARY KEY (Codigo_ref)

);
-- ddl-end --
ALTER TABLE public.Articulo OWNER TO postgres;
-- ddl-end --

-- object: public.Portatil | type: TABLE --
-- DROP TABLE IF EXISTS public.Portatil CASCADE;
CREATE TABLE public.Portatil(
	Panel varchar,
	Peso smallint,
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio float,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	rutaImagen varchar,
-- 	ID_Tienda smallint,
	CONSTRAINT Portatil_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.Portatil OWNER TO postgres;
-- ddl-end --

-- object: public.WebCam | type: TABLE --
-- DROP TABLE IF EXISTS public.WebCam CASCADE;
CREATE TABLE public.WebCam(
	Calidad varchar,
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio float,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	rutaImagen varchar,
-- 	ID_Tienda smallint,
	CONSTRAINT WebCam_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.WebCam OWNER TO postgres;
-- ddl-end --

-- object: public.PcTorre | type: TABLE --
-- DROP TABLE IF EXISTS public.PcTorre CASCADE;
CREATE TABLE public.PcTorre(
	Nombre varchar,
	Creado boolean,
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio float,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	rutaImagen varchar,
-- 	ID_Tienda smallint,
	CONSTRAINT PcTorre_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.PcTorre OWNER TO postgres;
-- ddl-end --

-- object: public.Grafica | type: TABLE --
-- DROP TABLE IF EXISTS public.Grafica CASCADE;
CREATE TABLE public.Grafica(
	Generacion smallint,
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio float,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	rutaImagen varchar,
-- 	ID_Tienda smallint,
	CONSTRAINT Grafica_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.Grafica OWNER TO postgres;
-- ddl-end --

-- object: public.Procesador | type: TABLE --
-- DROP TABLE IF EXISTS public.Procesador CASCADE;
CREATE TABLE public.Procesador(
	Socket varchar,
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio float,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	rutaImagen varchar,
-- 	ID_Tienda smallint,
	CONSTRAINT Procesador_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.Procesador OWNER TO postgres;
-- ddl-end --

-- object: public.Memoria_RAM | type: TABLE --
-- DROP TABLE IF EXISTS public.Memoria_RAM CASCADE;
CREATE TABLE public.Memoria_RAM(
	PN varchar,
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio float,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	rutaImagen varchar,
-- 	ID_Tienda smallint,
	CONSTRAINT Memoria_RAM_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.Memoria_RAM OWNER TO postgres;
-- ddl-end --

-- object: public.Disco_duro | type: TABLE --
-- DROP TABLE IF EXISTS public.Disco_duro CASCADE;
CREATE TABLE public.Disco_duro(
	Tipo varchar,
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio float,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	rutaImagen varchar,
-- 	ID_Tienda smallint,
	CONSTRAINT Disco_duro_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.Disco_duro OWNER TO postgres;
-- ddl-end --

-- object: public.Placa_base | type: TABLE --
-- DROP TABLE IF EXISTS public.Placa_base CASCADE;
CREATE TABLE public.Placa_base(
	Socket varchar,
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio float,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	rutaImagen varchar,
-- 	ID_Tienda smallint,
	CONSTRAINT Placa_base_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.Placa_base OWNER TO postgres;
-- ddl-end --

-- object: public.Fuente_alimentacion | type: TABLE --
-- DROP TABLE IF EXISTS public.Fuente_alimentacion CASCADE;
CREATE TABLE public.Fuente_alimentacion(
	Potencia smallint,
	Certificacion varchar,
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio float,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	rutaImagen varchar,
-- 	ID_Tienda smallint,
	CONSTRAINT Fuente_alimentacion_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.Fuente_alimentacion OWNER TO postgres;
-- ddl-end --

-- object: public.Caja | type: TABLE --
-- DROP TABLE IF EXISTS public.Caja CASCADE;
CREATE TABLE public.Caja(
	Cristal boolean,
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio float,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	rutaImagen varchar,
-- 	ID_Tienda smallint,
	CONSTRAINT Caja_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.Caja OWNER TO postgres;
-- ddl-end --

-- object: public.Raton | type: TABLE --
-- DROP TABLE IF EXISTS public.Raton CASCADE;
CREATE TABLE public.Raton(
	DPI smallint,
	Tipo varchar,
	Peso smallint,
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio float,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	rutaImagen varchar,
-- 	ID_Tienda smallint,
	CONSTRAINT Raton_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.Raton OWNER TO postgres;
-- ddl-end --

-- object: Tienda_fk | type: CONSTRAINT --
-- ALTER TABLE public.Articulo DROP CONSTRAINT IF EXISTS Tienda_fk CASCADE;
ALTER TABLE public.Articulo ADD CONSTRAINT Tienda_fk FOREIGN KEY (ID_Tienda)
REFERENCES public.Tienda (ID) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.Teclado | type: TABLE --
-- DROP TABLE IF EXISTS public.Teclado CASCADE;
CREATE TABLE public.Teclado(
	Tipo varchar,
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio float,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	rutaImagen varchar,
-- 	ID_Tienda smallint,
	CONSTRAINT Teclado_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.Teclado OWNER TO postgres;
-- ddl-end --

-- object: public.Pedido | type: TABLE --
-- DROP TABLE IF EXISTS public.Pedido CASCADE;
CREATE TABLE public.Pedido(
	Precio_total float,
	Fecha date,
	Id smallint NOT NULL,
	codigos smallint[],
	Estado smallint,
	Email_Cliente varchar,
	CONSTRAINT Pedido_pk PRIMARY KEY (Id)

);
-- ddl-end --
ALTER TABLE public.Pedido OWNER TO postgres;
-- ddl-end --

-- object: public.Monitor | type: TABLE --
-- DROP TABLE IF EXISTS public.Monitor CASCADE;
CREATE TABLE public.Monitor(
	Pulgadas smallint,
	Panel varchar,
	Hz smallint,
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio float,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	rutaImagen varchar,
-- 	ID_Tienda smallint,
	CONSTRAINT Monitor_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.Monitor OWNER TO postgres;
-- ddl-end --

-- object: public.Puntuacion | type: TABLE --
-- DROP TABLE IF EXISTS public.Puntuacion CASCADE;
CREATE TABLE public.Puntuacion(
	Nota smallint NOT NULL,
	Email varchar NOT NULL,
	codigo_ref smallint NOT NULL,
	CONSTRAINT Puntuacion_pk PRIMARY KEY (Email,codigo_ref)

);
-- ddl-end --
ALTER TABLE public.Puntuacion OWNER TO postgres;
-- ddl-end --

-- object: Cliente_fk | type: CONSTRAINT --
-- ALTER TABLE public.Pedido DROP CONSTRAINT IF EXISTS Cliente_fk CASCADE;
ALTER TABLE public.Pedido ADD CONSTRAINT Cliente_fk FOREIGN KEY (Email_Cliente)
REFERENCES public.Cliente (Email) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.Carrito | type: TABLE --
-- DROP TABLE IF EXISTS public.Carrito CASCADE;
CREATE TABLE public.Carrito(
	datosArticulos varchar,
	Email_Cliente varchar NOT NULL,
	CONSTRAINT Carrito_pk PRIMARY KEY (Email_Cliente)

);
-- ddl-end --
ALTER TABLE public.Carrito OWNER TO postgres;
-- ddl-end --

-- object: Cliente_fk | type: CONSTRAINT --
-- ALTER TABLE public.Carrito DROP CONSTRAINT IF EXISTS Cliente_fk CASCADE;
ALTER TABLE public.Carrito ADD CONSTRAINT Cliente_fk FOREIGN KEY (Email_Cliente)
REFERENCES public.Cliente (Email) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: Carrito_uq | type: CONSTRAINT --
-- ALTER TABLE public.Carrito DROP CONSTRAINT IF EXISTS Carrito_uq CASCADE;
ALTER TABLE public.Carrito ADD CONSTRAINT Carrito_uq UNIQUE (Email_Cliente);
-- ddl-end --

-- INSERTS -- 


-- TIENDA Y USUARIOS --

INSERT INTO Tienda VALUES(0, 'ElReyDelPc', 'Direccion1', 28073, 'Ciudad1', 'Provincia1');
INSERT INTO Empleado VALUES('Juan', 'Apellido2', 'email@email.com', 'Direccion2', 621512233, 'pass1',0,'DNI1','Empleado1');
INSERT INTO Cliente VALUES('Pedro', 'Apellido1', 'email2@email.com', 'Direccion1', 621424230, 'pass1',0,'tarjeta1');

INSERT INTO Empleado VALUES('Maria', 'Apellido2', 'prueba@gmail.com', 'DireccionX', 671512237, '111',0,'DNI2','Empleado2');
INSERT INTO Cliente VALUES('Sandra', 'Apellido1', 'cliente1@gmail.com', 'Direccion3', 631426239, '111',0,'tarjeta2');
INSERT INTO Cliente VALUES('Marta', 'Apellido1', 'cliente2@gmail.com', 'Direccion4', 641424731, '111',0,'tarjeta3');
INSERT INTO Cliente VALUES('Julio', 'Apellido1', 'cliente3@gmail.com', 'Direccion5', 651424732, '111',0,'tarjeta4');
INSERT INTO Cliente VALUES('Javier', 'Apellido1', 'cliente4@gmail.com', 'Direccion6', 656624234, '111',0,'tarjeta5');

-- ARTICULOS -- 

INSERT INTO Portatil VALUES('Portatil Acer ChromeBook', 1, 350, 'Descripcion1', 2,'/images/portatil1.png',0,'IPS',2);
INSERT INTO Portatil VALUES('Portatil ASUS ChromeBook', 123, 399, 'Descripcion123', 2,'/images/portatil1.png',0,'IPS',2);
INSERT INTO Portatil VALUES('Portatil ASUS TUF Gaming', 124, 899, 'Descripcion124', 3,'/images/portatil1.png',0,'IPS',3);
INSERT INTO Portatil VALUES('Portatil Lenovo iDeaPad 4', 125, 420, 'Descripcion125', 5,'/images/portatil1.png',0,'IPS',2);
INSERT INTO Portatil VALUES('Portatil HP ChromeBook', 126, 500, 'Descripcion126', 1,'/images/portatil1.png',0,'IPS',1);
INSERT INTO Portatil VALUES('Portatil HP Pavilion Gaming', 127, 905, 'Descripcion127', 2,'/images/portatil1.png',0,'IPS',4);

INSERT INTO WebCam VALUES('Webcam Logitech C505', 2, 30.5, 'Descripcion2', 4,'/images/cam1.png' ,0,'HD');
INSERT INTO WebCam VALUES('Webcam Logitech C920', 222, 90, 'Descripcion222', 3,'/images/cam1.png' ,0,'4k');
INSERT INTO WebCam VALUES('Webcam Owlotech', 223, 20, 'Descripcion223', 2,'/images/cam1.png' ,0,'HD');
INSERT INTO WebCam VALUES('Webcam Logitech Brio', 224, 80, 'Descripcion224', 8,'/images/cam1.png' ,0,'4k');
INSERT INTO WebCam VALUES('Webcam Logitech C930', 225, 70, 'Descripcion225', 1,'/images/cam1.png' ,0,'HD');

INSERT INTO PcTorre VALUES('PcTorre Prestige P100A', 3, 502, 'AMD', 1,'/images/torre1.png',0,'Prestige',false);
INSERT INTO PcTorre VALUES('PcTorre MSI Bronze', 332, 800, 'Descripcion332', 2,'/images/torre1.png',0,'MSI Bronze',false);
INSERT INTO PcTorre VALUES('PcTorre Milenium Machine 1 Mini', 333, 700, 'Descripcion333', 4,'/images/torre1.png',0,'Milenium Machine',false);
INSERT INTO PcTorre VALUES('PcTorre Zone Evil Silver', 334, 899.99, 'Descripcion334', 1,'/images/torre1.png',0,'Zone Evil',false);

INSERT INTO Grafica VALUES('Grafica NVIDIA GTX 3070', 4, 540, 'Descripcion4', 1,'/images/grafica1.jpg', 0, 3);
INSERT INTO Grafica VALUES('Grafica NVIDIA GTX 3080', 441, 850, 'Descripcion441', 0,'/images/grafica1.jpg', 0, 3);
INSERT INTO Grafica VALUES('Grafica NVIDIA GTX 3080Ti', 442, 999, 'Descripcion443', 2,'/images/grafica1.jpg', 0, 3);
INSERT INTO Grafica VALUES('Grafica AMD 5700XT', 443, 440, 'Descripcion444', 2,'/images/grafica1.jpg', 0, 2);
INSERT INTO Grafica VALUES('Grafica NVIDIA GTX 1060', 444, 200, 'Descripcion445', 10,'/images/grafica1.jpg', 0, 1);

INSERT INTO Procesador VALUES('CPU AMD Ryzen 7 3700x', 5, 310, 'Descripcion5', 3,'/images/cpu1.png', 0,'AM4');
INSERT INTO Procesador VALUES('CPU AMD Ryzen 5 3600', 552, 189, 'Descripcion552', 10,'/images/cpu1.png', 0,'AM4');
INSERT INTO Procesador VALUES('CPU AMD Ryzen 5 5600x', 553, 320, 'Descripcion553', 1,'/images/cpu1.png', 0,'AM4');
INSERT INTO Procesador VALUES('CPU AMD Ryzen 7 2700x', 554, 190, 'Descripcion554', 2,'/images/cpu1.png', 0,'AM4');
INSERT INTO Procesador VALUES('CPU Intel Core i5-10400F', 555, 139.90, 'Descripcion555', 3,'/images/cpu1.png', 0,'LGA1200');
INSERT INTO Procesador VALUES('CPU Intel Core i9-9900k', 556, 550, 'Descripcion556', 4,'/images/cpu1.png', 0,'1151');

INSERT INTO Memoria_RAM VALUES('RAM Kingston HyperX Fury', 6, 120, 'Descripcion6', 2,'/images/ram1.png', 0,'HX432C16FB3K2/16');
INSERT INTO Memoria_RAM VALUES('RAM Crucial DDR4 2400', 661, 80, 'Descripcion661', 3,'/images/ram1.png', 0,'HX426C16FB4/16');
INSERT INTO Memoria_RAM VALUES('RAM G.Skill Trident DDR4 3200', 662, 90, 'Descripcion662', 1,'/images/ram1.png', 0,'F4-3200C16D-16GTZRX');
INSERT INTO Memoria_RAM VALUES('RAM Crorsair Vengance RGB pro', 663, 100, 'Descripcion663', 6,'/images/ram1.png', 0,'CMW16GX4M2Z3200C16');

INSERT INTO Disco_duro VALUES('Disco SSD Kingstone A400', 7, 50, 'Descripcion7', 4,'/images/disco1.png', 0,'SSD');
INSERT INTO Disco_duro VALUES('Disco SSD Samsung 860 EVO', 772, 220, 'Descripcion772', 4,'/images/disco1.png', 0,'SSD');
INSERT INTO Disco_duro VALUES('Disco HDD Barracuda B342 5T', 773, 300, 'Descripcion773', 4,'/images/disco1.png', 0,'HDD');
insert into disco_duro VALUES('Disco HDD Barracuda 1T', 776, 49.0, 'El mejor del mercado', 10, '/images/disco1.png', 0, 'HDD');

INSERT INTO Placa_base VALUES('Placa Gigabyte B450M', 8, 80, 'Descripcion8', 3,'/images/placa1.png', 0,'AM4');
INSERT INTO Placa_base VALUES('Placa MSI B550', 882, 129.80, 'Descripcion882', 1,'/images/placa1.png', 0,'AM4');
INSERT INTO Placa_base VALUES('Placa AORUS x470M', 883, 130, 'Descripcion883', 4,'/images/placa1.png', 0,'AM4');
INSERT INTO Placa_base VALUES('Placa Gigabyte Z490', 884, 150, 'Descripcion884', 2,'/images/placa1.png', 0,'LGA1200');

INSERT INTO Fuente_alimentacion VALUES('Fuente Tempest Gaming', 9, 39.98, 'Descripcion9', 3,'/images/fa1.png', 0,750,'GOLD');
INSERT INTO Fuente_alimentacion VALUES('Fuente Corsair CV Series', 992, 120, 'Descripcion992', 3,'/images/fa1.png', 0,850,'Titanium');

INSERT INTO Caja VALUES('Caja Thermaltake H200', 10, 150, 'Descripcion10', 1,'/images/caja1.png', 0, true);
INSERT INTO Caja VALUES('Caja Nox', 101, 120, 'Descripcion101', 3,'/images/caja1.png', 0, false);
INSERT INTO Caja VALUES('Caja NZXT', 102, 91.47, 'Descripcion102', 2,'/images/caja1.png', 0, true);

INSERT INTO Raton VALUES('Raton Logitech GPro', 11, 115, 'Descripcion11', 3,'/images/raton1.png', 0, 8000,'Tipo1',0.5);
INSERT INTO Raton VALUES('Raton Logitech G520', 112, 40, 'Descripcion112', 1,'/images/raton1.png', 0, 16000,'Tipo3',0.8);
INSERT INTO Raton VALUES('Raton Logitech G203', 113, 50, 'Descripcion113', 5,'/images/raton1.png', 0, 3000,'Tipo2',0.7);

INSERT INTO Teclado VALUES('Teclado MSI Vigor GK50', 12, 60, 'Descripcion12', 12,'/images/teclado1.png', 0, 'Mecanico');
INSERT INTO Teclado VALUES('Teclado Corsair', 129, 140, 'Descripcion129', 1,'/images/teclado1.png', 0, 'Mecanico');
INSERT INTO Teclado VALUES('Teclado NewSkill', 128, 105, 'Descripcion128', 2,'/images/teclado1.png', 0, 'Mecanico');

INSERT INTO Monitor VALUES('Monitor BenQ 144hz', 13, 202, 'Descripcion13', 8,'/images/monitor1.png', 0,20,'PN',144);
INSERT INTO Monitor VALUES('Monitor Zowie XL241LP1', 132, 222, 'Descripcion132', 1,'/images/monitor1.png', 0,24,'PN',144);
INSERT INTO Monitor VALUES('Monitor BenQ 60hz', 133, 100, 'Descripcion133', 3,'/images/monitor1.png', 0,26,'IPS',60);


INSERT INTO Carrito VALUES('4-5-3-8-10','email2@email.com');
INSERT INTO Carrito VALUES('6-3','cliente1@gmail.com');
INSERT INTO Carrito VALUES('1-2-3-7','cliente2@gmail.com');
INSERT INTO Carrito VALUES('5-2-3-8','cliente3@gmail.com');
INSERT INTO Carrito VALUES('4-11-13-7','cliente4@gmail.com');

INSERT INTO Pedido VALUES(100.9,'2017-03-14',0,'{3,4}',0,'email2@email.com');
INSERT INTO Pedido VALUES(30.75,'2017-03-14',1,'{2,1}',0,'email2@email.com');
INSERT INTO Pedido VALUES(50,'2017-03-14',2,'{5,6}',0,'email2@email.com');

INSERT INTO Pedido VALUES(500,'2017-03-14',3,'{5,6}',0,'cliente1@gmail.com');
INSERT INTO Pedido VALUES(100.9,'2017-03-14',4,'{3,4}',0,'cliente1@gmail.com');
INSERT INTO Pedido VALUES(300.75,'2017-03-14',5,'{2,1}',0,'cliente1@gmail.com');

-- PUNTUACION EJEMPLO -- 

INSERT INTO Puntuacion VALUES(4,'email2@email.com',4);
INSERT INTO Puntuacion VALUES(3,'email2@email.com',9);

INSERT INTO Puntuacion VALUES(4,'cliente1@gmail.com',1);
INSERT INTO Puntuacion VALUES(3,'cliente1@gmail.com',2);
INSERT INTO Puntuacion VALUES(2,'cliente1@gmail.com',3);
INSERT INTO Puntuacion VALUES(3,'cliente1@gmail.com',7);
INSERT INTO Puntuacion VALUES(3,'cliente1@gmail.com',8);
INSERT INTO Puntuacion VALUES(3,'cliente1@gmail.com',9);
INSERT INTO Puntuacion VALUES(3,'cliente1@gmail.com',10);
INSERT INTO Puntuacion VALUES(3,'cliente1@gmail.com',11);

INSERT INTO Puntuacion VALUES(3,'cliente2@gmail.com',6);
INSERT INTO Puntuacion VALUES(3,'cliente2@gmail.com',7);
INSERT INTO Puntuacion VALUES(3,'cliente2@gmail.com',8);
INSERT INTO Puntuacion VALUES(3,'cliente2@gmail.com',9);
INSERT INTO Puntuacion VALUES(3,'cliente2@gmail.com',10);
INSERT INTO Puntuacion VALUES(3,'cliente2@gmail.com',11);
INSERT INTO Puntuacion VALUES(3,'cliente2@gmail.com',12);
INSERT INTO Puntuacion VALUES(3,'cliente2@gmail.com',13);

INSERT INTO Puntuacion VALUES(4,'cliente3@gmail.com',1);
INSERT INTO Puntuacion VALUES(3,'cliente3@gmail.com',2);
INSERT INTO Puntuacion VALUES(2,'cliente3@gmail.com',3);
INSERT INTO Puntuacion VALUES(4,'cliente3@gmail.com',4);
INSERT INTO Puntuacion VALUES(5,'cliente3@gmail.com',5);
INSERT INTO Puntuacion VALUES(3,'cliente3@gmail.com',6);
INSERT INTO Puntuacion VALUES(3,'cliente3@gmail.com',7);
INSERT INTO Puntuacion VALUES(3,'cliente3@gmail.com',8);
INSERT INTO Puntuacion VALUES(3,'cliente3@gmail.com',9);
INSERT INTO Puntuacion VALUES(3,'cliente3@gmail.com',10);


INSERT INTO Puntuacion VALUES(2,'cliente4@gmail.com',4);
INSERT INTO Puntuacion VALUES(1,'cliente4@gmail.com',3);
INSERT INTO Puntuacion VALUES(1,'cliente4@gmail.com',2);
INSERT INTO Puntuacion VALUES(2,'cliente4@gmail.com',5);





