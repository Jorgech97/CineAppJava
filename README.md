Este proyecto implementa una aplicación de escritorio en Java (usando NetBeans y Swing) para gestionar solicitudes de compra de entradas en el “Cinema del Pueblo”. Toda la información se almacena en una lista enlazada simple hecha a mano, sin utilizar colecciones predefinidas de Java, y permanece en memoria hasta que se cierra la aplicación.

Se definen dos clases principales en el modelo:

Nodo, que contiene una instancia de Solicitud y un puntero al siguiente nodo.

ListaSolicitudes, que mantiene el puntero a la cabeza de la lista, un contador de IDs y métodos para agregar nodos al final, eliminar nodos por ID y recorrer la lista.

Cada vez que el usuario registra una solicitud, al formulario gráfico llegan estos datos:

ID de la película: entero autogenerado e incremental.

Nombre del solicitante: texto ingresado en un campo.

Edad: número entero aleatorio entre 12 y 100.

Precio de la entrada: calculado automáticamente según la edad:

12–20 años → ¢2000

21–64 años → ¢2500

65–100 años → ¢1500

Pagado: booleano, inicializado en false.

Funcionalidades principales:

Registro de solicitud
El usuario completa el formulario en pantalla (sin cuadros de diálogo emergentes) y pulsa “Registrar”. Se genera un nodo con los datos y se inserta al final de la lista enlazada.

Listado de solicitudes
Al acceder al listado, la aplicación recorre la lista desde la cabeza siguiendo los punteros siguiente y vuelca cada solicitud en una JTable, mostrando todas sus columnas (ID, nombre, edad, precio y estado de pago).

Pagar una solicitud
Desde la tabla de solicitudes, el usuario selecciona una fila y hace clic en “Pagar”. El nodo correspondiente se elimina de la lista enlazada y la tabla se actualiza para reflejar la eliminación.

Para probar la aplicación basta con abrir el proyecto en NetBeans, asegurarse de tener configurado un JDK 8 o superior, y ejecutar la clase MainFrame.java. No hay almacenamiento en disco: todos los datos viven en la lista enlazada hasta que se cierre la ventana principal.

<img width="481" height="546" alt="image" src="https://github.com/user-attachments/assets/b98e2d47-81fa-411b-ae53-231292c12c4f" />

<img width="481" height="544" alt="image" src="https://github.com/user-attachments/assets/a53642d1-26ca-4442-b0ee-815bad2a7e8e" />


