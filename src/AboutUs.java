package main;

import javax.swing.*;
import java.awt.*;

public class AboutUs extends JPanel {

    public AboutUs(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel aboutUsLabel = new JLabel("Sobre Nosotros");
        aboutUsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea aboutUsText = new JTextArea("Somos una plataforma de cursos online bla bla bla...\n¡Gracias por visitar nuestra página!");
        aboutUsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutUsText.setEditable(false);

        JButton backButton = new JButton("Volver a la Página Principal");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Homepage"));

        add(aboutUsLabel);
        add(aboutUsText);
        add(backButton);
    }
}

