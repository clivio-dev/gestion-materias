INSERT INTO departments (id, name) VALUES
(1, 'Departamento de Ingeniería'),
(2, 'Departamento de Humanidades y Ciencias Sociales'),
(3, 'Departamento de Económicas'),
(4, 'Departamento de Derecho'),
(5, 'Departamento de Salud');

-- Curriculums para el Departamento de Ingeniería (id = 1)
INSERT INTO curriculums (id, name, department_id) VALUES
(1, 'Arquitectura',1),
(2, 'Ingeniería Mecánica',1),
(3, 'Ingeniería en Electrónica',1),
(4, 'Ingeniería en Informática',1),
(5, 'Diseño de Aplicaciones Móviles',1),
(6, 'Ingeniería Industrial',1),
(7, 'Ingeniería Civil',1),
(8, 'Desarrollo Web',1),
(9, 'Comunicación Social',2),
(10, 'Trabajo Social',2),
(11, 'Relaciones Laborales', 2),
(12, 'Educación Física',2),
(13, 'Relaciones Públicas',2),
(14, 'Ceremonial y Protocolo',2),
(15, 'Administración',3),
(16, 'Contador Público',3),
(17, 'Comercio Internacional',3),
(18, 'Economía',3),
(20, 'Abogacía',4),
(19, 'Ciencia Política',4),
(21, 'Procurador',4),
(22, 'Licenciatura en Enfermería',5),
(23, 'Licenciatura en Nutrición',5),
(24, 'Licenciatura en Kinesiología y Fisiatría',5),
(25, 'Medicina',5),
(26, 'Tecnicatura en Anatomía Patológica',5);

-- INSERT INTO departments_curriculum_list (curriculum_list_id,department_id) VALUES
-- (9,2),
-- (10,2),
-- (11,2),
-- (12,2),
-- (13,2),
-- (14,2),
-- (15,3),
-- (16,3),
-- (17,3),
-- (18,3),
-- (19,4),
-- (20,4),
-- (21,5),
-- (22,5),
-- (23,5),
-- (24,5),
-- (25,5),
-- (26,5);


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

-- Materias de Abogacía
INSERT INTO subjects (id, name) VALUES
(2351, 'Elementos de Filosofía'),
(2352, 'Procesos Sociohistóricos Mundiales'),
(2353, 'Elementos de Sociología'),
(2354, 'Introducción a la Ciencia Política'),
(2355, 'Taller de Integración'),
(2356, 'Procesos Sociohistóricos Argentinos'),
(2357, 'Elementos de Economía'),
(2358, 'Elementos de Administración'),
(2359, 'Metodología de la Investigación I'),
(2360, 'Teoría Sociológica'),
(2361, 'Economía I'),
(2362, 'Teoría Política I'),
(2363, 'Psicosociología de las Organizaciones'),
(2364, 'Economía II'),
(2365, 'Teoría Política II'),
(2366, 'Administración Pública'),
(2367, 'Metodología de la Investigación II'),
(2368, 'Derecho Constitucional'),
(2369, 'Demografía Social'),
(2370, 'Política y Comunicación'),
(2371, 'Economía Política Argentina'),
(2372, 'Sistemas Políticos Comparados'),
(2373, 'Teoría Política Contemporánea'),
(2374, 'Gestión de Políticas Públicas'),
(2375, 'Derecho Administrativo'),
(2376, 'Finanzas Públicas'),
(2377, 'Relaciones Internacionales'),
(2378, 'Seminario de Tópicos de Avanzada'),
(2379, 'Historia Política Americana'),
(2380, 'Taller de Gestión de Políticas Sociales'),
(2381, 'Taller de Gestión de Políticas de Producción'),
(901, 'Inglés I'),
(902, 'Inglés II'),
(903, 'Inglés III'),
(904, 'Inglés IV'),
(911, 'Computación I'),
(912, 'Computación II');

-- Correlatividades de Abogacía
INSERT INTO subject_prerequisites (subject_id, prerequisite_id) VALUES
-- Taller de Integración
(2355, 2353),
(2355, 2354),
-- Teoría Política I
(2362, 2354),
-- Metodología de la Investigación I
(2359, 2355),
-- Teoría Sociológica
(2360, 2353),
(2360, 2354),
-- Economía I
(2361, 2357),
-- Procesos Sociohistóricos Argentinos
(2356, 2352),
-- Historia Política Americana
(2379, 2362),
-- Economía II
(2364, 2361),
-- Teoría Política II
(2365, 2362),
(2365, 2360),
-- Administración Pública
(2366, 2358),
-- Metodología de la Investigación II
(2367, 2359),
-- Demografía Social
(2369, 2365),
-- Política y Comunicación
(2370, 2365),
-- Economía Política Argentina
(2371, 2369),
-- Sistemas Políticos Comparados
(2372, 2368),
-- Teoría Política Contemporánea
(2373, 2367),
(2373, 2368),
(2373, 2370),
-- Gestión de Políticas Públicas
(2374, 2364),
(2374, 2365),
(2374, 2366),
-- Derecho Administrativo
(2375, 2368),
-- Relaciones Internacionales
(2377, 2373),
-- Psicosociología de las Organizaciones
(2363, 2360),
-- Seminario de Tópicos de Avanzada
(2378, 2371),
(2378, 2372),
(2378, 2373),
-- Taller de Gestión de Políticas Sociales
(2380, 2363),
(2380, 2374),
(2380, 2375),
(2380, 2376),
-- Taller de Gestión de Políticas de Producción
(2381, 2363),
(2381, 2374),
(2381, 2375),
(2381, 2376),
-- Inglés II
(902, 901),
-- Inglés III
(903, 902),
-- Inglés IV
(904, 903),
-- Computación II
(912, 911);

-- Asociar materias con el curriculum de Abogacía (id = 19)
INSERT INTO curriculums_subjects (curriculum_id, subject_id) VALUES
(19, 2351), (19, 2352), (19, 2353), (19, 2354), (19, 2355),
(19, 2356), (19, 2357), (19, 2358), (19, 2359), (19, 2360),
(19, 2361), (19, 2362), (19, 2363), (19, 2364), (19, 2365),
(19, 2366), (19, 2367), (19, 2368), (19, 2369), (19, 2370),
(19, 2371), (19, 2372), (19, 2373), (19, 2374), (19, 2375),
(19, 2376), (19, 2377), (19, 2378), (19, 2379), (19, 2380),
(19, 2381), (19, 901), (19, 902), (19, 903), (19, 904),
(19, 911), (19, 912);

UPDATE subjects SET description='
#### Introducción
La asignatura **Matemática Discreta (MD)** es una disciplina fundamental en el ámbito de las ciencias de la computación y la informática. Su enfoque principal es el estudio de procesos y fenómenos finitos, en contraste con las matemáticas continuas, que tratan procesos infinitos. Esto implica el análisis de objetos que pueden ser descompuestos en partes distintas y separadas, lo que la hace esencial para abordar problemas complejos en múltiples campos tecnológicos y científicos.

---

#### Importancia y Aplicaciones
Matemática Discreta tiene aplicaciones clave en áreas como:
- **Diseño de redes informáticas eficientes:** Optimización de recursos y flujo de datos.
- **Asignación de frecuencias en telefonía celular:** Evitando interferencias y maximizando la cobertura.
- **Gestión de problemas ambientales:** Seguimiento y modelado de contaminación.
- **Programación de proyectos a gran escala:** Uso de algoritmos eficientes.
- **Optimización de rutas y logística:** Reducción de costos y tiempos.
- **Toma de decisiones:** Modelado lógico y algoritmos de evaluación.

Estas aplicaciones permiten a los estudiantes comprender la relevancia de la asignatura en el mundo real, preparándolos para un desempeño profesional efectivo en el siglo XXI.

---

#### Metodología de Enseñanza
La asignatura adopta un enfoque moderno de aprendizaje denominado **estrategia de aula extendida**, que combina clases tradicionales con tecnología educativa. Este modelo promueve el aprendizaje activo y el desarrollo integral de los estudiantes mediante herramientas como:
- **Plataformas virtuales:** Campus MIeL y el entorno EVEAMD.
- **Clases teórico-prácticas:** Complementadas con ejercicios en línea.
- **Actividades de aprendizaje semanales:** Como autoevaluaciones obligatorias y ejercicios prácticos no obligatorios.
- **Clases de resolución de problemas virtuales:** Grabadas para consulta posterior.
- **Tutorías personalizadas:** A través de foros en línea.

Además, se emplean metodologías activas en el aula, como el uso de aplicaciones interactivas (e.g., Socrative) y recursos digitales para fomentar el aprendizaje colaborativo.

---

#### Objetivos de Aprendizaje
El curso busca que los estudiantes:
1. **Dominen conceptos clave de la matemática discreta.**
2. Justifiquen propiedades y relaciones mediante razonamientos lógicos.
3. Clasifiquen estructuras matemáticas como grafos, árboles y lenguajes formales.
4. **Desarrollen técnicas de resolución de problemas:** Aplicando conocimientos más allá de la memorización.
5. Comprendan y transfieran conceptos a asignaturas avanzadas y al ámbito profesional.

---

#### Contenidos Principales
1. **Teoría de conjuntos y lenguajes:**
   - Operaciones entre conjuntos.
   - Producto cartesiano.
   - Lenguajes formales y sus operaciones.
2. **Relaciones y estructuras:**
   - Relaciones binarias, de equivalencia y de orden.
   - Representación gráfica y matricial.
   - Álgebra de Boole.
3. **Grafos y dígrafos:**
   - Definiciones, propiedades y aplicaciones.
   - Árboles y recorridos.
4. **Gramáticas y autómatas:**
   - Lenguajes regulares y máquinas de estado finito.
5. **Análisis combinatorio:**
   - Técnicas básicas de conteo y análisis avanzado.
6. **Técnicas de demostración:**
   - Lógica proposicional y de predicados.
   - Verificación de algoritmos.
7. **Teoría de números:**
   - Divisibilidad, MCD, MCM e inducción matemática.

---

#### Bibliografía Recomendada
- **Obligatoria:**
  - Grimaldi, R.P. _Matemática Discreta y Combinatoria_. Addison Wesley, 3ra edición, 1997.
- **Complementaria:**
  - Kolman, B., Busby, R., y Ross, S. _Estructura de Matemática Discreta para Computación_. Prentice Hall, 3ra edición, 1997.
  - Liu, C.L. _Elementos de Matemática Discreta_. McGraw-Hill, 2da edición, 1995.
  - Scheinerman, E. _Matemáticas Discretas_. Thomson Learning, 1ra edición, 2001.

---

#### Competencias a Desarrollar
- **Genéricas:**
  - Comunicación efectiva.
  - Trabajo en equipo.
  - Aprendizaje continuo.
- **Específicas:**
  - Dominio de estructuras matemáticas y algoritmos aplicables al ámbito informático.

---

Este curso no solo sienta las bases teóricas para disciplinas avanzadas, sino que también fomenta habilidades prácticas indispensables en el campo de la informática, alineándose con las demandas profesionales del siglo XXI.' WHERE id=3621;

UPDATE subjects SET description='
#### Introducción
La asignatura **Análisis Matemático I** es una piedra angular en la formación de los estudiantes de ingeniería informática, pues proporciona las herramientas y el razonamiento matemático necesario para modelar, analizar y resolver problemas en diversos contextos tecnológicos y científicos. Esta disciplina se centra en el **cálculo diferencial de una variable**, destacando su aplicación interdisciplinaria en problemas reales y su relevancia en la futura práctica profesional.

La matemática, en este caso el cálculo, no solo es una herramienta técnica, sino también un lenguaje universal de precisión y abstracción que permite optimizar diseños, predecir comportamientos y ahorrar recursos. Además, desarrolla un orden lógico y fomenta el pensamiento crítico, habilidades esenciales en la ingeniería.

---

#### Importancia y Aplicaciones
El objetivo general de esta asignatura es que los estudiantes adquieran conocimientos matemáticos propios del cálculo y desarrollen habilidades que les permitan:
- **Modelar problemas reales:** Usar funciones matemáticas (lineales, cuadráticas, exponenciales, trigonométricas, etc.) como herramientas de análisis.
- **Interpretar resultados matemáticos:** Aplicar conceptos como límites, derivadas y polinomios de Taylor para analizar comportamientos y optimizar sistemas.
- **Resolver problemas complejos:** Adaptar métodos de cálculo a diversas situaciones, replicando la dinámica interdisciplinaria de la práctica profesional.

El curso también promueve la curiosidad, la creatividad y el razonamiento independiente, elementos clave para el aprendizaje autónomo y el éxito profesional.

---

#### Objetivos de Aprendizaje
1. **Cognitivos:**
   - Comprender definiciones, propiedades y teoremas fundamentales del cálculo diferencial.
   - Desarrollar estrategias para abordar problemas matemáticos y aplicarlos a contextos prácticos.

2. **Actitudinales:**
   - Trabajar en equipo valorando las contribuciones individuales y colectivas.
   - Desarrollar autonomía y responsabilidad en el aprendizaje.

3. **Metacognitivos:**
   - Reflexionar sobre los propios procesos de aprendizaje para optimizarlos.

4. **Habilidades específicas:**
   - Manejo de herramientas informáticas relacionadas con las matemáticas, como GeoGebra.
   - Resolución de problemas aplicados con funciones matemáticas.

---

#### Contenidos Principales
1. **Funciones:**
   - Definiciones, dominio e imagen.
   - Funciones algebraicas y trascendentes, operaciones y gráficas.
   - Transformaciones de funciones (desplazamientos, reflejos, contracciones).
   - Función inversa y sus propiedades.

2. **Límite funcional:**
   - Definición y propiedades.
   - Límites finitos, infinitos y de variable infinita.
   - Continuidad y discontinuidades.

3. **Derivadas y diferenciales:**
   - Razón de cambio media e instantánea.
   - Cálculo y reglas de derivación.
   - Derivadas de funciones compuestas e implícitas.
   - Uso del diferencial como aproximación.

4. **Polinomios de Taylor y Mac Laurin:**
   - Expansión de funciones y aproximaciones mediante polinomios.

---

#### Evaluación
El proceso evaluativo combina el aprendizaje continuo con la medición de habilidades adquiridas:
1. **Primer parcial:**
   - Incluye unidades 1 y 2.
   - Trabajo individual con plazo de entrega y uso de GeoGebra.
2. **Segundo parcial:**
   - Aborda todo el contenido.
   - Prueba escrita individual.
3. **Recuperatorios:**
   - Opcional para uno de los parciales, reemplazando la nota anterior.

El curso se considera:
- **Aprobado directamente:** Si ambas notas son ≥ 7.
- **Cursado:** Si las notas están entre 4 y 6, requiriendo examen final para aprobar.
- **Reprobado:** Si alguna nota es < 4.

---

#### Bibliografía Recomendada
- **Obligatoria:**
  - Stewart, J. _Cálculo: Trascendentes Tempranas_. Thomson Learning, 4ª edición, 2004.
  - Larson, R. y Edwards, B. _Cálculo en una Variable_. McGraw Hill, 9ª edición, 2010.
- **Complementaria:**
  - Hernández, E. _Cálculo Diferencial e Integral con Aplicaciones_. Instituto Tecnológico de Costa Rica, 1ª edición, 2013.
  - Stewart, J. _Cálculo de una Variable_. Cengage Learning, 8ª edición, 2018.

---

#### Competencias a Desarrollar
1. **Genéricas:**
   - Comunicación efectiva.
   - Resolución de problemas de ingeniería.
   - Trabajo en equipo y aprendizaje continuo.
   - Ética y responsabilidad profesional.

2. **Específicas:**
   - Dominio del cálculo diferencial y su aplicación en ingeniería.
   - Capacidad para modelar y analizar fenómenos utilizando funciones matemáticas.

---

La asignatura **Análisis Matemático I** no solo busca desarrollar competencias matemáticas, sino también habilidades prácticas y reflexivas que son esenciales en el ámbito profesional de la ingeniería.' WHERE id=3622;
UPDATE subjects SET description='
#### Introducción
La asignatura **Programación Inicial** es un componente fundamental del trayecto de formación en ingeniería en informática, diseñada para introducir al estudiante en el **arte de la programación**. Su objetivo principal es proporcionar las bases para la resolución de problemas mediante el diseño, análisis y codificación de algoritmos. Utilizando herramientas como diagramas de flujo y el lenguaje de programación C, el curso combina teoría y práctica para desarrollar habilidades técnicas y lógicas esenciales en la informática.

---

#### Importancia y Aplicaciones
El enfoque de **Programación Inicial** busca:
1. Enseñar los principios básicos de resolución de problemas computacionales.
2. Desarrollar habilidades para diseñar algoritmos y representarlos de manera gráfica.
3. Familiarizar al estudiante con la codificación en lenguaje C, uno de los lenguajes más versátiles y ampliamente utilizados en ingeniería y desarrollo de software.

El conocimiento adquirido permite a los estudiantes realizar aplicaciones funcionales, como sistemas básicos para la gestión administrativa (e.g., inventarios o videoclubes). Si bien las soluciones iniciales no son óptimas, forman la base para comprender tecnologías más avanzadas.

---

#### Metodología de Enseñanza
La metodología combina:
1. **Clases teórico-prácticas:** Se presentan conceptos teóricos fundamentales seguidos de ejercicios prácticos que consolidan el aprendizaje.
2. **Uso de herramientas digitales:** Software como Code::Blocks y diagramas de flujo para la representación visual de algoritmos.
3. **Plataforma virtual MIeL:** Proporciona materiales como guías teóricas, prácticas, videos y foros interactivos, fomentando el aprendizaje autónomo y colaborativo.
4. **Clases de consulta:** Disponibles tanto en formato presencial como virtual mediante plataformas como Teams.

El enfoque promueve habilidades como el trabajo en equipo, la búsqueda bibliográfica, la documentación de soluciones y la evaluación de distintas alternativas algorítmicas.

---

#### Objetivos de Aprendizaje
El curso persigue los siguientes objetivos:
1. **Reconocimiento de herramientas computacionales actuales:** Capacitar al estudiante en el uso de tecnologías y lenguajes relevantes.
2. **Desarrollo del razonamiento lógico:** Resolver problemas a través de procesos estructurados y bien definidos.
3. **Comprensión de estructuras básicas:** Introducir conceptos como procesos secuenciales, decisiones simples y múltiples, ciclos y estructuras modulares.
4. **Dominio del lenguaje C:** Aprender a codificar y depurar programas básicos.

---

#### Contenidos Principales
1. **Introducción a la computación:**
   - Funcionamiento de un computador digital, hardware y software.
   - Ejemplos de aplicaciones básicas y su ejecución.

2. **Resolución de problemas:**
   - Enunciados, análisis y estrategias.
   - Diseño descendente (top-down).
   - Algoritmos y diagramas de lógica.

3. **Introducción al lenguaje C:**
   - Historia y fundamentos del lenguaje.
   - Estructura general de un programa, variables y operadores.
   - Funciones básicas de entrada/salida (e.g., `printf` y `scanf`).

4. **Estructuras de control:**
   - Selección simple y múltiple (`if`, `switch`).
   - Operadores lógicos y su combinación.
   - Estructuras de repetición (`for`, `while`, `do-while`).

5. **Programación modular y funciones:**
   - Declaración y definición de funciones.
   - Concepto de parámetros y paso por valor.
   - Uso de funciones estándar y recursividad.

6. **Estructuras de datos básicas:**
   - Arrays unidimensionales y multidimensionales.
   - Operaciones básicas como búsqueda y ordenamiento.

---

#### Competencias a Desarrollar
1. **Genéricas:**
   - Identificación y resolución de problemas de ingeniería en sistemas informáticos.
   - Aprendizaje continuo y trabajo en equipo.

2. **Específicas:**
   - Diseño y desarrollo de sistemas de información y software.
   - Programación estructurada y modular.

---

#### Bibliografía Recomendada
- **Obligatoria:**
  - Freijedo, Cortagerena. _Tecnologías de la Información y las Comunicaciones_. Ediciones Macchi, 1ª Ed., 2000.
  - Byron, S. Gottfried. _Programación en C_. McGraw-Hill, 2ª Ed., 2015.

- **Complementaria:**
  - Kernighan, B., Ritchie, D. _El Lenguaje de Programación C_. Prentice Hall, 2ª Ed., 2010.

---

La asignatura **Programación Inicial** establece una sólida base para los estudiantes en su camino hacia la especialización en programación estructurada y desarrollo de software, promoviendo un aprendizaje técnico y práctico que será esencial para su futuro profesional.' WHERE id=3623;
UPDATE subjects SET description='
#### Introducción
La asignatura **Introducción a los Sistemas de Información** constituye el punto de partida en el trayecto de Desarrollo de Software dentro de la carrera de Ingeniería en Informática. Se centra en el estudio y análisis de los sistemas de información, con especial énfasis en los sistemas informatizados. A través de actividades teóricas y prácticas, los estudiantes desarrollan habilidades para aplicar el enfoque sistémico, identificar ciclos de vida del desarrollo de software y trabajar en equipo, adquiriendo competencias clave para su futuro profesional.

---

#### Importancia y Aplicaciones
Los **sistemas de información** son esenciales para gestionar, procesar y analizar datos en diversas áreas. Esta asignatura tiene como objetivo proporcionar:
1. **Fundamentos teóricos y prácticos** para comprender y clasificar sistemas.
2. **Habilidades de análisis y resolución de problemas**, útiles para abordar proyectos de software en contextos reales.
3. **Competencias en trabajo colaborativo y roles en proyectos de software**, preparando a los estudiantes para entornos laborales interdisciplinarios.

El conocimiento adquirido facilita la transición a materias avanzadas como Introducción a la Gestión de Requisitos y el Taller de Integración.

---

#### Metodología de Enseñanza
La enseñanza combina:
1. **Clases teóricas:** Presentación de conceptos fundamentales sobre sistemas y metodologías.
2. **Actividades prácticas:** Resolución de casos, simulaciones de roles y elaboración de proyectos.
3. **Enfoque interactivo:** A través de juegos de roles y discusiones grupales.
4. **Evaluación continua:** Los estudiantes reciben retroalimentación constante para mejorar sus competencias.

Este enfoque fomenta la reflexión individual y grupal, así como el aprendizaje basado en experiencias prácticas.

---

#### Objetivos de Aprendizaje
El curso persigue los siguientes objetivos:
1. **Cognitivos:**
   - Comprender la teoría general de sistemas y su clasificación.
   - Aplicar el enfoque sistémico en el análisis de sistemas informatizados.
2. **Procedimentales:**
   - Utilizar herramientas y métodos formales para analizar sistemas y resolver problemas.
   - Implementar ciclos de vida adecuados para distintos tipos de software.
3. **Actitudinales:**
   - Trabajar en equipo, identificando roles y desarrollando habilidades de escucha activa.
   - Desarrollar una actitud profesional ética y responsable.

---

#### Contenidos Principales
1. **Fundamentos de sistemas:**
   - Definición de sistemas, subsistemas y módulos.
   - Seguridad de los sistemas de información (confidencialidad, integridad y disponibilidad).

2. **Teoría General de Sistemas (TGS):**
   - Conceptos de objetivo, límite y alcance.
   - Clasificación de sistemas por tipo y naturaleza.

3. **Tipos de sistemas:**
   - Clasificación dentro de las organizaciones: transaccionales, gerenciales, expertos, inteligencia artificial, entre otros.

4. **Resolución de problemas y desarrollo de software:**
   - Métodos de resolución de problemas.
   - Proceso de construcción de software (las 4 P: Personal, Producto, Proceso, Proyecto).

5. **Ciclos de vida del desarrollo de software:**
   - Modelos en cascada, espiral, prototipos y desarrollo basado en componentes.

6. **Trabajo en equipo y roles:**
   - Juego de roles para representar las dinámicas cliente-desarrollador.
   - Técnicas de entrevista y escucha activa.

---

#### Competencias a Desarrollar
1. **Genéricas:**
   - Comunicación efectiva.
   - Identificación y resolución de problemas de sistemas de información.
   - Desempeño en equipos y aprendizaje continuo.
   - Gestión de proyectos y evaluación de su impacto social.

2. **Específicas:**
   - Especificación y desarrollo de sistemas de información.
   - Gestión de seguridad informática y calidad de software.
   - Diseño y dirección de proyectos relacionados con sistemas de comunicación de datos.

---

#### Bibliografía Recomendada
- **Obligatoria:**
  - Von Bertalanffy, L. _Teoría General de Sistemas_. Fondo de Cultura Económica, 1ª Ed., 1976.
  - Pressman, R. _Ingeniería del Software: Un Enfoque Práctico_. McGraw-Hill, 7ª Ed., 2010.
  - Sommerville, I. _Ingeniería de Software_. Addison Wesley, 7ª Ed., 2005.
- **Complementaria:**
  - Kendall & Kendall. _Análisis y Diseño de Sistemas_. Pearson Prentice Hall, 8ª Ed., 2011.
  - Ortiz Crespo, R. _Aprender a Escuchar_. Lulu, 1ª Ed., 2007.

---

La asignatura **Introducción a los Sistemas de Información** prepara a los estudiantes para el diseño, análisis y gestión de proyectos de software, formando una base sólida para su desarrollo profesional en ingeniería informática.' WHERE id=3624;
UPDATE subjects SET description='
#### Introducción
La asignatura **Sistemas de Numeración** es una materia básica dentro del trayecto de Infraestructura de la carrera de Ingeniería en Informática. Proporciona conocimientos fundamentales sobre los sistemas de numeración, sus aplicaciones y la codificación de datos en Tecnologías de la Información y la Comunicación (TIC). Este aprendizaje es esencial para entender la digitalización en la **Industria 4.0**, así como para el desarrollo y la implementación de tecnologías futuras en el ámbito ingenieril.

---

#### Importancia y Aplicaciones
El curso aborda conceptos clave como:
1. **Sistemas de numeración y su representación** en formatos digitales.
2. **Codificación de datos numéricos y alfanuméricos**, incluyendo códigos detectores y correctores de errores.
3. **Digitalización de información** y sus aplicaciones en redes, teleinformática y sistemas operativos.

Estos conocimientos son fundamentales para el diseño, análisis y desarrollo de sistemas informáticos avanzados, así como para aplicaciones en redes, hardware y software.

---

#### Metodología de Enseñanza
La metodología combina teoría y práctica mediante:
1. **Clases teórico-prácticas:** Uso de ejemplos, material multimedia y ejercicios interactivos.
2. **Trabajos prácticos grupales (TPG):** Resolución de problemas aplicados en equipos, promoviendo el aprendizaje colaborativo.
3. **Recursos virtuales (MIeL):** Disponibles para material teórico, guías prácticas y retroalimentación continua.
4. **Charlas técnicas:** Realizadas por empresas del Polo Tecnológico UNLaM, abordando temas como impresión 3D y realidad virtual.

El enfoque fomenta el desarrollo del "saber ser" mediante el "saber hacer", integrando teoría y práctica.

---

#### Objetivos de Aprendizaje
El curso busca que los estudiantes:
1. **Comprendan y apliquen sistemas de numeración:** Incluyendo conversiones, operaciones y representaciones numéricas.
2. **Reconozcan la importancia de la digitalización:** En el marco de la Revolución Industrial 4.0 y sus pilares tecnológicos (e.g., IoT, Big Data, Cloud Computing).
3. **Dominen técnicas de codificación:** Para garantizar la integridad y corrección de datos.
4. **Comprendan conceptos básicos de teleinformática y redes:** Incluyendo direccionamiento IPv4/IPv6 y protocolos de comunicación.
5. **Reconozcan el papel del software:** Clasificación, funciones del sistema operativo y aplicaciones basadas en la nube.

---

#### Contenidos Principales
1. **Introducción a la Digitalización y Tecnologías Digitales:**
   - Magnitudes analógicas y digitales.
   - Impacto de la digitalización en la sociedad.
   - Industria 4.0 y sus pilares tecnológicos: IoT, ciberseguridad, simulaciones, entre otros.

2. **Sistemas de Numeración:**
   - Sistemas posicionales y conversiones entre bases.
   - Representación y aritmética de números enteros y reales.
   - Norma IEEE-754 para punto flotante.

3. **Códigos:**
   - Códigos numéricos y alfanuméricos (ASCII, UNICODE).
   - Códigos detectores y correctores de errores (Hamming).
   - Aplicaciones industriales de códigos, como QR y realidad aumentada.

4. **Aplicaciones de Sistemas de Numeración en Redes:**
   - Conceptos de teleinformática y protocolos.
   - Redes LAN y WAN, hardware de red (switches y routers).
   - Introducción a Internet y redes móviles.

5. **Introducción al Software:**
   - Clasificación del software (de sistema, de aplicación, en la nube).
   - Funciones y características del sistema operativo.

---

#### Competencias a Desarrollar
1. **Genéricas:**
   - Identificación y resolución de problemas en sistemas informáticos.
   - Diseño y desarrollo de proyectos de ingeniería en sistemas de información.
   - Trabajo en equipo, comunicación efectiva y aprendizaje continuo.

2. **Específicas:**
   - Desarrollo de sistemas de comunicación de datos.
   - Implementación de sistemas de codificación y digitalización.
   - Comprensión de protocolos y arquitecturas de redes.

---

#### Bibliografía Recomendada
- **Obligatoria:**
  - Material elaborado por los docentes de la cátedra (UNLaM, 2023).
- **Complementaria:**
  - Szklanny, F. _Introducción a los Sistemas Digitales_. Tercer Milenio, 2ª Ed., 2002.
  - Floyd, T. _Fundamentos de Sistemas Digitales_. Prentice Hall, 9ª Ed., 2006.
  - Tanenbaum, A. S. _Redes de Computadoras_. Prentice Hall, 4ª Ed., 2003.

---

La asignatura **Sistemas de Numeración** forma una base sólida para el análisis y diseño de sistemas digitales, integrando conocimientos técnicos y habilidades prácticas que son esenciales en la Ingeniería en Informática.' WHERE id=3625;
UPDATE subjects SET description='
#### Introducción
La asignatura **Principios de Calidad de Software** es parte del trayecto de Calidad y Seguridad de la Información dentro de la carrera de Ingeniería en Informática. Su objetivo principal es formar a los estudiantes en la gestión de calidad aplicada al desarrollo de software, proporcionando herramientas y conceptos clave que permitan prevenir problemas, optimizar procesos y garantizar productos de alta calidad.

El curso destaca la importancia de los estándares internacionales de calidad y el impacto positivo de una gestión de calidad eficiente en las organizaciones.

---

#### Importancia y Aplicaciones
La calidad del software es un componente esencial en la ingeniería de sistemas de información, ya que:
1. **Optimiza los procesos de desarrollo y mantenimiento del software.**
2. **Mejora la satisfacción del cliente**, asegurando que el producto cumpla con las expectativas.
3. **Reduce costos asociados a errores o problemas**, mediante la prevención y el control de calidad.

Los conocimientos adquiridos son aplicables en todas las etapas del ciclo de vida del desarrollo de software, desde el diseño hasta la implementación y el mantenimiento.

---

#### Metodología de Enseñanza
El curso combina teoría y práctica a través de:
1. **Clases presenciales:** Exposición teórica y resolución de casos empresariales reales.
2. **Trabajos prácticos grupales:** Enfoque colaborativo para simular escenarios de gestión de calidad.
3. **Uso de la plataforma MIeL:** Gestión de tareas, consultas y retroalimentación.
4. **Enfoque aplicado:** Generación de una actitud proactiva y de toma de decisiones en los estudiantes.

---

#### Objetivos de Aprendizaje
El curso busca que los estudiantes:
1. **Comprendan el concepto de calidad:** Desde su importancia estratégica hasta su aplicación práctica en proyectos de software.
2. **Conozcan modelos y normas internacionales:** Como soporte para la gestión de calidad.
3. **Adquieran habilidades en testing:** Aplicando técnicas de verificación y validación.
4. **Desarrollen competencias analíticas:** Para evaluar métricas, costos y estrategias en calidad de software.

---

#### Contenidos Principales
1. **El concepto de calidad:**
   - Modelos de calidad de software.
   - Estrategias y tácticas en la calidad.
   - Métricas del software.

2. **Gestión de procesos:**
   - Mapa de procesos y diagramas de flujo.
   - Diferencias entre proceso, procedimiento y sistema.
   - Proceso software y gestión de proyectos.

3. **Clientes y calidad:**
   - Relación entre cliente y ciclo de vida del software.
   - Visión de calidad para cliente y productor.

4. **Testing de software:**
   - Tipos de pruebas: estáticas, dinámicas, caja negra y caja blanca.
   - Generación de casos de prueba.
   - Verificación, validación y depuración de errores.

5. **Normas internacionales:**
   - Origen y organismos de normalización (ISO, entre otros).
   - Principales estándares de calidad en software.

6. **Gestión de calidad total:**
   - Principios de calidad y mejora continua.
   - Costos de la no calidad.
   - Ciclo de Deming y sistemas de gestión de calidad.

---

#### Competencias a Desarrollar
1. **Genéricas:**
   - Trabajo en equipo y comunicación efectiva.
   - Actuación ética y responsable en el ámbito profesional.
   - Aprendizaje continuo y actitud emprendedora.

2. **Específicas:**
   - Definición y aplicación de métricas en calidad de software.
   - Dirección y control de sistemas de información, seguridad y calidad de software.
   - Implementación de estándares y procedimientos de certificación.

---

#### Bibliografía Recomendada
- **Obligatoria:**
  - Pressman, R. S. _Ingeniería de Software_. McGraw Hill, 7ª Ed., 2010.
  - Miraval, F. E. _Gestión de la Calidad según Norma ISO 9001:2015_. Dunken, 2ª Ed., 2017.
  - Evans, J. R., Lindsay, W. M. _Administración y Control de la Calidad_. Cengage, 4ª Ed., 1995.

- **Complementaria:**
  - Sommerville, I. _Ingeniería de Software_. Pearson, 7ª Ed., 2005.
  - Volpentesta, J. R. _Organizaciones, Procedimientos y Estructuras_. Buyatti, 3ª Ed., 2015.

---

La asignatura **Principios de Calidad de Software** proporciona a los estudiantes una visión integral de los estándares, procesos y herramientas de calidad, sentando las bases para la implementación de sistemas informáticos robustos y confiables.' WHERE id=3626;
UPDATE subjects SET description='
#### Introducción
La asignatura **Álgebra y Geometría Analítica I** constituye la base para el estudio del Álgebra Lineal, esencial en la resolución de problemas en diversos campos de la ingeniería y la informática. Este curso combina teoría y aplicaciones prácticas para desarrollar habilidades de abstracción, modelado matemático y razonamiento lógico. Se enfoca en el estudio de vectores, matrices, sistemas de ecuaciones lineales y geometría en el plano y el espacio tridimensional.

Estas herramientas son fundamentales para disciplinas como física, análisis matemático, informática gráfica, procesamiento de datos y criptografía, entre otras.

---

#### Importancia y Aplicaciones
1. **Resolución de problemas matemáticos:** Aplicaciones en sistemas de ecuaciones lineales y geometría analítica.
2. **Bases para asignaturas avanzadas:** La comprensión del álgebra y la geometría es esencial para materias como Álgebra y Geometría Analítica II, Matemática Discreta y Física I.
3. **Aplicaciones prácticas:** Uso en áreas como diseño gráfico, simulaciones, inteligencia artificial, y análisis estructural.

Los conceptos desarrollados permiten a los estudiantes abordar problemas desde diferentes perspectivas, integrando métodos algebraicos y gráficos.

---

#### Metodología de Enseñanza
El curso combina:
1. **Clases teórico-prácticas:** Exposición de conceptos y resolución de ejercicios.
2. **Trabajo grupal:** Fomentado a través de talleres interactivos.
3. **Uso de herramientas tecnológicas:** Integración de software como GeoGebra para modelado y visualización.
4. **Plataforma MIeL:** Disponibilidad de recursos complementarios, participación en foros y comunicación con docentes.

Este enfoque busca un aprendizaje activo, combinando ejercicios guiados y autonomía en el desarrollo de problemas.

---

#### Objetivos de Aprendizaje
**Generales:**
1. Comprender los conceptos fundamentales del álgebra lineal y la geometría analítica.
2. Desarrollar estrategias para plantear y resolver problemas.
3. Fomentar el aprendizaje autónomo a través de la reflexión y el razonamiento.

**Específicos:**
1. Conocer y aplicar operaciones con vectores y matrices.
2. Resolver problemas mediante sistemas de ecuaciones lineales y sus propiedades.
3. Comprender las relaciones entre rectas y planos en el espacio tridimensional.
4. Aplicar conceptos algebraicos y geométricos a problemas prácticos y reales.

---

#### Contenidos Principales
1. **Vectores:**
   - Magnitudes escalares y vectoriales.
   - Operaciones en R2 y R3: suma, producto escalar, producto vectorial y mixto.
   - Aplicaciones geométricas: ángulo entre vectores, proyecciones y coplanaridad.

2. **Matrices y sistemas de ecuaciones lineales:**
   - Clasificación y operaciones con matrices.
   - Resolución de sistemas lineales mediante métodos de Gauss y Gauss-Jordan.
   - Propiedades y cálculo de determinantes.
   - Uso de la matriz inversa en la solución de sistemas lineales.

3. **Geometría analítica:**
   - Ecuaciones de rectas y planos en diferentes representaciones.
   - Posiciones relativas entre rectas, planos y puntos en el espacio.
   - Cálculo de distancias entre elementos geométricos.

4. **Aplicaciones de álgebra y geometría:**
   - Resolución de problemas en criptografía, dinámica de tráfico, transmisión de calor y procesos de Markov.
   - Interpolación polinómica y sistemas sociológicos modelados algebraicamente.

---

#### Competencias a Desarrollar
1. **Genéricas:**
   - Comunicación efectiva y trabajo en equipo.
   - Desarrollo de una actitud profesional ética y responsable.
   - Capacidad para el aprendizaje continuo y la resolución de problemas complejos.

2. **Específicas:**
   - Aplicación de conceptos algebraicos y geométricos en la ingeniería informática.
   - Uso de herramientas tecnológicas para modelado y simulación.

---

#### Bibliografía Recomendada
- **Obligatoria:**
  - Kozak, A. M., Pastorelli, S., Vardanega, P. _Nociones de Geometría Analítica y Álgebra Lineal_. McGraw Hill, 1ª Ed., 2007.
  - Grossman, S., Flores Godoy, J. _Álgebra Lineal_. McGraw Hill, 7ª Ed., 2012.
  - Lay, D. _Álgebra Lineal y sus Aplicaciones_. Pearson, 5ª Ed., 2016.

- **Complementaria:**
  - Strang, G. _Álgebra Lineal y sus Aplicaciones_. International Thomson, 4ª Ed., 2007.
  - Kolman, B., Hill, D. _Álgebra Lineal_. Pearson, 8ª Ed., 2006.

---

La asignatura **Álgebra y Geometría Analítica I** establece los cimientos para desarrollar habilidades matemáticas avanzadas, indispensables para el análisis y diseño en el ámbito de la ingeniería informática.' WHERE id=3627;
UPDATE subjects SET description='
#### Introducción
La asignatura **Física I** pertenece al trayecto de Ciencias Básicas en la carrera de Ingeniería en Informática. Está diseñada para proporcionar una base sólida en los conceptos fundamentales de la física clásica, enfatizando su aplicación en situaciones prácticas y profesionales. Este curso busca desarrollar habilidades en modelado físico-matemático, análisis experimental y resolución de problemas, estableciendo una conexión directa con las matemáticas y otras ciencias básicas.

---

#### Importancia y Aplicaciones
La física tiene un papel central en la ingeniería, ya que:
1. **Proporciona herramientas analíticas y experimentales** para comprender y modelar fenómenos físicos.
2. **Sirve como base para asignaturas avanzadas**, como Física II y disciplinas de especialización en ingeniería.
3. **Facilita el desarrollo tecnológico**, permitiendo interpretar fenómenos y diseñar soluciones innovadoras.

Los conocimientos adquiridos son esenciales para resolver problemas en sistemas dinámicos, diseño de máquinas, sistemas electrónicos, entre otros.

---

#### Metodología de Enseñanza
El curso combina:
1. **Clases teórico-prácticas:** Alternando teoría y resolución de problemas para consolidar conceptos.
2. **Laboratorios:** Experimentos para validar teorías y desarrollar habilidades de análisis experimental.
3. **Uso de recursos digitales:** Simulaciones, videos y materiales complementarios disponibles en plataformas digitales como MIeL.
4. **Trabajo grupal:** Fomento de habilidades de colaboración y resolución colectiva de problemas.

Este enfoque permite integrar la teoría y la práctica, fomentando un aprendizaje activo y aplicado.

---

#### Objetivos de Aprendizaje
**Generales:**
1. Comprender los principios fundamentales de la física clásica.
2. Desarrollar habilidades para modelar situaciones físicas mediante ecuaciones matemáticas.
3. Fomentar la capacidad de resolver problemas aplicando leyes físicas.

**Específicos:**
1. Aplicar conceptos de cinemática, dinámica y conservación de energía a problemas físicos.
2. Realizar mediciones, interpretar resultados y elaborar informes técnicos.
3. Comprender y aplicar los principios de la dinámica de partículas y cuerpos rígidos.

---

#### Contenidos Principales
1. **Mediciones físicas:**
   - Magnitudes fundamentales, unidades del Sistema Internacional (SI) y propagación de incertidumbres.
   - Análisis de errores sistemáticos y fortuitos.

2. **Cinemática:**
   - Movimiento en una, dos y tres dimensiones.
   - Desplazamiento, velocidad y aceleración en sistemas rectilíneos y curvilíneos.
   - Movimiento relativo, parabólico y circular.

3. **Dinámica de partículas:**
   - Leyes de Newton y Ley de Gravitación Universal.
   - Impulso, cantidad de movimiento y conservación del momento lineal.
   - Trabajo, energía cinética y potencial, fuerzas conservativas y no conservativas.

4. **Dinámica del sistema de partículas:**
   - Centro de masa, momento angular y conservación del momento angular.
   - Energía cinética y conservación de la energía.

5. **Cinemática y dinámica del cuerpo rígido:**
   - Movimiento de rotación y traslación.
   - Momento de inercia, teorema de Steiner y energía cinética rotacional.
   - Giroscopios, precesión y conservación de la energía mecánica.

---

#### Competencias a Desarrollar
1. **Genéricas:**
   - Trabajo en equipo y comunicación efectiva.
   - Actitud ética y profesional.
   - Aprendizaje continuo y generación de soluciones tecnológicas.

2. **Específicas:**
   - Análisis y modelado físico-matemático.
   - Diseño y ejecución de experimentos físicos.
   - Resolución de problemas complejos en ingeniería aplicada.

---

#### Bibliografía Recomendada
- **Obligatoria:**
  - Serway, R., Jewett, J. _Física para Ciencias e Ingeniería_. Cengage, 7ª Ed., 2008.
  - Tipler, P., Mosca, G. _Física para la Ciencia y la Tecnología_. Reverte, 6ª Ed., 2010.

- **Complementaria:**
  - Alonso, M., Finn, E. _Física_. Pearson, 10ª Ed., 1999.
  - Resnick, R., Holliday, D., Krane, K. _Física 1_. Continental, 4ª Ed., 2001.

---

La asignatura **Física I** proporciona un marco conceptual sólido y habilidades prácticas esenciales para los desafíos técnicos y científicos en la carrera de Ingeniería en Informática.' WHERE id=3628;
UPDATE subjects SET description='
La materia **Programación Estructurada Básica** forma parte del primer nivel del trayecto de programación en la carrera de Ingeniería en Informática. Se considera una asignatura esencial dentro del plan de estudios, diseñada para brindar a los estudiantes las bases necesarias en la resolución de problemas mediante el desarrollo de algoritmos y la implementación en lenguaje de programación C.

### Objetivo general

El propósito central de esta materia es entrenar a los estudiantes en el desarrollo lógico y estructurado de soluciones computacionales, utilizando el lenguaje de programación C. Esto incluye aprender a diseñar, codificar, probar y ejecutar programas que resuelvan problemas específicos. La asignatura busca no solo impartir conocimientos técnicos, sino también fomentar habilidades como el análisis crítico, la creatividad en la resolución de problemas y el trabajo en equipo.

### Subobjetivos principales

1. **Diseño lógico y resolución de problemas:** Los estudiantes aprenderán a interpretar y desarrollar algoritmos efectivos para resolver diversas problemáticas, con énfasis en la correcta estructuración y codificación de los mismos.

2. **Codificación en lenguaje C:** Se hace énfasis en la implementación de algoritmos utilizando este lenguaje, que es ampliamente aplicable en múltiples áreas de la ingeniería.

3. **Preparación para futuros desarrollos:** Se busca que los alumnos adquieran una base sólida para avanzar hacia asignaturas más complejas como Programación Orientada a Objetos y Programación Visual.

### Contenidos teóricos y prácticos

1. **Fundamentos de programación:**
   - Declaración de variables y tipos de datos.
   - Conceptos básicos sobre algoritmos y estructuras de control.
   - Representación lógica de procesos mediante diagramas y pseudocódigo.

2. **Manejo de estructuras de datos:**
   - Definición y uso de arrays.
   - Operaciones de ordenamiento y búsqueda.
   - Uso de estructuras personalizadas (structs) y estructuras anidadas.

3. **Manipulación de cadenas de caracteres (strings):**
   - Funciones estándar como `strlen`, `strcpy`, `strcat` y `strcmp`.
   - Representación y procesamiento de cadenas.

4. **Archivos:**
   - Conceptos básicos sobre archivos binarios y de texto.
   - Creación, lectura y escritura de archivos.
   - Métodos para buscar información en archivos binarios.
   - Implementación de programas que manejan datos mediante altas, bajas, consultas y modificaciones.

5. **Programación aplicada:**
   - Desarrollo de programas integrados que combinen todas las técnicas aprendidas, incluyendo sistemas simples como gestión de inventarios.

### Metodología de enseñanza

La metodología de esta asignatura combina clases teóricas con actividades prácticas, donde los estudiantes son guiados en la resolución de problemas reales. Durante las clases prácticas, se utilizan herramientas como el editor de código **CodeBlocks**, y se accede a recursos digitales en la plataforma **Miel**, que incluye contenidos teóricos, ejercicios, foros, y material audiovisual.

Además, se fomentan actividades de consulta y aprendizaje colaborativo, tanto en modalidad presencial como virtual, a través de Microsoft Teams. Estas herramientas permiten una interacción continua entre docentes y estudiantes, favoreciendo el aprendizaje activo y autónomo.

### Competencias a desarrollar

1. **Genéricas:**
   - Identificación y resolución de problemas de ingeniería en sistemas de información.
   - Adquisición de habilidades para el aprendizaje continuo.

2. **Específicas:**
   - Especificación, diseño y desarrollo de software.
   - Análisis y desarrollo de sistemas de información.

### Importancia en el plan de estudios

La asignatura establece las bases para un entendimiento profundo de las tecnologías de programación y prepara al estudiante para enfrentarse a retos más complejos en cursos posteriores. Los conocimientos adquiridos en esta materia tienen aplicaciones prácticas inmediatas y constituyen un pilar fundamental para avanzar hacia enfoques modernos como la Programación Orientada a Objetos y el desarrollo de interfaces gráficas.

### Bibliografía recomendada

- **Bibliografía obligatoria:**
  - Byron S. Gottfried, *Programación en C*, McGraw-Hill, 2015.
  - Freijedo/Cortagerena, *Tecnologías de la Información y las Comunicaciones*, Ediciones Macchi, 2000.

- **Bibliografía complementaria:**
  - B. Kernighan, D. Ritchie, *El Lenguaje de Programación C*, Prentice Hall, 2010.

Esta materia proporciona una sólida base en programación estructurada, indispensable para cualquier profesional de la informática y la ingeniería.' WHERE id=3629;
UPDATE subjects SET description='
La asignatura **Introducción a la Gestión de Requisitos** pertenece al trayecto de Desarrollo de Software dentro de la carrera de Ingeniería en Informática. Está orientada a proporcionar a los estudiantes un marco de referencia teórico y práctico para el desarrollo de software, introduciéndolos a los principios de la Ingeniería de Software y específicamente a la Ingeniería de Requisitos. La materia busca desarrollar competencias esenciales en la identificación, análisis, especificación y gestión de requisitos en sistemas de información.

### Objetivo general

El objetivo principal de esta asignatura es capacitar a los estudiantes para comprender y aplicar los fundamentos de la Ingeniería de Requisitos, integrándolos dentro del ciclo de vida del desarrollo de software. Se hace énfasis en la sistematización de procesos, el uso de estándares internacionales, y el desarrollo de habilidades críticas para interactuar con distintos actores involucrados en proyectos de software.

### Metodología de enseñanza

La metodología adoptada busca contextualizar constantemente los contenidos impartidos, vinculándolos con conocimientos previos y los requerimientos futuros de la disciplina. Esto se logra mediante actividades como:
- Análisis de casos de estudio.
- Ejercicios prácticos.
- Producción de resúmenes y presentaciones.
- Exposición oral y debates grupales.
- Elaboración de artefactos como diagramas, prototipos y especificaciones formales.

La enseñanza fomenta un enfoque aplicado, crítico y colaborativo, que permita a los estudiantes desarrollar un conocimiento significativo y habilidades prácticas. Además, se propicia un proceso de evaluación continuo e integrado con las actividades diarias.

### Contenidos principales

1. **Introducción a la Ingeniería de Requisitos:**
   - Necesidad de la sistematización en el desarrollo de software.
   - Los actores en el proceso de desarrollo: cliente, usuario, sponsor, analistas, diseñadores y gestores.
   - Tipos de requisitos: funcionales, no funcionales y reglas de negocio.
   - Modelos de procesos de Ingeniería de Requisitos (Sommerville, Wiegers).

2. **Proceso de adquisición del conocimiento:**
   - Métodos de relevamiento y elicitación.
   - Fuentes de información: documentos, entrevistas, observación directa, registros multimedia.
   - Técnicas de extracción y educción del conocimiento, como entrevistas, encuestas y lluvia de ideas.

3. **Modelado y representación de requisitos:**
   - Uso de lenguajes naturales, plantillas y diagramas.
   - Casos de uso (Ivar Jacobson) y diagramas de casos de uso (UML).
   - Técnicas de validación y verificación de requisitos.

4. **Especificación y gestión de requisitos:**
   - Documentación bajo el estándar IEEE 830.
   - Historias de usuario en el contexto de metodologías ágiles (Scrum).
   - Gestión de cambios en requisitos y versionado.

### Competencias a desarrollar

1. **Genéricas:**
   - Trabajo en equipo y comunicación efectiva.
   - Gestión y planificación de proyectos de software.
   - Evaluación del impacto social y profesional de las soluciones desarrolladas.

2. **Específicas:**
   - Identificación y resolución de problemas en sistemas de información.
   - Especificación, diseño y desarrollo de software.
   - Aplicación de estándares internacionales en la documentación y modelado de requisitos.

### Importancia en el plan de estudios

Esta asignatura sienta las bases para asignaturas más avanzadas en desarrollo de software, como Análisis de Sistemas y Taller de Integración. Los conocimientos adquiridos son esenciales para una correcta gestión de los requisitos, un factor crítico en el éxito de proyectos informáticos. Además, los estudiantes aprenden a interactuar con distintos roles dentro de un proyecto, un aspecto clave para su desempeño profesional.

### Bibliografía recomendada

1. **Bibliografía obligatoria:**
   - Ian Sommerville, *Ingeniería de Software*, Pearson Educación, 9° Edición (2011).
   - Roger S. Pressman, *Ingeniería de Software: Un Enfoque Práctico*, McGraw Hill, 7° Edición (2010).

2. **Bibliografía complementaria:**
   - Craig Larman, *UML y Patrones*, Pearson, 2° Edición (2003).
   - Edward Yourdon, *Análisis Estructurado Moderno*, Prentice Hall, 1° Edición (1993).

Esta materia prepara a los estudiantes para enfrentar desafíos reales en la especificación y gestión de requisitos, fundamentales para el éxito en el desarrollo de sistemas y software.' WHERE id=3630;

-- UPDATE subjects SET description='' WHERE id=3631;
-- UPDATE subjects SET description='' WHERE id=3632;
-- UPDATE subjects SET description='' WHERE id=3633;
-- UPDATE subjects SET description='' WHERE id=3634;
-- UPDATE subjects SET description='' WHERE id=3635;
-- UPDATE subjects SET description='' WHERE id=3636;
-- UPDATE subjects SET description='' WHERE id=3637;
-- UPDATE subjects SET description='' WHERE id=3638;
-- UPDATE subjects SET description='' WHERE id=3639;
-- UPDATE subjects SET description='' WHERE id=3640;
-- UPDATE subjects SET description='' WHERE id=3641;
-- UPDATE subjects SET description='' WHERE id=3642;
-- UPDATE subjects SET description='' WHERE id=3643;
-- UPDATE subjects SET description='' WHERE id=3644;
-- UPDATE subjects SET description='' WHERE id=3645;
-- UPDATE subjects SET description='' WHERE id=3646;
-- UPDATE subjects SET description='' WHERE id=3647;
-- UPDATE subjects SET description='' WHERE id=3648;
-- UPDATE subjects SET description='' WHERE id=3649;
-- UPDATE subjects SET description='' WHERE id=3650;
-- UPDATE subjects SET description='' WHERE id=3651;
-- UPDATE subjects SET description='' WHERE id=3652;
-- UPDATE subjects SET description='' WHERE id=3653;
-- UPDATE subjects SET description='' WHERE id=3654;
-- UPDATE subjects SET description='' WHERE id=3655;
-- UPDATE subjects SET description='' WHERE id=3656;
-- UPDATE subjects SET description='' WHERE id=3657;
-- UPDATE subjects SET description='' WHERE id=3658;
-- UPDATE subjects SET description='' WHERE id=3659;
-- UPDATE subjects SET description='' WHERE id=3660;
-- UPDATE subjects SET description='' WHERE id=3661;
-- UPDATE subjects SET description='' WHERE id=3662;
-- UPDATE subjects SET description='' WHERE id=3663;
-- UPDATE subjects SET description='' WHERE id=3664;
-- UPDATE subjects SET description='' WHERE id=3665;
-- UPDATE subjects SET description='' WHERE id=3666;
-- UPDATE subjects SET description='' WHERE id=3667;
-- UPDATE subjects SET description='' WHERE id=3668;
-- UPDATE subjects SET description='' WHERE id=3669;
-- UPDATE subjects SET description='' WHERE id=3670;
-- UPDATE subjects SET description='' WHERE id=3671;
-- UPDATE subjects SET description='' WHERE id=3672;
-- UPDATE subjects SET description='' WHERE id=3673;
-- UPDATE subjects SET description='' WHERE id=3674;
-- UPDATE subjects SET description='' WHERE id=3675;
-- UPDATE subjects SET description='' WHERE id=3676;
-- UPDATE subjects SET description='' WHERE id=3677;
-- UPDATE subjects SET description='' WHERE id=3678;
-- UPDATE subjects SET description='' WHERE id=3679;
-- UPDATE subjects SET description='' WHERE id=3680;
-- UPDATE subjects SET description='' WHERE id=3681;
-- UPDATE subjects SET description='' WHERE id=3682;
-- UPDATE subjects SET description='' WHERE id=3683;
-- UPDATE subjects SET description='' WHERE id=3684;
-- UPDATE subjects SET description='' WHERE id=3685;
-- UPDATE subjects SET description='' WHERE id=3686;
-- UPDATE subjects SET description='' WHERE id=3687;
-- UPDATE subjects SET description='' WHERE id=3688;
-- UPDATE subjects SET description='' WHERE id=3689;
-- UPDATE subjects SET description='' WHERE id=3690;
-- UPDATE subjects SET description='' WHERE id=3691;
-- UPDATE subjects SET description='' WHERE id=3692;
-- UPDATE subjects SET description='' WHERE id=3693;
-- UPDATE subjects SET description='' WHERE id=3694;
-- UPDATE subjects SET description='' WHERE id=3695;
-- UPDATE subjects SET description='' WHERE id=3696;
-- UPDATE subjects SET description='' WHERE id=3697;
-- UPDATE subjects SET description='' WHERE id=3698;
-- UPDATE subjects SET description='' WHERE id=3699;
-- UPDATE subjects SET description='' WHERE id=3700;
-- UPDATE subjects SET description='' WHERE id=3701;
-- UPDATE subjects SET description='' WHERE id=3702;
-- UPDATE subjects SET description='' WHERE id=3703;
-- UPDATE subjects SET description='' WHERE id=3704;
-- UPDATE subjects SET description='' WHERE id=3705;
-- UPDATE subjects SET description='' WHERE id=3706;
-- UPDATE subjects SET description='' WHERE id=3707;
-- UPDATE subjects SET description='' WHERE id=3708;
-- UPDATE subjects SET description='' WHERE id=3709;
-- UPDATE subjects SET description='' WHERE id=3710;
-- UPDATE subjects SET description='' WHERE id=3711;
-- UPDATE subjects SET description='' WHERE id=3712;
-- UPDATE subjects SET description='' WHERE id=3713;
-- UPDATE subjects SET description='' WHERE id=3714;
-- UPDATE subjects SET description='' WHERE id=3715;
-- UPDATE subjects SET description='' WHERE id=3716;
-- UPDATE subjects SET description='' WHERE id=3717;
-- UPDATE subjects SET description='' WHERE id=3718;
-- UPDATE subjects SET description='' WHERE id=3719;
-- UPDATE subjects SET description='' WHERE id=3720;
-- UPDATE subjects SET description='' WHERE id=3721;
-- UPDATE subjects SET description='' WHERE id=3722;
-- UPDATE subjects SET description='' WHERE id=3723;
-- UPDATE subjects SET description='' WHERE id=3724;
-- UPDATE subjects SET description='' WHERE id=3725;
-- UPDATE subjects SET description='' WHERE id=3726;
-- UPDATE subjects SET description='' WHERE id=3727;
-- UPDATE subjects SET description='' WHERE id=3728;
-- UPDATE subjects SET description='' WHERE id=3729;
-- UPDATE subjects SET description='' WHERE id=3730;
-- UPDATE subjects SET description='' WHERE id=3731;
-- UPDATE subjects SET description='' WHERE id=3732;
-- UPDATE subjects SET description='' WHERE id=3733;
-- UPDATE subjects SET description='' WHERE id=3734;
-- UPDATE subjects SET description='' WHERE id=3735;
-- UPDATE subjects SET description='' WHERE id=3736;
-- UPDATE subjects SET description='' WHERE id=3737;
-- UPDATE subjects SET description='' WHERE id=3738;
-- UPDATE subjects SET description='' WHERE id=3739;
-- UPDATE subjects SET description='' WHERE id=3740;
-- UPDATE subjects SET description='' WHERE id=3741;
-- UPDATE subjects SET description='' WHERE id=3742;
-- UPDATE subjects SET description='' WHERE id=3743;
-- UPDATE subjects SET description='' WHERE id=3744;
-- UPDATE subjects SET description='' WHERE id=3745;
-- UPDATE subjects SET description='' WHERE id=3746;
-- UPDATE subjects SET description='' WHERE id=3747;
