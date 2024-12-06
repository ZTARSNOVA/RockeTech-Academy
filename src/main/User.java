package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class User extends JFrame {

    private JTabbedPane tabbedPane;
    private JTextArea statusTextArea;
    private JComboBox<String> courseComboBox, teacherComboBox, timeComboBox, modalityComboBox, levelComboBox;
    private JTextField NameField, SurnameField, DNIField, DateField, PlaceField, NationalityField, AddressField, PhoneField, EmailField;
    private JComboBox<String> genderComboBox, EducationComboBox;
    private JLabel photoLabel;
    private File photoFile;
    private DefaultTableModel TableModel;
    private JLabel searchResultLabel;
    private DefaultTableModel courseTableModel; // Para los cursos
    private DefaultTableModel studentTableModel; // Para los estudiantes
    private List<String[]> allCourses = new ArrayList<>();
    private List<String[]> allStudents = new ArrayList<>();


    public User() {
        // Configuración de la ventana principal
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
        setLayout(new BorderLayout());

        // el fondo personalizado
        BackgroundPanel backgroundPanel = new BackgroundPanel(
                new ImageIcon(getClass().getResource("/resources/FondoUI.jpeg")).getImage()
        );
        backgroundPanel.setLayout(new BorderLayout());
        add(backgroundPanel, BorderLayout.CENTER);



        // TabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(200, 500));
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tabbedPane.setOpaque(false);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        backgroundPanel.add(tabbedPane, BorderLayout.CENTER);


        addCourseRegistrationTab();
        addCourseSearchTab();
        addStudentRegistrationTab();
        addStudentSearchTab();

        // Botón Home
        RoundedButton homeButton = new RoundedButton("Home", 15, Color.CYAN, Color.BLACK);
        homeButton.setFont(new Font("Arial", Font.BOLD, 18));
        homeButton.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.add(homeButton);

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Metodos para configurar las pestañas
    private void addCourseRegistrationTab() {
        JPanel tab1Panel = new JPanel(new GridLayout(6, 2, 5, 5));
        tab1Panel.add(new JLabel("Select a course:"));
        courseComboBox = new JComboBox<>(new String[]{"Introduction to Programming", "Robotics", "Digital Art and Graphic Design", "Programming and Video Game Development"});
        tab1Panel.add(courseComboBox);

        tab1Panel.add(new JLabel("Teacher:"));
        teacherComboBox = new JComboBox<>(new String[]{"Camila", "Claudio", "Boris", "Luciana"});
        tab1Panel.add(teacherComboBox);

        tab1Panel.add(new JLabel("Duration Time:"));
        timeComboBox = new JComboBox<>(new String[]{"1 Month", "3 Months", "6 Months", "1 Year"});
        tab1Panel.add(timeComboBox);

        tab1Panel.add(new JLabel("Modality:"));
        modalityComboBox = new JComboBox<>(new String[]{"Online", "In-Person", "Hybrid"});
        tab1Panel.add(modalityComboBox);

        tab1Panel.add(new JLabel("Level:"));
        levelComboBox = new JComboBox<>(new String[]{"Beginner", "Intermediate", "Advanced"});
        tab1Panel.add(levelComboBox);

        JButton registerButton = new JButton("Register Course");
        registerButton.addActionListener(e -> {
            String course = (String) courseComboBox.getSelectedItem();
            String teacher = (String) teacherComboBox.getSelectedItem();
            String time = (String) timeComboBox.getSelectedItem();
            String modality = (String) modalityComboBox.getSelectedItem();
            String level = (String) levelComboBox.getSelectedItem();

            if (course == null || teacher == null || time == null || modality == null || level == null) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("courses.txt", true))) {
                String[] newCourse = {course, teacher, time, modality, level};
                bw.write(String.join(";", newCourse));
                bw.newLine();
                JOptionPane.showMessageDialog(this, "Course registered successfully", "Course Registration", JOptionPane.INFORMATION_MESSAGE);

                // Agregar a la lista global
                if (allCourses == null) allCourses = new ArrayList<>();
                allCourses.add(newCourse);

                // Actualiza la tabla
                updateTable(TableModel, allCourses);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error registering the course", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        tab1Panel.add(registerButton);

        tabbedPane.addTab("Register Course", tab1Panel);
    }

    private void addCourseSearchTab() {
        JPanel tab2Panel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton1 = new JButton("Search");
        JLabel searchResultLabel = new JLabel();
        searchPanel.add(new JLabel("Search course:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton1);
        tab2Panel.add(searchPanel, BorderLayout.NORTH);

        String[] courseColumnNames = {"Course", "Teacher", "Duration", "Modality", "Level"};
        courseTableModel = new DefaultTableModel(courseColumnNames, 0);
        JTable courseTable = new JTable(courseTableModel);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        tab2Panel.add(scrollPane, BorderLayout.CENTER);
        tab2Panel.add(searchResultLabel, BorderLayout.SOUTH);

        searchButton1.addActionListener(e -> {
            String searchTerm = searchField.getText().trim();
            if (searchTerm.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a course name to search.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            courseTableModel.setRowCount(0);
            allCourses = readDataFromFile("courses.txt");

            boolean found = false;
            for (String[] course : allCourses) {
                if (course[0].equalsIgnoreCase(searchTerm)) {
                    courseTableModel.addRow(course);
                    found = true;
                }
            }

            searchResultLabel.setText(found ? "Course found!" : "Course not found.");
        });

        tabbedPane.addTab("Search Course", tab2Panel);
    }


    private void addStudentRegistrationTab() {
        // Tab 3: Registrar alumnno
        JPanel tab3Panel = new JPanel(null);


        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(10, 10, 150, 25);
        tab3Panel.add(nameLabel);
        NameField = new JTextField();
        NameField.setBounds(170, 10, 200, 25);
        tab3Panel.add(NameField);

        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setBounds(10, 45, 150, 25);
        tab3Panel.add(surnameLabel);
        SurnameField = new JTextField();
        SurnameField .setBounds(170, 45, 200, 25);
        tab3Panel.add(SurnameField );

        JLabel dniLabel = new JLabel("ID Number:");
        dniLabel.setBounds(10, 80, 150, 25);
        tab3Panel.add(dniLabel);
        DNIField = new JTextField();
        DNIField.setBounds(170, 80, 200, 25);
        tab3Panel.add(DNIField);

        JLabel dateLabel = new JLabel("Date of Birth:");
        dateLabel.setBounds(10, 115, 150, 25);
        tab3Panel.add(dateLabel);
        DateField = new JTextField("dd/mm/yyyy");
        DateField.setBounds(170, 115, 200, 25);
        tab3Panel.add(DateField);

        JLabel placeLabel = new JLabel("Place of Birth:");
        placeLabel.setBounds(10, 150, 150, 25);
        tab3Panel.add(placeLabel);
        PlaceField = new JTextField();
        PlaceField.setBounds(170, 150, 200, 25);
        tab3Panel.add(PlaceField);

        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setBounds(10, 185, 150, 25);
        tab3Panel.add(nationalityLabel);
        NationalityField = new JTextField();
        NationalityField.setBounds(170, 185, 200, 25);
        tab3Panel.add(NationalityField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(10, 220, 150, 25);
        tab3Panel.add(genderLabel);
        genderComboBox= new JComboBox<>(new String[]{"Male", "Female"});
        genderComboBox.setBounds(170, 220, 200, 25);
        tab3Panel.add(genderComboBox);

        JLabel addressLabel = new JLabel("Full Address:");
        addressLabel.setBounds(10, 255, 150, 25);
        tab3Panel.add(addressLabel);
        AddressField = new JTextField();
        AddressField.setBounds(170, 255, 200, 25);
        tab3Panel.add(AddressField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(10, 290, 150, 25);
        tab3Panel.add(phoneLabel);
        PhoneField = new JTextField();
        PhoneField.setBounds(170, 290, 200, 25);
        tab3Panel.add(PhoneField);

        JLabel emailLabel = new JLabel("Email Address:");
        emailLabel.setBounds(10, 325, 150, 25);
        tab3Panel.add(emailLabel);
        EmailField = new JTextField();
        EmailField.setBounds(170, 325, 200, 25);
        tab3Panel.add(EmailField);

        JLabel educationLabel = new JLabel("Educational Level:");
        educationLabel.setBounds(10, 360, 150, 25);
        tab3Panel.add(educationLabel);
        EducationComboBox = new JComboBox<>(new String[]{"Primary", "Secondary", "High School"});
        EducationComboBox.setBounds(170, 360, 200, 25);
        tab3Panel.add(EducationComboBox);

        photoLabel = new JLabel();
        photoLabel.setBounds(500, 10, 150, 150);
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        tab3Panel.add(photoLabel);



        JButton loadPhotoButton = new JButton("Load Photo");
        loadPhotoButton.setBounds(500, 170, 150, 25);
        loadPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //evento de cargar foto
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(User.this) == JFileChooser.APPROVE_OPTION) {
                    photoFile = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(new ImageIcon(photoFile.getAbsolutePath()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
                    photoLabel.setIcon(icon);
                }
            }
        });
        tab3Panel.add(loadPhotoButton);

        JButton registerGuestButton = new JButton("Register");
        registerGuestButton.setBounds(120, 450, 100, 25);
        registerGuestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = NameField.getText().trim();
                String surname = SurnameField.getText().trim();
                String dni = DNIField.getText().trim();
                String date = DateField.getText().trim();
                String place = PlaceField.getText().trim();
                String gender = (String) genderComboBox.getSelectedItem();
                String education = (String) EducationComboBox.getSelectedItem();
                String phone = PhoneField.getText().trim();
                String email = EmailField.getText().trim();

                if (name.isEmpty() || surname.isEmpty() || dni.isEmpty() || date.isEmpty() ||
                        place.isEmpty() || gender == null || education == null || phone.isEmpty() || email.isEmpty() || photoFile == null) {
                    JOptionPane.showMessageDialog(User.this, "Complete all fields and upload a photo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter("alumnos.txt", true))) {
                    String[] newStudent = {name, surname, dni, date, place, gender, education, phone, email, photoFile.getAbsolutePath()};
                    bw.write(String.join(";", newStudent));
                    bw.newLine();
                    JOptionPane.showMessageDialog(User.this, "Student registered successfully!", "Registration", JOptionPane.INFORMATION_MESSAGE);

                    allStudents.add(newStudent);
                    updateTable(studentTableModel, allStudents);

                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(User.this, "Error saving student data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        tab3Panel.add(registerGuestButton);

        JButton clearGuestButton = new JButton("Clean");
        clearGuestButton.setBounds(230, 450, 100, 25);
        clearGuestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NameField.setText("");
                SurnameField.setText("");
                EmailField.setText("");
                PhoneField.setText("");
                EducationComboBox.setSelectedIndex(0);
                photoLabel.setIcon(null);
                photoFile = null;
            }
        });
        tab3Panel.add(clearGuestButton);

        tabbedPane.addTab("Register alumno", tab3Panel);
    }

    private void addStudentSearchTab() {
        JPanel tab4Panel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton2 = new JButton("Search");
        JLabel searchResultLabel = new JLabel();

        searchPanel.add(new JLabel("Search student by DNI:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton2);
        tab4Panel.add(searchPanel, BorderLayout.NORTH);

        String[] studentColumnNames = {"Name", "Surname", "DNI", "Date of Birth", "Place of Birth", "Gender",
                "Education Level", "Phone", "Email"};
        studentTableModel = new DefaultTableModel(studentColumnNames, 0);
        JTable studentTable = new JTable(studentTableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        tab4Panel.add(scrollPane, BorderLayout.CENTER);
        tab4Panel.add(searchResultLabel, BorderLayout.SOUTH);

        searchButton2.addActionListener(e -> {
            String searchTerm = searchField.getText().trim();
            boolean found = false;
            studentTableModel.setRowCount(0); // Limpiar tabla

            // Cargar estudiantes desde el archivo
            allStudents = readDataFromFile("alumnos.txt");
            if (allStudents.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No students found in the database.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (String[] student : allStudents) {
                if (student[2].equalsIgnoreCase(searchTerm)) { // Buscar por DNI (índice 2)
                    studentTableModel.addRow(student);
                    found = true;
                }
            }

            searchResultLabel.setText(found ? "Student found!" : "Student not found.");
        });



        tabbedPane.addTab("Search Student", tab4Panel);
    }



    // Metodo para leer datos desde un archivo y retornarlos como una lista de arrays de Strings
    private List<String[]> readDataFromFile(String fileName) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(";");
                if (row.length >= 7) {
                    data.add(row);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading file: " + fileName, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }



    // Metodo para actualizar la tabla con los datos del archivo
    private void updateTable(DefaultTableModel tableModel, List<String[]> data) {
        // Limpiar tabla actual
        tableModel.setRowCount(0);


        for (String[] row : data) {
            tableModel.addRow(row);
        }
    }


    // el fondo personalizado
    private static class BackgroundPanel extends JPanel {
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

    public class RoundedButton extends JButton {
        private Color backgroundColor;
        private Color textColor;

        public RoundedButton(String text, int arcWidth, Color backgroundColor, Color textColor) {
            super(text);
            this.backgroundColor = backgroundColor;
            this.textColor = textColor;
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        
            g2.setColor(textColor);
            g2.setFont(getFont());
            FontMetrics fm = g2.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();
            g2.drawString(getText(), x, y);

            g2.dispose();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(User::new);
    }
}
