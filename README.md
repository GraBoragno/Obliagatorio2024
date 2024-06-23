Descripción de los procesos de carga y realización de reportes:

Procesos de carga:

Obtuvimos los datos del csv proporcionado, decidimos crear una clase llamada ‘csv’ para poder realizar la lectura y el procesamiento del archivo especificado por la ruta ‘direcCSV’. 
Comenzamos el método de carga definiendo la ruta del archivo CSV e inicializamos una instancia llamada ‘hashMap’  con una capacidad inicial de 50. Luego utilizamos un ‘BufferedReader’ que se encarga de leer el archivo CSV, el cual se saltea la primera línea y lee las siguientes dentro de un while. Para cada línea se crea una instancia de canción, estas líneas se dividen por atributo utilizando ‘split(“\”, \””)’ que fue el criterio que consideramos más conveniente para este caso, también decidimos eliminar comillas y caracteres innecesarios.
Continuamos asignando los valores extraídos del CSV a los atributos de la clase ‘Cancion’. Los atributos de URL y título se asignan a través  del array ‘aux’ que contiene los valores, también separamos los nombres de los artistas y los guardamos en una lista. Luego convertimos los valores numéricos de diferentes atributos como ‘daily_rank’ y ‘weekly_movement’ a sus respectivos tipos, sean int o float, asignándoles sus propiedades correspondientes. También convertimos la fecha a un objeto del tipo ‘LocalDate’ y le asignamos la propiedad ‘snapshot_date’. Finalizamos la asignación de valores manejando el atributo ‘país’ el cual es asignado el valor ‘World’ si se encuentra vacío.
Para organizar en las estructuras elegidas, comenzamos verificando si existe una entrada en el ‘hashMap’ (que creamos previamente) para la fecha ‘date’, en el caso de no existir se crea una nueva instancia. Siguiendo con el país verificamos si existe una lista de canciones, si no existe se inicializa la nueva lista de 50 elementos. Terminamos añadiendo la canción a su lista correspondiente según su rango diario. Finalizamos la carga de datos manejando excepciones como ‘IOException’ y ‘PosicionInvalida’ para verificar que no ocurran errores durante el procesamiento de datos.
En resumen, el proceso de carga de datos del programa consiste en leer un archivo CSV, validar y transformar los datos, y almacenarlos en una estructura de hash table que permite acceder a las canciones de manera eficiente según la fecha y el país. 

→ Cantidad de memoria RAM consumida: 0.7765101 Gb
→ Tiempo de ejecución promedio: 3,692 segundos


Realización de reportes:

Funcion1: 
Esta función toma como parámetros una fecha ‘fecha1’ y  un pais ‘pais1’, y maneja dos excepciones ‘InformacionInvalida’ y ‘PosicionInvalida’. El objetivo principal de la función es encontrar las top 10 canciones en un país y día dados. 
Comenzamos la función recuperando los datos por fecha, utilizando el parámetro proporcionado ‘fecha1’ para obtener un objeto ‘MyHashTableImpl<String, LinkedListImpl<Cancion>[]>’ de el hashMap. Continuamos verificando la existencia de los datos, si para la fecha dada no se encuentran datos imprimimos un mensaje y se termina la función. Si se encuentran los datos, la función continua utilizando ‘pais1’ para obtener una lista de canciones del ‘hashPais’, si no se encuentran datos para ese país en esa fecha también se imprime un mensaje y termina la función. Finalizamos recorriendo los primeros 10 elementos del ‘top50’ y si el primero no es nulo, imprime las 10 canciones correspondientes a los parámetros proporcionados.

→ Cantidad de memoria RAM consumida: 0.001953125 Gb
→ Tiempo de ejecución promedio: 0,004 segundos
	
Funcion2: 
Esta función toma como parámetro una fecha ‘fecha’, y maneja dos excepciones ‘InformacionInvalida’ y ‘PosicionInvalida’. El objetivo principal de la función es encontrar las top 5 canciones que aparecen en más top 50 en un día dado.
Comenzamos obteniendo un hash table que contiene la información de las canciones para la fecha dada. Continuamos verificando si hay datos disponibles para dicha fecha, si no hay datos imprime un mensaje y la función termina. Luego inicializamos dos hash tables nuevos ‘cancionesCount’ que cuenta la cantidad de veces que aparece cada canción y ‘cancionMap’ para almacenar las canciones por su título. Seguimos la función recorriendo el hashPais, accediendo a la lista de las top 50 canciones de cada país, mientras se itera sobre cada lista de canciones ‘top50’  se incrementa el conteo en ‘cancionesCount’ y se guarda en ‘cancionMap’. Creamos un array llamado ‘topCounts’ que almacena los conteos mas altos y otro llamado ‘topCanciones’ que almacena las canciones correspondientes. Iteramos sobre ‘cancionesCount’ para identificar las 5 con mayor conteo y se actualizan ‘topCounts’ y ‘topCanciones’ según necesario. Finalizamos imprimiendo el resultado final y devolviendo las 5 canciones que más aparecen en la fecha.

→ Cantidad de memoria RAM consumida: 0.0029296875 Gb
→ Tiempo de ejecución promedio:  0,034 segundos

Funcion3: 
Esta función toma como parámetros una fecha inicial ‘fecha1’, y maneja dos excepciones ‘InformacionInvalida’ y ‘PosicionInvalida’. El objetivo principal de la función es encontrar los Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado.
Comenzamos obteniendo dos hash tables de la estructura principal ‘hashMap’ utilizando las fechas proporcionadas, si no tienen datos imprime un mensaje y finaliza la función. Luego accedemos a los buckets del hashMap que nos devuelve un array de ‘DoubleNode<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>[]>>’, inicializamos variables ‘posicion1’ y ‘posicion2’ para almacenar las posiciones de ‘fecha 1’ y ‘fecha2’ respectivamente. Continuamos iterando sobre el array de buckets donde verificamos si cada bucket no es nulo, y se compara la clave de cada bucket con ambas fechas para determinar sus posiciones. Finalizamos imprimiendo el resultado y devolviendo el resultado de la consulta.

→ Cantidad de memoria RAM consumida: 0.0029296875 Gb
→ Tiempo de ejecución promedio:  0,252 segundos

Funcion4: 
Esta función toma como parámetros una fecha ‘fecha’, un pais ‘pais’ y el nombre de un artista ‘artista’, y maneja dos excepciones ‘InformacionInvalida’ y ‘PosicionInvalida’. El objetivo principal de la función es contar la cantidad de veces que aparece un artista específico en un top 50 en una fecha dada.
Comenzamos iniciando la variable de ‘apariciones’ para contar cuantas veces aparece el artista dado, luego obtenemos un hash table llamado ‘hashPais’ utilizando la fecha dada, si no hay datos se imprime un mensaje y termina la función. Continuamos accediendo al top 50 del país especificado previamente, si no hay datos para ese país en esa fecha se imprime un mensaje y la función termina. Luego tomamos el nombre del artista proporcionado y lo convertimos a minúsculas. Finalizamos iterando sobre la lista de canciones del top 50, para cada canción obtenemos la lista de artistas y verificamos que el artista dado se encuentre en la lista, si se encuentra una coincidencia incrementamos la variable aplicaciones; Al terminar de comparar imprimimos el mensaje con el resultado obtenido

→ Cantidad de memoria RAM consumida: 0.001956125 Gb
→ Tiempo de ejecución promedio: 0,005 segundos


Funcion5: 
Esta función toma como parámetros una fecha ‘fechaInicio’, otra fecha ‘fechaFin’, un tempo ‘tempoMin’ y otro tempo ‘tempoMax’, y maneja dos excepciones ‘InformacionInvalida’ y ‘PosicionInvalida’. El objetivo principal de la función es contar la cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.
Comenzamos iniciando la variable ‘ContadorCanciones’ la cual lleva la cuenta de las canciones que cumplen con el rango de tempo especificado. Luego iteramos sobre las fechas dadas utilizando un for con ’LocalDate’, para cada fecha intentamos obtener un ‘hashPais’ correspondiente, si no se encuentran datos para esa fecha se continúa con la siguiente fecha. Continuamos iterando sobre los buckets de ‘hashPais’ que tienen las listas de canciones para los diferentes países, verifica si hay datos validos en el bucket actual y obtiene la lista de canciones. Para cada lista se itera sobre las canciones y para cada canción se verifica si su tempo se encuentra dentro del rango especificado previamente, si la canción cumple con el tempo se incrementa el contador ‘contadorCanciones’. Finalizamos imprimiendo un mensaje con los resultados obtenidos.

→ Cantidad de memoria RAM consumida: 0.0029296875 Gb
→ Tiempo de ejecución promedio: 0,008 segundos
