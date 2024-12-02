package main;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JOptionPane;
import swing.MyPassword;
import swing.MyTextField;

public class Login extends JPanel {

    private RoundedButton homeButton;

    public Login() {
        initComponents();
        setPreferredSize(new Dimension(400, 500));
    }

    public void login() {
        txtUser.grabFocus();
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    // Metodo para leer desde el archivo usuarios.txt
    private boolean verificarLogin(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3 && data[0].equals(username) && data[2].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        txtUser = new MyTextField();
        txtEmail = new MyTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPass = new MyPassword();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        myButton1 = new swing.MyButton();


        Font Orbitron2Font;
        try {
            Orbitron2Font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/Orbitron-VariableFont_wght.ttf"))
                    .deriveFont(Font.BOLD, 18f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            Orbitron2Font = new Font("Arial", Font.BOLD, 18);
        }

        Font SpaceMonoFont;
        try {
            SpaceMonoFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/SpaceMono-Regular.ttf"))
                    .deriveFont(Font.PLAIN, 15f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            SpaceMonoFont = new Font("Arial", Font.PLAIN, 15);
        }

        homeButton = new RoundedButton("Home", 15, Color.CYAN, Color.BLACK);
        homeButton.setFont(Orbitron2Font);

        setOpaque(false);

        jLabel1.setText("User Name");
        jLabel1.setFont(SpaceMonoFont);
        jLabel1.setForeground(Color.WHITE);

        Font Orbitron3Font;
        try {
            Orbitron3Font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/Orbitron-VariableFont_wght.ttf"))
                    .deriveFont(Font.BOLD, 48f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            Orbitron3Font = new Font("Orbitron", Font.BOLD, 48);
        }

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Login");
        jLabel2.setFont(Orbitron3Font);
        jLabel2.setForeground(Color.WHITE);

        jLabel3.setText("Password");
        jLabel3.setFont(SpaceMonoFont);
        jLabel3.setForeground(Color.WHITE);

        jLabel4.setText("Email");
        jLabel4.setFont(SpaceMonoFont);
        jLabel4.setForeground(Color.WHITE);

        RoundedButton myButton1 = new RoundedButton("Sign up", 15, Color.BLACK, Color.WHITE);
        myButton1.setFont(Orbitron2Font);

        // Evento para el boton de Login
        myButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String username = txtUser.getText();
                String password = new String(txtPass.getPassword());
                if (verificarLogin(username, password)) {
                    JOptionPane.showMessageDialog(Login.this, "Login exitoso");
                } else {

                    JOptionPane.showMessageDialog(Login.this, "Los datos están incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(50, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4)
                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(myButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(homeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(50, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGap(18, 18, 18)
                    .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(50, Short.MAX_VALUE))
        );
    }

    private JButton cmdRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private swing.MyButton myButton1;
    private MyPassword txtPass;
    private swing.MyTextField txtEmail;
    private MyTextField txtUser;

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
