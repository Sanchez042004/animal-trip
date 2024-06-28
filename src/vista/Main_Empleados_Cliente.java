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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import static vista.Login_Empleado.username;


public class Main_Empleados_Cliente extends javax.swing.JFrame {
    private JTextField txtDocumento;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JComboBox<String> cmbTipoDocumento;
    private JPasswordField txtContraseña;
    private JButton btnRegistrar;
    private JButton btnRegresar1;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    private JButton btnRegresar2;
    private JTextField txtDocumento1;
    private JComboBox<String> comboBoxColumnas;
    private JTextField txtActualizacion;
    private JButton btnActualizar1;
    private JButton btnRegresar3;
    private JTextField txtDocumento2;
    private JButton btnEliminar1;
    private JButton btnRegresar4;

    
    public Main_Empleados_Cliente() {
        
        setTitle("CRUD Cliente");
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
       
        JButton btnInsertar = new JButton("Insertar cliente");
        JButton btnVer = new JButton("Ver clientes");
        JButton btnActualizar = new JButton("Actualizar clientes");
        JButton btnEliminar = new JButton("Eliminar clientes");
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
                // Limpiar panel
                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                JLabel lblDocumento = new JLabel("Documento:");
                JLabel lblNombre = new JLabel("Nombre:");
                JLabel lblDireccion = new JLabel("Dirección:");
                JLabel lblTelefono = new JLabel("Teléfono:");
                JLabel lblTipoDocumento = new JLabel("Tipo de Documento:");
                JLabel lblContraseña = new JLabel("Contraseña:");

                // Campos de texto
                txtDocumento = new JTextField(20);
                txtNombre = new JTextField(20);
                txtDireccion = new JTextField(20);
                txtTelefono = new JTextField(20);
                txtContraseña = new JPasswordField(20);

                // ComboBox de tipo de documento
                String[] tiposDocumento = {"1", "2", "3", "4", "5"};
                cmbTipoDocumento = new JComboBox<>(tiposDocumento);

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

                lblDocumento.setForeground(Color.BLACK);
                lblNombre.setForeground(Color.RED);
                lblDireccion.setForeground(Color.BLACK);
                lblTelefono.setForeground(Color.GREEN);
                lblTipoDocumento.setForeground(Color.BLUE);
                lblContraseña.setForeground(Color.BLACK);

                lblDocumento.setBounds(x, y, labelWidth, labelHeight);
                txtDocumento.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblNombre.setBounds(x, y, labelWidth, labelHeight);
                txtNombre.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblDireccion.setBounds(x, y, labelWidth, labelHeight);
                txtDireccion.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblTelefono.setBounds(x, y, labelWidth, labelHeight);
                txtTelefono.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblTipoDocumento.setBounds(x, y, labelWidth, labelHeight);
                cmbTipoDocumento.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblContraseña.setBounds(x, y, labelWidth, labelHeight);
                txtContraseña.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                btnRegistrar.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                btnRegresar1.setBounds(x + labelWidth, y + verticalSpacing, fieldWidth, fieldHeight);

                // Agregar componentes al panel
                panel.add(lblDocumento);
                panel.add(txtDocumento);
                panel.add(lblNombre);
                panel.add(txtNombre);
                panel.add(lblDireccion);
                panel.add(txtDireccion);
                panel.add(lblTelefono);
                panel.add(txtTelefono);
                panel.add(lblTipoDocumento);
                panel.add(cmbTipoDocumento);
                panel.add(lblContraseña);
                panel.add(txtContraseña);
                panel.add(btnRegistrar);
                panel.add(btnRegresar1);

                panel.add(backgroundLabel);
                // Filtro de documento solo para números - documento
                PlainDocument docDocumento = new PlainDocument();
                docDocumento.setDocumentFilter(new DocumentFilter() {
                    @Override
                    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
                            throws BadLocationException {
                        fb.insertString(offset, string.replaceAll("\\D", ""), attr); // Remover caracteres no numéricos
                    }

                    @Override
                    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                            throws BadLocationException {
                        fb.replace(offset, length, text.replaceAll("\\D", ""), attrs); // Remover caracteres no numéricos
                    }
                });
                txtDocumento.setDocument(docDocumento);
                // Filtro de documento solo para números - telefono
                PlainDocument docTelefono = new PlainDocument();
                docTelefono.setDocumentFilter(new DocumentFilter() {
                    @Override
                    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
                            throws BadLocationException {
                        fb.insertString(offset, string.replaceAll("\\D", ""), attr); // Remover caracteres no numéricos
                    }

                    @Override
                    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                            throws BadLocationException {
                        fb.replace(offset, length, text.replaceAll("\\D", ""), attrs); // Remover caracteres no numéricos
                    }
                });
                txtTelefono.setDocument(docTelefono);

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
                        String documento_cliente = txtDocumento.getText();
                        String nombre_cliente = txtNombre.getText();
                        String direccion_cliente = txtDireccion.getText();
                        String telefono_cliente = txtTelefono.getText();
                        String tipo_documento = cmbTipoDocumento.getSelectedItem().toString();
                        String contraseña_cliente = new String(txtContraseña.getPassword()); // Obtener la contraseña como texto

                        // Verificar si algún campo está vacío
                        if (documento_cliente.isEmpty() || nombre_cliente.isEmpty() || direccion_cliente.isEmpty()
                                || telefono_cliente.isEmpty() || contraseña_cliente.isEmpty()) {
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
                            String sql = "INSERT INTO dbo.Cliente (documento_cliente, nombre_cliente, direccion_cliente, telefono_cliente, tipo_documento, contraseña_cliente) VALUES (?, ?, ?, ?, ?, ?)";
                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setString(1, documento_cliente);
                            statement.setString(2, nombre_cliente);
                            statement.setString(3, direccion_cliente);
                            statement.setString(4, telefono_cliente);
                            statement.setString(5, tipo_documento);
                            statement.setString(6, contraseña_cliente);

                            // Ejecutar la sentencia SQL
                            statement.executeUpdate();

                            // Cerrar la conexión y mostrar un mensaje de éxito
                            statement.close();
                            conn.close();
                            JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente");

                            // Limpiar los campos de texto después de registrar
                            txtDocumento.setText("");
                            txtNombre.setText("");
                            txtDireccion.setText("");
                            txtTelefono.setText("");
                            txtContraseña.setText("");

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al registrar el cliente");
                        }
                    }
                });
                btnRegresar1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Main_Empleados_Cliente cliente = new Main_Empleados_Cliente();
                        cliente.setVisible(true);
                        dispose();

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
                tablaClientes = new JTable(modeloTabla);
                JScrollPane scrollPane = new JScrollPane(tablaClientes);
                getContentPane().add(scrollPane);
                getContentPane().add(panel);
                modeloTabla.addColumn("Documento");
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Dirección");
                modeloTabla.addColumn("Teléfono");
                modeloTabla.addColumn("Tipo de Documento");
                modeloTabla.addColumn("Contraseña");
                btnRegresar2 = new JButton("Regresar");
                panel.setLayout(new BorderLayout());
                panel.add(scrollPane, BorderLayout.CENTER);
                panel.add(btnRegresar2, BorderLayout.SOUTH);
                String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                        + "database=AnimalTripOficial;"
                        + "user=sa;"
                        + "password=hola123;"
                        + "loginTimeout=30;";

                try {
                    Connection conn = DriverManager.getConnection(conexionUrl);

                    String sql = "SELECT * FROM dbo.Cliente";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);

                    // Borra los registros existentes en la tabla
                    modeloTabla.setRowCount(0);

                    // Agrega los registros de la base de datos a la tabla
                    while (resultSet.next()) {
                        String documento_cliente = resultSet.getString("documento_cliente");
                        String nombre_cliente = resultSet.getString("nombre_cliente");
                        String direccion_cliente = resultSet.getString("direccion_cliente");
                        String telefono_cliente = resultSet.getString("telefono_cliente");
                        String tipo_documento = resultSet.getString("tipo_documento");
                        String contraseña_cliente = resultSet.getString("contraseña_cliente");

                        Object[] fila = {documento_cliente, nombre_cliente, direccion_cliente, telefono_cliente, tipo_documento, contraseña_cliente};
                        modeloTabla.addRow(fila);
                    }

                    statement.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al obtener los registros de clientes");
                }
                btnRegresar2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                       Main_Empleados_Cliente cliente = new Main_Empleados_Cliente();
                        cliente.setVisible(true);
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
                JLabel lblDocumento1 = new JLabel("Documento:");
                lblDocumento1.setBounds(10, 100, 100, 25);
                txtDocumento1 = new JTextField(20);
                txtDocumento1.setBounds(120, 100, 150, 25);

                JLabel lblColumna = new JLabel("Columna a actualizar:");
                lblColumna.setBounds(10, 20, 100, 25);

                String[] columnas = {"nombre_cliente", "direccion_cliente", "telefono_cliente", "tipo_documento", "contraseña_cliente"};
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

                panel.add(lblDocumento1);
                panel.add(txtDocumento1);
                panel.add(lblColumna);
                panel.add(comboBoxColumnas);
                panel.add(lblActualizacion);
                panel.add(txtActualizacion);
                panel.add(btnActualizar1);
                panel.add(btnRegresar3);
                panel.add(backgroundLabel);

                btnActualizar1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String columnaSeleccionada = (String) comboBoxColumnas.getSelectedItem();
                        String actualizacion = txtActualizacion.getText();
                        String documento_cliente = txtDocumento1.getText();

                        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                                + "database=AnimalTripOficial;"
                                + "user=sa;"
                                + "password=hola123;"
                                + "loginTimeout=30;";

                        try {
                            Connection conn = DriverManager.getConnection(conexionUrl);
                            String sql = "UPDATE dbo.Cliente SET " + columnaSeleccionada + " = ? WHERE documento_cliente = ?";

                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setString(1, actualizacion);
                            statement.setString(2, documento_cliente);

                            // Ejecutar la sentencia SQL
                            int filasActualizadas = statement.executeUpdate();

                            statement.close();
                            conn.close();

                            if (filasActualizadas > 0) {
                                JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con el documento especificado");
                            }

                            // Limpiar los campos de texto después de actualizar
                            txtActualizacion.setText("");
                            txtDocumento1.setText("");

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente");
                        }
                    }
                });
                btnRegresar3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                       Main_Empleados_Cliente cliente = new Main_Empleados_Cliente();
                        cliente.setVisible(true);
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
                JLabel lblDocumento2 = new JLabel("Documento:");
                txtDocumento2 = new JTextField(10);
                btnEliminar1 = new JButton("Eliminar");
                btnRegresar4 = new JButton("Regresar");
                int x = 50;
                int y = 50;
                int labelWidth = 120;
                int labelHeight = 25;
                int fieldWidth = 200;
                int fieldHeight = 25;
                int verticalSpacing = 30;

                lblDocumento2.setForeground(Color.RED);
                lblDocumento2.setBounds(x, y, labelWidth, labelHeight);
                txtDocumento2.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                btnEliminar1.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                btnRegresar4.setBounds(x + labelWidth, y + verticalSpacing, fieldWidth, fieldHeight);
                panel.add(lblDocumento2);
                panel.add(txtDocumento2);
                panel.add(btnEliminar1);
                panel.add(btnRegresar4);
                panel.add(backgroundLabel);
                getContentPane().add(panel);

                btnEliminar1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String documentoCliente = txtDocumento2.getText();

                        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                                + "database=AnimalTripOficial;"
                                + "user=sa;"
                                + "password=hola123;"
                                + "loginTimeout=30;";

                        try {
                            Connection conn = DriverManager.getConnection(conexionUrl);

                            // Preparar la sentencia SQL con el valor del documento a eliminar
                            String sql = "DELETE FROM dbo.Cliente WHERE documento_cliente = ?";
                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setString(1, documentoCliente);

                            // Ejecutar la sentencia SQL
                            int rowsAffected = statement.executeUpdate();

                            // Verificar si se eliminó correctamente el registro
                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con el documento especificado");
                            }

                            statement.close();
                            conn.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente");
                        }
                    }
                });
                btnRegresar4.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Main_Empleados_Cliente cliente = new Main_Empleados_Cliente();
                                cliente.setVisible(true);
                                dispose();

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
            java.util.logging.Logger.getLogger(Main_Empleados_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Empleados_Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
