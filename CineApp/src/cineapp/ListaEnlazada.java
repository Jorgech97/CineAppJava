package cineapp;                            // Paquete de la aplicación

public class ListaEnlazada {
    private Nodo cabeza;                    // Referencia al primer nodo de la lista
    private int contadorId;                // Para generar IDs consecutivos

    // Constructor: lista vacía y contador de ID inicia en 1
    public ListaEnlazada() {
        cabeza = null;                      // No hay nodos al principio
        contadorId = 1;                     // Primer ID será 1
    }

    /** Devuelve un ID único y lo incrementa */
    public int generarId() {
        return contadorId++;                // Post-incremento: devuelve valor y luego suma 1
    }

    /** Agrega un nuevo nodo al final de la lista */
    public void agregar(Nodo nuevo) {
        if (cabeza == null) {               // Si la lista está vacía...
            cabeza = nuevo;                 // ...el nuevo nodo es la cabeza
        } else {
            Nodo actual = cabeza;           // Si no, recorremos hasta el final
            while (actual.siguiente != null) {
                actual = actual.siguiente;  // Avanzar al siguiente nodo
            }
            actual.siguiente = nuevo;       // Enlazar el último nodo al nuevo
        }
    }

    /**
     * Elimina el nodo con el ID dado.
     * @return true si se eliminó, false si no se encontró.
     */
    public boolean eliminarPorId(int id) {
        if (cabeza == null) return false;   // Lista vacía → nada que eliminar

        if (cabeza.id == id) {              // Si la cabeza es el nodo a eliminar...
            cabeza = cabeza.siguiente;      // ...la nueva cabeza es el segundo nodo
            return true;
        }

        Nodo actual = cabeza;
        // Buscar el nodo anterior al que queremos borrar
        while (actual.siguiente != null && actual.siguiente.id != id) {
            actual = actual.siguiente;
        }
        if (actual.siguiente != null) {     // Si encontramos el ID en siguiente
            actual.siguiente = actual.siguiente.siguiente; // Saltar el nodo
            return true;
        }
        return false;                        // No halló el ID
    }

    /** Devuelve la cabeza para poder recorrer la lista desde la GUI */
    public Nodo getCabeza() {
        return cabeza;
    }
}
