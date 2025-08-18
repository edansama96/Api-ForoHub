# 📌 ForoHub - API REST

ForoHub es una aplicación estilo **foro de discusión** desarrollada con **Spring Boot**.  
Su objetivo es permitir a los usuarios registrarse, iniciar sesión, crear tópicos (preguntas o hilos de discusión), responder a esos tópicos y administrar la información con reglas de negocio claras.  

El proyecto sigue principios de arquitectura limpia, seguridad con **JWT (JSON Web Token)** y persistencia con **Spring Data JPA**.

---

## ⚙️ Funcionalidades principales

- **Registro y autenticación de usuarios** con roles y perfiles.
- **Creación, actualización, consulta y eliminación (CRUD) de tópicos.**
- **Respuestas a los tópicos**, incluyendo la posibilidad de marcar una respuesta como solución.
- **Asociación de tópicos a cursos**, lo que organiza los debates por área de conocimiento.
- **Seguridad con Spring Security + JWT** para proteger los endpoints.
- **Validaciones de negocio** (no permitir tópicos duplicados en un curso, usuarios autenticados para participar, etc.).

---

## 🛠️ Tecnologías utilizadas

- **Java 17+**
- **Spring Boot 3**
- **Spring Security + JWT**
- **Spring Data JPA / Hibernate**
- **Base de datos relacional (MySQL / PostgreSQL)**
- **Maven**
- **Lombok**
- **Trello (gestión de tareas y sprints)**

---

## 🗂️ Estructura de la base de datos

A continuación, se describe el modelo de datos reflejado en el diagrama:

### 🔹 Usuario
- **id**  
- **nombre**  
- **correoElectronico**  
- **contrasena** (encriptada con BCrypt)  
- **perfiles** → relación con la tabla `Perfil`

👉 Un usuario puede crear múltiples **tópicos** y múltiples **respuestas**.

---

### 🔹 Perfil
- **id**  
- **nombre** (ejemplo: `ROLE_USER`, `ROLE_ADMIN`)  

👉 Define los permisos de acceso de los usuarios.

---

### 🔹 Curso
- **id**  
- **nombre**  
- **categoria**  

👉 Cada tópico pertenece a un curso.

---

### 🔹 Tópico
- **id**  
- **titulo**  
- **mensaje**  
- **fechaCreacion**  
- **status** (ej: abierto, cerrado, resuelto)  
- **autor** (FK → Usuario)  
- **curso** (FK → Curso)  
- **respuestas** (1:N → Respuesta)

👉 Es el núcleo del foro: un hilo de discusión iniciado por un usuario.

---

### 🔹 Respuesta
- **id**  
- **mensaje**  
- **fechaCreacion**  
- **autor** (FK → Usuario)  
- **topico** (FK → Tópico)  
- **solucion** (boolean, indica si resuelve el tópico)

👉 Una respuesta está asociada a un único tópico y a un autor.

---

## 🔄 Relaciones principales

- **Usuario ↔ Perfil** → Relación N:N (un usuario puede tener varios perfiles).  
- **Usuario ↔ Tópico** → Relación 1:N (un usuario puede crear muchos tópicos).  
- **Usuario ↔ Respuesta** → Relación 1:N (un usuario puede dar muchas respuestas).  
- **Curso ↔ Tópico** → Relación 1:N (un curso puede tener varios tópicos).  
- **Tópico ↔ Respuesta** → Relación 1:N (un tópico puede tener múltiples respuestas).  

---

## 🚀 Flujo de uso

1. El **usuario se registra o inicia sesión** → recibe un **JWT**.
2. Con el token, puede **crear un tópico** en un curso existente.
3. Otros usuarios pueden **responder al tópico**.
4. El autor del tópico puede marcar una **respuesta como solución**.
5. Los administradores (ROLE_ADMIN) pueden gestionar usuarios, tópicos y cursos.

---

## 📌 Ejemplo de endpoints

- `POST /login` → Autenticación (retorna JWT).  
- `POST /usuarios` → Registro de nuevos usuarios.  
- `GET /topicos` → Listar tópicos (paginación incluida).  
- `POST /topicos` → Crear un nuevo tópico.  
- `PUT /topicos/{id}` → Actualizar un tópico.  
- `DELETE /topicos/{id}` → Eliminar un tópico.  
- `POST /respuestas` → Responder a un tópico.  

---

## ✅ Estado del proyecto

- [x] Configuración de seguridad (Spring Security + JWT).  
- [x] Entidades mapeadas con JPA.  
- [x] Repositorios y servicios implementados.  
- [x] CRUD de usuarios y tópicos.  
- [ ] Documentación con Swagger.  
- [ ] Despliegue en la nube (Heroku/AWS).  

---

## 📊 Diagrama de base de datos

![Diagrama de base de datos - ForoHub](./diagrama-base-de-datos-forohub.png)

---

## 📝 Conclusión

ForoHub es un **foro educativo y colaborativo**, diseñado para ser escalable, seguro y fácil de mantener.  
La base de datos refleja la interacción entre **usuarios, cursos, tópicos y respuestas**, con un sistema de perfiles para manejar permisos y roles.
