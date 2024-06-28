package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import modelo.Conexion;

public class Login_Cliente extends javax.swing.JFrame {

    private JTextField txtDocumento;
    private JPasswordField txtContraseña;
    private JButton btnLogin;
    private JButton btnRegresar;
    private Conexion databaseConnector;

    public Login_Cliente(Conexion connector) {
        this.databaseConnector = connector;

        setTitle("Login de Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(517, 540);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        // Crear un JLabel con la imagen de fondo
        ImageIcon backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("imagenes/fondo-cliente.png"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        panel.add(backgroundLabel);

        panel.setLayout(null);

        JLabel lblDocumento = new JLabel("Documento:");
        lblDocumento.setBounds(20, 30, 80, 25);

        txtDocumento = new JTextField();
        txtDocumento.setBounds(100, 30, 160, 25);

        JLabel lblContraseña = new JLabel("Contraseña:");
        lblContraseña.setBounds(20, 70, 80, 25);

        txtContraseña = new JPasswordField();
        txtContraseña.setBounds(100, 70, 160, 25);

        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBounds(100, 110, 120, 25);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(100, 150, 120, 25);

        panel.add(lblDocumento);
        panel.add(txtDocumento);
        panel.add(lblContraseña);
        panel.add(txtContraseña);
        panel.add(btnLogin);
        panel.add(btnRegresar);
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

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String documento = txtDocumento.getText();
                String contraseña = new String(txtContraseña.getPassword());
                String nombre_cliente = databaseConnector.obtenerNombreCliente(documento);
                String documento_cliente = databaseConnector.obtenerDocumentoCliente(contraseña);

                // Validar las credenciales del cliente
                boolean isValidCredentials = validateCredentials(documento, contraseña);

                if (isValidCredentials) {
                    // Credenciales válidas, mostrar mensaje de éxito y realizar acciones adicionales
                    //JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                    String nombreCliente = databaseConnector.obtenerNombreCliente(documento);
                    String documentoCliente = databaseConnector.obtenerDocumentoCliente(contraseña);

                    // Crear una instancia de ClientMenu y pasar el nombre del cliente como parámetro
                    ClienteMenu clientMenu = new ClienteMenu(nombre_cliente, documento_cliente);
                    clientMenu.setVisible(true);
                    // Cerrar el frame actual (LoginCliente)
                    dispose();
                } else {
                    // Credenciales inválidas, mostrar mensaje de error
                    JOptionPane.showMessageDialog(null, "Credenciales inválidas");
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

    }

    private boolean validateCredentials(String documento, String contraseña) {
        boolean isValid = false;

        // Crear la consulta SQL para validar las credenciales
        String query = "SELECT * FROM Cliente WHERE documento_cliente = ? AND contraseña_cliente = ?";

        try {
            // Obtener la conexión a la base de datos
            Connection connection = databaseConnector.getConexion();

            // Crear el objeto PreparedStatement para ejecutar la consulta con parámetros
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, documento);
            statement.setString(2, contraseña);

            // Ejecutar la consulta y obtener el resultado
            ResultSet resultSet = statement.executeQuery();

            // Verificar si el resultado contiene algún registro
            if (resultSet.next()) {
                // Las credenciales son válidas
                isValid = true;
            }

            // Cerrar los recursos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isValid;
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
            java.util.logging.Logger.getLogger(Login_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        Conexion databaseConnector = new Conexion();

        // Crea y muestra el frame de login cliente
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_Cliente(databaseConnector).setVisible(true);
            }

        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
