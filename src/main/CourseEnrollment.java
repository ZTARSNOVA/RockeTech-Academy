package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseEnrollment extends JFrame {

    public CourseEnrollment() {
        // Configurar la ventana principal
        setTitle("Matrícula de Cursos");
        setSize(400, 300);  // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrar la ventana en la pantalla

        // Panel principal para contener los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Diseño vertical

        // Crear los JCheckBox para los cursos
        JCheckBox curso1 = new JCheckBox("Curso de Java");
        JCheckBox curso2 = new JCheckBox("Curso de Python");
        JCheckBox curso3 = new JCheckBox("Curso de JavaScript");
        JCheckBox curso4 = new JCheckBox("Curso de SQL");

        // Agregar los cursos al panel
        panel.add(curso1);
        panel.add(curso2);
        panel.add(curso3);
        panel.add(curso4);

        // Crear el botón para confirmar la matrícula
        JButton confirmarButton = new JButton("Confirmar Matrícula");

        // Acción cuando se hace clic en el botón
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un StringBuilder para guardar los cursos seleccionados
                StringBuilder cursosMatriculados = new StringBuilder("Has sido matriculado en:\n");

                // Verificar qué cursos han sido seleccionados
                if (curso1.isSelected()) {
                    cursosMatriculados.append("- Curso de Java\n");
                }
                if (curso2.isSelected()) {
                    cursosMatriculados.append("- Curso de Python\n");
                }
                if (curso3.isSelected()) {
                    cursosMatriculados.append("- Curso de JavaScript\n");
                }
                if (curso4.isSelected()) {
                    cursosMatriculados.append("- Curso de SQL\n");
                }

                // Si ningún curso fue seleccionado, mostrar mensaje adecuado
                if (cursosMatriculados.length() == "Has sido matriculado en:\n".length()) {
                    cursosMatriculados.append("No has seleccionado ningún curso.");
                }

                // Mostrar el mensaje de agradecimiento
                JOptionPane.showMessageDialog(CourseEnrollment.this, cursosMatriculados.toString(), "Matrícula Confirmada", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Agregar el botón al panel
        panel.add(confirmarButton);

        // Agregar el panel a la ventana
        add(panel);

        // Hacer visible la ventana
        setVisible(true);
    }

    /*public static void main(String[] args) {
        new CourseEnrollment();
    }*/
}
