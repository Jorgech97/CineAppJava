// Jorge Chaves Montiel
// 116910248
package cineapp;                            // Paquete de la aplicación

import java.awt.*;                          // Para layouts y componentes
import java.util.Random;                    // Para generar edad aleatoria
import javax.swing.*;                       // Bibliotecas Swing
import javax.swing.table.DefaultTableModel; // Modelo para JTable

public class MainFrame extends JFrame {
    // ==== Modelo de datos ====
    private ListaEnlazada lista;            // Nuestra lista enlazada “hecha a mano”

    // ==== Componentes de la GUI ====
    private JTabbedPane tabbedPane;         
    // --- Pestaña Registro ---
    private JTextField txtNombre;           
    private JTextField txtId;               
    private JTextField txtEdad;             
    private JTextField txtPrecio;           
    private JCheckBox chkPagado;            
    private JButton btnRegistrar;           
    private JLabel lblErrorRegistro;        
    // --- Pestaña Listado ---
    private JTable tableSolicitudes;        
    private JButton btnPagar;               
    private JLabel lblErrorListado;        

    // Constructor
    public MainFrame() {
        super("Registro de Entradas de Cine"); // Título de la ventana
        lista = new ListaEnlazada();           // Instanciar la lista vacía
        initComponents();                      // Construir la GUI
        inicializarTabla();                    // Configurar columnas de la tabla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra app al cerrar ventana
        pack();                                // Ajusta tamaño según contenidos
        setLocationRelativeTo(null);           // Centra la ventana en pantalla
    }

    // Construye todos los componentes y layout
    private void initComponents() {
        tabbedPane = new JTabbedPane();

        // --- Panel Registro ---
        JPanel regPanel = new JPanel();
        regPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Margen
        regPanel.setLayout(new BoxLayout(regPanel, BoxLayout.Y_AXIS));

        // Formulario con GridLayout: 5 filas, 2 columnas, separación 5px
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        // Fila 1: Nombre
        formPanel.add(new JLabel("Nombre del solicitante:"));
        txtNombre = new JTextField();
        formPanel.add(txtNombre);
        // Fila 2: ID
        formPanel.add(new JLabel("ID de la película:"));
        txtId = new JTextField();
        txtId.setEditable(false);            // No editable
        formPanel.add(txtId);
        // Fila 3: Edad
        formPanel.add(new JLabel("Edad:"));
        txtEdad = new JTextField();
        txtEdad.setEditable(false);
        formPanel.add(txtEdad);
        // Fila 4: Precio
        formPanel.add(new JLabel("Precio (₡):"));
        txtPrecio = new JTextField();
        txtPrecio.setEditable(false);
        formPanel.add(txtPrecio);
        // Fila 5: Pagado
        formPanel.add(new JLabel("Pagado:"));
        chkPagado = new JCheckBox();
        chkPagado.setEnabled(false);         // Solo lectura
        formPanel.add(chkPagado);

        regPanel.add(formPanel);

        // Botón Registrar centrado
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnRegistrar = new JButton("Registrar");
        buttonPanel.add(btnRegistrar);
        regPanel.add(Box.createVerticalStrut(10)); // Espacio vertical
        regPanel.add(buttonPanel);

        // Label de error Registro
        lblErrorRegistro = new JLabel(" ");
        lblErrorRegistro.setForeground(Color.RED);
        regPanel.add(Box.createVerticalStrut(5));
        regPanel.add(lblErrorRegistro);

        tabbedPane.addTab("Registro", regPanel);

        // --- Panel Listado ---
        JPanel listPanel = new JPanel(new BorderLayout(5,5));
        listPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Tabla de solicitudes dentro de un scroll
        tableSolicitudes = new JTable();
        JScrollPane scroll = new JScrollPane(tableSolicitudes);
        listPanel.add(scroll, BorderLayout.CENTER);

        // Botón Pagar y label de error abajo
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPagar = new JButton("Pagar");
        bottomPanel.add(btnPagar);
        lblErrorListado = new JLabel(" ");
        lblErrorListado.setForeground(Color.RED);
        bottomPanel.add(lblErrorListado);

        listPanel.add(bottomPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("Listado", listPanel);

        // Agregar el tabbedPane al frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Asociar eventos de botones
        btnRegistrar.addActionListener(e -> registrarSolicitud());
        btnPagar.addActionListener(e -> pagarSolicitud());
    }

    /** Configura el modelo de columnas de la tabla (no editable) */
    private void inicializarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID", "Nombre", "Edad", "Precio", "Pagado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;                  // Ninguna celda es editable
            }
        };
        tableSolicitudes.setModel(modelo);    // Asigna el modelo a la tabla
    }

    /** Recarga la tabla con todos los nodos de la lista enlazada */
    private void actualizarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tableSolicitudes.getModel();
        modelo.setRowCount(0);                // Limpiar filas actuales
        Nodo actual = lista.getCabeza();      // Empezar por la cabeza
        while (actual != null) {
            // Añadir fila con los datos del nodo
            modelo.addRow(new Object[]{
                actual.id,
                actual.nombre,
                actual.edad,
                actual.precio,
                actual.pagado
            });
            actual = actual.siguiente;       // Avanzar al siguiente nodo
        }
    }

    /** Lógica que se ejecuta al pulsar “Registrar” */
    private void registrarSolicitud() {
        lblErrorRegistro.setText(" ");        // Limpiar mensaje de error
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {               // Validar que tengan nombre
            lblErrorRegistro.setText("Ingrese el nombre del solicitante.");
            return;                           // Salir sin continuar
        }
        // Generación de datos automáticos
        int id = lista.generarId();           // ID consecutivo
        int edad = new Random().nextInt(89) + 12;  // Edad entre 12 y 100
        int precio;
        if (edad <= 20)       precio = 2000;   // Precio según rango de edad
        else if (edad <= 64)  precio = 2500;
        else                  precio = 1500;

        // Crear nodo y agregarlo a la lista
        Nodo nuevo = new Nodo(id, nombre, edad, precio);
        lista.agregar(nuevo);

        // Mostrar los datos generados en los campos (solo lectura)
        txtId.setText(String.valueOf(id));
        txtEdad.setText(String.valueOf(edad));
        txtPrecio.setText(String.valueOf(precio));
        chkPagado.setSelected(false);         // Siempre false al crear

        txtNombre.setText("");                // Limpiar campo de nombre
        actualizarTabla();                    // Refrescar listado
        tabbedPane.setSelectedIndex(1);       // Cambiar a pestaña Listado
    }

    /** Lógica que se ejecuta al pulsar “Pagar” */
    private void pagarSolicitud() {
        lblErrorListado.setText(" ");         // Limpiar mensaje de error
        int fila = tableSolicitudes.getSelectedRow();
        if (fila == -1) {                     // Validar que hayan seleccionado algo
            lblErrorListado.setText("Seleccione una solicitud para pagar.");
            return;
        }
        int id = (int) tableSolicitudes.getValueAt(fila, 0); // Obtener ID de la fila
        boolean ok = lista.eliminarPorId(id); // Intentar eliminar de la lista
        if (ok) {
            actualizarTabla();                // Si fue exitoso, refrescar tabla
        } else {
            lblErrorListado.setText("Error al eliminar la solicitud.");
        }
    }

    // Punto de entrada de la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true); // Mostrar ventana en el hilo de Swing
        });
    }
}
