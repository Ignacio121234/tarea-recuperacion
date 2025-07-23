package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * Clase principal que representa la ventana de la aplicación.
 * Contiene los paneles del menú, tareas por hacer, en progreso y completadas.
 */


public class Ventana extends JFrame {



    //Constructor de la clase Ventana
    public Ventana() {

        // Titulo de la ventana
        setTitle("Tablero kanban");

        // Cierra la aplicación al cerrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define el tamaño inicial de la ventana
        setSize(1200, 800);

        // Establece el layout en una grid de 1 fila y 4 columnas
        setLayout(new GridLayout(1,4));


        // Panel de tareas por hacer
        PaneToDo paneToDo = new PaneToDo();


        // Panel de tareas completadas
        PanelDone panelDone = new PanelDone();

        // Panel de tareas en progreso
        PanelDoing panelDoing = new PanelDoing();

        //panel menu que permite agregar notas y guardarlas
        PanelMenu panelMenu = new PanelMenu(paneToDo,panelDoing,panelDone);


        // Se añade el panel de menu
        add(panelMenu);

        // Scroll para el panel de tareas por hacer
        JScrollPane scrollPane = new JScrollPane(paneToDo,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Scroll para el panel de tareas en progreso
        JScrollPane scrollPane2 = new JScrollPane(panelDoing,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Scroll para el panel de tareas completadas
        JScrollPane scrollPane3 = new JScrollPane(panelDone,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);








        // Scroll para el panel de tareas completadas
        add(scrollPane);
        add(scrollPane2);
        add(scrollPane3);

        // Hace visible la ventana
        setVisible(true);
    }






}

