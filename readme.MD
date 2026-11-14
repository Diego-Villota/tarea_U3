# Tarea U3 — Sorteo de un torneo

Aplicación Java de consola que organiza un torneo de eliminación directa con 16 equipos. Utiliza la arquitectura Modelo–Vista–Controlador (MVC), emparejamiento recursivo, excepciones personalizadas y serialización para guardar y cargar el torneo. Los enfrentamientos y los ganadores se seleccionan aleatoriamente.

## Importar y ejecutar en Eclipse IDE

Necesitas Eclipse IDE for Java Developers y un JDK configurado. El código utiliza métodos disponibles desde Java 11; las pruebas de desarrollo se realizaron con Java 25.

1. Descarga el repositorio desde **Code → Download ZIP** y extrae su contenido. También puedes clonarlo con Git:

   ```bash
   git clone https://github.com/Diego-Villota/tarea_U3.git
   ```

2. Abre Eclipse y selecciona tu espacio de trabajo. En Windows, entra en **Window → Preferences → Java → Installed JREs** y selecciona el JDK que usarás. Si falta, agrégalo con **Add → Standard VM**, indicando la carpeta del JDK. [Configurar Java en Eclipse](https://help.eclipse.org/latest/topic/org.eclipse.jdt.doc.user/tasks/task-assign_default_jre.htm).

3. Ve a **File → Import → General → Existing Projects into Workspace → Next**. En **Select root directory**, selecciona la carpeta descargada o clonada que contiene `.project`, `.classpath` y `src`. Marca `tarea_U3` y pulsa **Finish**. Si clonaste el repositorio, deja desmarcado **Copy projects into workspace** para trabajar sobre esa misma carpeta. [Importar un proyecto existente](https://help.eclipse.org/latest/topic/org.eclipse.platform.doc.user/tasks/tasks-importproject.htm).

4. En las propiedades del proyecto, comprueba que **Resource → Text file encoding** sea **UTF-8**. Si Eclipse señala problemas de versión, revisa que **Java Compiler** y **Java Build Path → Libraries → JRE System Library** utilicen una versión compatible con el JDK elegido, de Java 11 en adelante.

5. Abre `src/aplicacion/Main.java`, haz clic derecho y selecciona **Run As → Java Application**. El menú aparecerá en la pestaña **Console**. Haz clic dentro de ella para escribir una opción y pulsa Enter. [Ejecutar una aplicación Java](https://help.eclipse.org/latest/topic/org.eclipse.jdt.doc.user/tasks/task-launching_java_program.htm).

## Uso rápido

| Opción | Acción |
| --- | --- |
| `1` | Introducir 16 equipos distintos, generar el torneo y mostrar el resultado. |
| `2` | Mostrar las rondas y el campeón del torneo actual. |
| `3` | Guardar el torneo en la ruta indicada. |
| `4` | Cargar un torneo guardado. Después usa `2` para mostrarlo. |
| `0` | Salir del programa. |

Para guardar una prueba, escribe `torneo_prueba.ser` cuando se solicite la ruta. Una ruta relativa se resuelve desde el directorio de trabajo de la ejecución. Puedes consultar ese directorio en **Run → Run Configurations → Java Application → Main → Arguments → Working directory**. Para cargar desde otra ubicación, introduce la ruta completa del archivo. [Configuración de ejecución](https://help.eclipse.org/latest/topic/org.eclipse.jdt.doc.user/tasks/tasks-java-local-configuration.htm).

La opción `1` reemplaza el torneo en memoria cuando termina de crear el nuevo. Guarda el torneo actual si quieres conservarlo antes de generar otro o salir.

## Casos de prueba manuales

Para crear un torneo de prueba, utiliza los nombres `Equipo 1`, `Equipo 2`, …, `Equipo 16`, introduciendo uno por línea. En los casos que requieren un torneo existente, créalo con la opción `1` o cárgalo con la opción `4`.

| Caso | Pasos o datos de entrada | Resultado esperado |
| --- | --- | --- |
| Crear un torneo | Selecciona `1` e introduce los 16 nombres distintos. | Se muestra la confirmación, las cuatro rondas y un campeón. |
| Revisar los emparejamientos | Observa el torneo creado con `1` o mostrado con `2`. | Hay 8, 4, 2 y 1 partidos en octavos, cuartos, semifinales y final. Cada equipo juega como máximo una vez por ronda y se enfrenta a un rival distinto. |
| Revisar la clasificación | Compara cada ronda con la siguiente y revisa el campeón. | De cada partido avanza exactamente uno de sus participantes. El campeón es uno de los dos finalistas. |
| Nombre vacío | Durante la creación, pulsa Enter sin escribir un nombre o introduce solo espacios. | Se muestra un error y se solicita otra vez el mismo número de equipo. |
| Nombre repetido | Durante la creación, introduce `Barcelona` y luego `barcelona`. | Se informa que el nombre ya existe y se pide otro nombre para ese equipo. |
| Opción inválida | Escribe `9` o `abc` en el menú. | Aparece «Opción no válida» y se vuelve a mostrar el menú. |
| Mostrar sin torneo | Inicia una nueva ejecución y selecciona `2`. | Aparece «Primero cree o cargue un torneo». |
| Guardar sin torneo | Inicia una nueva ejecución y selecciona `3`. | Aparece «Primero cree o cargue un torneo». |
| Guardar y recuperar | Crea un torneo, anota los partidos y el campeón, usa `3` y guarda como `torneo_prueba.ser`. Sal con `0`, ejecuta de nuevo, usa `4` con la misma ruta y luego `2`. | Se recuperan exactamente los mismos enfrentamientos y el mismo campeón. |
| Archivo inexistente | Selecciona `4` e introduce la ruta de un archivo que no exista. | Se muestra un error de carga y el menú continúa disponible. |
| Ruta vacía | Con un torneo creado, selecciona `3` y pulsa Enter en la ruta. Repite con `4`. | En ambos casos aparece «La ruta no puede estar vacía». |
| Error al guardar | Con un torneo creado, selecciona `3` e introduce `carpeta_inexistente/torneo.ser`, comprobando antes que esa carpeta no exista. | Se informa que no se pudo guardar el torneo y el programa permite continuar. |
| Archivo de formato incorrecto | Crea un archivo de texto con cualquier contenido. Selecciona `4` e introduce su ruta. | Se informa que no se pudo cargar el torneo y el programa permite continuar. |
| Conservar el torneo ante un error | Crea un torneo y anota sus resultados. Intenta cargar un archivo inexistente con `4` y luego selecciona `2`. | Se muestran los mismos resultados del torneo que estaba en memoria. |
| Salir | Selecciona `0`. | Aparece «Hasta pronto» y termina la ejecución. |

Los resultados de sorteos nuevos pueden variar. Al cargar un archivo guardado deben conservarse sus resultados originales.
