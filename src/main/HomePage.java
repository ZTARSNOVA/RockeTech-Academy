package main;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
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

        // Botones de navegación
        RoundedButton programsButton = new RoundedButton("Programs", 15, Color.WHITE, Color.BLACK);
        programsButton.setBounds(400, 45, 150, 30);
        backgroundPanel.add(programsButton);

        RoundedButton coursesButton = new RoundedButton("Courses", 15, Color.WHITE, Color.BLACK);
        coursesButton.setBounds(600, 45, 150, 30);
        backgroundPanel.add(coursesButton);

        RoundedButton aboutUsButton = new RoundedButton("About us", 15, Color.WHITE, Color.BLACK);
        aboutUsButton.setBounds(800, 45, 150, 30);
        backgroundPanel.add(aboutUsButton);

        // el titulo "RockeTech"
        JLabel titleLabel = new JLabel("RockeTech");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE); // Establecer color del texto a negro
        titleLabel.setBounds(200, 150, 1000, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(titleLabel);

        // El texto descriptivo
        JLabel descriptionLabel = new JLabel("Register today and access educational programs");
        JLabel descriptionLabel2 = new JLabel("specialized for integration and success");
        JLabel descriptionLabel3 = new JLabel("in children's academic achievement.");

        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        descriptionLabel.setBounds(300, 240, 800, 50);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        descriptionLabel2.setForeground(Color.WHITE);
        descriptionLabel2.setFont(new Font("Arial", Font.PLAIN, 15));
        descriptionLabel2.setBounds(300, 260, 800, 50);
        descriptionLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        descriptionLabel3.setForeground(Color.WHITE);
        descriptionLabel3.setFont(new Font("Arial", Font.PLAIN, 15));
        descriptionLabel3.setBounds(300, 280, 800, 50);
        descriptionLabel3.setHorizontalAlignment(SwingConstants.CENTER);

        backgroundPanel.add(descriptionLabel);
        backgroundPanel.add(descriptionLabel2);
        backgroundPanel.add(descriptionLabel3);

        // El boton "Get Started"
        RoundedButton getStartedButton = new RoundedButton("Get started", 30, Color.CYAN, Color.BLACK);
        getStartedButton.setBounds(600, 340, 200, 70);
        backgroundPanel.add(getStartedButton);

        // El boton "Login in"
        RoundedButton loginButton = new RoundedButton("Login in", 15, Color.BLACK, Color.WHITE);
        loginButton.setBounds(1150, 45, 150, 30);
        backgroundPanel.add(loginButton);

        // El boton "Get Started"
        getStartedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Main().setVisible(true);
                dispose(); // Cierra la página principal
            }
        });

        // El boton "Login in"
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Main().setVisible(true);
                dispose(); // Cierra la página principal
            }
        });

        // las paginas (clases) navegación del Homepage

        programsButton.addActionListener(new ActionListener() { // Integrante ->  Boris Alonso
            public void actionPerformed(ActionEvent e) {
                showMessage("Programs Page");
            }
        });

        coursesButton.addActionListener(new ActionListener() { // Integrante -> Claudio Fernando
            public void actionPerformed(ActionEvent e) {
                new CourseEnrollment().setVisible(true);
                dispose();
            }
        });

        aboutUsButton.addActionListener(new ActionListener() { // Integrante -> Luciana Gianmariel
            public void actionPerformed(ActionEvent e) {
                // Llamar a la nueva ventana AboutUs
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
