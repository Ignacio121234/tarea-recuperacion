package org.example.Vista;

    import javax.swing.*;
    import java.awt.*;


    /**
     * Panel que representa la seccion "Por hacer" en la aplicacion Kanban.
     * Las notas se apilan en una columna vertical con fondo verde.
     */
    public class PaneToDo extends JPanel {
        private JLabel titulopanel;

        /**
         * Constructor del panel "Por hacer".
         * Configura el layout vertical y el color de fondo.
         */
        public PaneToDo() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(Color.GREEN);

            titulopanel = new JLabel("Por Hacer");
            titulopanel.setFont(new Font("Arial", Font.PLAIN, 30));
            titulopanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            add(titulopanel);



        }






        }



