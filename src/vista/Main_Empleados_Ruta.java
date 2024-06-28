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
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import static vista.Login_Empleado.username;

public class Main_Empleados_Ruta extends javax.swing.JFrame {

    private JTextField txtNombre;
    private JTextField txtProfesional;
    private JTextField txtConductor;
    private JTextField txtEstado;
    private JButton btnRegistrar;
    private JButton btnRegresar1;
    private JTable tablaRuta;
    private DefaultTableModel modeloTabla;
    private JButton btnRegresar2;
    private JTextField txtId_ruta;
    private JComboBox<String> comboBoxColumnas;
    private JTextField txtActualizacion;
    private JButton btnActualizar1;
    private JButton btnRegresar3;
    private JTextField txtDocumento;
    private JButton btnEliminar1;
    private JButton btnRegresar4;
    
    public Main_Empleados_Ruta() {
        setTitle("CRUD Ruta");
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
       
        JButton btnInsertar = new JButton("Insertar ruta");
        JButton btnVer = new JButton("Ver ruta");
        JButton btnActualizar = new JButton("Actualizar ruta");
        JButton btnEliminar = new JButton("Eliminar ruta");
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
                JLabel lblProfesional = new JLabel("Id profesional:");
                JLabel lblConductor = new JLabel("Id conductor:");
                JLabel lblEstado = new JLabel("Estado:");
                // Campos de texto
                txtNombre = new JTextField(20);
                txtProfesional = new JTextField(20);
                txtConductor = new JTextField(20);
                txtEstado = new JTextField(20);
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
                lblProfesional.setForeground(Color.BLACK);
                lblConductor.setForeground(Color.GREEN);
                lblEstado.setForeground(Color.BLUE);

                lblNombre.setBounds(x, y, labelWidth, labelHeight);
                txtNombre.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblProfesional.setBounds(x, y, labelWidth, labelHeight);
                txtProfesional.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblConductor.setBounds(x, y, labelWidth, labelHeight);
                txtConductor.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblEstado.setBounds(x, y, labelWidth, labelHeight);
                txtEstado.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                btnRegistrar.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                btnRegresar1.setBounds(x + labelWidth, y + verticalSpacing, fieldWidth, fieldHeight);
                panel.add(lblNombre);
                panel.add(txtNombre);
                panel.add(lblProfesional);
                panel.add(txtProfesional);
                panel.add(lblConductor);
                panel.add(txtConductor);
                panel.add(lblEstado);
                panel.add(txtEstado);
                panel.add(btnRegistrar);
                panel.add(btnRegresar1);
                panel.add(backgroundLabel);
                // Filtro de nombre solo para letras
                PlainDocument docNombre = new PlainDocument();
                docNombre.setDocumentFilter(new DocumentFilter() {
                    @Override
                    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
                            throws BadLocationException {
                        fb.insertString(offset, string.replaceAll("\\d", ""), attr); // Remover caracteres numéricos
                    }

                    @Override
                    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                            throws BadLocationException {
                        fb.replace(offset, length, text.replaceAll("\\d", ""), attrs); // Remover caracteres numéricos
                    }
                });
                txtNombre.setDocument(docNombre);
                
                btnRegistrar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Obtener los valores de los campos de texto
                        String nombre_ruta = txtNombre.getText();
                        String id_profesional = txtProfesional.getText();
                        String id_conductor = txtConductor.getText();
                        String estado = txtEstado.getText();

                        // Crear la conexión a la base de datos
                        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                                + "database=animalTrip11;"
                                + "user=sa;"
                                + "password=hola123;"
                                + "loginTimeout=30;";

                        try {
                            Connection conn = DriverManager.getConnection(conexionUrl);

                            // Preparar la sentencia SQL con los valores capturados del formulario
                            String sql = "INSERT INTO dbo.Ruta (nombre_ruta, id_profesional, id_conductor, estado) VALUES (?, ?, ?, ?)";
                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setString(1, nombre_ruta);
                            statement.setString(2, id_profesional);
                            statement.setString(3, id_conductor);
                            statement.setString(4, estado);

                            // Ejecutar la sentencia SQL
                            statement.executeUpdate();

                            // Cerrar la conexión y mostrar un mensaje de éxito
                            statement.close();
                            conn.close();
                            JOptionPane.showMessageDialog(null, "Ruta registrado exitosamente");

                            // Limpiar los campos de texto después de registrar
                            txtNombre.setText("");
                            txtProfesional.setText("");
                            txtConductor.setText("");
                            txtEstado.setText("");

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al registrar la ruta");
                        }
                    }
                });
                btnRegresar1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Main_Empleados_Ruta ruta = new Main_Empleados_Ruta();
                        ruta.setVisible(true);
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
                tablaRuta = new JTable(modeloTabla);
                JScrollPane scrollPane = new JScrollPane(tablaRuta);
                getContentPane().add(scrollPane);
                getContentPane().add(panel);
                // Agrega las columnas a la tabla
                modeloTabla.addColumn("Id_ruta");
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Id_profesional");
                modeloTabla.addColumn("Id_conductor");
                modeloTabla.addColumn("Estado");
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

                    String sql = "SELECT * FROM dbo.Ruta";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);

                    // Borra los registros existentes en la tabla
                    modeloTabla.setRowCount(0);

                    // Agrega los registros de la base de datos a la tabla
                    while (resultSet.next()) {
                        String id_ruta = resultSet.getString("id_ruta");
                        String nombre_ruta = resultSet.getString("nombre_ruta");
                        String id_profesional = resultSet.getString("id_profesional");
                        String id_conductor = resultSet.getString("id_conductor");
                        String estado = resultSet.getString("estado");

                        Object[] fila = {id_ruta, nombre_ruta, id_profesional, id_conductor, estado};
                        modeloTabla.addRow(fila);
                    }

                    statement.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al obtener los registros de las rutas");
                }
                btnRegresar2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Main_Empleados_Ruta ruta = new Main_Empleados_Ruta();
                        ruta.setVisible(true);
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
                JLabel lblId_ruta = new JLabel("Id ruta:");
                lblId_ruta.setBounds(10, 100, 100, 25);
                txtId_ruta = new JTextField(20);
                txtId_ruta.setBounds(120, 100, 150, 25);

                JLabel lblColumna = new JLabel("Columna a actualizar:");
                lblColumna.setBounds(10, 20, 100, 25);

                String[] columnas = {"id_profesional", "id_conductor", "estado"};
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

                panel.add(lblId_ruta);
                panel.add(txtId_ruta);
                panel.add(lblColumna);
                panel.add(comboBoxColumnas);
                panel.add(lblActualizacion);
                panel.add(txtActualizacion);
                panel.add(btnActualizar1);
                panel.add(btnRegresar3);
                panel.add(backgroundLabel);

                btnActualizar1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String id_ruta = txtId_ruta.getText();
                        String columnaSeleccionada = (String) comboBoxColumnas.getSelectedItem();
                        String actualizacion = txtActualizacion.getText();
                        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                                + "database=animalTrip11;"
                                + "user=sa;"
                                + "password=hola123;"
                                + "loginTimeout=30;";

                        try {
                            Connection conn = DriverManager.getConnection(conexionUrl);
                            String sql = "UPDATE dbo.Ruta SET " + columnaSeleccionada + " = ? WHERE id_ruta = ?";

                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setString(1, actualizacion);
                            statement.setString(2, id_ruta);

                            // Ejecutar la sentencia SQL
                            int filasActualizadas = statement.executeUpdate();

                            statement.close();
                            conn.close();

                            if (filasActualizadas > 0) {
                                JOptionPane.showMessageDialog(null, "Ruta actualizada exitosamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se encontró ninguna ruta con el id especificado");
                            }

                            // Limpiar los campos de texto después de actualizar
                            txtActualizacion.setText("");
                            txtId_ruta.setText("");

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al actualizar la ruta");
                        }
                    }
                });
                btnRegresar3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Main_Empleados_Ruta ruta = new Main_Empleados_Ruta();
                        ruta.setVisible(true);
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
                JLabel lblDocumento = new JLabel("Id ruta:");
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
                        String id_ruta = txtDocumento.getText();

                        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                                + "database=animalTrip11;"
                                + "user=sa;"
                                + "password=hola123;"
                                + "loginTimeout=30;";

                        try {
                            Connection conn = DriverManager.getConnection(conexionUrl);

                            // Preparar la sentencia SQL con el valor del documento a eliminar
                            String sql = "DELETE FROM dbo.Ruta WHERE id_ruta = ?";
                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setString(1, id_ruta);

                            // Ejecutar la sentencia SQL
                            int rowsAffected = statement.executeUpdate();

                            // Verificar si se eliminó correctamente el registro
                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Ruta eliminado exitosamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se encontró ninguna ruta con el id especificado");
                            }

                            statement.close();
                            conn.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al eliminar la ruta");
                        }
                    }
                });
                btnRegresar4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Main_Empleados_Ruta ruta = new Main_Empleados_Ruta();
                        ruta.setVisible(true);
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
            java.util.logging.Logger.getLogger(Main_Empleados_Ruta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Ruta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Ruta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Ruta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Empleados_Ruta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
