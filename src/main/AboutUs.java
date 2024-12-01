package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Título
        JLabel title = new JLabel("About Us", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE); // Establecer color del texto a negro
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(title);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre componentes

        String[] integrantes = {"Claudio", "Luciana", "Camila", "Boris"};
        JComboBox<String> comboBox = new JComboBox<>(integrantes);
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(comboBox);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre componentes

        JTextArea infoArea = new JTextArea(5, 20);
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setText("Seleccione un integrante para ver su información.");
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(scrollPane);

        // Acción del combo box
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBox.getSelectedItem();
                switch (selected) {
                    case "Claudio":
                        infoArea.setText("Claudio está cursando la carrera de Ciencia de Datos.");
                        break;
                    case "Luciana":
                        infoArea.setText("Luciana está cursando la carrera de Ciencia de Datos.");
                        break;
                    case "Camila":
                        infoArea.setText("Camila está cursando la carrera de Ciencia de Datos.");
                        break;
                    case "Boris":
                        infoArea.setText("Boris está cursando la carrera de Ciencia de Datos.");
                        break;
                }
            }
        });


        backgroundPanel.add(contentPanel);

        // Espacio entre el JTextArea y el botón Home
        contentPanel.add(Box.createRigidArea(new Dimension(0, 40))); // Aumenta el tamaño del espacio aquí

        // Botón Home
        homeButton = new RoundedButton("Home", 15, Color.CYAN, Color.BLACK);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AboutUs aboutUs = new AboutUs();
            aboutUs.setVisible(true);
        });
    }

}
