package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CoursesPage extends JPanel {

    public CoursesPage(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel coursesLabel = new JLabel("Selecciona los Cursos");
        coursesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JCheckBox javaCheckbox = new JCheckBox("Curso de Java");
        JCheckBox pythonCheckbox = new JCheckBox("Curso de Python");
        JCheckBox webDevCheckbox = new JCheckBox("Curso de Desarrollo Web");

        JButton enrollButton = new JButton("Matricularse");
        enrollButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        enrollButton.addActionListener(e -> {
            String selectedCourses = "Cursos seleccionados: ";
            if (javaCheckbox.isSelected()) selectedCourses += "Java, ";
            if (pythonCheckbox.isSelected()) selectedCourses += "Python, ";
            if (webDevCheckbox.isSelected()) selectedCourses += "Desarrollo Web, ";

            if (selectedCourses.endsWith(", ")) {
                selectedCourses = selectedCourses.substring(0, selectedCourses.length() - 2);
            }
            JOptionPane.showMessageDialog(CoursesPage.this, selectedCourses);
        });

        JButton backButton = new JButton("Volver a la Página Principal");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Homepage"));

        add(coursesLabel);
        add(javaCheckbox);
        add(pythonCheckbox);
        add(webDevCheckbox);
        add(enrollButton);
        add(backButton);
    }
}
