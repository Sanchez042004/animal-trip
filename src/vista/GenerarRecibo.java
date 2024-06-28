package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GenerarRecibo extends javax.swing.JFrame {

    private JTable table;
    private JButton btnRegresar;
    private JButton btnPagar;
    private JLabel lblCliente;
    private JLabel lblCliente1;
    private JComboBox<String> cmbIDReserva;

    public GenerarRecibo(String nombre_cliente, String documento_cliente) {
        setTitle("Tus recibos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        getContentPane().add(panel);

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
        model.addColumn("id_factura");
        model.addColumn("fecha_factura");
        model.addColumn("descripcion_servicio");
        model.addColumn("precio_total");
        model.addColumn("id_reserva");

        // Crear el JTable con el modelo de tabla
        table = new JTable(model);

        // Agregar el JTable a un JScrollPane para permitir desplazamiento
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        Conexion databaseConnector = new Conexion();
        Connection connection = databaseConnector.getConexion();

        // Realizar la consulta SQL
        try {
            String query = "SELECT * FROM dbo.Factura WHERE documento_cliente = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, documento_cliente);
            ResultSet resultSet = statement.executeQuery();

            // Recorrer los registros obtenidos
            while (resultSet.next()) {
                // Obtener los valores de cada columna en el registro actual
                String id_factura = resultSet.getString("id_factura");
                String fecha_factura = resultSet.getString("fecha_factura");
                String descripcion_servicio = resultSet.getString("descripcion_servicio");
                String precio_total = resultSet.getString("precio_total");
                String id_reserva = resultSet.getString("id_reserva");

                // Crear un vector para almacenar los datos de cada fila
                Vector<String> row = new Vector<>();
                row.add(id_factura);
                row.add(fecha_factura);
                row.add(descripcion_servicio);
                row.add(precio_total);
                row.add(id_reserva);

                // Agregar la fila al modelo de tabla
                model.addRow(row);
            }

            // Cerrar recursos
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Etiquetas
        JLabel lblIDReserva = new JLabel("ID de la reserva:");
        cmbIDReserva = new JComboBox<>();
        panel.add(lblIDReserva, BorderLayout.WEST);
        panel.add(cmbIDReserva, BorderLayout.CENTER);
        setContentPane(panel);

        setVisible(true);

        // Realizar la consulta SQL
        try {
            String query = "SELECT id_reserva FROM dbo.Reserva WHERE documento_cliente = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, documento_cliente);
            ResultSet resultSet = statement.executeQuery();

            // Recorrer los registros obtenidos
            while (resultSet.next()) {
                // Obtener el valor de id_mascota en el registro actual
                String id_reserva = resultSet.getString("id_reserva");

                // Agregar el valor al JComboBox
                cmbIDReserva.addItem(id_reserva);
            }

            // Cerrar recursos de la consulta
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

// Agregar el JComboBox y el JLabel al contenedor adecuado (por ejemplo, un JPanel)
        panel.add(cmbIDReserva);
        panel.add(lblIDReserva);

        // Botón de registrar
        btnPagar = new JButton("Pagar");
        btnRegresar = new JButton("Regresar");

        int x = 50;
        int y = 50;
        int labelWidth = 120;
        int labelHeight = 25;
        int fieldWidth = 200;
        int fieldHeight = 25;
        int verticalSpacing = 30;

        lblIDReserva.setForeground(Color.BLACK);

        lblIDReserva.setBounds(x, y, labelWidth, labelHeight);
        cmbIDReserva.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
        y += verticalSpacing;

        // Agregar componentes al panel
        panel.add(lblIDReserva);
        panel.add(cmbIDReserva);

        // Botones para realizar acciones
        btnPagar.setFont(btnPagar.getFont().deriveFont(Font.ITALIC, 16)); // Aumentar el tamaño del texto del botón
        btnPagar.setPreferredSize(
                new Dimension(150, 40)); // Establecer el tamaño preferido del botón   
        // Crear un panel para contener el botón y utilizar FlowLayout para centrarlo
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnPagar);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si se ha seleccionado un registro en el JComboBox
                if (cmbIDReserva.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "No hay registros seleccionados");
                    return;
                }

                String id_reserva = cmbIDReserva.getSelectedItem().toString();

                // Asignar los valores para el nuevo registro de factura
                String fecha_factura = "2023-05-29";
                String descripcion_servicio = "Viaje de mascota";
                String precio_total = "50000";

                // Crear la conexión a la base de datos
                String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                        + "database=AnimalTripOficial;"
                        + "user=sa;"
                        + "password=hola123;"
                        + "loginTimeout=30;";

                try (Connection connection = DriverManager.getConnection(conexionUrl)) {
                    // Verificar si ya existe una factura para el id_reserva
                    String selectQuery = "SELECT COUNT(*) FROM dbo.Factura WHERE id_reserva = ?";
                    PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                    selectStatement.setString(1, id_reserva);
                    ResultSet resultSet = selectStatement.executeQuery();
                    resultSet.next();
                    int count = resultSet.getInt(1);
                    resultSet.close();
                    selectStatement.close();

                    if (count > 0) {
                        JOptionPane.showMessageDialog(null, "Ya existe una factura registrada para el id_reserva: " + id_reserva);
                        return;
                    }

                    // Convertir id_reserva a entero
                    int idReserva = Integer.parseInt(id_reserva);

                    // Llamar al stored procedure sp_generar_recibo
                    String command = "sqlcmd -S localhost -d AnimalTripOficial -U sa -P hola123 -Q \"EXEC sp_generar_recibo @id_reserva = " + idReserva + "\"";

                    // Ejecutar el comando y redirigir la salida
                    Process process = Runtime.getRuntime().exec(command);

                    // Leer la salida del proceso
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    StringBuilder output = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        output.append(line).append("\n");
                    }

                    int exitCode = process.waitFor();
                    if (exitCode == 0) {
                        // Insertar el nuevo registro en la tabla dbo.Factura
                        String insertQuery = "INSERT INTO dbo.Factura (fecha_factura, descripcion_servicio, precio_total, documento_cliente, id_reserva) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                        insertStatement.setString(1, fecha_factura);
                        insertStatement.setString(2, descripcion_servicio);
                        insertStatement.setString(3, precio_total);
                        insertStatement.setString(4, documento_cliente); // Asegúrate de tener el valor correcto para documento_cliente
                        insertStatement.setString(5, id_reserva);
                        insertStatement.executeUpdate();
                        insertStatement.close();

                        JOptionPane.showMessageDialog(null, output.toString(), "Recibo de reserva", JOptionPane.INFORMATION_MESSAGE);
                        JOptionPane.showMessageDialog(null, "Factura generada correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al generar el recibo", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al generar la factura");
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al ejecutar el stored procedure");
                }
            }
        });

        btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(btnRegresar.getFont().deriveFont(Font.ITALIC, 16)); // Aumentar el tamaño del texto del botón
        btnRegresar.setPreferredSize(new Dimension(120, 40)); // Establecer el tamaño preferido del botón

        // Crear un panel para contener el botón y utilizar FlowLayout para centrarlo
        buttonPanel.add(btnRegresar);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar el JFrame actual
                dispose();

                // Abrir el JFrame clientMenu
                ClientMenu clientMenu = new ClientMenu(nombre_cliente, documento_cliente);
                clientMenu.setVisible(true);
            }
        });

        //initComponents();
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
            java.util.logging.Logger.getLogger(GenerarRecibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerarRecibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerarRecibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerarRecibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerarRecibo("Nombre del Cliente", "Documento del Cliente").setVisible(true);
            }
        });

        // Crear una instancia de la clase Conexion para conectarse a la base de datos
        Conexion databaseConnector = new Conexion();
        databaseConnector.getConexion();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
