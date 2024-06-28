package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import modelo.Conexion;
import java.util.HashMap;


public class CrearReservacion extends javax.swing.JFrame {

    private JTable table;
    private JButton btnRegresar;
    private JLabel lblCliente;
    private JLabel lblCliente1;
    private JFormattedTextField txtFechaReserva;
    private JFormattedTextField txtHoraReserva;
    private JComboBox<String> cmbTipoViaje;
    private JComboBox<String> cmbRuta;
    private JComboBox<String> cmbMascota;
    private JButton btnReservacion;

    public CrearReservacion(String nombre_cliente, String documento_cliente) {

        initComponents();
        setTitle("Tus reservaciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setLayout(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setContentPane(panel);

        // Panel para contener las etiquetas de cliente
        JPanel clientePanel = new JPanel(new BorderLayout());

        // Etiqueta para mostrar la información del cliente
        lblCliente = new JLabel("USUARIO: " + nombre_cliente);
        lblCliente.setBorder(new EmptyBorder(10, 10, 10, 0)); // Establecer margen superior e inferior
        lblCliente.setFont(lblCliente.getFont().deriveFont(lblCliente.getFont().getStyle() | Font.BOLD)); // Aplicar negrita
        clientePanel.add(lblCliente, BorderLayout.WEST);

        lblCliente1 = new JLabel("ID: " + documento_cliente);
        lblCliente1.setBorder(new EmptyBorder(10, 0, 10, 10)); // Establecer margen superior e inferior
        lblCliente1.setFont(lblCliente1.getFont().deriveFont(lblCliente1.getFont().getStyle() | Font.BOLD)); // Aplicar negrita
        clientePanel.add(lblCliente1, BorderLayout.EAST);

        panel.add(clientePanel);

        // Crear un modelo de tabla
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que todas las celdas sean no editables
            }
        };

        model.addColumn("id_reserva");
        model.addColumn("fecha_reserva");
        model.addColumn("hora_reserva");
        model.addColumn("id_tipo_viaje");
        model.addColumn("id_ruta");
        model.addColumn("id_mascota");

        // Crear el JTable con el modelo de tabla
        table = new JTable(model);

        // Agregar el JTable a un JScrollPane para permitir desplazamiento
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

        Conexion databaseConnector = new Conexion();
        Connection connection = databaseConnector.getConexion();

        // Realizar la consulta SQL 1
        try {
            String query = "SELECT * FROM dbo.Reserva WHERE documento_cliente = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, documento_cliente);
            ResultSet resultSet = statement.executeQuery();

            // Recorrer los registros obtenidos
            while (resultSet.next()) {
                // Obtener los valores de cada columna en el registro actual
                String id_reserva = resultSet.getString("id_reserva");
                String fecha_reserva = resultSet.getString("fecha_reserva");
                String hora_reserva = resultSet.getString("hora_reserva");
                String id_tipo_viaje = resultSet.getString("id_tipo_viaje");
                String id_ruta = resultSet.getString("id_ruta");
                String id_mascota = resultSet.getString("id_mascota");

                // Crear un vector para almacenar los datos de cada fila
                Vector<String> row = new Vector<>();
                row.add(id_reserva);
                row.add(fecha_reserva);
                row.add(hora_reserva);
                row.add(id_tipo_viaje);
                row.add(id_ruta);
                row.add(id_mascota);

                // Agregar la fila al modelo de tabla
                model.addRow(row);
            }

            // Cerrar recursos de la consulta 1
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Etiquetas
        JLabel lblFechaReserva = new JLabel("Fecha reserva (mm/dd/aa)");

        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            txtFechaReserva = new JFormattedTextField(dateMask);
            txtFechaReserva.setColumns(10);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JLabel lblHoraReserva = new JLabel("Hora reserva (hh:mm formato 24 horas)");

        try {
            MaskFormatter timeMask = new MaskFormatter("##:##");
            timeMask.setPlaceholderCharacter('_');
            txtHoraReserva = new JFormattedTextField(timeMask);
            txtHoraReserva.setColumns(5);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JLabel lblTipoViaje = new JLabel("Tipo de viaje");
        String[] tiposViaje = {"1", "2"};
        cmbTipoViaje = new JComboBox<>(tiposViaje);

        JLabel lblRuta = new JLabel("Tipo de ruta");
        String[] tiposRuta = {"1", "2", "3", "4", "5"};
        cmbRuta = new JComboBox<>(tiposRuta);

        JLabel lblMascota = new JLabel("ID de mascota");
        cmbMascota = new JComboBox<>();

// Realizar la consulta SQL
        try {
            String query = "SELECT id_mascota FROM dbo.Mascota WHERE documento_cliente = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, documento_cliente);
            ResultSet resultSet = statement.executeQuery();

            // Recorrer los registros obtenidos
            while (resultSet.next()) {
                // Obtener el valor de id_mascota en el registro actual
                String id_mascota = resultSet.getString("id_mascota");

                // Agregar el valor al JComboBox
                cmbMascota.addItem(id_mascota);
            }

            // Cerrar recursos de la consulta
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

// Agregar el JComboBox y el JLabel al contenedor adecuado (por ejemplo, un JPanel)
        panel.add(cmbMascota);
        panel.add(lblMascota);

// Agregar un ActionListener al JComboBox
        cmbMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener la selección actual del JComboBox
                String selectedMascota = (String) cmbMascota.getSelectedItem();

                // Actualizar el texto del JLabel con la selección actual
                lblMascota.setText("ID de mascota: " + selectedMascota);
            }
        });

        // Botones para realizar acciones
        btnReservacion = new JButton("Reservar");
        btnRegresar = new JButton("Regresar");

        int x = 50;
        int y = 50;
        int labelWidth = 120;
        int labelHeight = 25;
        int fieldWidth = 200;
        int fieldHeight = 25;
        int verticalSpacing = 30;

        lblFechaReserva.setForeground(Color.BLACK);
        lblFechaReserva.setHorizontalAlignment(SwingConstants.CENTER);
        lblHoraReserva.setForeground(Color.BLACK);
        lblHoraReserva.setHorizontalAlignment(SwingConstants.CENTER);
        lblTipoViaje.setForeground(Color.BLACK);
        lblTipoViaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblRuta.setForeground(Color.BLACK);
        lblRuta.setHorizontalAlignment(SwingConstants.CENTER);
        lblMascota.setForeground(Color.BLACK);
        lblMascota.setHorizontalAlignment(SwingConstants.CENTER);

        lblFechaReserva.setBounds(x, y, labelWidth, labelHeight);
        txtFechaReserva.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
        y += verticalSpacing;

        lblHoraReserva.setBounds(x, y, labelWidth, labelHeight);
        txtHoraReserva.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
        y += verticalSpacing;

        lblTipoViaje.setBounds(x, y, labelWidth, labelHeight);
        cmbTipoViaje.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
        y += verticalSpacing;

        lblRuta.setBounds(x, y, labelWidth, labelHeight);
        cmbRuta.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
        y += verticalSpacing;

        lblMascota.setBounds(x, y, labelWidth, labelHeight);
        cmbMascota.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
        y += verticalSpacing;

        btnReservacion.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
        btnRegresar.setBounds(x + labelWidth, y + verticalSpacing, fieldWidth, fieldHeight);

        // Agregar componentes al panel
        panel.add(lblFechaReserva);
        panel.add(txtFechaReserva);
        panel.add(lblHoraReserva);
        panel.add(txtHoraReserva);
        panel.add(lblTipoViaje);
        panel.add(cmbTipoViaje);
        panel.add(lblRuta);
        panel.add(cmbRuta);
        panel.add(lblMascota);
        panel.add(cmbMascota);
        panel.add(btnReservacion);
        panel.add(btnRegresar);

        btnReservacion.setFont(btnReservacion.getFont().deriveFont(Font.ITALIC, 16)); // Aumentar el tamaño del texto del botón
        btnReservacion.setPreferredSize(
                new Dimension(120, 40)); // Establecer el tamaño preferido del botón   
        // Crear un panel para contener el botón y utilizar FlowLayout para centrarlo
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnReservacion);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        btnReservacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de texto
                String fecha_reserva = txtFechaReserva.getText();
                String hora_reserva = txtHoraReserva.getText();
                String id_tipo_viaje = cmbTipoViaje.getSelectedItem().toString();
                String id_ruta = cmbRuta.getSelectedItem().toString();
                String id_mascota = cmbMascota.getSelectedItem().toString();

                // Verificar si algún campo está vacío
                if (fecha_reserva.isEmpty() || hora_reserva.isEmpty() || id_tipo_viaje.isEmpty()
                        || id_ruta.isEmpty() || id_mascota.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
                    return; // Salir del método sin ejecutar la consulta SQL
                }

                // Crear la conexión a la base de datos
                String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                        + "database=AnimalTripOficial;"
                        + "user=sa;"
                        + "password=hola123;"
                        + "loginTimeout=30;";

                try {
                    Connection conn = DriverManager.getConnection(conexionUrl);

                    // Preparar la sentencia SQL con los valores capturados del formulario
                    String sql = "INSERT INTO dbo.Reserva (fecha_reserva, hora_reserva, id_tipo_viaje, documento_cliente, id_ruta, id_mascota) VALUES (?, ?, ?, ?, ? ,?)";
                    PreparedStatement statement = conn.prepareStatement(sql);
                    statement.setString(1, fecha_reserva);
                    statement.setString(2, hora_reserva);
                    statement.setString(3, id_tipo_viaje);
                    statement.setString(4, documento_cliente);
                    statement.setString(5, id_ruta);
                    statement.setString(6, id_mascota);
                    // Ejecutar la sentencia SQL
                    statement.executeUpdate();

                    // Cerrar la conexión
                    statement.close();
                    conn.close();

                    // Mostrar mensaje de éxito solo si la reserva se ha registrado correctamente
                    JOptionPane.showMessageDialog(null, "Reserva registrada exitosamente (Por favor, salga y vuelva a ingresar para ver reflejada su reserva)");
                   
                    // Limpiar los campos de texto después de registrar
                    txtFechaReserva.setText("");
                    txtHoraReserva.setText("");

                } catch (SQLException ex) {
                    if (ex.getErrorCode() == 50000) {
                        // Si el código de error es 50000, significa que es el error generado por el RAISERROR en el trigger
                        String mensajeError = ex.getMessage();
                        JOptionPane.showMessageDialog(null, mensajeError);
                        txtFechaReserva.setText("");
                        txtHoraReserva.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al registrar la reserva");
                        txtFechaReserva.setText("");
                        txtHoraReserva.setText("");
                    }
                }

            }
        });

        btnRegresar.setFont(btnRegresar.getFont().deriveFont(Font.ITALIC, 16)); // Aumentar el tamaño del texto del botón
        btnRegresar.setPreferredSize(
                new Dimension(120, 40)); // Establecer el tamaño preferido del botón   
        // Crear un panel para contener el botón y utilizar FlowLayout para centrarlo
        buttonPanel.add(btnRegresar);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        btnRegresar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Cerrar el JFrame actual
                dispose();

                // Abrir el JFrame clientMenu
                ClienteMenu clientMenu = new ClienteMenu(nombre_cliente, documento_cliente);
                clientMenu.setVisible(true);
            }
        }
        );

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        CrearReservacion example = new CrearReservacion("Nombre del Cliente", "Documento del Cliente");
        example.setVisible(true);
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CrearReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearReservacion("Nombre del Cliente", "Documento del Cliente").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
