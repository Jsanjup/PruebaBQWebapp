# WebappPruebaBQ
Web application para la prueba técnica de selección.

[Finalizada]

Realizada usando el MVC framework de Spring en el entorno de desarrollo Eclipse. Todas las librerias utilizadas están en la carpeta WebContent/WEB_INF/lib, deben ser incluidas en el build path para continuar el desarrollo.


Requisitos:

1. Front donde un usuario tenga un formulario donde meter:
	- Nombre
	- e-mail
	- Identificador
[HECHO]

2. Una vez el usuario ha sido creado. Se le mandara un email de confirmación. [HECHO]

3. Existirá un webservice (REST) llamado addMessage, donde pasandole el identificador de un usuario, y un literal, se guardará en base de datos. En caso de no existir dicho usuario, deberá rechazar el mensaje y guardar un log de lo ocurrido. [HECHO]

4. Deberá existir una vista donde visualizar todos los mensajes recibidos y los usuarios que los han mandado. Esta lista se podrá filtrar por usuario mediante un desplegable. [HECHO]

5. Esta lista de mensajes recibidos también podra ser exportada a formato CSV. [HECHO]

6. Explicar en una memoria, como hacer esta aplicación escalable y de alta disponibilidad. [NO HECHO]


# Proceso de realización [Requisitos 1 a 5]:

A - Creación de la estructura MVC.

1- Creación de un nuevo proyecto web dinámico en eclipse y de un repositorio git asociado.

2- Creación de los archivos de servlet.xml y web.xml, para describir el funcionamiento del servlet y el mapping. Unícamente necesitamos un servlet que renderice los archivos .jsp, por lo que todos los servicios están mapeados a este.

3- Creación de una clase MainApp que renderice el index (y, hasta la integración con la base de datos, almacene el modelo como objetos estáticos de la clase).
A su vez, creación del index.jsp con las 3 simples opciones: crear nuevo usuario, enviar mensaje y ver el historial de mensajes.

4- Creacion de los objetos del modelo Usuario y Mensaje, con los campos necesarios:
nombre, email y nick para el usuario y autor y texto para los mensajes  (todos String).

5- Creación del resto de vistas (archivos .jsp registro, enviar mensaje e historial) y los controladores asociados siguiendo el mismo modelo.
Los controladores gestionan las peticiones REST, accediendo al modelo y indicando las vistas a renderizar.

6- Exportación de la webapp como WAR a la carpeta "webapps" del servidor tomcat y pruebas de ejecución en "localhost:8080/PruebaBQWebapp/".

7- Integración de logging: inclusión de la libreria log4j de apache. Configuración del archivo log4j.properties para indicar el archivo en el que se guarde el log y marcar el umbral a nivel "error", para filtrar unicamente los mensajes introducidos por mí (a nivel de error).


B - Integración con la base de datos.

1- Creación de la nueva base de datos (pruebabq) en mysql (escuchando en el puerto 3306).

2- Creación de las dos tablas asociadas al modelo Mensaje y Usuario con los scripts (en la carpeta scripts sql) descritos.

3- Creación de las clases DAO asociadas y las implementaciones de estas, UsuarioJDBCTemplate y MensajeJDBCTemplate. Incluyen todos los comandos SQL para las operaciones CRUD de usuarios o mensajes. Así mismo, creación de los Mappers para la extracción de objetos de la BBDD.

4- Inclusión de las Beans en el archivo xml del servlet, describiendo la conexión con la BBDD MySQL.

5- Conexión de las implementaciones JDBC creadas mediante @Autowire en los controladores precisados e integración en las vistas.
No se verifica la corrección o no repetición de los datos introducidos, porque no se especifica en los requisitos.


C - Implementación de la exportación como CSV

1- Importación de las librerias superCSV para automatizar la exportación de los resultados de las búsquedas en SQL a formato CSV.

2- Implementación de un nuevo controller para la operación GET /csvExport que escriba el archivo a descargar en la respuesta HTTP.


D- Integración de el servicio de e-mails de confirmación.

1- Importación de las librerías de JavaMail para el envío de mensajes.

2- Los mensajes se envían a través de los servidores SMTP de Gmail. Por tanto, se utilizan las librerias de JavaMail para usar Gmail sobre una sesión TLS. Como no quería introducir los datos de mi cuenta personal, una cuenta de gmail ha sido creada exclusivamente para esta prueba, con el nombre de usuario y contraseña que figuran en el archivo.

3- Establecimiento de una sesión TLS y envío de mensajes genéricos a las direcciones de e-mail introducidas desde la cuenta creada para la prueba. No se verifica que las direcciones introducidas sean validas porque no se solicita en los requisitos.



# Memoria explicando como hacer esta aplicación escalable y de alta disponibilidad. [Requisito 6]

Mis conocimientos del framework de Spring y de las librerias utilizadas para la realización del proyecto no son suficientes para conocer su comportamiento detallado frente a una gran cantidad de peticiones. Por tanto, no sé como responderían o como de adecuadas serían estas librerías en estos casos.

Puedo suponer que las conexiones a gmail mediante conexiones TLS serían un elemento bloqueante, por lo que se podría modificar el código para mantener una única sesión abierta. Por otro lado supongo que los servidores de gmail si tendrán una alta disponibilidad, pero que limitarían a un usuario regular.

Supongo que una solución arquitectural como configurar réplicas en otros servidores y un balanceador de carga podría mejorar la disponibilidad. Pero para un gran número de peticiones, tanto al servidor como a la base de datos, no sé como hacer la aplicación escalable, al menos desde un punto de vista de software (con los frameworks utilizados o con otros). 

Por tanto no puedo completar adecuadamente este requisito.
