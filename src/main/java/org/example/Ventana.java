package org.example;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    public Ventana() {

        setTitle("APP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLayout(new GridLayout(1,4));

        PaneToDo paneToDo = new PaneToDo();
        PanelDone panelDone = new PanelDone();
        PanelDoing panelDoing = new PanelDoing();
        PanelMenu panelMenu = new PanelMenu(paneToDo,panelDoing,panelDone);



        add(panelMenu);

        JScrollPane scrollPane = new JScrollPane(paneToDo,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JScrollPane scrollPane2 = new JScrollPane(panelDoing,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JScrollPane scrollPane3 = new JScrollPane(panelDone,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);









        add(scrollPane);
        add(scrollPane2);
        add(scrollPane3);


        setVisible(true);
    }






}

