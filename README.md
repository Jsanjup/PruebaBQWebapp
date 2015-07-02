# WebappPruebaBQ
Web application para la prueba técnica de selección.

[En proceso]

Realizada usando el MVC framework de Spring en el entorno de desarrollo Eclipse.

Requisitos:
1. Front donde un usuario tenga un formulario donde meter:
	- Nombre
	- e-mail
	- Identificador
2. Una vez el usuario ha sido creado. Se le mandara un email de confirmación.
3. Existirá un webservice (REST) llamado addMessage, donde pasandole el identificador de un usuario, y un literal, se guardará en base de datos. En caso de no existir dicho usuario, deberá rechazar el mensaje y guardar un log de lo ocurrido.
4. Deberá existir una vista donde visualizar todos los mensajes recibidos y los usuarios que los han mandado. Esta lista se podrá filtrar por usuario mediante un desplegable.
5. Esta lista de mensajes recibidos también podra ser exportada a formato CSV
6. Explicar en una memoria, como hacer esta aplicación escalable y de alta disponibilidad.

Proceso de realización:
1- Creación de un nuevo proyecto web dinámico en eclipse y de un repositorio git asociado.
2- Creación de los archivos de servlet.xml y web.xml, para describir el funcionamiento del servlet y el mapping. Unícamente necesitamos un servlet que renderice los archivos .jsp, por lo que todos los servicios están mapeados a este.
3- Creación de una clase MainApp que renderice el index (y, hasta la integración con la base de datos, almacene el modelo como objetos estáticos de la clase).
A su vez, creación del index.jsp con las 3 simples opciones: crear nuevo usuario, enviar mensaje y ver el historial de mensajes.
4- Creacion de los objetos del modelo Usuario y Mensaje, con los campos necesarios:
nombre, email y nick para el usuario y autor y texto para los mensajes  (todos String).
5- Creación del resto de vistas (registro, enviar mensaje e historial) y los controladores asociados.

