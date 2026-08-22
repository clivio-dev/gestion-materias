# Diseño: integración segura de Flyway

## Objetivo

Incorporar Flyway como gestor de las migraciones futuras sin alterar la estructura
ni los datos de las bases MySQL existentes.

## Decisión

Flyway se habilitará para los perfiles MySQL y hará baseline de bases no vacías en
la versión `20260822`. Esta versión representa la migración del Plan de Estudios
2023 que ya fue aplicada manualmente. Así, Flyway crea su historial sin volver a
ejecutar ese script y ejecuta únicamente migraciones posteriores.

Hibernate mantiene temporalmente `ddl-auto=update`; una migración posterior podrá
trasladar todo el esquema a Flyway y cambiarlo por `validate`. Las pruebas H2
actuales deshabilitan Flyway para no intentar aplicar una migración MySQL antes de
que Hibernate construya el esquema efímero.

## Validación

Se verificará una ejecución de Spring Boot contra MySQL y la creación de
`flyway_schema_history` con baseline `20260822`, junto con la preservación de las
67 materias y 112 correlatividades del currículo 4.
