package org.example.Vista;

import javax.swing.*;
import java.awt.*;


/**
 * Panel que representa la seccion "En proceso" en la aplicacion Kanban.
 * Las notas se apilan en una columna vertical con fondo rojo.
 */
public class PanelDoing extends JPanel {

    /**
     * Constructor del panel "En proceso".
     * Configura el layout vertical y el color de fondo.
     */
    public PanelDoing(){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.RED);
    }
}