package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JPanel {

    public Homepage(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Bienvenido a RockeTech");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Login");
            }
        });

        JButton registerButton = new JButton("Registrar");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Register");
            }
        });

        JButton coursesButton = new JButton("Cursos");
        coursesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        coursesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Courses");
            }
        });

        JButton aboutUsButton = new JButton("Sobre Nosotros");
        aboutUsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutUsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "AboutUs");
            }
        });

        add(welcomeLabel);
        add(loginButton);
        add(registerButton);
        add(coursesButton);
        add(aboutUsButton);
    }
}
