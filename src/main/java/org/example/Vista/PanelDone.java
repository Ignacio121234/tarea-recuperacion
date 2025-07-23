package org.example.Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que representa la sección "Terminado" en la aplicacion Kanban.
 * Las notas se apilan en una columna vertical con fondo azul.
 */
public class PanelDone extends JPanel {

    /**
     * Constructor del panel "Terminado".
     * Configura el layout vertical y el color de fondo.
     */
    public PanelDone(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLUE);
    }

}