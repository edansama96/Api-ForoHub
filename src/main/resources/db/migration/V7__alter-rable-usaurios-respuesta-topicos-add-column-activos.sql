-- 1. Agregar campo "activo" a Tópicos
ALTER TABLE topicos ADD activo TINYINT DEFAULT 1 NOT NULL;

-- 2. Agregar campo "activo" a Respuestas
ALTER TABLE respuestas ADD activo TINYINT DEFAULT 1 NOT NULL;

-- 3. Agregar campo "activo" a Usuarios
ALTER TABLE usuarios ADD activo TINYINT DEFAULT 1 NOT NULL;
