package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterPage extends JPanel {

    public RegisterPage(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel registerLabel = new JLabel("Registrar Nueva Cuenta");
        registerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        JButton registerButton = new JButton("Registrar");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterPage.this, "Por favor ingrese un usuario y una contraseña.");
                    return;
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt", true))) {
                    writer.write(username + ":" + password);
                    writer.newLine();
                    JOptionPane.showMessageDialog(RegisterPage.this, "Registro Exitoso!");
                    cardLayout.show(cardPanel, "Homepage");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(RegisterPage.this, "Error al guardar el usuario.");
                }
            }
        });

        JButton backButton = new JButton("Volver a la Página Principal");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Homepage"));

        add(registerLabel);
        add(usernameField);
        add(passwordField);
        add(registerButton);
        add(backButton);
    }
}

