package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import javax.swing.*;

public class HomePage extends JFrame {

    public HomePage() {
        initComponents();
    }

    private void initComponents() {
        // Ventana del JFrame
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
        setLayout(new BorderLayout());

        // El panel con un fondo
        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon(getClass().getResource("/resources/FondoUI2.jpeg")).getImage());
        add(backgroundPanel, BorderLayout.CENTER);
        backgroundPanel.setLayout(null);

        Font OrbitronFont;
        try {
            OrbitronFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/Orbitron-VariableFont_wght.ttf"))
                    .deriveFont(Font.BOLD, 40f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            OrbitronFont = new Font("Orbitron-VariableFont_wght", Font.BOLD, 40);
        }

        // Configuración del JLabel
        JLabel titleLabel = new JLabel("RockeTech");
        titleLabel.setFont(OrbitronFont);
        titleLabel.setForeground(Color.WHITE); // Color del texto
        titleLabel.setBounds(200, 150, 1000, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(titleLabel);


        Font SpaceMonoFont;
        try {
            SpaceMonoFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/SpaceMono-Regular.ttf"))
                    .deriveFont(Font.PLAIN, 15f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            SpaceMonoFont = new Font("Arial", Font.PLAIN, 15);
        }

        // El texto descriptivo
        JLabel descriptionLabel = new JLabel("Register today and access educational programs");
        JLabel descriptionLabel2 = new JLabel("specialized for integration and success");
        JLabel descriptionLabel3 = new JLabel("in children's academic achievement.");

        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setFont(SpaceMonoFont);
        descriptionLabel.setBounds(300, 240, 800, 50);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        descriptionLabel2.setForeground(Color.WHITE);
        descriptionLabel2.setFont(SpaceMonoFont);
        descriptionLabel2.setBounds(300, 260, 800, 50);
        descriptionLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        descriptionLabel3.setForeground(Color.WHITE);
        descriptionLabel3.setFont(SpaceMonoFont);
        descriptionLabel3.setBounds(300, 280, 800, 50);
        descriptionLabel3.setHorizontalAlignment(SwingConstants.CENTER);

        backgroundPanel.add(descriptionLabel);
        backgroundPanel.add(descriptionLabel2);
        backgroundPanel.add(descriptionLabel3);

        Font Orbitron2Font;
        try {
            Orbitron2Font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/Orbitron-VariableFont_wght.ttf"))
                    .deriveFont(Font.BOLD, 18f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            Orbitron2Font = new Font("Arial", Font.BOLD, 20);
        }

        // Botón "Programs"
        RoundedButton programsButton = new RoundedButton("Programs", 15, Color.YELLOW, Color.BLACK);
        programsButton.setFont(Orbitron2Font);
        programsButton.setBounds(400, 45, 150, 30);
        backgroundPanel.add(programsButton);

        // Botón "Courses"
        RoundedButton coursesButton = new RoundedButton("Courses", 15, Color.YELLOW, Color.BLACK);
        coursesButton.setFont(Orbitron2Font);
        coursesButton.setBounds(600, 45, 150, 30);
        backgroundPanel.add(coursesButton);

        // Botón "About Us"
        RoundedButton aboutUsButton = new RoundedButton("About us", 15, Color.YELLOW, Color.BLACK);
        aboutUsButton.setFont(Orbitron2Font);
        aboutUsButton.setBounds(800, 45, 150, 30);
        backgroundPanel.add(aboutUsButton);

        // Botón "Get Started"
        RoundedButton getStartedButton = new RoundedButton("Get started", 30, Color.CYAN, Color.BLACK);
        getStartedButton.setFont(Orbitron2Font);
        getStartedButton.setBounds(600, 340, 200, 70);
        backgroundPanel.add(getStartedButton);

        // Botón "Login in"
        RoundedButton loginButton = new RoundedButton("Login in", 15, Color.BLACK, Color.WHITE);
        loginButton.setFont(Orbitron2Font);
        loginButton.setBounds(1150, 45, 150, 30);
        backgroundPanel.add(loginButton);

        // Acción de los botones
        getStartedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Main().setVisible(true);
                dispose(); // Cierra la página principal
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Main().setVisible(true);
                dispose(); // Cierra la página principal
            }
        });

        // Las paginas (clases) navegación del Homepage
        programsButton.addActionListener(new ActionListener() { // Integrante -> Boris Alonso
            public void actionPerformed(ActionEvent e) {
                showMessage("Programs Page");
            }
        });

        coursesButton.addActionListener(new ActionListener() { // Integrante -> Claudio Fernando
            public void actionPerformed(ActionEvent e) {
                new Courses().setVisible(true);
                dispose();
            }
        });

        aboutUsButton.addActionListener(new ActionListener() { // Integrante -> Luciana Gianmariel
            public void actionPerformed(ActionEvent e) {
                new AboutUs().setVisible(true);
                dispose();
            }
        });

        setTitle("RockeTech");
    }

    private void showMessage(String pageTitle) {
        JFrame pageFrame = new JFrame(pageTitle);
        pageFrame.setSize(600, 400);
        pageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label = new JLabel(pageTitle, JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        pageFrame.add(label);
        pageFrame.setVisible(true);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Clase para el fondo del panel
    class BackgroundPanel extends JPanel {
        private Image image;

        public BackgroundPanel(Image image) {
            this.image = image;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Clase para crear un botón RoundedButton
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
            setFont(new Font("Arial", Font.BOLD, 20));
            setBorderPainted(false);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.setColor(backgroundColor);
            g2.fill(shape);
            g2.dispose();
            super.paintComponent(g);
        }

        public void setContentAreaFilled(boolean b) {
            // area del contenido
        }
    }
}
