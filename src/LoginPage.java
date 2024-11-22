package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginPage extends JPanel {

    public LoginPage(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel loginLabel = new JLabel("Iniciar Sesión");
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(LoginPage.this, "Por favor ingrese un usuario y una contraseña.");
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
                String line;
                boolean loginSuccess = false;

                while ((line = reader.readLine()) != null) {
                    String[] userDetails = line.split(":");
                    String storedUsername = userDetails[0];
                    String storedPassword = userDetails[1];

                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        loginSuccess = true;
                        break;
                    }
                }

                if (loginSuccess) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Login Exitoso!");
                    cardLayout.show(cardPanel, "Homepage");
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Usuario o contraseña incorrectos.");
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(LoginPage.this, "Error al verificar el usuario.");
            }
        });

        JButton backButton = new JButton("Volver a la Página Principal");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Homepage"));

        add(loginLabel);
        add(usernameField);
        add(passwordField);
        add(loginButton);
        add(backButton);
    }
}


