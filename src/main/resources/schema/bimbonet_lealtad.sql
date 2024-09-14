-- DROP SEQUENCE public.accion_recompensa_id_seq;
CREATE SEQUENCE public.accion_recompensa_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
	CACHE 1
	NO CYCLE;

-- DROP SEQUENCE public.acciones_id_seq;
CREATE SEQUENCE public.acciones_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
	CACHE 1
	NO CYCLE;

-- DROP SEQUENCE public.puntos_id_seq;
CREATE SEQUENCE public.puntos_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
	CACHE 1
	NO CYCLE;

-- DROP SEQUENCE public.recompensas_id_seq;
CREATE SEQUENCE public.recompensas_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
	CACHE 1
	NO CYCLE;

-- DROP SEQUENCE public.roles_id_seq;
CREATE SEQUENCE public.roles_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
	CACHE 1
	NO CYCLE;

-- DROP SEQUENCE public.usuarios_id_seq;
CREATE SEQUENCE public.usuarios_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
	CACHE 1
	NO CYCLE;

-- DROP SEQUENCE public.usuarios_recompensas_id_seq;
CREATE SEQUENCE public.usuarios_recompensas_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
	CACHE 1
	NO CYCLE;

-- DROP TABLE public.acciones;
CREATE TABLE public.acciones (
    id serial4 NOT NULL,
    descripcion varchar(255) NOT NULL,
    tiene_recompensa bool DEFAULT false NULL,
    fecha_creacion timestamp NOT NULL,
    CONSTRAINT acciones_pkey PRIMARY KEY (id)
);

-- DROP TABLE public.recompensas;
CREATE TABLE public.recompensas (
    id serial4 NOT NULL,
    nombre text NOT NULL,
    valor int4 NOT NULL,
    date_created timestamp DEFAULT now() NOT NULL,
    puntos int4 NULL,
    CONSTRAINT recompensas_pk PRIMARY KEY (id)
);

-- DROP TABLE public.roles;
CREATE TABLE public.roles (
    id serial4 NOT NULL,
    "name" varchar(50) NOT NULL,
    CONSTRAINT roles_name_key UNIQUE (name),
    CONSTRAINT roles_pkey PRIMARY KEY (id)
);

-- DROP TABLE public.usuarios;
CREATE TABLE public.usuarios (
    id serial4 NOT NULL,
    nombre_completo text NOT NULL,
    email varchar(256) DEFAULT ''::character varying NOT NULL,
    "password" text NOT NULL,
    date_created timestamp DEFAULT now() NOT NULL,
    enabled bool DEFAULT true NOT NULL,
    CONSTRAINT usuarios_pk PRIMARY KEY (id)
);

-- DROP TABLE public.accion_recompensa;
CREATE TABLE public.accion_recompensa (
    id serial4 NOT NULL,
    accion_id int8 NOT NULL,
    recompensa_id int8 NOT NULL,
    CONSTRAINT accion_recompensa_pkey PRIMARY KEY (id),
    CONSTRAINT fk_accion FOREIGN KEY (accion_id) REFERENCES public.acciones(id),
    CONSTRAINT fk_recompensa FOREIGN KEY (recompensa_id) REFERENCES public.recompensas(id)
);

-- DROP TABLE public.puntos;
CREATE TABLE public.puntos (
    id serial4 NOT NULL,
    recompensa_id int8 NOT NULL,
    usuario_id int8 NOT NULL,
    cantidad int8 NOT NULL,
    date_created timestamp DEFAULT now() NOT NULL,
    activo bool DEFAULT true NULL,
    CONSTRAINT puntos_pk PRIMARY KEY (id),
    CONSTRAINT puntos_recompensa_fk FOREIGN KEY (recompensa_id) REFERENCES public.recompensas(id),
    CONSTRAINT puntos_usuario_fk FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id)
);

-- DROP TABLE public.usuarios_recompensas;
CREATE TABLE public.usuarios_recompensas (
    id serial4 NOT NULL,
    recompensa_id int8 NOT NULL,
    usuario_id int8 NOT NULL,
    valor int8 NOT NULL,
    CONSTRAINT usuarios_recompensas_pk PRIMARY KEY (id),
    CONSTRAINT usuarios_recompensas_recompensa_fk FOREIGN KEY (recompensa_id) REFERENCES public.recompensas(id),
CONSTRAINT usuarios_recompensas_usuario_fk FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id)
);

-- DROP TABLE public.usuarios_roles;
CREATE TABLE public.usuarios_roles (
   usuario_id int4 NOT NULL,
   rol_id int4 NOT NULL,
   CONSTRAINT usuarios_roles_pkey PRIMARY KEY (usuario_id, rol_id),
   CONSTRAINT usuarios_roles_rol_id_fkey FOREIGN KEY (rol_id) REFERENCES public.roles(id) ON DELETE CASCADE,
   CONSTRAINT usuarios_roles_usuario_id_fkey FOREIGN KEY (usuario_id) REFERENCES public.usuarios(id) ON DELETE CASCADE
);