package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

public class Courses extends JFrame {

    private RoundedButton homeButton;
    private RoundedButton confirmButton;

    public Courses() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize window
        setLayout(new BorderLayout());

        BackgroundPanel backgroundPanel = new BackgroundPanel(
                new ImageIcon(getClass().getResource("/resources/FondoUI.jpeg")).getImage()
        );
        add(backgroundPanel, BorderLayout.CENTER);
        backgroundPanel.setLayout(new GridBagLayout()); // Center content

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

        Font SpaceMonoFont;
        try {
            SpaceMonoFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/SpaceMono-Regular.ttf"))
                    .deriveFont(Font.PLAIN, 15f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            SpaceMonoFont = new Font("Arial", Font.PLAIN, 15);
        }

        // Title
        JLabel title = new JLabel("Course Information", JLabel.CENTER);
        title.setFont(OrbitronFont);
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(title);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between title and courses

        // Panel for rounded border
        RoundedPanel roundedPanel = new RoundedPanel(25, Color.yellow); // Color Rounded Panel
        roundedPanel.setLayout(new GridBagLayout()); // Center content
        roundedPanel.setOpaque(false);
        roundedPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Course JCheckBoxes
        JCheckBox course1 = new JCheckBox("Introduction to Programming");
        course1.setFont(SpaceMonoFont);

        JCheckBox course2 = new JCheckBox("Robotics");
        course2.setFont(SpaceMonoFont);

        JCheckBox course3 = new JCheckBox("Digital Art and Graphic Design");
        course3.setFont(SpaceMonoFont);

        JCheckBox course4 = new JCheckBox("Programming and Video Game Development");
        course4.setFont(SpaceMonoFont);

        JCheckBox[] courses = {course1, course2, course3, course4};

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setOpaque(false);
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        for (JCheckBox course : courses) {
            course.setOpaque(false);
            course.setForeground(Color.BLACK);
            course.setFont(SpaceMonoFont);
            checkBoxPanel.add(course);
        }

        roundedPanel.add(checkBoxPanel, new GridBagConstraints());

        contentPanel.add(roundedPanel);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Space between courses and Confirm button

        // JTextArea and JScrollPane for displaying course information

        JTextArea infoArea = new JTextArea(15, 45);
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setText("</ Select a course to view more details />");
        // Font to Consoles
        Font codeFont = new Font("Consolas", Font.PLAIN, 14); // Fuente Consolas, normal, tamaño 14
        infoArea.setFont(codeFont);


        // the background and text color of the JTextArea
        infoArea.setBackground(Color.BLACK); // Background color of the text area
        infoArea.setForeground(Color.green); // Text color for contrast
        JScrollPane scrollPane = new JScrollPane(infoArea);

        // background color of the JScrollPane
        scrollPane.getViewport().setBackground(Color.BLACK);

        //the border color of the JScrollPane
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

        confirmButton = new RoundedButton("Confirm Course", 15, Color.WHITE, Color.BLACK);
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Confirm button action
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder infoCourses = new StringBuilder();
                if (course1.isSelected()) {
                    infoCourses.append("Introduction to Programming:\n")
                            .append("This course covers the basics of programming, including variables, loops, and functions.\n\n");
                }
                if (course2.isSelected()) {
                    infoCourses.append("Robotics:\n")
                            .append("Learn about basic robotics concepts and programming robots to perform tasks.\n\n");
                }
                if (course3.isSelected()) {
                    infoCourses.append("Digital Art and Graphic Design:\n")
                            .append("Explore digital art tools and graphic design techniques for creative projects.\n\n");
                }
                if (course4.isSelected()) {
                    infoCourses.append("Programming and Video Game Development:\n")
                            .append("Learn to program and develop your own video games from scratch.\n\n");
                }

                if (infoCourses.length() == 0) {
                    infoCourses.append("</ No courses selected />");
                }

                infoArea.setText(infoCourses.toString());
            }
        });
        contentPanel.add(confirmButton);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 40))); // Space between Confirm and Home button


        Font Orbitron2Font;
        try {
            Orbitron2Font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/Orbitron-VariableFont_wght.ttf"))
                    .deriveFont(Font.BOLD, 18f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            Orbitron2Font = new Font("Arial", Font.BOLD, 18);
        }
        confirmButton.setFont(Orbitron2Font);

        // Home button
        homeButton = new RoundedButton("Home", 15, Color.CYAN, Color.BLACK);
        homeButton.setFont(Orbitron2Font);

        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.addActionListener(e -> {
            dispose(); // Close current window
            SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
        });
        contentPanel.add(homeButton);

        backgroundPanel.add(contentPanel);

        RoundedPanel rightPanel = new RoundedPanel(25, Color.lightGray); // Color Rounded Panel
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(scrollPane, BorderLayout.CENTER);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));

        backgroundPanel.add(rightPanel, new GridBagConstraints() {{


        }});
        setTitle("Courses");
    }

    // Rounded Panel class
    class RoundedPanel extends JPanel {
        private int radius;
        private Color backgroundColor;

        public RoundedPanel(int radius, Color backgroundColor) {
            this.radius = radius;
            this.backgroundColor = backgroundColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(backgroundColor);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
            g2.dispose();
        }
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

    // Rounded button class
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
        SwingUtilities.invokeLater(() -> new Courses().setVisible(true));
    }
}
