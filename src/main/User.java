package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.IOException;


public class User extends JFrame {

    private JTabbedPane tabbedPane;
    private JTextArea statusTextArea;
    private JComboBox<String> courseComboBox, teacherComboBox, timeComboBox, modalityComboBox, levelComboBox;
    private JTextField NameField, SurnameField, DNIField, DateField, PlaceField, NationalityField, AddressField, PhoneField, EmailField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> EducationComboBox;

    private JLabel photoLabel;
    private File photoFile;
    private DefaultTableModel TableModel, tTableModel;
    private JLabel searchResultLabel;
    private List<String[]> allcourses;
    private List<String[]> allalumnos;



    public User() {

        // Configuración de la ventana principal
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar la ventana
        setLayout(new BorderLayout());



        // Creación del JTabbedPane
        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(600, 400)); // Ajusta el tamaño del tabbedPane



        // Tab 1: Registro de Cursos

        JPanel tab1Panel = new JPanel(new GridLayout(7, 2, 5, 5)); // Layout con espaciado


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
        registerButton.setPreferredSize(new Dimension(150, 30));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course = (String) courseComboBox.getSelectedItem();
                String teacher = (String) teacherComboBox.getSelectedItem();
                String time = (String) timeComboBox.getSelectedItem();
                String modality = (String) modalityComboBox.getSelectedItem();
                String level = (String) levelComboBox.getSelectedItem();

                if (course == null || teacher == null || time == null || modality == null || level == null) {
                    JOptionPane.showMessageDialog(User.this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter("courses.txt", true))) {
                    bw.write(course + ";" + teacher + ";" + time + ";" + modality + ";" + level);
                    bw.newLine();
                    JOptionPane.showMessageDialog(User.this, "Course registered successfully", "Course Registration", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(User.this, "Error registering the course", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        tab1Panel.add(registerButton);

        tabbedPane.addTab("Register Course", tab1Panel);

        add(tabbedPane);

        // Inicializar modelo de la tabla para Tab 2
        String[] bookColumnNames = {"Course", "Teacher", "Duration", "Modality", "Level"};
        TableModel = new DefaultTableModel(bookColumnNames, 0);

        // Obtener todos los datos desde el archivo courses.txt y llenar la tabla
        allcourses = readDataFromFile("courses.txt");
        updateTable(TableModel, allcourses);

        // Tab 2: Buscar course y mostrar en JTable
        JPanel tab2Panel = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchResultLabel = new JLabel();
        searchPanel.add(new JLabel("Search course:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        tab2Panel.add(searchPanel, BorderLayout.NORTH);

        JTable courseTable = new JTable(TableModel);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        tab2Panel.add(scrollPane, BorderLayout.CENTER);
        tab2Panel.add(searchResultLabel, BorderLayout.SOUTH); // Result label

        searchButton.addActionListener(new ActionListener() { //evento boton buscar
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText().trim();
                boolean found = false;
                TableModel.setRowCount(0); // Limpiar la tabla

                // Actualizar la lista y la tabla después de registrar el curso
                allcourses = readDataFromFile("courses.txt");
                updateTable(TableModel, allcourses);

                for (String[] book : allcourses) {
                    if (book[0].equalsIgnoreCase(searchTerm)) {
                        TableModel.addRow(book);
                        found = true;
                    }
                }

                if (!found) {
                    searchResultLabel.setText("Libro no encontrado");
                } else {
                    searchResultLabel.setText("Búsqueda encontrada!");
                }
            }
        });

        tabbedPane.addTab("Search course", tab2Panel);

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
                String name = NameField.getText();
                String surname = SurnameField.getText();
                String email = EmailField.getText();
                String phone = PhoneField.getText();
                String eventCode = (String) EducationComboBox.getSelectedItem();

                if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || phone.isEmpty() || eventCode.isEmpty() || photoFile == null) {
                    JOptionPane.showMessageDialog(User.this, "Complete todos los campos y cargue una foto", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (BufferedWriter bw = new BufferedWriter(new FileWriter("alumnos.txt", true))) {
                    bw.write(name + ";" + surname + ";" + email + ";" + phone + ";" + eventCode + ";" + photoFile.getAbsolutePath());
                    bw.newLine();
                    JOptionPane.showMessageDialog(User.this, "Invitado registrado exitosamente", "Registro de Invitado", JOptionPane.INFORMATION_MESSAGE);

                    // Actualizar la lista y la tabla después de registrar el alumno
                    allalumnos.add(new String[] { name, surname, email, phone, eventCode, photoFile.getAbsolutePath() });
                    updateTable(TableModel, allalumnos);

                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(User.this, "Error al registrar el invitado", "Error", JOptionPane.ERROR_MESSAGE);
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

        // Inicializar modelo de la tabla para Tab 4
        String[] guestColumnNames = {"First Name", "Last Name", "Email", "Phone", "Educational level", "Photo"};
        TableModel = new DefaultTableModel(guestColumnNames, 0);

        // Obtener todos los datos desde el archivo alumno.txt y llenar la tabla
        allalumnos = readDataFromFile("alumnos.txt");
        updateTable(TableModel, allalumnos);

        // Tab 4: Buscar y mostrar en JTable
        JPanel tab4Panel = new JPanel(new BorderLayout());

        JPanel guestSearchPanel = new JPanel();
        JTextField guestSearchField = new JTextField(20);
        JButton guestSearchButton = new JButton("Buscar");
        JLabel guestSearchResultLabel = new JLabel();
        guestSearchPanel.add(new JLabel("Buscar alumno:"));
        guestSearchPanel.add(guestSearchField);
        guestSearchPanel.add(guestSearchButton);
        tab4Panel.add(guestSearchPanel, BorderLayout.NORTH);

        JTable guestTable = new JTable(TableModel);
        JScrollPane guestScrollPane = new JScrollPane(guestTable);
        tab4Panel.add(guestScrollPane, BorderLayout.CENTER);
        tab4Panel.add(guestSearchResultLabel, BorderLayout.SOUTH); // Result label

        guestSearchButton.addActionListener(new ActionListener() { //evento boton buscar alumno
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = guestSearchField.getText().trim();
                boolean found = false;
                TableModel.setRowCount(0); // Limpiar la tabla

                // Actualizar la lista y la tabla después de registrar el alumno
                allalumnos = readDataFromFile("alumnos.txt");
                updateTable(TableModel, allalumnos);

                for (String[] guest : allalumnos) {
                    if (guest[0].equalsIgnoreCase(searchTerm)) {
                        TableModel.addRow(guest);
                        found = true;
                    }
                }

                if (!found) {
                    guestSearchResultLabel.setText("alumno encontrado");
                } else {
                    guestSearchResultLabel.setText("Búsqueda encontrada!");
                }
            }
        });

        tabbedPane.addTab("Buscar alumno", tab4Panel);

        // Agregar el JTabbedPane al JFrame
        add(tabbedPane);

        // Configuración del área de estado
        statusTextArea = new JTextArea(5, 40);
        statusTextArea.setEditable(false);
        add(new JScrollPane(statusTextArea), BorderLayout.SOUTH);

        // Mostrar la ventana
        setVisible(true);
    }

    // Metodo para leer datos desde un archivo y retornarlos como una lista de arrays de Strings
    private List<String[]> readDataFromFile(String filename) {
        List<String[]> data = new ArrayList<>();
        // BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                data.add(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }



    // Metodo para actualizar la tabla con los datos del archivo
    private void updateTable(DefaultTableModel tableModel, List<String[]> data) {
        // Limpiar tabla actual
        tableModel.setRowCount(0);

        // Llenar la tabla con los datos
        for (String[] row : data) {
            tableModel.addRow(row);
        }
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



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new User();
            }
        });
    }
}
