package swing;

import javax.swing.*;
import java.awt.*;

public class PanelColor extends JLayeredPane {
    private Image backgroundImage;

    public PanelColor() {
        try {
            // Carga la imagen de fondo
            backgroundImage = new ImageIcon(getClass().getResource("/resources/FondoUI.jpeg")).getImage();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // la imagen de fondo
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
