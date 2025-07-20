package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelMenu extends JPanel implements ActionListener {
    private JPanel panelbtn1;
    private JPanel panelbtn2;
    private JPanel panelbtn3;

    private JButton menubtn1;
    private JButton menubtn2;
    private JButton menubtn3;
    private PaneToDo paneToDo;

    public PanelMenu(PaneToDo paneToDo){
        this.paneToDo = paneToDo;

        setLayout(new GridLayout(4,1));
        setPreferredSize(new Dimension(300,800));
        setVisible(true);


        panelbtn1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelbtn2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelbtn3 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        menubtn1 = new JButton("Crear nota");
        menubtn2 = new JButton("btn2");
        menubtn3 = new JButton("btn3");



        menubtn1.setPreferredSize(new Dimension(100,50));
        menubtn1.addActionListener(this);
        menubtn2.setPreferredSize(new Dimension(100,50));
        menubtn3.setPreferredSize(new Dimension(100,50));



        panelbtn1.add(menubtn1);
        panelbtn2.add(menubtn2);
        panelbtn3.add(menubtn3);


        add(panelbtn1);
        add(panelbtn2);
        add(panelbtn3);





    }

    private void updateScreen() {
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menubtn1) {
            String title = JOptionPane.showInputDialog(this, "Ingrese título de la nota:");
            if (title == null || title.trim().isEmpty()) return;

            String description = JOptionPane.showInputDialog(this, "Ingrese la descripción de la nota:");
            if (description == null) description = "";



            Note note = new Note(title.trim(),description.trim());
            paneToDo.add(note);
            paneToDo.revalidate();
            paneToDo.repaint();



        }


    }
}