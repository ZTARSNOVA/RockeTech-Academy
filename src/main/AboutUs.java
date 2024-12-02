package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

public class AboutUs extends JFrame {
    private RoundedButton homeButton;

    public AboutUs() {

        // Configuración de la ventana principal
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
        setLayout(new BorderLayout());

        // Panel con fondo
        BackgroundPanel backgroundPanel = new BackgroundPanel(
                new ImageIcon(getClass().getResource("/resources/FondoUI.jpeg")).getImage()
        );
        add(backgroundPanel, BorderLayout.CENTER);
        backgroundPanel.setLayout(new GridBagLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font OrbitronFont;
        try {
            OrbitronFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/Orbitron-VariableFont_wght.ttf"))
                    .deriveFont(Font.BOLD, 40f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            OrbitronFont = new Font("Orbitron-VariableFont_wght", Font.BOLD, 40);
        }


        // Título
        JLabel title = new JLabel("About Us", JLabel.CENTER);
        title.setFont(OrbitronFont);
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(title);

        
        Font SpaceMonoFont;
        try {
            SpaceMonoFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/SpaceMono-Regular.ttf"))
                    .deriveFont(Font.PLAIN, 15f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            SpaceMonoFont = new Font("Arial", Font.PLAIN, 15);
        }


        String[] integrantes = {"Claudio", "Luciana", "Camila", "Boris"};
        RoundedComboBox<String> comboBox = new RoundedComboBox<>(integrantes, 20);
        comboBox.setFont(SpaceMonoFont);


        /*JLabel title2 = new JLabel("Github: ", JLabel.CENTER);
        title2.setFont(OrbitronFont);
        title2.setForeground(Color.WHITE);
        title2.setAlignmentX(Component.CENTER_ALIGNMENT);
        backgroundPanel.add(title2);*/

        Image iconCamila = new ImageIcon(getClass().getResource("/resources/iconCamila.jpg")).getImage();
        CircularImageButton githubButton = new CircularImageButton(iconCamila, "https://github.com/ZTARSNOVA");

        Image iconBoris = new ImageIcon(getClass().getResource("/resources/iconBoris.png")).getImage();
        CircularImageButton borisButton = new CircularImageButton(iconBoris, "https://github.com/BorisEstradaS");

        Image iconClaudio = new ImageIcon(getClass().getResource("/resources/iconCLBOO.png")).getImage();
        CircularImageButton ClaudioButton = new CircularImageButton(iconClaudio, "https://github.com/CLB00");

        Image iconLuciana = new ImageIcon(getClass().getResource("/resources/iconGianmariel.png")).getImage();
        CircularImageButton LucianaButton = new CircularImageButton(iconLuciana, "https://github.com/GianmarielVargas");


        JPanel comboPanel = new JPanel(new BorderLayout());
        comboPanel.setOpaque(false);
        comboPanel.add(comboBox, BorderLayout.CENTER);
        comboPanel.add(githubButton, BorderLayout.EAST);

        comboPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(comboPanel);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JTextArea infoArea = new JTextArea(15, 45);
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setText("</ Select a member to view their information />");
        Font codeFont = new Font("Consolas", Font.PLAIN, 14);
        infoArea.setFont(codeFont);

        infoArea.setBackground(Color.BLACK);
        infoArea.setForeground(Color.GREEN);
        JScrollPane scrollPane = new JScrollPane(infoArea);

        scrollPane.getViewport().setBackground(Color.BLACK);

        scrollPane.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

        RoundedPanel rightPanel = new RoundedPanel(25, Color.LIGHT_GRAY); // Color Rounded Panel
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));

        contentPanel.add(rightPanel);

        // Acción del combo box
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBox.getSelectedItem();
                switch (selected) {
                    case "Claudio":
                        infoArea.setText("Claudio is studying Data Science.");
                        break;
                    case "Luciana":
                        infoArea.setText("Luciana is studying Data Science.");
                        break;
                    case "Camila":
                        infoArea.setText("Camila is studying Data Science.");
                        break;
                    case "Boris":
                        infoArea.setText("Boris is studying Data Science.");
                        break;
                }
            }
        });

        // Espacio entre el JTextArea y el botón Home
        contentPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        Font Orbitron2Font;
        try {
            Orbitron2Font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/Orbitron-VariableFont_wght.ttf"))
                    .deriveFont(Font.BOLD, 18f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            Orbitron2Font = new Font("Arial", Font.BOLD, 18);
        }

        // Botón Home
        homeButton = new RoundedButton("Home", 15, Color.CYAN, Color.BLACK);
        homeButton.setFont(Orbitron2Font);
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
                SwingUtilities.invokeLater(() -> {
                    new HomePage().setVisible(true); // Abre la ventana HomePage
                });
            }
        });
        contentPanel.add(homeButton);

        backgroundPanel.add(contentPanel);

        // Coordenadas del githubButton
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        backgroundPanel.add(githubButton, gbc);

        // Coordenadas del borisButton
        GridBagConstraints gbcBoris = new GridBagConstraints();
        gbcBoris.gridx = 2;
        gbcBoris.insets = new Insets(5, 5, 5, 5);
        backgroundPanel.add(borisButton, gbcBoris);

        // Coordenadas del ClaudioButton
        GridBagConstraints gbcClaudio= new GridBagConstraints();
        gbcClaudio.gridx = 3;
        gbcClaudio.insets = new Insets(5, 5, 5, 5);
        backgroundPanel.add(ClaudioButton, gbcClaudio);

        // Coordenadas del LucianaButton
        GridBagConstraints gbcLuciana= new GridBagConstraints();
        gbcLuciana.gridx = 4;
        gbcLuciana.insets = new Insets(5, 5, 5, 5);
        backgroundPanel.add(LucianaButton, gbcLuciana);

        setTitle("About Us");
    }

    // Clase para el fondo del panel
    class BackgroundPanel extends JPanel {
        private Image image;

        public BackgroundPanel(Image image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Clase para el panel redondeado
    class RoundedPanel extends JPanel {
        private int radius;
        private Color backgroundColor;

        public RoundedPanel(int radius, Color backgroundColor) {
            this.radius = radius;
            this.backgroundColor = backgroundColor;
            setBackground(backgroundColor);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.setColor(backgroundColor);
            g2.fill(shape);
            g2.setColor(Color.BLACK);
            g2.draw(shape);
            g2.dispose();
        }
    }

    // Clase de botón redondeado
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

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape shape = new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.setColor(backgroundColor);
            g2.fill(shape);
            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        public void setContentAreaFilled(boolean b) {
        }
    }

    class CircularImageButton extends JButton {
        private Image image;
        private String profileLink;

        public CircularImageButton(Image image, String profileLink) {
            this.image = image;
            this.profileLink = profileLink;
            setOpaque(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setPreferredSize(new Dimension(120, 120));
            addActionListener(e -> openGitHubProfile());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, getWidth(), getHeight()));
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }

        private void openGitHubProfile() {
            try {
                Desktop.getDesktop().browse(new java.net.URI(profileLink));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    class RoundedComboBox<E> extends JComboBox<E> {
        private int radius;

        public RoundedComboBox(E[] items, int radius) {
            super(items);
            this.radius = radius;
            setOpaque(false);
            setBackground(Color.YELLOW);
            setForeground(Color.BLACK);
            setFont(new Font("Arial", Font.BOLD, 14));
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.setColor(getBackground());
            g2.fill(shape);
            g2.setColor(Color.GRAY);
            g2.draw(shape);
            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            // No border needed, already drawn in paintComponent
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AboutUs().setVisible(true));
    }
}
