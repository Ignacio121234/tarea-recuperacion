package org.example.Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que representa la sección "Terminado" en la aplicacion Kanban.
 * Las notas se apilan en una columna vertical con fondo azul.
 */
public class PanelDone extends JPanel {
    private JLabel titulopanel;
    /**
     * Constructor del panel "Terminado".
     * Configura el layout vertical y el color de fondo.
     */
    public PanelDone(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLUE);

        titulopanel = new JLabel("Terminado");
        titulopanel.setFont(new Font("Arial", Font.PLAIN, 30));
        titulopanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(titulopanel);
    }

}