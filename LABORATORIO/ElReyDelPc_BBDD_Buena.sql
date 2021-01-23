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
	ID_Usuario smallint NOT NULL,
	Nombre varchar,
	Apellido varchar,
	Email varchar,
	Direccion varchar,
	Telefono integer,
	Password varchar NOT NULL,
	ID_Tienda smallint NOT NULL,
	CONSTRAINT Usuario_pk PRIMARY KEY (ID_Usuario)

);
-- ddl-end --
ALTER TABLE public.Usuario OWNER TO postgres;
-- ddl-end --

-- object: public.Empleado | type: TABLE --
-- DROP TABLE IF EXISTS public.Empleado CASCADE;
CREATE TABLE public.Empleado(
	DNI varchar,
	Cargo varchar,
-- 	ID_Usuario smallint NOT NULL,
-- 	Nombre varchar,
-- 	Apellido varchar,
-- 	Email varchar,
-- 	Direccion varchar,
-- 	Telefono integer,
-- 	Password varchar NOT NULL,
-- 	ID_Tienda smallint NOT NULL,
	CONSTRAINT Empleado_pk PRIMARY KEY (ID_Usuario)

) INHERITS(public.Usuario)
;
-- ddl-end --
ALTER TABLE public.Empleado OWNER TO postgres;
-- ddl-end --

-- object: public.Cliente | type: TABLE --
-- DROP TABLE IF EXISTS public.Cliente CASCADE;
CREATE TABLE public.Cliente(
	Tarjeta varchar,
-- 	ID_Usuario smallint NOT NULL,
-- 	Nombre varchar,
-- 	Apellido varchar,
-- 	Email varchar,
-- 	Direccion varchar,
-- 	Telefono integer,
-- 	Password varchar NOT NULL,
-- 	ID_Tienda smallint NOT NULL,
	CONSTRAINT Cliente_pk PRIMARY KEY (ID_Usuario)

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
	Precio smallint,
	Descripcion text,
	Stock smallint,
	ID_Tienda smallint,
	Id_Pedido smallint,
	Fecha date,
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
-- 	Precio smallint,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	ID_Tienda smallint,
-- 	Id_Pedido smallint,
-- 	Fecha date,
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
-- 	Precio smallint,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	ID_Tienda smallint,
-- 	Id_Pedido smallint,
-- 	Fecha date,
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
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio smallint,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	ID_Tienda smallint,
-- 	Id_Pedido smallint,
-- 	Fecha date,
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
-- 	Precio smallint,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	ID_Tienda smallint,
-- 	Id_Pedido smallint,
-- 	Fecha date,
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
-- 	Precio smallint,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	ID_Tienda smallint,
-- 	Id_Pedido smallint,
-- 	Fecha date,
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
-- 	Precio smallint,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	ID_Tienda smallint,
-- 	Id_Pedido smallint,
-- 	Fecha date,
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
-- 	Precio smallint,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	ID_Tienda smallint,
-- 	Id_Pedido smallint,
-- 	Fecha date,
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
-- 	Precio smallint,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	ID_Tienda smallint,
-- 	Id_Pedido smallint,
-- 	Fecha date,
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
-- 	Precio smallint,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	ID_Tienda smallint,
-- 	Id_Pedido smallint,
-- 	Fecha date,
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
-- 	Precio smallint,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	ID_Tienda smallint,
-- 	Id_Pedido smallint,
-- 	Fecha date,
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
-- 	Precio smallint,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	ID_Tienda smallint,
-- 	Id_Pedido smallint,
-- 	Fecha date,
	CONSTRAINT Raton_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.Raton OWNER TO postgres;
-- ddl-end --

-- object: public.Carrito | type: TABLE --
-- DROP TABLE IF EXISTS public.Carrito CASCADE;
CREATE TABLE public.Carrito(
	Precio_total money,
	ID_Usuario_Cliente smallint NOT NULL,
	CONSTRAINT Carrito_pk PRIMARY KEY (ID_Usuario_Cliente)

);
-- ddl-end --
ALTER TABLE public.Carrito OWNER TO postgres;
-- ddl-end --

-- object: Cliente_fk | type: CONSTRAINT --
-- ALTER TABLE public.Carrito DROP CONSTRAINT IF EXISTS Cliente_fk CASCADE;
ALTER TABLE public.Carrito ADD CONSTRAINT Cliente_fk FOREIGN KEY (ID_Usuario_Cliente)
REFERENCES public.Cliente (ID_Usuario) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: Carrito_uq | type: CONSTRAINT --
-- ALTER TABLE public.Carrito DROP CONSTRAINT IF EXISTS Carrito_uq CASCADE;
ALTER TABLE public.Carrito ADD CONSTRAINT Carrito_uq UNIQUE (ID_Usuario_Cliente);
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
-- 	Precio smallint,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	ID_Tienda smallint,
-- 	Id_Pedido smallint,
-- 	Fecha date,
	CONSTRAINT Teclado_pk PRIMARY KEY (Codigo_ref)

) INHERITS(public.Articulo)
;
-- ddl-end --
ALTER TABLE public.Teclado OWNER TO postgres;
-- ddl-end --

-- object: public.Pedido | type: TABLE --
-- DROP TABLE IF EXISTS public.Pedido CASCADE;
CREATE TABLE public.Pedido(
	Precio_total money,
	ID_Usuario_Cliente smallint,
	Id smallint NOT NULL,
	CONSTRAINT Pedido_pk PRIMARY KEY (Id)

);
-- ddl-end --
ALTER TABLE public.Pedido OWNER TO postgres;
-- ddl-end --

-- object: Cliente_fk | type: CONSTRAINT --
-- ALTER TABLE public.Pedido DROP CONSTRAINT IF EXISTS Cliente_fk CASCADE;
ALTER TABLE public.Pedido ADD CONSTRAINT Cliente_fk FOREIGN KEY (ID_Usuario_Cliente)
REFERENCES public.Cliente (ID_Usuario) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.Monitor | type: TABLE --
-- DROP TABLE IF EXISTS public.Monitor CASCADE;
CREATE TABLE public.Monitor(
	Pulgadas smallint,
	Panel varchar,
	Hz smallint,
-- 	Modelo varchar,
-- 	Codigo_ref smallint NOT NULL,
-- 	Precio smallint,
-- 	Descripcion text,
-- 	Stock smallint,
-- 	ID_Tienda smallint,
-- 	Id_Pedido smallint,
-- 	Fecha date,
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
	Codigo_ref_Monitor smallint,
	Codigo_ref_Placa_base smallint,
	Codigo_ref_Procesador smallint,
	Codigo_ref_WebCam smallint,
	Codigo_ref_PcTorre smallint,
	Codigo_ref_Portatil smallint,
	Codigo_ref_Teclado smallint,
	Codigo_ref_Raton smallint,
	Codigo_ref_Fuente_alimentacion smallint,
	Codigo_ref_Memoria_RAM smallint,
	Codigo_ref_Grafica smallint,
	Codigo_ref_Disco_duro smallint,
	Codigo_ref_Caja smallint
);
-- ddl-end --
ALTER TABLE public.Puntuacion OWNER TO postgres;
-- ddl-end --

-- object: Pedido_fk | type: CONSTRAINT --
-- ALTER TABLE public.Articulo DROP CONSTRAINT IF EXISTS Pedido_fk CASCADE;
ALTER TABLE public.Articulo ADD CONSTRAINT Pedido_fk FOREIGN KEY (Id_Pedido)
REFERENCES public.Pedido (Id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: Monitor_fk | type: CONSTRAINT --
-- ALTER TABLE public.Puntuacion DROP CONSTRAINT IF EXISTS Monitor_fk CASCADE;
ALTER TABLE public.Puntuacion ADD CONSTRAINT Monitor_fk FOREIGN KEY (Codigo_ref_Monitor)
REFERENCES public.Monitor (Codigo_ref) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: Placa_base_fk | type: CONSTRAINT --
-- ALTER TABLE public.Puntuacion DROP CONSTRAINT IF EXISTS Placa_base_fk CASCADE;
ALTER TABLE public.Puntuacion ADD CONSTRAINT Placa_base_fk FOREIGN KEY (Codigo_ref_Placa_base)
REFERENCES public.Placa_base (Codigo_ref) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: Procesador_fk | type: CONSTRAINT --
-- ALTER TABLE public.Puntuacion DROP CONSTRAINT IF EXISTS Procesador_fk CASCADE;
ALTER TABLE public.Puntuacion ADD CONSTRAINT Procesador_fk FOREIGN KEY (Codigo_ref_Procesador)
REFERENCES public.Procesador (Codigo_ref) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: WebCam_fk | type: CONSTRAINT --
-- ALTER TABLE public.Puntuacion DROP CONSTRAINT IF EXISTS WebCam_fk CASCADE;
ALTER TABLE public.Puntuacion ADD CONSTRAINT WebCam_fk FOREIGN KEY (Codigo_ref_WebCam)
REFERENCES public.WebCam (Codigo_ref) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: PcTorre_fk | type: CONSTRAINT --
-- ALTER TABLE public.Puntuacion DROP CONSTRAINT IF EXISTS PcTorre_fk CASCADE;
ALTER TABLE public.Puntuacion ADD CONSTRAINT PcTorre_fk FOREIGN KEY (Codigo_ref_PcTorre)
REFERENCES public.PcTorre (Codigo_ref) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: Portatil_fk | type: CONSTRAINT --
-- ALTER TABLE public.Puntuacion DROP CONSTRAINT IF EXISTS Portatil_fk CASCADE;
ALTER TABLE public.Puntuacion ADD CONSTRAINT Portatil_fk FOREIGN KEY (Codigo_ref_Portatil)
REFERENCES public.Portatil (Codigo_ref) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: Teclado_fk | type: CONSTRAINT --
-- ALTER TABLE public.Puntuacion DROP CONSTRAINT IF EXISTS Teclado_fk CASCADE;
ALTER TABLE public.Puntuacion ADD CONSTRAINT Teclado_fk FOREIGN KEY (Codigo_ref_Teclado)
REFERENCES public.Teclado (Codigo_ref) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: Raton_fk | type: CONSTRAINT --
-- ALTER TABLE public.Puntuacion DROP CONSTRAINT IF EXISTS Raton_fk CASCADE;
ALTER TABLE public.Puntuacion ADD CONSTRAINT Raton_fk FOREIGN KEY (Codigo_ref_Raton)
REFERENCES public.Raton (Codigo_ref) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: Fuente_alimentacion_fk | type: CONSTRAINT --
-- ALTER TABLE public.Puntuacion DROP CONSTRAINT IF EXISTS Fuente_alimentacion_fk CASCADE;
ALTER TABLE public.Puntuacion ADD CONSTRAINT Fuente_alimentacion_fk FOREIGN KEY (Codigo_ref_Fuente_alimentacion)
REFERENCES public.Fuente_alimentacion (Codigo_ref) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: Memoria_RAM_fk | type: CONSTRAINT --
-- ALTER TABLE public.Puntuacion DROP CONSTRAINT IF EXISTS Memoria_RAM_fk CASCADE;
ALTER TABLE public.Puntuacion ADD CONSTRAINT Memoria_RAM_fk FOREIGN KEY (Codigo_ref_Memoria_RAM)
REFERENCES public.Memoria_RAM (Codigo_ref) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: Grafica_fk | type: CONSTRAINT --
-- ALTER TABLE public.Puntuacion DROP CONSTRAINT IF EXISTS Grafica_fk CASCADE;
ALTER TABLE public.Puntuacion ADD CONSTRAINT Grafica_fk FOREIGN KEY (Codigo_ref_Grafica)
REFERENCES public.Grafica (Codigo_ref) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: Disco_duro_fk | type: CONSTRAINT --
-- ALTER TABLE public.Puntuacion DROP CONSTRAINT IF EXISTS Disco_duro_fk CASCADE;
ALTER TABLE public.Puntuacion ADD CONSTRAINT Disco_duro_fk FOREIGN KEY (Codigo_ref_Disco_duro)
REFERENCES public.Disco_duro (Codigo_ref) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: Caja_fk | type: CONSTRAINT --
-- ALTER TABLE public.Puntuacion DROP CONSTRAINT IF EXISTS Caja_fk CASCADE;
ALTER TABLE public.Puntuacion ADD CONSTRAINT Caja_fk FOREIGN KEY (Codigo_ref_Caja)
REFERENCES public.Caja (Codigo_ref) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- INSERTS -- 


-- TIENDA Y USUARIOS --

INSERT INTO Tienda VALUES(0, 'Name1', 'Direccion1', 28073, 'Ciudad1', 'Provincia1');
INSERT INTO Empleado VALUES(1,'Name2', 'Apellido2', 'email2@email.com', 'Direccion2', 621512233, 'pass1',0,'DNI1','Empleado1');
INSERT INTO Cliente VALUES(2,'Name3', 'Apellido1', 'email1@email.com', 'Direccion1', 621424233, 'pass1',0,'tarjeta1');

-- TRAMITESS -- 

INSERT INTO Carrito VALUES(0,2);
INSERT INTO Pedido VALUES(0,2,1);
INSERT INTO Articulo VALUES('Modelo1', 3, 20, 'Descripcion1', 3, 0, null, null);

-- ARTICULOS -- 

INSERT INTO Portatil VALUES('Modelo1', 1, 20, 'Descripcion1', 2, 0, null, null,'IPS',2);
INSERT INTO WebCam VALUES('Modelo2', 2, 26, 'Descripcion2', 4, 0, null, null,'HD');
INSERT INTO PcTorre VALUES('Modelo3', 3, 21, 'Descripcion3', 7, 0, null, null,'NombreTorre1');
INSERT INTO Grafica VALUES('Modelo4', 4, 500, 'Descripcion4', 5, 0, null, null,3);
INSERT INTO Procesador VALUES('Modelo5', 5, 25, 'Descripcion5', 6, 0, null, null,'AM4');
INSERT INTO Memoria_RAM VALUES('Modelo6', 6, 25, 'Descripcion6', 6, 0, null, null,'PN1');
INSERT INTO Disco_duro VALUES('Modelo7', 7, 25, 'Descripcion7', 4, 0, null, null,'SSD');
INSERT INTO Placa_base VALUES('Modelo8', 8, 253, 'Descripcion8', 8, 0, null, null,'AM4');
INSERT INTO Fuente_alimentacion VALUES('Modelo9', 9, 25, 'Descripcion9', 3, 0, null, null,750,'GOLD');
INSERT INTO Caja VALUES('Modelo10', 10, 25, 'Descripcion10', 10, 0, null, null,true);
INSERT INTO Raton VALUES('Modelo11', 11, 115, 'Descripcion11', 11, 0, null, null,8000,'Tipo1',0.5);
INSERT INTO Teclado VALUES('Modelo12', 12, 125, 'Descripcion12', 12, 0, null, null,'Tipo1');
INSERT INTO Monitor VALUES('Modelo13', 13, 222, 'Descripcion13', 11, 0, null, null,24,'PN',144);

-- PUNTUACION EJEMPLO PARA GRAFICA -- 

INSERT INTO Puntuacion (Nota,Codigo_ref_grafica) VALUES(4,4);
INSERT INTO Puntuacion (Nota,Codigo_ref_grafica) VALUES(2,4);
INSERT INTO Puntuacion (Nota,Codigo_ref_grafica) VALUES(0,4);
INSERT INTO Puntuacion (Nota,Codigo_ref_grafica) VALUES(5,4);
INSERT INTO Puntuacion (Nota,Codigo_ref_grafica) VALUES(3,4);



