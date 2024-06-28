package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static vista.Login_Empleado.username;

public class Main_Empleados_Destino extends javax.swing.JFrame {

    private JTextField txtNombre;
    private JTextField txtId_ruta;
    private JButton btnRegistrar;
    private JButton btnRegresar1;
    private JTable tablaDestino;
    private DefaultTableModel modeloTabla;
    private JButton btnRegresar2;
    private JTextField txtId_destino;
    private JComboBox<String> comboBoxColumnas;
    private JTextField txtActualizacion;
    private JButton btnActualizar1;
    private JButton btnRegresar3;
    private JTextField txtDocumento;
    private JButton btnEliminar1;
    private JButton btnRegresar4;
    
    public Main_Empleados_Destino() {
        setTitle("CRUD Destino");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(517, 540);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        
        ImageIcon backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("imagenes/fondo-empleado.png"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        //panel.add(backgroundLabel);
        
        panel.setLayout(null);
        //initComponents();
       
        JButton btnInsertar = new JButton("Insertar destino");
        JButton btnVer = new JButton("Ver destinos");
        JButton btnActualizar = new JButton("Actualizar destino");
        JButton btnEliminar = new JButton("Eliminar destino");
        JButton btnRegresar = new JButton("Regresar");
        btnInsertar.setBounds(50, 50, 180, 30);
        btnVer.setBounds(50, 90, 180, 30);
        btnActualizar.setBounds(50, 130, 180, 30);
        btnEliminar.setBounds(50, 170, 180, 30);
        btnRegresar.setBounds(50, 210, 180, 30);
        
        panel.add(btnInsertar);
        panel.add(btnVer);
        panel.add(btnActualizar);
        panel.add(btnEliminar);
        panel.add(btnRegresar);
        panel.add(backgroundLabel);
        
        btnInsertar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                JLabel lblNombre = new JLabel("Nombre:");
                JLabel lblId_ruta = new JLabel("Id ruta:");
                // Campos de texto
                txtNombre = new JTextField(20);
                txtId_ruta = new JTextField(20);
                // Botón de registrar
                btnRegistrar = new JButton("Registrar");
                btnRegresar1 = new JButton("Regresar");
                int x = 50;
                int y = 50;
                int labelWidth = 120;
                int labelHeight = 25;
                int fieldWidth = 200;
                int fieldHeight = 25;
                int verticalSpacing = 30;
                lblNombre.setForeground(Color.RED);
                lblId_ruta.setForeground(Color.BLACK);
                lblNombre.setBounds(x, y, labelWidth, labelHeight);
                txtNombre.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;
                lblId_ruta.setBounds(x, y, labelWidth, labelHeight);
                txtId_ruta.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                btnRegistrar.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                btnRegresar1.setBounds(x + labelWidth, y + verticalSpacing, fieldWidth, fieldHeight);
                panel.add(lblNombre);
                panel.add(txtNombre);
                panel.add(lblId_ruta);
                panel.add(txtId_ruta);
                panel.add(btnRegistrar);
                panel.add(btnRegresar1);
                panel.add(backgroundLabel);

                btnRegistrar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Obtener los valores de los campos de texto
                        String nombre_destino = txtNombre.getText();
                        String id_ruta = txtId_ruta.getText();
                        // Crear la conexión a la base de datos
                        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                                + "database=animalTrip11;"
                                + "user=sa;"
                                + "password=hola123;"
                                + "loginTimeout=30;";

                        try {
                            Connection conn = DriverManager.getConnection(conexionUrl);

                            // Preparar la sentencia SQL con los valores capturados del formulario
                            String sql = "INSERT INTO dbo.Destino (nombre_destino, id_ruta) VALUES (?, ?)";
                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setString(1, nombre_destino);
                            statement.setString(2, id_ruta);
                            // Ejecutar la sentencia SQL
                            statement.executeUpdate();

                            // Cerrar la conexión y mostrar un mensaje de éxito
                            statement.close();
                            conn.close();
                            JOptionPane.showMessageDialog(null, "Ruta registrado exitosamente");

                            // Limpiar los campos de texto después de registrar
                            txtNombre.setText("");
                            txtId_ruta.setText("");

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al registrar la ruta");
                        }
                    }
                });
                btnRegresar1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Main_Empleados_Destino destino = new Main_Empleados_Destino();
                        destino.setVisible(true);
                        dispose(); // Cierra la ventana actual
                    }
                });

            }
        });
        
        btnVer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                modeloTabla = new DefaultTableModel();
                tablaDestino = new JTable(modeloTabla);
                JScrollPane scrollPane = new JScrollPane(tablaDestino);
                getContentPane().add(scrollPane);
                getContentPane().add(panel);
                // Agrega las columnas a la tabla
                modeloTabla.addColumn("Id_destino");
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Id_ruta");
                btnRegresar2 = new JButton("Regresar");
                panel.setLayout(new BorderLayout());
                panel.add(scrollPane, BorderLayout.CENTER);
                panel.add(btnRegresar2, BorderLayout.SOUTH);
                String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                        + "database=animalTrip11;"
                        + "user=sa;"
                        + "password=hola123;"
                        + "loginTimeout=30;";

                try {
                    Connection conn = DriverManager.getConnection(conexionUrl);

                    String sql = "SELECT * FROM dbo.Destino";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);

                    // Borra los registros existentes en la tabla
                    modeloTabla.setRowCount(0);

                    // Agrega los registros de la base de datos a la tabla
                    while (resultSet.next()) {
                        String id_destino = resultSet.getString("id_destino");
                        String nombre_destino = resultSet.getString("nombre_destino");
                        String id_ruta = resultSet.getString("id_ruta");
                        Object[] fila = {id_destino, nombre_destino, id_ruta};
                        modeloTabla.addRow(fila);
                    }

                    statement.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al obtener los registros de los destinos");
                }
                btnRegresar2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Main_Empleados_Destino destino = new Main_Empleados_Destino();
                        destino.setVisible(true);
                        dispose(); // Cierra la ventana actual
                    }
                });
            }
        });
        
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                JLabel lblId_destino = new JLabel("Id destino:");
                lblId_destino.setBounds(10, 100, 100, 25);
                txtId_destino = new JTextField(20);
                txtId_destino.setBounds(120, 100, 150, 25);

                JLabel lblColumna = new JLabel("Columna a actualizar:");
                lblColumna.setBounds(10, 20, 100, 25);

                String[] columnas = {"nombre_destino", "id_ruta"};
                comboBoxColumnas = new JComboBox<>(columnas);
                comboBoxColumnas.setBounds(120, 20, 150, 25);

                JLabel lblActualizacion = new JLabel("Actualización:");
                lblActualizacion.setBounds(10, 60, 100, 25);

                txtActualizacion = new JTextField();
                txtActualizacion.setBounds(120, 60, 150, 25);

                btnActualizar1 = new JButton("Actualizar");
                btnActualizar1.setBounds(120, 140, 100, 30);

                btnRegresar3 = new JButton("Regresar");
                btnRegresar3.setBounds(120, 180, 100, 30);

                panel.add(lblId_destino);
                panel.add(txtId_destino);
                panel.add(lblColumna);
                panel.add(comboBoxColumnas);
                panel.add(lblActualizacion);
                panel.add(txtActualizacion);
                panel.add(btnActualizar1);
                panel.add(btnRegresar3);
                panel.add(backgroundLabel);

                btnActualizar1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String id_destino = txtId_destino.getText();
                        String columnaSeleccionada = (String) comboBoxColumnas.getSelectedItem();
                        String actualizacion = txtActualizacion.getText();
                        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                                + "database=animalTrip11;"
                                + "user=sa;"
                                + "password=hola123;"
                                + "loginTimeout=30;";

                        try {
                            Connection conn = DriverManager.getConnection(conexionUrl);
                            String sql = "UPDATE dbo.Destino SET " + columnaSeleccionada + " = ? WHERE id_destino = ?";

                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setString(1, actualizacion);
                            statement.setString(2, id_destino);

                            // Ejecutar la sentencia SQL
                            int filasActualizadas = statement.executeUpdate();

                            statement.close();
                            conn.close();

                            if (filasActualizadas > 0) {
                                JOptionPane.showMessageDialog(null, "Ruta actualizada exitosamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se encontró ningún destino con el id especificado");
                            }

                            // Limpiar los campos de texto después de actualizar
                            txtActualizacion.setText("");
                            txtId_destino.setText("");

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al actualizar la ruta");
                        }
                    }
                });
                btnRegresar3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Main_Empleados_Destino destino = new Main_Empleados_Destino();
                        destino.setVisible(true);
                        dispose(); // Cierra la ventana actual
                    }
                });
            }
        });
        
        
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                JLabel lblDocumento = new JLabel("Id destino:");
                txtDocumento = new JTextField(10);
                btnEliminar1 = new JButton("Eliminar");
                btnRegresar4 = new JButton("Regresar");
                int x = 50;
                int y = 50;
                int labelWidth = 120;
                int labelHeight = 25;
                int fieldWidth = 200;
                int fieldHeight = 25;
                int verticalSpacing = 30;

                lblDocumento.setForeground(Color.RED);
                lblDocumento.setBounds(x, y, labelWidth, labelHeight);
                txtDocumento.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                btnEliminar1.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                btnRegresar4.setBounds(x + labelWidth, y + verticalSpacing, fieldWidth, fieldHeight);

                panel.add(lblDocumento);
                panel.add(txtDocumento);
                panel.add(btnEliminar1);
                panel.add(btnRegresar4);
                panel.add(backgroundLabel);
                getContentPane().add(panel);

                btnEliminar1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String id_destino = txtDocumento.getText();

                        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                                + "database=animalTrip11;"
                                + "user=sa;"
                                + "password=hola123;"
                                + "loginTimeout=30;";

                        try {
                            Connection conn = DriverManager.getConnection(conexionUrl);

                            // Preparar la sentencia SQL con el valor del documento a eliminar
                            String sql = "DELETE FROM dbo.Destino WHERE id_destino = ?";
                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setString(1, id_destino);

                            // Ejecutar la sentencia SQL
                            int rowsAffected = statement.executeUpdate();

                            // Verificar si se eliminó correctamente el registro
                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Destino eliminado exitosamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se encontró ningún destino con el id especificado");
                            }

                            statement.close();
                            conn.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al eliminar el destino");
                        }
                    }
                });
                btnRegresar4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Main_Empleados_Destino destino = new Main_Empleados_Destino();
                        destino.setVisible(true);
                        dispose(); // Cierra la ventana actual
                    }
                });
            }
        });
        
        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main_Empleados menu= new Main_Empleados(username);
                menu.setVisible(true);
                dispose();

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
            java.util.logging.Logger.getLogger(Main_Empleados_Destino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Destino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Destino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Destino.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Empleados_Destino().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
