CREATE TABLE PRODUCTOS (
	CODIGO_PR serial NOT NULL,
	NOMBRE_PR varchar(500) NOT NULL,
	DESCRIPCION_PR varchar(5000) NOT NULL,
	PRECIO_PR FLOAT NOT NULL,
	CODIGO_REF_PR varchar(100) NOT NULL UNIQUE,
	CODIGO_TP integer NOT NULL,
	ESTADO_PR varchar(1) NOT NULL,
	USUARIO_ACT_PR integer NOT NULL,
	FECHA_ACT_PR DATE NOT NULL,
	CONSTRAINT PRODUCTOS_pk PRIMARY KEY (CODIGO_PR)
) WITH (
  OIDS=FALSE
);



CREATE TABLE TIPO_PRODUCTO (
	CODIGO_TP serial NOT NULL,
	NOMBRE_TP varchar(500) NOT NULL,
	CODIGO_EM integer NOT NULL,
	ESTADO_TP varchar(1) NOT NULL,
	USUARIO_ACT_TP integer NOT NULL,
	FECHA_ACT_TP DATE NOT NULL,
	CONSTRAINT TIPO_PRODUCTO_pk PRIMARY KEY (CODIGO_TP)
) WITH (
  OIDS=FALSE
);



CREATE TABLE IMPUESTOS (
	CODIGO_IP serial NOT NULL,
	NOMBRE_IP varchar(500) NOT NULL,
	VALOR_IP FLOAT NOT NULL,
	ESTADO_IP varchar(1) NOT NULL,
	USUARIO_ACT_IP integer NOT NULL,
	FECHA_ACT_IP DATE NOT NULL,
	TIPO_IMPUESTO integer NOT NULL,
	CONSTRAINT IMPUESTOS_pk PRIMARY KEY (CODIGO_IP)
) WITH (
  OIDS=FALSE
);



CREATE TABLE CLIENTE (
	CODIGO_CL serial NOT NULL,
	NOMBRE_CL varchar(500) NOT NULL,
	APELLIDO_CL varchar(500) NOT NULL,
	IDENTIFICACION_CL varchar(13) NOT NULL,
	CORREO_CL varchar(500) NOT NULL,
	CODIGO_TI integer NOT NULL,
	DIRECCION_CL varchar(2000) NOT NULL,
	TELEFONO_CL varchar(50) NOT NULL,
	CODIGO_CI integer NOT NULL,
	ESTADO_CL varchar(1) NOT NULL,
	USUARIO_ACT_CL integer NOT NULL,
	FECHA_ACT_CL DATE NOT NULL,
	CONSTRAINT CLIENTE_pk PRIMARY KEY (CODIGO_CL)
) WITH (
  OIDS=FALSE
);



CREATE TABLE TIPO_IDENTIFICACION (
	CODIGO_TI serial NOT NULL,
	NOMBRE_TI varchar(500) NOT NULL,
	ESTADO_TI varchar(1) NOT NULL,
	USUARIO_ACT_TI integer NOT NULL,
	FECHA_ACT_TI DATE NOT NULL,
	CONSTRAINT TIPO_IDENTIFICACION_pk PRIMARY KEY (CODIGO_TI)
) WITH (
  OIDS=FALSE
);



CREATE TABLE TIPO_FACTURA (
	CODIGO_TF serial NOT NULL,
	NOMBRE_TF varchar(500) NOT NULL,
	ESTADO_TF varchar(1) NOT NULL,
	USUARIO_ACT_TF integer NOT NULL,
	FECHA_ACT_TF DATE NOT NULL,
	CONSTRAINT TIPO_FACTURA_pk PRIMARY KEY (CODIGO_TF)
) WITH (
  OIDS=FALSE
);



CREATE TABLE FACTURA (
	CODIGO_FC serial NOT NULL,
	NUMERO_FC VARCHAR(100) NOT NULL UNIQUE,
	FECHA_FC DATE NOT NULL,
	CODIGO_EM integer NOT NULL,
	CODIGO_CLIENTE integer NOT NULL,
	CODIGO_TF integer NOT NULL,
	CODIGO_CI integer NOT NULL,
	CODIGO_EMP integer NOT NULL,
	ESTADO_FC varchar(1) NOT NULL,
	USUARIO_ACT_FC integer NOT NULL,
	FECHA_ACT_FC DATE NOT NULL,
	TOTAL_FC FLOAT NOT NULL,
	VALOR_IMP_FC FLOAT NOT NULL,
	SUBTOTAL_FC FLOAT NOT NULL,
	CONSTRAINT FACTURA_pk PRIMARY KEY (CODIGO_FC)
) WITH (
  OIDS=FALSE
);



CREATE TABLE EMPRESA (
	CODIGO_EM serial NOT NULL,
	NOMBRE_EM varchar(500) NOT NULL,
	DIRECCION varchar(2000) NOT NULL,
	TELEFONO_EM varchar(50) NOT NULL,
	CORREO_EM varchar(500) NOT NULL,
	RUC varchar(13) NOT NULL,
	CODIGO_PA integer NOT NULL,
	ESTADO_EM varchar(1) NOT NULL,
	USUARIO_ACT_EM integer NOT NULL,
	FECHA_ACT_EM DATE NOT NULL,
	CONSTRAINT EMPRESA_pk PRIMARY KEY (CODIGO_EM)
) WITH (
  OIDS=FALSE
);



CREATE TABLE PAIS (
	CODIGO_PA serial NOT NULL,
	NOMBRE_PA varchar(500) NOT NULL,
	ESTADO_PA varchar(1) NOT NULL,
	USUARIO_ACT_PA integer NOT NULL,
	FECHA_ACT_PA DATE NOT NULL,
	CONSTRAINT PAIS_pk PRIMARY KEY (CODIGO_PA)
) WITH (
  OIDS=FALSE
);



CREATE TABLE PROVINCIA (
	CODIGO_PRO serial NOT NULL,
	NOMBRE_PRO varchar(500) NOT NULL,
	ESTADO_PRO varchar(1) NOT NULL,
	USUARIO_ACT_PRO integer NOT NULL,
	FECHA_AC_PRO DATE NOT NULL,
	CODIGO_PA integer NOT NULL,
	CONSTRAINT PROVINCIA_pk PRIMARY KEY (CODIGO_PRO)
) WITH (
  OIDS=FALSE
);



CREATE TABLE CANTON (
	CODIGO_CA serial NOT NULL,
	NOMBRE_CA varchar(500) NOT NULL,
	ESTADO_CA varchar(1) NOT NULL,
	USUARIO_ACT_CA integer NOT NULL,
	FECHA_ACT_CA DATE NOT NULL,
	CODIGO_PRO integer NOT NULL,
	CONSTRAINT CANTON_pk PRIMARY KEY (CODIGO_CA)
) WITH (
  OIDS=FALSE
);



CREATE TABLE CIUDAD (
	CODIGO_CI serial NOT NULL,
	NOMBRE_CI varchar(500) NOT NULL,
	ESTADO_CI varchar(1) NOT NULL,
	USUARIO_ACT_CI integer NOT NULL,
	FECHA_ACT_CI DATE NOT NULL,
	CODIGO_CA integer NOT NULL,
	CONSTRAINT CIUDAD_pk PRIMARY KEY (CODIGO_CI)
) WITH (
  OIDS=FALSE
);



CREATE TABLE LOCAL (
	CODIGO_LO serial NOT NULL,
	NOMBRE_LO varchar(500) NOT NULL,
	DIRECCION_CI varchar(2000) NOT NULL,
	TELEFONO_CI varchar(50) NOT NULL,
	USUARIO_ACT_LO integer NOT NULL,
	FECHA_ACT_LO DATE NOT NULL,
	CODIGO_EM integer NOT NULL,
	CODIGO_CI integer NOT NULL,
	ESTADO_LO varchar(1) NOT NULL,
	CONSTRAINT LOCAL_pk PRIMARY KEY (CODIGO_LO)
) WITH (
  OIDS=FALSE
);



CREATE TABLE EMPLEADO (
	CODIGO_EMP serial NOT NULL,
	NOMBRE_EMP varchar(500) NOT NULL,
	APELLIDO_EMP varchar(500) NOT NULL,
	IDENTIFICACION_EMP varchar(13) NOT NULL,
	CORREO_EMP varchar(500) NOT NULL,
	TELEFONO_EMP varchar(50) NOT NULL,
	CODIGO_LO integer NOT NULL,
	CODIGO_TI integer NOT NULL,
	ESTADO_EMP varchar(1) NOT NULL,
	USUARIO_ACT_EMP integer NOT NULL,
	FECHA_ACT_EMP DATE NOT NULL,
	CONSTRAINT EMPLEADO_pk PRIMARY KEY (CODIGO_EMP)
) WITH (
  OIDS=FALSE
);



CREATE TABLE DETALLE_FACTURA (
	CODIGO_DF serial NOT NULL,
	CODIGO_FC integer NOT NULL,
	CODIGO_PR integer NOT NULL,
	CANTIDAD_DF integer NOT NULL,
	VALOR_DF FLOAT NOT NULL,
	VALOR_IMPUESTO_DF FLOAT NOT NULL,
	VALOR_TOTAL_DF FLOAT NOT NULL,
	USUARIO_ACT_DF integer NOT NULL,
	FECHA_ACT_DF DATE NOT NULL,
	CONSTRAINT DETALLE_FACTURA_pk PRIMARY KEY (CODIGO_DF)
) WITH (
  OIDS=FALSE
);



CREATE TABLE IMPUESTO_FACTURA (
	CODIGO_IF serial NOT NULL,
	CODIGO_IP integer NOT NULL,
	CODIGO_TF integer NOT NULL,
	ESTADO_IF varchar(1) NOT NULL,
	USUARIO_ACT_IF integer NOT NULL,
	FECHA_ACT_IF DATE NOT NULL,
	CONSTRAINT IMPUESTO_FACTURA_pk PRIMARY KEY (CODIGO_IF)
) WITH (
  OIDS=FALSE
);


CREATE TABLE USUARIO (
	CODIGO_US serial NOT NULL,
	NOMBRE_US varchar(500) NOT NULL,
	ESTADO_US varchar(1) NOT NULL,
	FECHA_ACT_US DATE NOT NULL,
	IDENTIFICACION_US VARCHAR(50),
	CONSTRAINT USUARIO_pk PRIMARY KEY (CODIGO_US)
) WITH (
  OIDS=FALSE
);



CREATE TABLE USUARIO_CLAVE (
	CODIGO_USC serial NOT NULL,
	CLAVE_USC varchar(500) NOT NULL,
	ESTADO_USC varchar(1) NOT NULL,
	FECHA_ACT_USC DATE NOT NULL,
	CODIGO_US integer NOT NULL,
	CONSTRAINT USUARIO_CLAVE_pk PRIMARY KEY (CODIGO_USC)
) WITH (
  OIDS=FALSE
);


ALTER TABLE PRODUCTOS ADD CONSTRAINT PRODUCTOS_fk0 FOREIGN KEY (CODIGO_TP) REFERENCES TIPO_PRODUCTO(CODIGO_TP);

ALTER TABLE TIPO_PRODUCTO ADD CONSTRAINT TIPO_PRODUCTO_fk0 FOREIGN KEY (CODIGO_EM) REFERENCES EMPRESA(CODIGO_EM);


ALTER TABLE CLIENTE ADD CONSTRAINT CLIENTE_fk0 FOREIGN KEY (CODIGO_TI) REFERENCES TIPO_IDENTIFICACION(CODIGO_TI);
ALTER TABLE CLIENTE ADD CONSTRAINT CLIENTE_fk1 FOREIGN KEY (CODIGO_CI) REFERENCES CIUDAD(CODIGO_CI);



ALTER TABLE FACTURA ADD CONSTRAINT FACTURA_fk0 FOREIGN KEY (CODIGO_EM) REFERENCES EMPRESA(CODIGO_EM);
ALTER TABLE FACTURA ADD CONSTRAINT FACTURA_fk1 FOREIGN KEY (CODIGO_CLIENTE) REFERENCES CLIENTE(CODIGO_CL);
ALTER TABLE FACTURA ADD CONSTRAINT FACTURA_fk2 FOREIGN KEY (CODIGO_TF) REFERENCES TIPO_FACTURA(CODIGO_TF);
ALTER TABLE FACTURA ADD CONSTRAINT FACTURA_fk3 FOREIGN KEY (CODIGO_CI) REFERENCES CIUDAD(CODIGO_CI);
ALTER TABLE FACTURA ADD CONSTRAINT FACTURA_fk4 FOREIGN KEY (CODIGO_EMP) REFERENCES EMPLEADO(CODIGO_EMP);

ALTER TABLE EMPRESA ADD CONSTRAINT EMPRESA_fk0 FOREIGN KEY (CODIGO_PA) REFERENCES PAIS(CODIGO_PA);


ALTER TABLE PROVINCIA ADD CONSTRAINT PROVINCIA_fk0 FOREIGN KEY (CODIGO_PA) REFERENCES PAIS(CODIGO_PA);

ALTER TABLE CANTON ADD CONSTRAINT CANTON_fk0 FOREIGN KEY (CODIGO_PRO) REFERENCES PROVINCIA(CODIGO_PRO);

ALTER TABLE CIUDAD ADD CONSTRAINT CIUDAD_fk0 FOREIGN KEY (CODIGO_CA) REFERENCES CANTON(CODIGO_CA);

ALTER TABLE LOCAL ADD CONSTRAINT LOCAL_fk0 FOREIGN KEY (CODIGO_EM) REFERENCES EMPRESA(CODIGO_EM);
ALTER TABLE LOCAL ADD CONSTRAINT LOCAL_fk1 FOREIGN KEY (CODIGO_CI) REFERENCES CIUDAD(CODIGO_CI);

ALTER TABLE EMPLEADO ADD CONSTRAINT EMPLEADO_fk0 FOREIGN KEY (CODIGO_LO) REFERENCES LOCAL(CODIGO_LO);
ALTER TABLE EMPLEADO ADD CONSTRAINT EMPLEADO_fk1 FOREIGN KEY (CODIGO_TI) REFERENCES TIPO_IDENTIFICACION(CODIGO_TI);

ALTER TABLE DETALLE_FACTURA ADD CONSTRAINT DETALLE_FACTURA_fk0 FOREIGN KEY (CODIGO_FC) REFERENCES FACTURA(CODIGO_FC);
ALTER TABLE DETALLE_FACTURA ADD CONSTRAINT DETALLE_FACTURA_fk1 FOREIGN KEY (CODIGO_PR) REFERENCES PRODUCTOS(CODIGO_PR);

ALTER TABLE IMPUESTO_FACTURA ADD CONSTRAINT IMPUESTO_FACTURA_fk0 FOREIGN KEY (CODIGO_IP) REFERENCES IMPUESTOS(CODIGO_IP);
ALTER TABLE IMPUESTO_FACTURA ADD CONSTRAINT IMPUESTO_FACTURA_fk1 FOREIGN KEY (CODIGO_TF) REFERENCES TIPO_FACTURA(CODIGO_TF);

ALTER TABLE USUARIO_CLAVE ADD CONSTRAINT USUARIO_CLAVE_fk0 FOREIGN KEY (CODIGO_US) REFERENCES USUARIO(CODIGO_US);
