package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        
        Login login = new Login();
        Register register = new Register();

        panelColor1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // El panel de Login
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 50, 50, 25);
        gbc.anchor = GridBagConstraints.CENTER;
        panelColor1.add(login, gbc);

        // El panel de Register
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 25, 50, 50);
        panelColor1.add(register, gbc);

        // Boton "Home"
        login.getHomeButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HomePage().setVisible(true);
                dispose(); // Cierra la pag de Login/Register
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panelColor1 = new swing.PanelColor();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panelColor1);
        panelColor1.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 800, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout contentPaneLayout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelColor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelColor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    // Cambiar el main para que llame a HomePage (como pagina principal)
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    private swing.PanelColor panelColor1;
}