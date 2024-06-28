package vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import vista.Login_Empleado;


public class Main_Empleados extends javax.swing.JFrame {

    private String username;
    private JLabel lblBienvenido;
    private JLabel lblUsuario;
    private JLabel lblPermisos;
    private List<JButton> tablaButtons;
    private JButton btnSalir;
    
    public Main_Empleados(String username) {
        this.username= username;
        setTitle("Sistema de Gestión: Empleado");
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

        lblBienvenido = new JLabel("Bienvenido,");
        lblBienvenido.setFont(new Font("Arial", Font.BOLD, 18));
        lblBienvenido.setBounds(20, 20, 200, 30);
        //panel.add(lblBienvenido);

        lblUsuario = new JLabel(username);
        lblUsuario.setBounds(20, 60, 300, 20);
        //panel.add(lblUsuario);
        
        lblPermisos = new JLabel("Usted puede manipular estas tablas:");
        lblPermisos.setBounds(160, 55, 300, 30);

        btnSalir = new JButton("Cerrar Sesión");
        btnSalir.setBounds(280, 250, 100, 30);
        
        panel.add(lblBienvenido);
        panel.add(lblUsuario);
        panel.add(lblPermisos);
        
        // Crear botones para las tablas según los permisos del usuario
         tablaButtons = new ArrayList<>();
        int y = 100;
        JButton btnConductor = new JButton("Conductor");
        btnConductor.setBounds(20, y, 120, 30);

        JButton btnProfesional = new JButton("Profesional");
        btnProfesional.setBounds(20, y + 40, 120, 30);

        JButton btnReserva = new JButton("Reserva");
        btnReserva.setBounds(20, y, 120, 30);

        JButton btnFactura = new JButton("Factura");
        btnFactura.setBounds(20, y + 40, 120, 30);

        JButton btnDestino = new JButton("Destino");
        btnDestino.setBounds(20, y + 80, 120, 30);

        JButton btnReservaDestino = new JButton("Reserva_destino");
        btnReservaDestino.setBounds(20, y + 120, 120, 30);
        
        JButton btnCliente = new JButton("Cliente");
        btnCliente.setBounds(20, y + 120, 120, 30);
        
        JButton btnTipo_documento = new JButton("Tipo_documento");
        btnTipo_documento.setBounds(20, y + 120, 120, 30);
        
        JButton btnMascota = new JButton("Mascota");
        btnMascota.setBounds(20, y + 120, 120, 30);
        
        JButton btnLimitacion = new JButton("Limitación");
        btnLimitacion.setBounds(20, y + 120, 120, 30);
        
        JButton btnRaza = new JButton("Raza");
        btnRaza.setBounds(20, y + 120, 120, 30);
        
        JButton btnTipo_mascota = new JButton("Tipo_mascota");
        btnTipo_mascota.setBounds(20, y + 120, 120, 30);
        
        JButton btnReserva_mascota = new JButton("Reserva_mascota");
        btnReserva_mascota.setBounds(20, y + 120, 120, 30);
        
        JButton btnMascota_limitacionn = new JButton("Mascota_limitacionn");
        btnMascota_limitacionn.setBounds(20, y + 120, 120, 30);
        
        JButton btnRuta = new JButton("Ruta");
        btnRuta.setBounds(20, y + 120, 120, 30);
        
        JButton btnTipo_viaje = new JButton("Tipo_viaje");
        btnTipo_viaje.setBounds(20, y + 120, 120, 30);
  
        //initComponents();
        if (username.equalsIgnoreCase("Andres")) {
            // Agregar lógica específica para Andrés Sánchez
            addButton(panel, btnCliente, 20, y);
        } else if (username.equalsIgnoreCase("Laura Hernández")) {
            // Agregar lógica específica para Laura Hernández
            //addButton(panel, btnReserva, 20, y);
            //addButton(panel, btnFactura, 20, y + 40);
            addButton(panel, btnDestino, 20, y + 80);
        } else if (username.equalsIgnoreCase("Javier Rojas")) {
            // Agregar lógica específica para Javier Rojas
            addButton(panel, btnCliente, 20, y);
            //addButton(panel, btnTipo_documento, 20, y + 40);
        } else if (username.equalsIgnoreCase("María Torres")) {
            // Agregar lógica específica para María Torres
            addButton(panel, btnMascota, 20, y);
            addButton(panel, btnLimitacion, 20, y + 40);
            addButton(panel, btnRaza, 20, y + 80);
            //addButton(panel, btnTipo_mascota, 20, y + 120);
        } else if (username.equalsIgnoreCase("Jhonathan Palacios")) {
            // Agregar lógica específica para Jhonathan Palacios
            addButton(panel, btnDestino, 20, y);
            addButton(panel, btnRuta, 20, y + 40);
            //addButton(panel, btnTipo_viaje, 20, y + 80);
        } else if (username.equalsIgnoreCase("Héctor Cárdenas")) {
            // Agregar lógica específica para Héctor Cárdenas
            addButton(panel, btnConductor, 20, y);
            addButton(panel, btnProfesional, 20, y + 40);
            //addButton(panel, btnReserva, 20, y + 80);
            //addButton(panel, btnFactura, 20, y + 120);
            addButton(panel, btnDestino, 20, y + 160);
            addButton(panel, btnCliente, 160, y);
            //addButton(panel, btnTipo_documento, 160, y + 40);
            addButton(panel, btnMascota, 160, y + 80);
            addButton(panel, btnLimitacion, 160, y + 120);
            addButton(panel, btnRaza, 160, y + 160);
            //addButton(panel, btnTipo_mascota, 160, y + 200);
            addButton(panel, btnRuta, 300, y + 80);
            //addButton(panel, btnTipo_viaje, 300, y + 120);
   
        }
        panel.add(btnSalir);
        
        panel.add(backgroundLabel);
        
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               MainMenu menu = new MainMenu();
                menu.setVisible(true);
                dispose(); // Cierra la ventana actual
            }
        });
        
        btnCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main_Empleados_Cliente cliente= new Main_Empleados_Cliente();
                cliente.setVisible(true);
                dispose();

            }
        });
        
        btnMascota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main_Empleados_Mascota mascota= new Main_Empleados_Mascota();
                mascota.setVisible(true);
                dispose();

            }
        });
        
        btnConductor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main_Empleados_Conductor conductor= new Main_Empleados_Conductor();
                conductor.setVisible(true);
                dispose();

            }
        });
        
        btnProfesional.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main_Empleados_Profesional profesional= new Main_Empleados_Profesional();
                profesional.setVisible(true);
                dispose();

            }
        });
        
        btnRuta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main_Empleados_Ruta ruta= new Main_Empleados_Ruta();
                ruta.setVisible(true);
                dispose();

            }
        });
        
        btnDestino.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main_Empleados_Destino destino= new Main_Empleados_Destino();
                destino.setVisible(true);
                dispose();

            }
        });
        
        btnRaza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main_Empleados_Raza raza= new Main_Empleados_Raza();
                raza.setVisible(true);
                dispose();

            }
        });
        
        btnLimitacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main_Empleados_Limitacion limitacion= new Main_Empleados_Limitacion();
                limitacion.setVisible(true);
                dispose();

            }
        });
        
    }
   private void addButton(JPanel panel, JButton button, int x, int y) {
    button.setBounds(x, y, 120, 30);
    panel.add(button);
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
            java.util.logging.Logger.getLogger(Main_Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String username = Login_Empleado.username;
                System.out.println("El empleado es:" + username);

                // Crear el frame principal y mostrarlo
                Main_Empleados frame = new Main_Empleados(username);
                frame.setVisible(true);
            }
        });
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

