package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

public class Register extends JPanel {
    private JButton cmdBackLogin;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private swing.MyButton myButton1;
    private swing.MyPassword txtPass;
    private swing.MyPassword txtPass1;
    private swing.MyTextField txtEmail;
    private swing.MyTextField txtUser;


    public Register() {
        initComponents();
        setPreferredSize(new Dimension(400, 500));
    }

    public void register() {
        txtUser.grabFocus();
    }



    public void addEventCreateAccount(ActionListener event) {
        myButton1.addActionListener(event);
    }

    // Metodo para escribir en el archivo usuarios.txt
    private void escribirArchivo(String username, String email, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
            writer.write(username + "," + email + "," + password + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        swing.PanelBorder panelBorder = new swing.PanelBorder();
        panelBorder.setBackground(new Color(255, 255, 255));
        panelBorder.setLayout(new BorderLayout());

        txtUser = new swing.MyTextField();
        txtEmail = new swing.MyTextField();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        txtPass = new swing.MyPassword();
        jLabel3 = new JLabel();
        myButton1 = new swing.MyButton();
        jLabel4 = new JLabel();
        txtPass1 = new swing.MyPassword();
        jLabel5 = new JLabel();

        setBackground(new Color(255, 255, 255));
        setOpaque(false);

        Font Orbitron2Font;
        try {
            Orbitron2Font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/Orbitron-VariableFont_wght.ttf"))
                    .deriveFont(Font.BOLD, 18f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            Orbitron2Font = new Font("Arial", Font.BOLD, 20);
        }

        Font SpaceMonoFont;
        try {
            SpaceMonoFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/SpaceMono-Regular.ttf"))
                    .deriveFont(Font.PLAIN, 15f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            SpaceMonoFont = new Font("Arial", Font.PLAIN, 15);
        }

        jLabel1.setText("User Name");
        jLabel1.setFont(SpaceMonoFont);
        jLabel1.setForeground(Color.WHITE);

        Font Orbitron3Font;
        try {
            Orbitron3Font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/Orbitron-VariableFont_wght.ttf"))
                    .deriveFont(Font.BOLD, 48f); // Ajusta el tamaño aquí
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            Orbitron3Font = new Font("Orbitron", Font.BOLD, 48); // Fuente predeterminada en caso de error
        }

        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("Register");
        jLabel2.setFont(Orbitron3Font);
        jLabel2.setForeground(Color.WHITE);

        jLabel3.setText("Password");
        jLabel3.setFont(SpaceMonoFont);
        jLabel3.setForeground(Color.WHITE);

        jLabel4.setText("Confirm Password");
        jLabel4.setFont(SpaceMonoFont);
        jLabel4.setForeground(Color.WHITE);

        jLabel5.setText("Email");
        jLabel5.setFont(SpaceMonoFont);
        jLabel5.setForeground(Color.WHITE);

        RoundedButton myButton1 = new RoundedButton("Create account", 15, Color.BLACK, Color.WHITE);
        myButton1.setFont(Orbitron2Font);
        
        // Evento para el botón de registro
        myButton1.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String username = txtUser.getText();
                String email = txtEmail.getText();
                String password = new String(txtPass.getPassword());
                String confirmPassword = new String(txtPass1.getPassword());

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(Register.this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Escribir al archivo usuarios.txt
                escribirArchivo(username, email, password);

                JOptionPane.showMessageDialog(Register.this, "Registro completado", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        GroupLayout layout = new GroupLayout(panelBorder);
        panelBorder.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(50, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtUser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addComponent(txtPass, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(myButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addComponent(txtPass1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(50, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(txtPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(txtPass1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addComponent(myButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addContainerGap(50, Short.MAX_VALUE))
        );

        GroupLayout mainLayout = new GroupLayout(this);
        this.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(panelBorder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(panelBorder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    class RoundedButton extends JButton {
        private int radius;
        private Color backgroundColor;
        private Color textColor;

        public RoundedButton(String text, int radius, Color backgroundColor, Color textColor) {
            super(text);
            this.radius = radius;
            this.backgroundColor = backgroundColor;
            this.textColor = textColor;
            setOpaque(false);
            setFocusPainted(false);
            setBackground(backgroundColor);
            setForeground(textColor);
            setFont(new Font("Arial", Font.BOLD, 15));
            setBorderPainted(false);
            setPreferredSize(new Dimension(100, 30));
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape shape = new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.setColor(backgroundColor);
            g2.fill(shape);
            g2.dispose();
            super.paintComponent(g);
        }
        public void setContentAreaFilled(boolean b) {
        }
    }
}
