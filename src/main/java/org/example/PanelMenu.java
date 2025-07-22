package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class PanelMenu extends JPanel implements ActionListener {
    private JPanel panelbtn1;
    private JPanel panelbtn2;
    private JPanel panelbtn3;

    private JButton menubtn1;
    private JButton menubtn2;
    private JButton menubtn3;
    private PaneToDo paneToDo;
    private PanelDoing panelDoing;
    private PanelDone panelDone;
    private ArrayList<NoteData> NotasLista;

    public PanelMenu(PaneToDo paneToDo,PanelDoing panelDoing,PanelDone panelDone){
        this.paneToDo = paneToDo;
        this.panelDoing = panelDoing;
        this.panelDone = panelDone;

        NotasLista = new ArrayList<>();



        setLayout(new GridLayout(4,1));
        setPreferredSize(new Dimension(300,800));
        setVisible(true);


        panelbtn1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelbtn2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelbtn3 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        menubtn1 = new JButton("Crear nota");
        menubtn2 = new JButton("guardar notas");
        menubtn3 = new JButton("agregar notas");



        menubtn1.setPreferredSize(new Dimension(150,50));
        menubtn1.addActionListener(this);
        menubtn2.setPreferredSize(new Dimension(150,50));
        menubtn2.addActionListener(this);
        menubtn3.setPreferredSize(new Dimension(150,50));
        menubtn3.addActionListener(this);




        panelbtn1.add(menubtn1);
        panelbtn2.add(menubtn2);
        panelbtn3.add(menubtn3);


        add(panelbtn1);
        add(panelbtn2);
        add(panelbtn3);





    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menubtn1) {
            String title = JOptionPane.showInputDialog(this, "Ingrese título de la nota:");
            if (title == null || title.trim().isEmpty()) return;

            String description = JOptionPane.showInputDialog(this, "Ingrese la descripción de la nota:");
            if (description == null) description = "";



            NoteData data = new NoteData(title.trim(), description.trim(), 1);
            Note note = new Note(data, paneToDo, panelDoing, panelDone);
            paneToDo.add(note);
            paneToDo.revalidate();
            paneToDo.repaint();
            NotasLista.add(data);





        }
        if (e.getSource() == menubtn2) {
            guardarNotas();

        }
        if (e.getSource() == menubtn3) {
            cargarNotas();

        }



    }
    public void guardarNotas() {
        try (ObjectOutputStream textguardar = new ObjectOutputStream(new FileOutputStream("notas.dat"))) {
            textguardar.writeObject(NotasLista);
            System.out.println("Notas guardadas correctamente.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void cargarNotas() {
        try (ObjectInputStream textcargar = new ObjectInputStream(new FileInputStream("notas.dat"))) {
            NotasLista = (ArrayList<NoteData>) textcargar.readObject();
            System.out.println("Notas cargadas correctamente.");

            for (NoteData data : NotasLista) {
                Note note = new Note(data, paneToDo, panelDoing, panelDone);

                if (data.getPosition() == 1) {
                    paneToDo.add(note);
                } else if (data.getPosition() == 2) {
                    panelDoing.add(note);
                } else if (data.getPosition() == 3) {
                    panelDone.add(note);
                }
            }

            paneToDo.revalidate();
            paneToDo.repaint();
            panelDoing.revalidate();
            panelDoing.repaint();
            panelDone.revalidate();
            panelDone.repaint();

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}

