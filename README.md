Manual del Usuario – Plataforma de Evaluación por Niveles Cognitivos
====================================================================

Nombre del proyecto: Tarea2025
Lenguaje utilizado: Java
Interfaz gráfica: Swing
Entorno recomendado: IntelliJ IDEA 2024.3 o superior con JDK 24.0.1

--------------------------------------------------------------------
Estructura del Proyecto
--------------------------------------------------------------------
- backend: Contiene la lógica del sistema, clases para manejar preguntas, evaluación y carga de datos.
- frontend: Contiene la interfaz visual, interacción con el usuario y navegación entre preguntas.

--------------------------------------------------------------------
Objetivo General
--------------------------------------------------------------------
Permitir que un usuario rinda una prueba compuesta por preguntas clasificadas según la Taxonomía de Bloom. El sistema permite cargar preguntas desde un archivo externo, responderlas una por una, y obtener un resumen al finalizar.

--------------------------------------------------------------------
Características Principales
--------------------------------------------------------------------
- Soporta los tipos de preguntas: SELECCION_MULTIPLE y VERDADERO_FALSO.
- Cada pregunta está asociada a un nivel cognitivo de Bloom:
  RECORDAR, ENTENDER, APLICAR, ANALIZAR, EVALUAR, CREAR.
- Muestra un resumen de respuestas correctas e incorrectas al finalizar.
- La prueba es inmodificable una vez enviada.
- El archivo de preguntas se carga al iniciar la aplicación mediante un selector de archivos.

--------------------------------------------------------------------
Supuestos
--------------------------------------------------------------------
- El sistema se ejecuta desde IntelliJ IDEA con Java instalado correctamente.
- El archivo de preguntas tiene el formato adecuado.
- Todas las preguntas tienen al menos dos opciones.
- Los índices de respuesta comienzan desde 0.

--------------------------------------------------------------------
Pasos para Ejecutar el Sistema
--------------------------------------------------------------------
1. Abre el proyecto en IntelliJ IDEA.
2. Verifica que esté configurado con Java 24.0.1.
3. Ejecuta la clase `Main` ubicada en el paquete frontend.
4. Selecciona un archivo `.txt` con preguntas válidas.
5. Presiona "Iniciar prueba" para comenzar.
6. Contesta todas las preguntas con el botón "Siguiente".
7. Una vez respondidas, se mostrará un resumen de resultados:
   ✔ RESPUESTA CORRECTA: muestra las acertadas.
   ✘ RESPUESTA INCORRECTA: muestra las que no fueron correctas.

--------------------------------------------------------------------
Formato del Archivo de Preguntas
--------------------------------------------------------------------
Cada línea del archivo representa una pregunta con el siguiente formato:

enunciado;opcion1|opcion2|...;respuestaCorrecta;tipo;nivel;tiempo

- enunciado: texto de la pregunta.
- opciones: lista separada por "|".
- respuestaCorrecta: índice de la opción correcta (comienza en 0).
- tipo: SELECCION_MULTIPLE o VERDADERO_FALSO.
- nivel: nivel de Bloom (RECORDAR, ENTENDER, etc.).
- tiempo: minutos estimados.

--------------------------------------------------------------------
Ejemplo de Entrada
--------------------------------------------------------------------
¿Qué ciudad es capital de Francia?;París|Madrid|Berlín|Roma;0;SELECCION_MULTIPLE;RECORDAR;30
