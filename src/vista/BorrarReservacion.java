package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class BorrarReservacion extends javax.swing.JFrame {

    private JTable table;
    private JButton btnRegresar;
    private JLabel lblCliente;
    private JLabel lblCliente1;
    private JButton btnCancelar;
    private JComboBox<String> cmbIDReserva;

    public BorrarReservacion(String nombre_cliente, String documento_cliente) {
        initComponents();
        setTitle("Tus reservaciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
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
        lblCliente1.setBorder(new EmptyBorder(10, 0, 0, 10)); // Establecer margen superior e inferior
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
        btnCancelar = new JButton("Cancelar reserva");
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

        btnCancelar.setFont(btnCancelar.getFont().deriveFont(Font.ITALIC, 16)); // Aumentar el tamaño del texto del botón
        btnCancelar.setPreferredSize(
                new Dimension(150, 40)); // Establecer el tamaño preferido del botón   
        // Crear un panel para contener el botón y utilizar FlowLayout para centrarlo
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnCancelar);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si se ha seleccionado un registro en el JComboBox
                if (cmbIDReserva.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "No hay registros seleccionados");
                    return;
                }

                String id_reserva = cmbIDReserva.getSelectedItem().toString();
                // Crear la conexión a la base de datos
                String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                        + "database=AnimalTripOficial;"
                        + "user=sa;"
                        + "password=hola123;"
                        + "loginTimeout=30;";

                try {
                    
                    Connection conn = DriverManager.getConnection(conexionUrl);

                    // Ejecutar la sentencia de eliminación de la reserva
                    String deleteSql = "DELETE FROM dbo.Reserva WHERE documento_cliente = ? AND id_reserva = ?";
                    PreparedStatement deleteStatement = conn.prepareStatement(deleteSql);
                    deleteStatement.setString(1, documento_cliente);
                    deleteStatement.setString(2, id_reserva);
                    deleteStatement.executeUpdate();
                    deleteStatement.close();

                    // Ejecutar el trigger después de la eliminación
                    String triggerSql = "EXEC actualizar_estado_ruta";
                    Statement triggerStatement = conn.createStatement();
                    triggerStatement.executeUpdate(triggerSql);
                    triggerStatement.close();

                    conn.close();
                    JOptionPane.showMessageDialog(null, "Reserva cancelada exitosamente");
                    cmbIDReserva.setSelectedIndex(0);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Reserva cancelada exitosamente");
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
        BorrarReservacion example = new BorrarReservacion("Nombre del Cliente", "Documento del Cliente");
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
            java.util.logging.Logger.getLogger(BorrarReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BorrarReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BorrarReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BorrarReservacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BorrarReservacion("Nombre del Cliente", "Documento del Cliente").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
