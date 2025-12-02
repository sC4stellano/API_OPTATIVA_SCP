# API_OPTATIVA_SCP
API REST desarrollada con Spring Boot para gestionar proyectos, sus estados, las tecnologías utilizadas y los desarrolladores que han trabajado en ellos.

---

## Tecnologías

- Java 22  
- Spring Boot 4 (Spring Web, Spring Data JPA)  
- MySQL  
- Lombok  

---

## Modelo de datos

La base de datos `Proyectosdb` contiene las siguientes tablas:

- `projects`: proyecto (id, nombre, descripción, fechas, `demo_url`, `picture`, `status_status_id`).  
- `status`: estados posibles de un proyecto.  
- `technologies`: tecnologías disponibles.  
- `developers`: desarrolladores con datos de contacto y enlaces.  
- `technologies_used_in_projects`: relación N:M entre `projects` y `technologies`.  
- `developers_worked_on_projects`: relación N:M entre `projects` y `developers`.  

Relaciones principales:

- `Project` N:1 `Status`  
- `Project` N:M `Technology`  
- `Project` N:M `Developer`  

<img width="602" height="431" alt="image" src="https://github.com/user-attachments/assets/858d2f0a-f52a-46f6-a302-2c7b017049b2" />

## Arquitectura

Se sigue el patrón Controlador – Servicio – Repositorio.

- **Controladores (`Controller`)**  
  - `ProyectoController` – Proyectos  
  - `DeveloperController` – Desarrolladores  
  - `TechnologyController` – Tecnologías  
  - `StatusController` – Estados

- **Servicios (`Service` / `Service.impl`)**  
  - `ProjectService` / `ProjectServiceImpl`  
  - `DeveloperService` / `DeveloperServiceImpl`  
  - `TechnologyService` / `TechnologyServiceImpl`  
  - `StatusService` / `StatusServiceImpl`

- **Persistencia (`Persistence`)**  
  - `Model`: entidades JPA (`Project`, `Developer`, `Technology`, `Status`).  
  - `Repository`: interfaces que extienden `JpaRepository`/`CrudRepository`.

Todas las respuestas de los controladores se devuelven como `ResponseEntity` con códigos HTTP apropiados.
La BD se inicializa ejecutando el script SQL incluido (creación de tablas y datos de ejemplo para `status`, `technologies`, `developers`).

---

## Endpoints

Base común: `http://localhost:8080/api/v1`

### Proyectos

**GET `/api/v1/projects`**  
Obtiene todos los proyectos. Cada proyecto incluye:

- Estado (`status`)  
- Tecnologías utilizadas (`technologiesUsed`)  
- Desarrolladores que han trabajado en él (`developersWorking`)

**GET `/api/v1/projects/{word}`**  
Obtiene los proyectos cuyo nombre contiene `{word}` (búsqueda case‑insensitive).  
La estructura de la respuesta es la misma que en el endpoint anterior.

**POST `/api/v1/projects`**  
Inserta un nuevo proyecto.

{
  "name": "E-Commerce API",
  "description": "API REST para tienda online",
  "starDate": "2025-01-15",
  "endDate": "2025-06-30",
  "demoUrl": "https://ecommerce-demo.com",
  "picture": "https://via.placeholder.com/300"
}

**PUT `/api/v1/projects/{id}`**  
Edita el proyecto con identificador `{id}`.  
Devuelve el proyecto actualizado o `404` si no existe.

**DELETE `/api/v1/projects/{id}`**  
Elimina el proyecto con identificador `{id}`.  
Devuelve `204` si se elimina o `404` si no existe.

---

### Desarrolladores

**POST `/api/v1/developers`**  
Inserta un desarrollador.
{
  "name": "Carlos",
  "surname": "López",
  "email": "carlos@example.com",
  "linUrl": "https://linkedin.com/in/carlos",
  "gitUrl": "https://github.com/carlos"
}
**DELETE `/api/v1/developers/{id}`**  
Elimina un desarrollador por id.  
Devuelve `204` si se elimina o `404` si no existe.

*(Además existen endpoints GET y PUT para listar, buscar y editar desarrolladores.)*
### Tecnologías

**POST `/api/v1/technologies`**  
Inserta una tecnología.

Ejemplo de cuerpo:
{
  "techName": "React"
}
**DELETE `/api/v1/technologies/{id}`**  
Elimina una tecnología por id.  
Devuelve `204` si se elimina o `404` si no existe.

*(También existen endpoints GET y PUT para listar, buscar y editar tecnologías.)*

---

### Estados (soporte)

**Base:** `/api/v1/status`  

Endpoints para listar, buscar, crear, actualizar y eliminar estados de proyecto, permitiendo gestionar completamente la tabla `status`.

---
## Puesta en marcha

1. Crear la base de datos `Proyectosdb` en MySQL.  
2. Ejecutar el script SQL para crear tablas y datos de ejemplo.  
3. Configurar `application.yml` con URL, usuario y contraseña correctos.  
4. Ejecutar: mvn spring-boot:run
