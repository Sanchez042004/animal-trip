package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import modelo.Conexion;

public class ReadMascotas extends javax.swing.JFrame {

    private JTable table;
    private JButton btnRegresar;
    private JLabel lblCliente;
    private JLabel lblCliente1;

    public ReadMascotas(String nombre_cliente, String documento_cliente) {
        setTitle("Tus mascotas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.lightGray);
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
        model.addColumn("id_mascota");
        model.addColumn("nombre_mascota");
        model.addColumn("edad_mascota");
        model.addColumn("sexo_mascota");
        model.addColumn("peso_mascota");
        model.addColumn("carnet_vacuna");
        model.addColumn("id_raza");
        model.addColumn("id_tipo_mascota");

        // Crear el JTable con el modelo de tabla
        table = new JTable(model);

        // Agregar el JTable a un JScrollPane para permitir desplazamiento
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        Conexion databaseConnector = new Conexion();
        Connection connection = databaseConnector.getConexion();

        // Realizar la consulta SQL
        try {
            String query = "SELECT * FROM dbo.Mascota WHERE documento_cliente = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, documento_cliente);
            ResultSet resultSet = statement.executeQuery();

            // Recorrer los registros obtenidos
            while (resultSet.next()) {
                // Obtener los valores de cada columna en el registro actual
                String id_mascota = resultSet.getString("id_mascota");
                String nombre_mascota = resultSet.getString("nombre_mascota");
                String edad_mascota = resultSet.getString("edad_mascota");
                String sexo_mascota = resultSet.getString("sexo_mascota");
                String peso_mascota = resultSet.getString("peso_mascota");
                String carnet_vacuna = resultSet.getString("carnet_vacuna");
                String id_raza = resultSet.getString("id_raza");
                String id_tipo_mascota = resultSet.getString("id_tipo_mascota");

                // Crear un vector para almacenar los datos de cada fila
                Vector<String> row = new Vector<>();
                row.add(id_mascota);
                row.add(nombre_mascota);
                row.add(edad_mascota);
                row.add(sexo_mascota);
                row.add(peso_mascota);
                row.add(carnet_vacuna);
                row.add(id_raza);
                row.add(id_tipo_mascota);

                // Agregar la fila al modelo de tabla
                model.addRow(row);
            }

            // Cerrar recursos
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Botones para realizar acciones
        btnRegresar = new JButton("Regresar");
        btnRegresar.setFont(btnRegresar.getFont().deriveFont(Font.ITALIC, 16)); // Aumentar el tamaño del texto del botón
        btnRegresar.setPreferredSize(new Dimension(120, 40)); // Establecer el tamaño preferido del botón

// Crear un panel para contener el botón y utilizar FlowLayout para centrarlo
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
            .addGap(0, 739, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientMenu("Nombre del Cliente", "Documento del Cliente").setVisible(true);
            }
        });
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
            java.util.logging.Logger.getLogger(ClientMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        // Crear una instancia de la clase Conexion para conectarse a la base de datos
        Conexion databaseConnector = new Conexion();
        databaseConnector.getConexion();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
