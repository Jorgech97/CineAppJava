package cineapp;                            // Paquete de la aplicación

public class Nodo {
    public int id;                          // ID de la solicitud (autoincremental)
    public String nombre;                  // Nombre del solicitante
    public int edad;                       // Edad generada aleatoriamente
    public int precio;                     // Precio calculado según edad
    public boolean pagado;                 // Indica si ya se ha pagado
    public Nodo siguiente;                 // Puntero al siguiente nodo de la lista

    // Constructor: recibe id, nombre, edad y precio
    public Nodo(int id, String nombre, int edad, int precio) {
        this.id = id;                      // Asigna el ID
        this.nombre = nombre;              // Asigna el nombre
        this.edad = edad;                  // Asigna la edad
        this.precio = precio;              // Asigna el precio
        this.pagado = false;               // Siempre empieza en false (no pagado)
        this.siguiente = null;             // No apunta a ningún nodo al crearse
    }
}
