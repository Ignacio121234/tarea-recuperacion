package org.example.Vista;

import javax.swing.*;
import java.awt.*;


/**
 * Panel que representa la seccion "En proceso" en la aplicacion Kanban.
 * Las notas se apilan en una columna vertical con fondo rojo.
 */
public class PanelDoing extends JPanel {
    private JLabel titulopanel;
    /**
     * Constructor del panel "En proceso".
     * Configura el layout vertical y el color de fondo.
     */
    public PanelDoing(){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.RED);
        titulopanel = new JLabel("En Proceso");
        titulopanel.setFont(new Font("Arial", Font.PLAIN, 30));
        titulopanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(titulopanel);


    }
}