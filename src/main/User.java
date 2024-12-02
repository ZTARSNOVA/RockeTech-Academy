package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User extends JFrame {
    private JTabbedPane tabbedPane;
    private JTextArea statusTextArea;
    private JTextField coursesField, teacherField, tiemField, modalityField, levelField;
    private JTextField NameField, urnameField, EmailField, PhoneField;
    private JComboBox<String> eventCodeComboBox;
    private JLabel photoLabel;
    private File photoFile;
    private DefaultTableModel courseTableModel, guestTableModel;
    private JLabel searchResultLabel;
    private List<String[]> allcourse;
    private List<String[]> allusers;

    public User(){
        // Ventana del JFrame
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

        //Tab 1: Matricula de un curso
        JPanel tab1Panel = new JPanel(new GridLayout(7, 2));
        tab1Panel.setBackground(new Color(173, 216,230));
        tab1Panel.add(new JLabel("Select a course:"));
        coursesField = new JTextField();
        tab1Panel.add(coursesField);
        tab1Panel.add(new JLabel("Teacher:"));
        teacherField = new JTextField() ;
        tab1Panel.add(teacherField);
        tab1Panel.add(new JLabel("Duration Time:"));
        tiemField = new JTextField();
        tab1Panel.add(tiemField);
        tab1Panel.add(new JLabel("Modality:"));
        modalityField = new JTextField();
        tab1Panel.add(modalityField);
        tab1Panel.add(new JLabel("Level:"));
        levelField = new JTextField();
        tab1Panel.add(levelField);
    }
}
