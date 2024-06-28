package vista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static vista.Login_Empleado.username;

public class Main_Empleados_Profesional extends javax.swing.JFrame {

    private JTextField txtNombre;
    private JButton btnRegistrar;
    private JButton btnRegresar;
    private JButton btnRegresar1;
    private JTable tablaProfesional;
    private DefaultTableModel modeloTabla;
    private JButton btnRegresar2;
    private JTextField txtDocumento;
    private JButton btnEliminar1;
    private JButton btnRegresar3;
    
    public Main_Empleados_Profesional() {
        setTitle("CRUD Profesional");
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
       
        JButton btnInsertar = new JButton("Insertar profesional");
        JButton btnVer = new JButton("Ver profesionales");
        //JButton btnActualizar = new JButton("Actualizar conductor");
        JButton btnEliminar = new JButton("Eliminar profesional");
        JButton btnRegresar = new JButton("Regresar");
        btnInsertar.setBounds(50, 50, 180, 30);
        btnVer.setBounds(50, 90, 180, 30);
        //btnActualizar.setBounds(50, 130, 180, 30);
        btnEliminar.setBounds(50, 130, 180, 30);
        btnRegresar.setBounds(50, 170, 180, 30);
        
        panel.add(btnInsertar);
        panel.add(btnVer);
        //panel.add(btnActualizar);
        panel.add(btnEliminar);
        panel.add(btnRegresar);
        

        panel.add(backgroundLabel);
        
                btnInsertar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Limpiar panel
                        panel.removeAll();
                        panel.revalidate();
                        panel.repaint();
                        // Crear componentes para la inserción
                        JLabel lblNombre = new JLabel("Nombre:");
                        txtNombre = new JTextField(20);
                        btnRegistrar = new JButton("Registrar");
                        btnRegresar1 = new JButton("Regresar");

                        // Posicionamiento de componentes
                        int x = 50;
                        int y = 50;
                        int labelWidth = 120;
                        int labelHeight = 25;
                        int fieldWidth = 200;
                        int fieldHeight = 25;
                        int verticalSpacing = 30;

                        lblNombre.setForeground(Color.RED);
                        lblNombre.setBounds(x, y, labelWidth, labelHeight);
                        txtNombre.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                        y += verticalSpacing;

                        btnRegistrar.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                        btnRegresar1.setBounds(x + labelWidth, y + verticalSpacing, fieldWidth, fieldHeight);

                        // Agregar componentes al panel
                        panel.add(lblNombre);
                        panel.add(txtNombre);
                        panel.add(btnRegistrar);
                        panel.add(btnRegresar1);
                        panel.add(backgroundLabel);

                        btnRegistrar.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                // Obtener los valores de los campos de texto
                                String nombre_profesional = txtNombre.getText();
                                String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                                        + "database=animalTrip11;"
                                        + "user=sa;"
                                        + "password=hola123;"
                                        + "loginTimeout=30;";

                                try {
                                    Connection conn = DriverManager.getConnection(conexionUrl);

                                    // Preparar la sentencia SQL con los valores capturados del formulario
                                    String sql = "INSERT INTO dbo.Profesional (nombre_profesional) VALUES (?)";
                                    PreparedStatement statement = conn.prepareStatement(sql);
                                    statement.setString(1, nombre_profesional);

                                    // Ejecutar la sentencia SQL
                                    statement.executeUpdate();

                                    // Cerrar la conexión y mostrar un mensaje de éxito
                                    statement.close();
                                    conn.close();
                                    JOptionPane.showMessageDialog(null, "Profesional registrado exitosamente");

                                    // Limpiar los campos de texto después de registrar
                                    txtNombre.setText("");

                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                    JOptionPane.showMessageDialog(null, "Error al registrar el profesional");
                                }

                            }
                        });
                        
                        btnRegresar1.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Main_Empleados_Profesional profesional = new Main_Empleados_Profesional();
                                profesional.setVisible(true);
                                dispose(); // Cierra la ventana actual
                            }
                        });
                    }
                });
                        btnVer.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                // Limpiar panel
                                panel.removeAll();
                                panel.revalidate();
                                panel.repaint();
                                modeloTabla = new DefaultTableModel();
                                tablaProfesional = new JTable(modeloTabla);
                                JScrollPane scrollPane = new JScrollPane(tablaProfesional);
                                getContentPane().add(scrollPane);
                                getContentPane().add(panel);
                                // Agrega las columnas a la tabla
                                modeloTabla.addColumn("Id_profesional");
                                modeloTabla.addColumn("Nombre");
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

                                    String sql = "SELECT * FROM dbo.Profesional";
                                    Statement statement = conn.createStatement();
                                    ResultSet resultSet = statement.executeQuery(sql);

                                    // Borra los registros existentes en la tabla
                                    modeloTabla.setRowCount(0);

                                    // Agrega los registros de la base de datos a la tabla
                                    while (resultSet.next()) {
                                        String id_profesional = resultSet.getString("id_profesional");
                                        String nombre_profesional = resultSet.getString("nombre_profesional");

                                        Object[] fila = {id_profesional, nombre_profesional};
                                        modeloTabla.addRow(fila);
                                    }

                                    statement.close();
                                    conn.close();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                    JOptionPane.showMessageDialog(null, "Error al obtener los registros de los profesionales");
                                }
                                
                                btnRegresar2.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Main_Empleados_Profesional profesional = new Main_Empleados_Profesional();
                                profesional.setVisible(true);
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
                                
                                JLabel lblDocumento = new JLabel("Id profesional:");
                                txtDocumento = new JTextField(10);
                                btnEliminar1 = new JButton("Eliminar");
                                btnRegresar3 = new JButton("Regresar");
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
                                btnRegresar3.setBounds(x + labelWidth, y + verticalSpacing, fieldWidth, fieldHeight);

                                panel.add(lblDocumento);
                                panel.add(txtDocumento);
                                panel.add(btnEliminar1);
                                panel.add(btnRegresar3);
                                panel.add(backgroundLabel);
                                getContentPane().add(panel);
                                
                                btnEliminar1.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        String id_profesional = txtDocumento.getText();

                                        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                                                + "database=animalTrip11;"
                                                + "user=sa;"
                                                + "password=hola123;"
                                                + "loginTimeout=30;";

                        try {
                            Connection conn = DriverManager.getConnection(conexionUrl);

                            // Preparar la sentencia SQL con el valor del documento a eliminar
                            String sql = "DELETE FROM dbo.Profesional WHERE id_profesional = ?";
                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setString(1, id_profesional);

                            // Ejecutar la sentencia SQL
                            int rowsAffected = statement.executeUpdate();

                            // Verificar si se eliminó correctamente el registro
                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Profesional eliminado exitosamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se encontró ningún profesional con el id especificado");
                            }

                            statement.close();
                            conn.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al eliminar el profesional");
                        }
                    }
                });

                   btnRegresar3.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Main_Empleados_Profesional profesional = new Main_Empleados_Profesional();
                                profesional.setVisible(true);
                                dispose();

                            }
                        });             
            }
        });

                        btnRegresar.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Main_Empleados menu = new Main_Empleados(username);
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
            java.util.logging.Logger.getLogger(Main_Empleados_Profesional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Profesional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Profesional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Profesional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Empleados_Profesional().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
