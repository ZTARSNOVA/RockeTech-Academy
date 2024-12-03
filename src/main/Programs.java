package main;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

public class Programs extends JFrame {

    public Programs() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize window
        setLayout(new BorderLayout());
        setTitle("Programs");

        // Fondo
        BackgroundPanel backgroundPanel = new BackgroundPanel(
                new ImageIcon(getClass().getResource("/resources/FondoUI.jpeg")).getImage()
        );
        add(backgroundPanel, BorderLayout.CENTER);
        backgroundPanel.setLayout(new BorderLayout());

        // JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 18));

        // Tabuladores
        tabbedPane.addTab("Programación y Desarrollo", createProgramPanel());
        tabbedPane.addTab("Ciencias de Datos", createDataPanel());
        tabbedPane.addTab("Robótica e Ingeniería", createRoboticsPanel());
        tabbedPane.addTab("Arte Digital y Diseño", createArtPanel());

        backgroundPanel.add(tabbedPane, BorderLayout.CENTER);

        // Home Boton
        RoundedButton homeButton = new RoundedButton("Home", 15, Color.CYAN, Color.BLACK);
        homeButton.setFont(new Font("Arial", Font.BOLD, 18));
        homeButton.addActionListener(e -> {
            dispose(); // Cerrar ventana
            SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        buttonPanel.add(homeButton);

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Panel de Programación y Desarrollo
    private JPanel createProgramPanel() {
        String content = """
                Desarrollo Web Full Stack
                
                        • Introducción a HTML, CSS y JavaScript
                        • Desarrollo de aplicaciones con React
                        • Programación del backend con Node.js
                
                
                Desarrollo de Apps Móviles
                
                        • Fundamentos de desarrollo móvil
                        • Programación en Flutter
                        • Publicación de apps en tiendas digitales
                
                
                Introducción a la Inteligencia Artificial
                
                        • Programación básica en Python
                        • Algoritmos de Machine Learning
                        • Redes neuronales artificiales
                
                
                Programación de Videojuegos
                
                        • Introducción al diseño de videojuegos
                        • Desarrollo en Unity
                        • Modelado básico 3D
                
                
                Automatización con Python
                
                        • Fundamentos de Python
                        • Creación de scripts para automatización
                        • Manejo de datos con Pandas
                """;
        return createContentPanel(content);
    }

    // Panel de Ciencias de Datos
    private JPanel createDataPanel() {
        String content = """
                Ciencia de Datos para Principiantes
                
                        • Fundamentos de análisis de datos
                        • Introducción a bases de datos SQL
                        • Visualización de datos con Tableau
                
                
                Big Data y Análisis Avanzado
                
                        • Fundamentos de Big Data
                        • Procesamiento de datos con Hadoop
                        • Análisis avanzado con Spark
                
                
                Visualización de Datos
                
                        • Principios de diseño de dashboards
                        • Herramientas como Power BI y Excel avanzado
                        • Storytelling con datos
                
                
                Análisis Estadístico Aplicado
                
                        • Fundamentos de estadística descriptiva
                        • Probabilidades y regresiones
                        • Modelos predictivos básicos
                
                
                Técnicas de Machine Learning
                
                        • Aprendizaje supervisado
                        • Aprendizaje no supervisado
                        • Implementación de modelos en proyectos
                """;
        return createContentPanel(content);
    }

    // Panel de Robótica e Ingeniería
    private JPanel createRoboticsPanel() {
        String content = """
                Robótica para Principiantes
                
                        • Diseño y construcción de robots
                        • Programación básica de sensores
                        • Pruebas y ajustes en proyectos reales
                
                
                Introducción al Internet de las Cosas (IoT)
                
                        • Conexión de dispositivos inteligentes
                        • Programación de dispositivos IoT
                        • Seguridad en redes IoT
                
                
                Ingeniería de Sistemas Eléctricos
                
                        • Principios de electricidad y circuitos
                        • Diseño de sistemas eléctricos básicos
                        • Simulación y pruebas
                
                
                Impresión 3D y Fabricación Digital
                
                        • Modelado 3D en software especializado
                        • Manejo de impresoras 3D
                        • Optimización de diseños para impresión
                
                
                Electrónica Básica
                
                        • Componentes electrónicos esenciales
                        • Creación de circuitos simples
                        • Uso de plataformas como Arduino
                """;
        return createContentPanel(content);
    }

    // Panel de Arte Digital y Diseño
    private JPanel createArtPanel() {
        String content = """
                Diseño Gráfico Digital
                
                        • Introducción a herramientas como Photoshop e Illustrator
                        • Principios de diseño visual
                        • Creación de piezas gráficas
                
                
                Animación Digital
                
                        • Fundamentos de animación 2D y 3D
                        • Uso de software como Blender o Maya
                        • Producción de proyectos animados
                
                
                Fotografía y Edición Digital
                
                        • Técnicas de fotografía
                        • Edición con Lightroom y Photoshop
                        • Creación de portfolios visuales
                
                
                Producción de Música Digital
                
                        • Fundamentos de audio y grabación
                        • Uso de DAWs (Digital Audio Workstations)
                        • Mezcla y masterización básica
                
                
                Arte Generativo con Código
                
                        • Introducción a programación creativa
                        • Uso de Processing y P5.js
                        • Creación de obras digitales interactivas
                """;
        return createContentPanel(content);
    }

    // Metodo para crear paneles con el detalle de cursos
    private JPanel createContentPanel(String content) {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea(content);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.GREEN);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    // Background panel class
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
            Shape shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
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
        SwingUtilities.invokeLater(() -> new Programs().setVisible(true));
    }
}
