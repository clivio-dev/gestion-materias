# Diseño: sincronización del Plan de Estudios 2023 de Ingeniería en Informática

## Objetivo

Sincronizar el currículo `4` (Ingeniería en Informática) de MySQL con el PDF
`565_PlanInformatica2023nuevascorrelativas.pdf`.

## Alcance

La migración versionada incorporará las materias faltantes, actualizará los nombres,
cuatrimestres y la anualidad que declara el PDF, y reemplazará exclusivamente las
correlatividades y las asignaciones al currículo de ese plan. Se preservarán las
descripciones y cualquier relación ajena al currículo 4.

## Decisión

Se agregará un script SQL transaccional en `src/main/resources/db/migration/`. No
hay Flyway ni Liquibase configurados, por lo que se ejecutará de forma explícita
contra el MySQL local y quedará versionado como registro reproducible del cambio.

## Validación

El script verificará, antes de confirmar, la cantidad esperada de materias y
correlatividades del plan. Una prueba de integración comprobará que el currículo 4
contiene el conjunto esperado y relaciones representativas de las correlatividades
nuevas.
