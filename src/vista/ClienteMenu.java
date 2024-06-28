package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import modelo.Conexion;

public class ClienteMenu extends javax.swing.JFrame {

    private JLabel lblCliente;
    private JLabel lblCliente1;
    private JButton btnHacerReserva;
    private JButton btnCancelarReserva;
    private JButton btnGenerarRecibo;
    private JButton btnRegistrarMascota;
    private JButton btnConsultarMascotas;
    private JButton btnCerrarSesion;

    public ClienteMenu(String nombre_cliente, String documento_cliente) {
        setTitle("Menú del Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(517, 540);
        setResizable(false);
        setLocationRelativeTo(null);

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

        lblCliente = new JLabel("Hola, " + nombre_cliente);
        lblCliente1 = new JLabel("ID: " + documento_cliente);

        lblCliente.setBounds(10, 10, 200, 30);
        lblCliente.setFont(lblCliente.getFont().deriveFont(Font.BOLD | Font.ITALIC));
        panel.add(lblCliente);

        lblCliente1.setBounds(10, 30, 300, 30);
        lblCliente1.setFont(lblCliente1.getFont().deriveFont(Font.BOLD | Font.ITALIC));
        panel.add(lblCliente1);

        // Botones para realizar acciones
        btnHacerReserva = new JButton("Hacer Reserva");
        btnHacerReserva.setBounds(100, 110, 150, 25);
        btnCancelarReserva = new JButton("Cancelar Reserva");
        btnCancelarReserva.setBounds(100, 150, 150, 25);
        btnGenerarRecibo = new JButton("Generar Recibo");
        btnGenerarRecibo.setBounds(100, 190, 150, 25);
        btnConsultarMascotas = new JButton("Consultar Mascotas");
        btnConsultarMascotas.setBounds(100, 230, 150, 25);
        btnRegistrarMascota = new JButton("Registrar Mascota");
        btnRegistrarMascota.setBounds(100, 270, 150, 25);
        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setBounds(100, 310, 150, 25);

        panel.add(btnHacerReserva);
        panel.add(btnCancelarReserva);
        panel.add(btnGenerarRecibo);
        panel.add(btnConsultarMascotas);
        panel.add(btnRegistrarMascota);
        panel.add(btnCerrarSesion);
        panel.add(backgroundLabel); // Agregar el JLabel al panel

        btnHacerReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar el JFrame actual
                dispose();
                CrearReservacion reservacion = new CrearReservacion(nombre_cliente, documento_cliente);
                reservacion.setVisible(true);
            }
        });

        btnCancelarReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar el JFrame actual
                dispose();
                BorrarReservacion cancelarReservacion = new BorrarReservacion(nombre_cliente, documento_cliente);
                cancelarReservacion.setVisible(true);
            }
        });

        btnGenerarRecibo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar el JFrame actual
                dispose();
                GenerarRecibo recibo = new GenerarRecibo(nombre_cliente, documento_cliente);
                recibo.setVisible(true);
            }
        });

        btnConsultarMascotas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar el JFrame actual
                dispose();
                ReadMascotas consultarMascotas = new ReadMascotas(nombre_cliente, documento_cliente);
                consultarMascotas.setVisible(true);
            }
        });

        btnRegistrarMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar el JFrame actual
                dispose();
                NewPet registro_mascota = new NewPet(nombre_cliente, documento_cliente);
                registro_mascota.setVisible(true);

            }
        });

        btnCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Volver al menú de inicio de sesión
                MainMenu menu = new MainMenu();
                menu.setVisible(true);
                dispose(); // Cierra la ventana actual
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteMenu("Nombre del Cliente", "Documento del Cliente").setVisible(true);
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
            java.util.logging.Logger.getLogger(ClienteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        // Crear una instancia de la clase Conexion para conectarse a la base de datos
        Conexion databaseConnector = new Conexion();
        databaseConnector.getConexion();

    }

}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

