import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Constructor de la ventana principal
    public Main() {
        setTitle("RockeTech");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal que usará CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Crear los diferentes paneles de las páginas
        cardPanel.add(new main.Homepage(cardLayout, cardPanel), "Homepage");
        cardPanel.add(new main.LoginPage(cardLayout, cardPanel), "Login");
        cardPanel.add(new main.RegisterPage(cardLayout, cardPanel), "Register");
        cardPanel.add(new main.CoursesPage(cardLayout, cardPanel), "Courses");
        cardPanel.add(new main.AboutUs(cardLayout, cardPanel), "AboutUs");

        // Inicialmente mostramos la página principal (Homepage)
        cardLayout.show(cardPanel, "Homepage");

        // Agregar el panel principal a la ventana
        add(cardPanel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true); // Mostrar la ventana principal
            }
        });
    }
}
