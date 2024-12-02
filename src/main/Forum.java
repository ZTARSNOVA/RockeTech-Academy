package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

public class Forum extends JFrame {
    private RoundedButton homeButton;

    public Forum() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize window
        setLayout(new BorderLayout());

        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon(getClass().getResource("/resources/FondoUI.jpeg")).getImage());
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

        // Title
        JLabel title = new JLabel("Forum", JLabel.CENTER);
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


        JPanel CommentPanel = new JPanel();
        CommentPanel.setSize(400, 200);
        CommentPanel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Enter your comment");
        JTextArea text = new JTextArea(5, 20);
        JButton button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String comment = text.getText();
                if (comment.length() > 200) {
                    JOptionPane.showMessageDialog(CommentPanel, "Character limit exceeded");
                } else if (comment.length() < 25) {
                    JOptionPane.showMessageDialog(CommentPanel, "Minimum character requirement not met");
                } else {
                    JOptionPane.showMessageDialog(CommentPanel, "Comment submitted");
                }
            }
        });
        CommentPanel.add(label);
        CommentPanel.add(text);
        CommentPanel.add(button);
        CommentPanel.setVisible(true);

        roundedPanel.add(CommentPanel);
        contentPanel.add(roundedPanel);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 40))); // Space between Confirm and Home button


        Font Orbitron2Font;
        try {
            Orbitron2Font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/Orbitron-VariableFont_wght.ttf"))
                    .deriveFont(Font.BOLD, 18f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            Orbitron2Font = new Font("Arial", Font.BOLD, 18);
        }

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
        setTitle("Forum");
    }
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



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Forum().setVisible(true));
    }
}
