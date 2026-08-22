# Plan de Estudios 2023 de Ingeniería en Informática Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Sincronizar el currículo 4 de MySQL con las materias y correlatividades del PDF del Plan 2023.

**Architecture:** Se incorporará una migración SQL transaccional e idempotente que actúa únicamente sobre las materias que pertenecen al currículo 4. Antes de confirmar, validará el conjunto cargado; luego se ejecutará sobre MySQL y se verificará mediante consultas de lectura.

**Tech Stack:** Spring Boot 3, MySQL 8, SQL versionado, JUnit 5.

---

### Task 1: Definir el conjunto canónico del PDF

**Files:**
- Create: `src/main/resources/db/migration/V20260822__sync_plan_informatica_2023.sql`

**Step 1: Transcribir las materias, período y anualidad**

Incluir los códigos 3599, 3621–3680 (cuando existen en el PDF), 901–904 y 911–912; usar `INSERT ... ON DUPLICATE KEY UPDATE` sin sobrescribir descripciones.

**Step 2: Transcribir exactamente las correlatividades**

Representar cada código indicado por el PDF como una fila en `subject_prerequisites`.

### Task 2: Hacer la sincronización acotada y atómica

**Files:**
- Modify: `src/main/resources/db/migration/V20260822__sync_plan_informatica_2023.sql`

**Step 1: Iniciar transacción y validar el currículo objetivo**

Validar que exista `curriculums.id = 4` y que se denomine Ingeniería en Informática.

**Step 2: Reemplazar solo relaciones del plan**

Eliminar las filas de `curriculums_subjects` para el currículo 4 y las correlatividades de sus materias, volver a cargarlas según el PDF y no eliminar materias ni relaciones de otros currículos.

**Step 3: Validar y confirmar**

Comprobar las cantidades esperadas de materias y correlatividades; ante cualquier desvío, provocar un error para que MySQL revierta toda la transacción.

### Task 3: Agregar prueba de regresión de los datos

**Files:**
- Create: `src/test/java/Javastral/com/gestorMateriasWeb/model/repository/InformaticsCurriculum2023IntegrationTest.java`

**Step 1: Escribir la prueba que consulta el currículo 4**

Verificar que contenga las materias nuevas, que Proyecto Final sea anual y que las relaciones actualizadas (por ejemplo 3668→3664, 3659→3648 y 3672→3653) estén presentes.

**Step 2: Ejecutar la prueba**

Run: `./mvnw test -Dtest=InformaticsCurriculum2023IntegrationTest`

Expected: PASS.

### Task 4: Ejecutar y comprobar MySQL

**Files:**
- Modify: `src/main/resources/db/migration/V20260822__sync_plan_informatica_2023.sql`

**Step 1: Ejecutar el script sobre la conexión MySQL configurada**

Run: `mysql --defaults-extra-file=<archivo-temporal-seguro> gesmat < src/main/resources/db/migration/V20260822__sync_plan_informatica_2023.sql`

Expected: ejecución exitosa y transacción confirmada.

**Step 2: Consultar la BD resultante**

Comprobar la cantidad de materias asignadas al currículo y correlatividades clave de la nueva versión.

**Step 3: Commit**

```bash
git add src/main/resources/db/migration/V20260822__sync_plan_informatica_2023.sql src/test/java/Javastral/com/gestorMateriasWeb/model/repository/InformaticsCurriculum2023IntegrationTest.java
git commit -m "feat: sync informatics 2023 curriculum"
```
