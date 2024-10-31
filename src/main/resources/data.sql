INSERT INTO departments (id, name) VALUES
(1, 'Departamento de Ingeniería'),
(2, 'Departamento de Humanidades y Ciencias Sociales'),
(3, 'Departamento de Económicas'),
(4, 'Departamento de Derecho'),
(5, 'Departamento de Salud');

-- Curriculums para el Departamento de Ingeniería (id = 1)
INSERT INTO curriculums (id, name) VALUES
(1, 'Arquitectura' ),
(2, 'Ingeniería Mecánica'),
(3, 'Ingeniería en Informática'),
(4, 'Ingeniería en Electrónica'),
(5, 'Diseño de Aplicaciones Móviles'),
(6, 'Ingeniería Industrial'),
(7, 'Ingeniería Civil'),
(8, 'Desarrollo Web'),
(9, 'Comunicación Social'),
(10, 'Trabajo Social'),
(11, 'Relaciones Laborales'),
(12, 'Educación Física'),
(13, 'Relaciones Públicas'),
(14, 'Ceremonial y Protocolo'),
(15, 'Administración'),
(16, 'Contador Público'),
(17, 'Comercio Internacional'),
(18, 'Economía'),
(19, 'Abogacía'),
(20, 'Ciencia Política'),
(21, 'Procurador'),
(22, 'Licenciatura en Enfermería'),
(23, 'Licenciatura en Nutrición'),
(24, 'Licenciatura en Kinesiología y Fisiatría'),
(25, 'Medicina'),
(26, 'Tecnicatura en Anatomía Patológica');

INSERT INTO departments_curriculum_list (curriculum_list_id,department_id) VALUES
(1, 1),
(2,1),
(3,1),
(4,1),
(5,1),
(6,1),
(7,1),
(8,1),
(9,2),
(10,2),
(11,2),
(12,2),
(13,2),
(14,2),
(15,3),
(16,3),
(17,3),
(18,3),
(19,4),
(20,4),
(21,5),
(22,5),
(23,5),
(24,5),
(25,5),
(26,5);


-- Materias de Ingeniería en Informática
INSERT INTO subjects (id, name) VALUES
(3621, 'Matemática Discreta'),
(3622, 'Análisis Matemático I'),
(3623, 'Programación Inicial'),
(3624, 'Introducción a los Sistemas de Información'),
(3625, 'Sistemas de Numeración'),
(3626, 'Principios de Calidad de Software'),
(3627, 'Álgebra y Geometría Analítica I'),
(3628, 'Física I'),
(3629, 'Programación Estructurada Básica'),
(3630, 'Introducción a la Gestión de Requisitos'),
(3631, 'Fundamentos de Sistemas Embebidos'),
(3632, 'Introducción a los Proyectos Informáticos'),
(3633, 'Análisis Matemático II'),
(3634, 'Física II'),
(3635, 'Tópicos de Programación'),
(3636, 'Bases de Datos'),
(3637, 'Análisis de Sistemas'),
(3638, 'Arquitectura de Computadoras'),
(3639, 'Análisis Matemático III'),
(3640, 'Algoritmos y Estructuras de Datos'),
(3641, 'Bases de Datos Aplicadas'),
(3642, 'Principios de Diseño de Sistemas'),
(3643, 'Redes de Computadoras'),
(3644, 'Gestión de las Organizaciones'),
(3645, 'Álgebra y Geometría Analítica II'),
(3646, 'Paradigmas de Programación'),
(3647, 'Requisitos Avanzados'),
(3648, 'Diseño de Software'),
(3649, 'Sistemas Operativos'),
(3650, 'Seguridad de la Información'),
(3651, 'Probabilidad y Estadística'),
(3652, 'Programación Avanzada'),
(3653, 'Arquitectura de Sistemas Software'),
(3654, 'Virtualización de Hardware'),
(3655, 'Auditoria y Legislación'),
(3656, 'Estadística Aplicada'),
(3657, 'Autómatas y Gramáticas'),
(3658, 'Programación Concurrente'),
(3659, 'Gestión Aplic. al Des. de Software I'),
(3660, 'Sistemas Operativos Avanzados'),
(3661, 'Gestión de Proyectos'),
(3668, 'Inteligencia Artificial Aplicada'),
(3669, 'Innovacion y Emprendedorismo'),
(3670, 'Ciencia de Datos'),
(3671, 'Proyecto Final de Carrera'),
(3672, 'Electiva I'),
(3673, 'Electiva II'),
(3674, 'Electiva III'),
(3675, 'Práctica Profesional Supervisada'),
(3676, 'Responsabilidad Social Universitaria'),
(3680, 'Taller de Integración');

-- Correlatividades (prerequisites)
INSERT INTO subject_prerequisites (subject_id, prerequisite_id) VALUES
-- Física I
(3628, 3622),
-- Programación Estructurada Básica
(3629, 3623),
-- Introducción a la Gestión de Requisitos
(3630, 3624),
-- Fundamentos de Sistemas Embebidos
(3631, 3625),
-- Análisis Matemático II
(3633, 3622),
-- Física II
(3634, 3628),
-- Tópicos de Programación
(3635, 3629),
(3635, 3621),
-- Bases de Datos
(3636, 3629),
(3636, 3621),
-- Análisis de Sistemas
(3637, 3630),
-- Arquitectura de Computadoras
(3638, 3631),
-- Responsabilidad Social Universitaria
(3676, 3626),
-- Análisis Matemático III
(3639, 3633),
-- Algoritmos y Estructuras de Datos
(3640, 3635),
-- Bases de Datos Aplicadas
(3641, 3636),
-- Principios de Diseño de Sistemas
(3642, 3637),
(3642, 3626),
-- Redes de Computadoras
(3643, 3638),
(3643, 3634),
-- Gestión de las Organizaciones
(3644, 3632),
-- Taller de Integración
(3680, 3638),
(3680, 3636),
(3680, 3635),
(3680, 3632),
(3680, 3630),
(3680, 3626),
(3680, 3625),
(3680, 3624),
(3680, 3623),
(3680, 3621),
-- Álgebra y Geometría Analítica II
(3645, 3627),
-- Paradigmas de Programación
(3646, 3640),
(3646, 3633),
-- Requisitos Avanzados
(3647, 3642),
-- Diseño de Software
(3648, 3642),
(3648, 3636),
-- Sistemas Operativos
(3649, 3638),
-- Seguridad de la Información
(3650, 3643),
(3650, 3638),
(3650, 3635),
-- Práctica Profesional Supervisada
(3675, 3642),
-- Probabilidad y Estadística
(3651, 3645),
(3651, 3639),
(3651, 3621),
-- Programación Avanzada
(3652, 3641),
(3652, 3646),
-- Arquitectura de Sistemas Software
(3653, 3648),
-- Virtualización de Hardware
(3654, 3649),
(3654, 3645),
(3654, 3640),
-- Auditoria y Legislación
(3655, 3650),
-- Estadística Aplicada
(3656, 3651),
(3656, 3641),
-- Autómatas y Gramáticas
(3657, 3646),
-- Programación Concurrente
(3658, 3654),
(3658, 3646),
-- Gestión Aplic. al Des. de Software I
(3659, 3653),
(3659, 3647),
(3659, 3644),
-- Sistemas Operativos Avanzados
(3660, 3654),
-- Gestión de Proyectos
(3661, 3651),
(3661, 3650),
(3661, 3644),
-- Inteligencia Artificial Aplicada
(3668, 3656),
-- Innovacion y Emprendedorismo
(3669, 3661),
-- Ciencia de Datos
(3670, 3656),
-- Proyecto Final de Carrera
(3671, 3661),
(3671, 3660),
(3671, 3659),
(3671, 3656),
-- Electiva I
(3672, 3658),
(3672, 3661),
-- Electiva II
(3673, 3658),
-- Electiva III
(3674, 3658);

INSERT INTO curriculums_subjects (curriculum_id, subject_id) VALUES
(4,3621),
(4,3622),
(4,3623),
(4,3624),
(4,3625),
(4,3626),
(4,3627),
(4,3628),
(4,3629),
(4,3630),
(4,3631),
(4,3632),
(4,3633),
(4,3634),
(4,3635),
(4,3636),
(4,3637),
(4,3638),
(4,3639),
(4,3640),
(4,3641),
(4,3642),
(4,3643),
(4,3644),
(4,3645),
(4,3646),
(4,3647),
(4,3648),
(4,3649),
(4,3650),
(4,3651),
(4,3652),
(4,3653),
(4,3654),
(4,3655),
(4,3656),
(4,3657),
(4,3658),
(4,3659),
(4,3660),
(4,3661),
(4,3668),
(4,3669),
(4,3670),
(4,3671),
(4,3672),
(4,3673),
(4,3674),
(4,3675),
(4,3676),
(4,3680);