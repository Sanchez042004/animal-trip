package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class NewCliente extends javax.swing.JFrame {

    private JTextField txtDocumento;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JComboBox<String> cmbTipoDocumento;
    private JPasswordField txtContraseña;
    private JButton btnRegistrar;
    private JButton btnRegresar;

    public NewCliente() {
        setTitle("Registro de Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(517, 540);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        setContentPane(panel);

        ImageIcon backgroundImage = null;
        try {
            InputStream imageStream = getClass().getClassLoader().getResourceAsStream("imagenes/logo2.png");
            backgroundImage = new ImageIcon(ImageIO.read(imageStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        // Etiquetas
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
        btnRegresar = new JButton("Regresar");

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
        btnRegresar.setBounds(x + labelWidth, y + verticalSpacing, fieldWidth, fieldHeight);

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
        panel.add(btnRegresar);

        panel.add(backgroundLabel);

        // Filtro de documento solo para números - documento
        PlainDocument docDocumento = new PlainDocument();
        docDocumento.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                fb.insertString(offset, string.replaceAll("\\D", ""), attr); // Remover caracteres no numéricos
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
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
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                fb.insertString(offset, string.replaceAll("\\d", ""), attr); // Remover caracteres numéricos
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
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
                        + "database=animalTrip11;"
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

        btnRegresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu menu = new MainMenu();
                menu.setVisible(true);
                dispose(); // Cierra la ventana actual
            }
        });

        setLocationRelativeTo(null);

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
