package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class NewPet extends javax.swing.JFrame {

    private JTextField txtNombreMascota;
    private JTextField txtEdadMascota;
    private JComboBox<String> cmbSexoMascota;
    private JTextField txtPesoMascota;
    private JComboBox<String> cmbCarnetVacuna;
    private JComboBox<String> cmbRaza;
    private JComboBox<String> cmbTipoMascota;
    private JButton btnRegistrar;
    private JButton btnRegresar;

    public NewPet(String nombre_cliente, String documento_cliente) {
        initComponents();
        setTitle("Registro de Mascota");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(517, 540);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
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

        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblEdad = new JLabel("Edad:");
        JLabel lblSexo = new JLabel("Sexo:");
        JLabel lblPeso = new JLabel("Peso:");
        JLabel lblCarnet = new JLabel("¿Está vacunado?:");
        JLabel lblRaza = new JLabel("Raza:");
        JLabel lblTipoMascota = new JLabel("Tipo mascota:");

        // Campos de texto
        txtNombreMascota = new JTextField(20);
        txtEdadMascota = new JTextField(20);
        txtPesoMascota = new JTextField(20);

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
        btnRegresar = new JButton("Regresar");

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

        btnRegistrar.setBounds(x + labelWidth, y, fieldWidth, fieldHeight);
        btnRegresar.setBounds(x + labelWidth, y + verticalSpacing, fieldWidth, fieldHeight);

        // Agregar componentes al panel
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
        panel.add(btnRegistrar);
        panel.add(btnRegresar);

        panel.add(backgroundLabel);

        // Filtro de nombre solo para letras - Nombre
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

        // Filtro de documento solo para números - edad
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

        // Filtro de documento solo para números - peso
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

                // Verificar si algún campo está vacío
                if (nombre_mascota.isEmpty() || edad_mascota.isEmpty() || sexo_mascota.isEmpty()
                        || peso_mascota.isEmpty() || carnet_vacuna.isEmpty() || id_raza.isEmpty()
                        || id_tipo_mascota.isEmpty()) {
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

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar la mascota");
                }
            }
        });

        // Agregar un WindowListener al JFrame
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Evitar que se cierre el JFrame al hacer clic en el botón de cierre
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        // Agregar ActionListener al botón "Regresar"
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar el JFrame actual
                dispose();

                // Abrir el JFrame clientMenu
                ClientMenu clientMenu = new ClientMenu(nombre_cliente, documento_cliente);
                clientMenu.setVisible(true);
            }
        });

        setLocationRelativeTo(null);
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
            java.util.logging.Logger.getLogger(NewPet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewPet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewPet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewPet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewPet("Nombre del Cliente", "Documento del Cliente").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
