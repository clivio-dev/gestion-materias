-- Synchronizes curriculum 4 with the Ingeniería en Informática 2023 PDF.
-- Run this script explicitly with a MySQL 8 client. It is safe to repeat.

DROP PROCEDURE IF EXISTS validate_plan_informatica_2023;
DELIMITER $$
CREATE PROCEDURE validate_plan_informatica_2023(IN validate_final BOOLEAN)
BEGIN
    DECLARE department_matches INT DEFAULT 0;
    DECLARE curriculum_matches INT DEFAULT 0;
    DECLARE mapped_subjects INT DEFAULT 0;
    DECLARE prerequisites INT DEFAULT 0;

    SELECT COUNT(*) INTO department_matches
    FROM departments
    WHERE id = 1
      AND name = 'Departamento de Ingeniería';

    IF department_matches <> 1 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Department 1 is not Departamento de Ingeniería';
    END IF;

    SELECT COUNT(*) INTO curriculum_matches
    FROM curriculums
    WHERE id = 4
      AND name = 'Ingeniería en Informática'
      AND department_id = 1;

    IF curriculum_matches <> 1 THEN
        ROLLBACK;
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Curriculum 4 is not Ingeniería en Informática in department 1';
    END IF;

    IF validate_final THEN
        SELECT COUNT(*) INTO mapped_subjects
        FROM curriculums_subjects
        WHERE curriculum_id = 4;

        SELECT COUNT(*) INTO prerequisites
        FROM subject_prerequisites sp
        INNER JOIN curriculums_subjects cs ON cs.subject_id = sp.subject_id
        WHERE cs.curriculum_id = 4;

        IF mapped_subjects <> 67 OR prerequisites <> 112 THEN
            ROLLBACK;
            SIGNAL SQLSTATE '45000'
                SET MESSAGE_TEXT = 'Plan Informática 2023 validation failed: expected 67 subjects and 112 prerequisites';
        END IF;
    END IF;
END$$
DELIMITER ;

-- Temporary tables make the replacement set explicit and keep all deletes scoped to this plan.
DROP TEMPORARY TABLE IF EXISTS tmp_plan_informatica_2023_subjects;
CREATE TEMPORARY TABLE tmp_plan_informatica_2023_subjects (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    semester INT NOT NULL,
    anual BOOLEAN NOT NULL
);

INSERT INTO tmp_plan_informatica_2023_subjects (id, name, semester, anual) VALUES
    (3599, 'Redes Móviles e IoT', 10, FALSE),
    (3621, 'Matemática Discreta', 1, FALSE),
    (3622, 'Análisis Matemático I', 1, FALSE),
    (3623, 'Programación Inicial', 1, FALSE),
    (3624, 'Introducción a los Sistemas de Información', 1, FALSE),
    (3625, 'Sistemas de Numeración', 1, FALSE),
    (3626, 'Principios de Calidad de Software', 1, FALSE),
    (3627, 'Álgebra y Geometría Analítica I', 2, FALSE),
    (3628, 'Física I', 2, FALSE),
    (3629, 'Programación Estructurada Básica', 2, FALSE),
    (3630, 'Introducción a la Gestión de Requisitos', 2, FALSE),
    (3631, 'Fundamentos de Sistemas Embebidos', 2, FALSE),
    (3632, 'Introducción a los Proyectos Informáticos', 2, FALSE),
    (3633, 'Análisis Matemático II', 3, FALSE),
    (3634, 'Física II', 3, FALSE),
    (3635, 'Tópicos de Programación', 3, FALSE),
    (3636, 'Bases de Datos', 3, FALSE),
    (3637, 'Análisis de Sistemas', 3, FALSE),
    (3638, 'Arquitectura de Computadoras', 3, FALSE),
    (3676, 'Responsabilidad Social Universitaria', 3, FALSE),
    (3639, 'Análisis Matemático III', 4, FALSE),
    (3640, 'Algoritmos y Estructuras de Datos', 4, FALSE),
    (3641, 'Bases de Datos Aplicadas', 4, FALSE),
    (3642, 'Principios de Diseño de Sistemas', 4, FALSE),
    (3643, 'Redes de Computadoras', 4, FALSE),
    (3644, 'Gestión de las Organizaciones', 4, FALSE),
    (3680, 'Taller de Integración', 4, FALSE),
    (3645, 'Álgebra y Geometría Analítica II', 5, FALSE),
    (3646, 'Paradigmas de Programación', 5, FALSE),
    (3647, 'Requisitos Avanzados', 5, FALSE),
    (3648, 'Diseño de Software', 5, FALSE),
    (3649, 'Sistemas Operativos', 5, FALSE),
    (3650, 'Seguridad de la Información', 5, FALSE),
    (3675, 'Práctica Profesional Supervisada', 5, FALSE),
    (3651, 'Probabilidad y Estadística', 6, FALSE),
    (3652, 'Programación Avanzada', 6, FALSE),
    (3653, 'Arquitectura de Sistemas Software', 6, FALSE),
    (3654, 'Virtualización de Hardware', 6, FALSE),
    (3655, 'Auditoria y Legislación', 6, FALSE),
    (3656, 'Estadística Aplicada', 7, FALSE),
    (3657, 'Autómatas y Gramáticas', 7, FALSE),
    (3658, 'Programación Concurrente', 7, FALSE),
    (3659, 'Gestión Aplic. al Des. de Software I', 7, FALSE),
    (3660, 'Sistemas Operativos Avanzados', 7, FALSE),
    (3661, 'Gestión de Proyectos', 7, FALSE),
    (3662, 'Matemática Aplicada', 8, FALSE),
    (3663, 'Lenguajes y Compiladores', 8, FALSE),
    (3664, 'Inteligencia Artificial', 8, FALSE),
    (3665, 'Gestión Aplicada al Desarrollo de Software II', 8, FALSE),
    (3666, 'Seguridad Aplicada y Forensia', 8, FALSE),
    (3667, 'Gestión de la Calidad en Procesos de Sistemas', 8, FALSE),
    (3668, 'Inteligencia Artificial Aplicada', 9, FALSE),
    (3669, 'Innovación y Emprendedorismo', 9, FALSE),
    (3670, 'Ciencia de Datos', 9, FALSE),
    (3671, 'Proyecto Final de Carrera', 9, TRUE),
    (3672, 'Electiva I', 9, FALSE),
    (3673, 'Electiva II', 10, FALSE),
    (3674, 'Electiva III', 10, FALSE),
    (3677, 'Lenguaje Orientado a Negocios', 9, FALSE),
    (3678, 'Tecnologías en Seguridad', 10, FALSE),
    (3679, 'Visión Artificial', 10, FALSE),
    (901, 'Inglés Transversal Nivel I', 1, FALSE),
    (902, 'Inglés Transversal Nivel II', 2, FALSE),
    (903, 'Inglés Transversal Nivel III', 3, FALSE),
    (904, 'Inglés Transversal Nivel IV', 4, FALSE),
    (911, 'Computación Transversal Nivel I', 1, FALSE),
    (912, 'Computación Transversal Nivel II', 2, FALSE);

START TRANSACTION;

-- The department and curriculum are created only when absent; existing names are not overwritten.
INSERT IGNORE INTO departments (id, name) VALUES (1, 'Departamento de Ingeniería');
INSERT IGNORE INTO curriculums (id, name, department_id)
VALUES (4, 'Ingeniería en Informática', 1);

CALL validate_plan_informatica_2023(FALSE);

-- Deliberately omit description from the update clause so existing content is preserved.
INSERT INTO subjects (id, name, semester, anual)
SELECT id, name, semester, anual
FROM tmp_plan_informatica_2023_subjects
ON DUPLICATE KEY UPDATE
    name = VALUES(name),
    semester = VALUES(semester),
    anual = VALUES(anual);

DELETE sp
FROM subject_prerequisites sp
INNER JOIN tmp_plan_informatica_2023_subjects plan ON plan.id = sp.subject_id;

DELETE FROM curriculums_subjects WHERE curriculum_id = 4;

INSERT INTO curriculums_subjects (curriculum_id, subject_id)
SELECT 4, id FROM tmp_plan_informatica_2023_subjects;

INSERT INTO subject_prerequisites (subject_id, prerequisite_id) VALUES
    (3628, 3622), (3629, 3623), (3630, 3624), (3631, 3625),
    (3633, 3622), (3634, 3628), (3635, 3629), (3635, 3621),
    (3636, 3629), (3636, 3621), (3637, 3630), (3638, 3631),
    (3676, 3626), (3639, 3633), (3640, 3635), (3641, 3635),
    (3641, 3636), (3642, 3637), (3642, 3626), (3643, 3638),
    (3644, 3632),
    (3680, 3638), (3680, 3636), (3680, 3635), (3680, 3632),
    (3680, 3630), (3680, 3626), (3680, 3625), (3680, 3624),
    (3680, 3623), (3680, 3621),
    (3645, 3627), (3646, 3640), (3646, 3633), (3647, 3642),
    (3648, 3635), (3648, 3636), (3648, 3642), (3649, 3629),
    (3649, 3638), (3650, 3643), (3650, 3638), (3650, 3635),
    (3675, 3636), (3675, 3640), (3675, 3642), (3651, 3645),
    (3651, 3639), (3651, 3621), (3652, 3641), (3652, 3646),
    (3653, 3648), (3654, 3643), (3654, 3649), (3655, 3650),
    (3656, 3651), (3657, 3646), (3658, 3646), (3658, 3649),
    (3659, 3644), (3659, 3647), (3659, 3648), (3660, 3634),
    (3660, 3654), (3661, 3651), (3661, 3650), (3661, 3644),
    (3662, 3651), (3663, 3657), (3664, 3651), (3664, 3646),
    (3665, 3653), (3665, 3659), (3666, 3655), (3666, 3652),
    (3666, 3649), (3667, 3642), (3667, 3661), (3668, 3664),
    (3668, 3656), (3669, 3661), (3670, 3664), (3670, 3656),
    (3671, 3656), (3671, 3659), (3671, 3660), (3671, 3667),
    (3672, 3652), (3672, 3653), (3672, 3661), (3673, 3652),
    (3673, 3653), (3673, 3661), (3674, 3652), (3674, 3653),
    (3674, 3661), (3677, 3652), (3677, 3653), (3677, 3661),
    (3678, 3652), (3678, 3653), (3678, 3661), (3599, 3652),
    (3599, 3653), (3599, 3661), (3679, 3652), (3679, 3653),
    (3679, 3661), (902, 901), (903, 902), (904, 903), (912, 911);

CALL validate_plan_informatica_2023(TRUE);
COMMIT;

DROP PROCEDURE validate_plan_informatica_2023;
DROP TEMPORARY TABLE IF EXISTS tmp_plan_informatica_2023_subjects;
