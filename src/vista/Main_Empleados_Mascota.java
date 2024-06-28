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

public class Main_Empleados_Mascota extends javax.swing.JFrame {

    private JTextField txtNombreMascota;
    private JTextField txtEdadMascota;
    private JComboBox<String> cmbSexoMascota;
    private JTextField txtPesoMascota;
    private JComboBox<String> cmbCarnetVacuna;
    private JComboBox<String> cmbRaza;
    private JComboBox<String> cmbTipoMascota;
    private JTextField txtDocumento;
    private JButton btnRegistrar;
    private JButton btnRegresar1;
    private JTable tablaMascotas;
    private DefaultTableModel modeloTabla;
    private JButton btnRegresar2;
    private JTextField txtId_mascota;
    private JComboBox<String> comboBoxColumnas;
    private JTextField txtActualizacion;
    private JButton btnActualizar1;
    private JButton btnRegresar3;
    private JTextField txtId_mascota1;
    private JButton btnEliminar1;
    private JButton btnRegresar4;
    
    
    public Main_Empleados_Mascota() {
         setTitle("CRUD Mascota");
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
       
        JButton btnInsertar = new JButton("Insertar mascota");
        JButton btnVer = new JButton("Ver mascotas");
        JButton btnActualizar = new JButton("Actualizar mascotas");
        JButton btnEliminar = new JButton("Eliminar mascotas");
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
                JLabel lblEdad = new JLabel("Edad:");
                JLabel lblSexo = new JLabel("Sexo:");
                JLabel lblPeso = new JLabel("Peso:");
                JLabel lblCarnet = new JLabel("¿Está vacunado?:");
                JLabel lblRaza = new JLabel("Raza:");
                JLabel lblTipoMascota = new JLabel("Tipo mascota:");
                JLabel lblDocumento = new JLabel("Documento dueño:");
                // Campos de texto
                txtNombreMascota = new JTextField(20);
                txtEdadMascota = new JTextField(20);
                txtPesoMascota = new JTextField(20);
                txtDocumento = new JTextField(20);
                // ComboBox
                String[] sexos = {"M", "F"};
                cmbSexoMascota = new JComboBox<>(sexos);
                String[] vacunado = {"S", "N"};
                cmbCarnetVacuna = new JComboBox<>(vacunado);  // Cambio realizado aquí
                String[] razas = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
                cmbRaza = new JComboBox<>(razas);
                String[] tipoMascota = {"1", "2"};
                cmbTipoMascota = new JComboBox<>(tipoMascota);
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
                lblEdad.setForeground(Color.BLACK);
                lblSexo.setForeground(Color.GREEN);
                lblPeso.setForeground(Color.BLUE);
                lblCarnet.setForeground(Color.BLACK);
                lblRaza.setForeground(Color.BLUE);
                lblTipoMascota.setForeground(Color.RED);
                lblDocumento.setForeground(Color.GREEN);

                lblNombre.setBounds(x, y, labelWidth, labelHeight);
                txtNombreMascota.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblEdad.setBounds(x, y, labelWidth, labelHeight);
                txtEdadMascota.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblSexo.setBounds(x, y, labelWidth, labelHeight);
                cmbSexoMascota.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblPeso.setBounds(x, y, labelWidth, labelHeight);
                txtPesoMascota.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblCarnet.setBounds(x, y, labelWidth, labelHeight);
                cmbCarnetVacuna.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblRaza.setBounds(x, y, labelWidth, labelHeight);
                cmbRaza.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblTipoMascota.setBounds(x, y, labelWidth, labelHeight);
                cmbTipoMascota.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                lblDocumento.setBounds(x, y, labelWidth, labelHeight);
                txtDocumento.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                btnRegistrar.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                btnRegresar1.setBounds(x + labelWidth, y + verticalSpacing, fieldWidth, fieldHeight);
                panel.add(lblNombre);
                panel.add(txtNombreMascota);
                panel.add(lblEdad);
                panel.add(txtEdadMascota);
                panel.add(lblSexo);
                panel.add(cmbSexoMascota);
                panel.add(lblPeso);
                panel.add(txtPesoMascota);
                panel.add(lblCarnet);
                panel.add(cmbCarnetVacuna);
                panel.add(lblRaza);
                panel.add(cmbRaza);
                panel.add(lblTipoMascota);
                panel.add(cmbTipoMascota);
                panel.add(lblDocumento);
                panel.add(txtDocumento);
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
                txtNombreMascota.setDocument(docNombre);

                // Filtro de documento solo para números - telefono
                PlainDocument docNumeros = new PlainDocument();
                docNumeros.setDocumentFilter(new DocumentFilter() {
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
                txtEdadMascota.setDocument(docNumeros);

                // Filtro de documento solo para números - telefono
                PlainDocument docNumeros1 = new PlainDocument();
                docNumeros1.setDocumentFilter(new DocumentFilter() {
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
                txtPesoMascota.setDocument(docNumeros1);

                btnRegistrar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtener los valores de los campos de texto
                        String nombre_mascota = txtNombreMascota.getText();
                        String edad_mascota = txtEdadMascota.getText();
                        String sexo_mascota = cmbSexoMascota.getSelectedItem().toString();
                        String peso_mascota = txtPesoMascota.getText();
                        String carnet_vacuna = cmbCarnetVacuna.getSelectedItem().toString();
                        String id_raza = cmbRaza.getSelectedItem().toString();
                        String id_tipo_mascota = cmbTipoMascota.getSelectedItem().toString();
                        String documento_cliente = txtDocumento.getText();

                        // Verificar si algún campo está vacío
                        if (nombre_mascota.isEmpty() || edad_mascota.isEmpty() || sexo_mascota.isEmpty()
                                || peso_mascota.isEmpty() || carnet_vacuna.isEmpty() || id_raza.isEmpty()
                                || id_tipo_mascota.isEmpty() || documento_cliente.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
                            return; // Salir del método sin ejecutar la consulta SQL
                        }

                        // Crear la conexión a la base de datos
                        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                                + "database=animalTrip11;"
                                + "user=sa;"
                                + "password=hola123;"
                                + "loginTimeout=30;";

                        try {
                            Connection conn = DriverManager.getConnection(conexionUrl);

                            // Preparar la sentencia SQL con los valores capturados del formulario
                            String sql = "INSERT INTO dbo.Mascota (nombre_mascota, edad_mascota, sexo_mascota, peso_mascota, carnet_vacuna, id_raza, documento_cliente, id_tipo_mascota) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setString(1, nombre_mascota);
                            statement.setString(2, edad_mascota);
                            statement.setString(3, sexo_mascota);
                            statement.setString(4, peso_mascota);
                            statement.setString(5, carnet_vacuna);
                            statement.setString(6, id_raza);
                            statement.setString(7, documento_cliente);
                            statement.setString(8, id_tipo_mascota);
                            // Ejecutar la sentencia SQL
                            statement.executeUpdate();

                            // Cerrar la conexión y mostrar un mensaje de éxito
                            statement.close();
                            conn.close();
                            JOptionPane.showMessageDialog(null, "Mascota registrada exitosamente");

                            // Limpiar los campos de texto después de registrar
                            txtNombreMascota.setText("");
                            txtEdadMascota.setText("");
                            txtPesoMascota.setText("");
                            txtDocumento.setText("");

                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Error al registrar la mascota");
                        }
                    }
                });
                btnRegresar1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Main_Empleados_Mascota mascota = new Main_Empleados_Mascota();
                        mascota.setVisible(true);
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
                tablaMascotas = new JTable(modeloTabla);
                JScrollPane scrollPane = new JScrollPane(tablaMascotas);
                getContentPane().add(scrollPane);
                getContentPane().add(panel);
                modeloTabla.addColumn("Id_mascota");
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Edad");
                modeloTabla.addColumn("Sexo");
                modeloTabla.addColumn("Peso");
                modeloTabla.addColumn("¿Vacunado?");
                modeloTabla.addColumn("Raza");
                modeloTabla.addColumn("Dueño");
                modeloTabla.addColumn("Tipo de mascota");
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

                    String sql = "SELECT * FROM dbo.Mascota";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);

                    // Borra los registros existentes en la tabla
                    modeloTabla.setRowCount(0);

                    // Agrega los registros de la base de datos a la tabla
                    while (resultSet.next()) {
                        String id_mascota = resultSet.getString("id_mascota");
                        String nombre_mascota = resultSet.getString("nombre_mascota");
                        String edad_mascota = resultSet.getString("edad_mascota");
                        String sexo_mascota = resultSet.getString("sexo_mascota");
                        String peso_mascota = resultSet.getString("peso_mascota");
                        String carnet_vacuna = resultSet.getString("carnet_vacuna");
                        String id_raza = resultSet.getString("id_raza");
                        String documento_cliente = resultSet.getString("documento_cliente");
                        String id_tipo_mascota = resultSet.getString("id_tipo_mascota");

                        Object[] fila = {id_mascota, nombre_mascota, edad_mascota, sexo_mascota, peso_mascota, carnet_vacuna, id_raza, documento_cliente, id_tipo_mascota};
                        modeloTabla.addRow(fila);
                    }

                    statement.close();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al obtener los registros de la mascota");
                }
                btnRegresar2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Main_Empleados_Mascota mascota = new Main_Empleados_Mascota();
                        mascota.setVisible(true);
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
                JLabel lblId_mascota = new JLabel("Id mascota:");
                lblId_mascota.setBounds(10, 100, 100, 25);
                txtId_mascota = new JTextField(20);
                txtId_mascota.setBounds(120, 100, 150, 25);

                JLabel lblColumna = new JLabel("Columna a actualizar:");
                lblColumna.setBounds(10, 20, 100, 25);

                String[] columnas = {"nombre_mascota", "edad_mascota", "sexo_mascota", "peso_mascota", "carnet_vacuna", "id_raza", "documento_cliente", "id_tipo_mascota"};
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

                panel.add(lblId_mascota);
                panel.add(txtId_mascota);
                panel.add(lblColumna);
                panel.add(comboBoxColumnas);
                panel.add(lblActualizacion);
                panel.add(txtActualizacion);
                panel.add(btnActualizar1);
                panel.add(btnRegresar3);
                panel.add(backgroundLabel);

                btnActualizar1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String id_mascota = txtId_mascota.getText();
                        String columnaSeleccionada = (String) comboBoxColumnas.getSelectedItem();
                        String actualizacion = txtActualizacion.getText();

                        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                                + "database=animalTrip11;"
                                + "user=sa;"
                                + "password=hola123;"
                                + "loginTimeout=30;";

                        try {
                            Connection conn = DriverManager.getConnection(conexionUrl);
                            String sql = "UPDATE dbo.Mascota SET " + columnaSeleccionada + " = ? WHERE id_mascota = ?";

                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setString(1, actualizacion);
                            statement.setString(2, id_mascota);

                            // Ejecutar la sentencia SQL
                            int filasActualizadas = statement.executeUpdate();

                            statement.close();
                            conn.close();

                            if (filasActualizadas > 0) {
                                JOptionPane.showMessageDialog(null, "Mascota actualizada exitosamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se encontró ninguna mascota con el id especificado");
                            }

                            // Limpiar los campos de texto después de actualizar
                            txtActualizacion.setText("");
                            txtId_mascota.setText("");

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al actualizar la mascota");
                        }
                    }
                });
                btnRegresar3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Main_Empleados_Mascota mascota = new Main_Empleados_Mascota();
                        mascota.setVisible(true);
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
                JLabel lblId_mascota1 = new JLabel("Id mascota:");
                txtId_mascota1 = new JTextField(10);
                btnEliminar1 = new JButton("Eliminar");
                btnRegresar4 = new JButton("Regresar");

                int x = 50;
                int y = 50;
                int labelWidth = 120;
                int labelHeight = 25;
                int fieldWidth = 200;
                int fieldHeight = 25;
                int verticalSpacing = 30;

                lblId_mascota1.setForeground(Color.RED);
                lblId_mascota1.setBounds(x, y, labelWidth, labelHeight);
                txtId_mascota1.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                y += verticalSpacing;

                btnEliminar1.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
                btnRegresar4.setBounds(x + labelWidth, y + verticalSpacing, fieldWidth, fieldHeight);
                panel.add(lblId_mascota1);
                panel.add(txtId_mascota1);
                panel.add(btnEliminar1);
                panel.add(btnRegresar4);
                panel.add(backgroundLabel);
                getContentPane().add(panel);
                
                btnEliminar1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String id_mascota = txtId_mascota1.getText();

                        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                                + "database=animalTrip11;"
                                + "user=sa;"
                                + "password=hola123;"
                                + "loginTimeout=30;";

                        try {
                            Connection conn = DriverManager.getConnection(conexionUrl);

                            // Preparar la sentencia SQL con el valor del documento a eliminar
                            String sql = "DELETE FROM dbo.Mascota WHERE id_mascota = ?";
                            PreparedStatement statement = conn.prepareStatement(sql);
                            statement.setString(1, id_mascota);

                            // Ejecutar la sentencia SQL
                            int rowsAffected = statement.executeUpdate();

                            // Verificar si se eliminó correctamente el registro
                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Mascota eliminada exitosamente");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se encontró ninguna mascota con el id especificado");
                            }

                            statement.close();
                            conn.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error al eliminar la mascota");
                        }
                    }
                });
                btnRegresar4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Main_Empleados_Mascota mascota = new Main_Empleados_Mascota();
                        mascota.setVisible(true);
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
       // initComponents();
    }

    
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
            java.util.logging.Logger.getLogger(Main_Empleados_Mascota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Mascota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Mascota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados_Mascota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Empleados_Mascota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
