# Flyway Baseline Integration Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Configurar Flyway para registrar la línea base existente y aplicar migraciones MySQL posteriores con seguridad.

**Architecture:** Spring Boot configurará Flyway con `baseline-on-migrate` y versión `20260822` para las bases MySQL existentes. Hibernate conservará temporalmente la actualización de esquema; el perfil de tests H2 deshabilitará Flyway porque el esquema efímero es creado por JPA.

**Tech Stack:** Spring Boot 3.1, Flyway, MySQL 8, Maven, JUnit 5.

---

### Task 1: Añadir la dependencia de Flyway

**Files:**
- Modify: `pom.xml`

**Step 1: Declarar el módulo de Flyway y el soporte MySQL compatible con Spring Boot 3.1.**

**Step 2: Ejecutar la comprobación de Maven.**

Run: `./mvnw test -DskipTests`

Expected: compilación y resolución de dependencias exitosa.

### Task 2: Configurar los perfiles de ejecución

**Files:**
- Modify: `src/main/resources/application-dev.properties`
- Modify: `src/main/resources/application-prod.properties`
- Modify: `src/test/resources/application.properties`

**Step 1: Habilitar Flyway en MySQL.**

Configurar la ubicación `classpath:db/migration`, baseline automático y `baseline-version=20260822`.

**Step 2: Deshabilitar Flyway en H2 de tests.**

Mantener el ciclo `create-drop` actual sin ejecutar scripts MySQL.

### Task 3: Verificar el arranque y el historial MySQL

**Files:**
- Modify: `src/main/resources/db/migration/V20260822__sync_plan_informatica_2023.sql`

**Step 1: Arrancar Spring Boot con el perfil dev.**

Expected: Flyway crea o reconoce `flyway_schema_history` y registra baseline 20260822 sin ejecutar nuevamente la migración ya aplicada.

**Step 2: Consultar MySQL.**

Verificar la fila de baseline y preservar 67 materias y 112 correlatividades en currículo 4.

**Step 3: Commit.**

```bash
git add pom.xml src/main/resources/application-dev.properties src/main/resources/application-prod.properties src/test/resources/application.properties
git commit -m "feat: configure flyway migrations"
```
