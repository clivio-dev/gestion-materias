# Diseno: Reviews de materias y profesores

Fecha: 2026-03-01
Estado: Aprobado por usuario

## Objetivo
Permitir que alumnos autenticados creen y actualicen una unica review por materia y una unica review por profesor.

Reglas cerradas:
- Una sola review por usuario y por entidad (materia/profesor), editable.
- Profesores como entidad propia.
- Reviews anonimas publicamente (no exponer autor en endpoints publicos).
- Solo usuarios autenticados pueden crear/modificar reviews.
- Campos obligatorios de review: `rating` (1 a 5) y `comment`.

## Contexto actual
Backend Spring Boot con JWT, entidades `UserEntity`, `Subject`, `Department`, y respuestas estandar `Response<T>`.

## Enfoques evaluados
1. Dos tablas separadas (`subject_reviews`, `professor_reviews`) - recomendado.
2. Tabla polimorfica unica (`reviews` + `target_type/target_id`).
3. Tabla base + tablas hijas.

Recomendacion: opcion 1 por simplicidad, integridad referencial directa y queries mas claras para este proyecto.

## Modelo de datos propuesto

### Tabla `professors`
- `id` BIGINT PK
- `full_name` VARCHAR obligatorio
- `department_id` BIGINT FK nullable -> `departments(id)`
- `created_at`, `updated_at`

### Tabla puente `subject_professors`
- `subject_id` BIGINT FK -> `subjects(id)`
- `professor_id` BIGINT FK -> `professors(id)`
- PK compuesta (`subject_id`, `professor_id`)

### Tabla `subject_reviews`
- `id` BIGINT PK
- `user_id` BIGINT FK -> `users(id)`
- `subject_id` BIGINT FK -> `subjects(id)`
- `rating` TINYINT obligatorio, rango 1..5
- `comment` TEXT obligatorio
- `created_at`, `updated_at`
- `UNIQUE(user_id, subject_id)`
- indices: `subject_id` (y opcional `subject_id, rating`)

### Tabla `professor_reviews`
- `id` BIGINT PK
- `user_id` BIGINT FK -> `users(id)`
- `professor_id` BIGINT FK -> `professors(id)`
- `rating` TINYINT obligatorio, rango 1..5
- `comment` TEXT obligatorio
- `created_at`, `updated_at`
- `UNIQUE(user_id, professor_id)`
- indices: `professor_id` (y opcional `professor_id, rating`)

## Endpoints propuestos

### Reviews publicas
- `GET /subjects/{subjectId}/reviews`
- `GET /professors/{professorId}/reviews`

Respuesta sugerida:
- `summary`: `avgRating`, `totalReviews`
- `items`: `id`, `rating`, `comment`, `createdAt`, `updatedAt`
- Sin `userId` ni username.

### Reviews del usuario autenticado
- `PUT /subjects/{subjectId}/reviews/me` (create-or-update)
- `PUT /professors/{professorId}/reviews/me` (create-or-update)
- `GET /subjects/{subjectId}/reviews/me`
- `GET /professors/{professorId}/reviews/me`
- `DELETE /subjects/{subjectId}/reviews/me` (opcional MVP2)
- `DELETE /professors/{professorId}/reviews/me` (opcional MVP2)

Body para `PUT`:
```json
{
  "rating": 5,
  "comment": "Muy buena materia, exigente pero clara."
}
```

### Profesores y relacion con materias
- `GET /professors` (lista/busqueda)
- `GET /subjects/{subjectId}/professors`
- `POST /subjects/{subjectId}/professors/{professorId}` (admin/mod)
- `DELETE /subjects/{subjectId}/professors/{professorId}` (admin/mod)

## Flujo de datos

### Escritura (`PUT .../me`)
1. Obtener usuario autenticado desde `SecurityContext`.
2. Validar existencia de materia/profesor.
3. Validar request (`rating` 1..5, `comment` no vacio).
4. Buscar por `(user_id, target_id)`.
5. Si existe, actualizar; si no existe, crear.
6. Retornar review propia actualizada.

### Lectura publica (`GET .../reviews`)
1. Recuperar pagina de reviews del target.
2. Calcular resumen (`avgRating`, `totalReviews`).
3. Mapear a DTO anonimo (sin datos de autor).
4. Retornar en `Response<T>` con metadata.

## Validaciones y reglas
- `rating`: `@Min(1) @Max(5)`.
- `comment`: `@NotBlank` y `@Size(max = 2000)` recomendado.
- Constrain `UNIQUE` para garantizar una sola review por usuario.
- Whitelist de sort (`latest`, `rating_desc`) para evitar parametros invalidos.

## Manejo de errores
- `400`: validaciones de body o parametros invalidos.
- `401`: usuario no autenticado para endpoints `.../me`.
- `404`: materia/profesor no encontrado.
- `409` (opcional): conflictos por concurrencia si aplica.

Mantener formato de error consistente con `Response` / `Error` existente.

## Testing propuesto
- Unit tests de DTO validation (`rating`, `comment`).
- Integration tests de repositorios:
  - constraints `UNIQUE` por usuario-target.
  - calculo de promedio y conteo.
- Integration tests de controllers:
  - `PUT .../me` crea.
  - `PUT .../me` actualiza.
  - `GET .../reviews` no expone autor.
  - `GET .../me` requiere auth y devuelve solo review propia.
  - `DELETE .../me` (si se implementa) elimina correctamente.

## Riesgos y mitigaciones
- Riesgo: profesores duplicados por nombre.
  - Mitigacion: normalizacion y busqueda case-insensitive.
- Riesgo: promedio con baja muestra.
  - Mitigacion: exponer siempre `totalReviews` junto con `avgRating`.

## Alcance sugerido
MVP1:
- `GET /subjects/{id}/reviews`
- `GET /professors/{id}/reviews`
- `PUT /subjects/{id}/reviews/me`
- `PUT /professors/{id}/reviews/me`
- `GET /subjects/{id}/reviews/me`
- `GET /professors/{id}/reviews/me`
- `GET /professors`
- `GET /subjects/{id}/professors`

MVP2:
- Endpoints `DELETE .../me`
- Endpoints de asociacion admin/mod materia-profesor
