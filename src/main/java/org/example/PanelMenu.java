package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Clase que representa el panel lateral del menú.
 * Incluye botones para crear, guardar y cargar notas, así como un resumen de las tareas.
 */

public class PanelMenu extends JPanel implements ActionListener {


    //Paneles que contienen los botones
    private JPanel panelbtn1;
    private JPanel panelbtn2;
    private JPanel panelbtn3;


    //Botones del menu
    private JButton menubtn1;
    private JButton menubtn2;
    private JButton menubtn3;


    //Referencias a los paneles principales
    private PaneToDo paneToDo;
    private PanelDoing panelDoing;
    private PanelDone panelDone;

    //Lista de datos de las notas
    private ArrayList<NoteData> NotasLista;

    //area en la que se muestran las cantidades de tareas
    private JTextArea resumenTareas;

    //referencia global al panelmenu
    public static PanelMenu panelgobal;


    /**
     * Constructor del panel de menu.
     *
     * @param paneToDo Panel de tareas por hacer
     * @param panelDoing Panel de tareas en proceso
     * @param panelDone Panel de tareas completadas
     */

    public PanelMenu(PaneToDo paneToDo,PanelDoing panelDoing,PanelDone panelDone){
        this.paneToDo = paneToDo;
        this.panelDoing = panelDoing;
        this.panelDone = panelDone;
        panelgobal = this;

        NotasLista = new ArrayList<>();



        resumenTareas = new JTextArea(4, 20);
        resumenTareas.setEditable(false);
        resumenTareas.setFont(new Font("Arial", Font.PLAIN, 20));


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
        add(resumenTareas);
        actualizarResumen();




    }

    /**
     * Maneja los eventos de los botones del menu.
     *
     * @param e Evento de accion
     */
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
            actualizarResumen();






        }
        if (e.getSource() == menubtn2) {
            guardarNotas();

        }
        if (e.getSource() == menubtn3) {
            cargarNotas();
            actualizarResumen();
        }



    }

    /**
     * Guarda las notas actuales en un archivo llamado "notas.dat".
     */
    public void guardarNotas() {
        NotasLista.clear();


        for (Component comp : paneToDo.getComponents()) {
            if (comp instanceof Note) {
                Note note = (Note) comp;
                NoteData data = note.getData();
                data.setPosition(1);
                NotasLista.add(data);
            }
        }


        for (Component comp : panelDoing.getComponents()) {
            if (comp instanceof Note) {
                Note note = (Note) comp;
                NoteData data = note.getData();
                data.setPosition(2);
                NotasLista.add(data);
            }
        }


        for (Component comp : panelDone.getComponents()) {
            if (comp instanceof Note) {
                Note note = (Note) comp;
                NoteData data = note.getData();
                data.setPosition(3);
                NotasLista.add(data);
            }
        }


        try (ObjectOutputStream textguardar = new ObjectOutputStream(new FileOutputStream("notas.dat"))) {
            textguardar.writeObject(NotasLista);
            System.out.println("Notas guardadas correctamente.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Carga las notas desde el archivo "notas.dat" y las distribuye en los paneles correspondientes.
     */
    public void cargarNotas() {
        try (ObjectInputStream textcargar = new ObjectInputStream(new FileInputStream("notas.dat"))) {
            NotasLista = (ArrayList<NoteData>) textcargar.readObject();

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
    private int contarNotas(JPanel panel) {
        int contador = 0;
        for (Component comp : panel.getComponents()) {
            if (comp instanceof Note) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Cuenta cuantas notas hay en un panel dado.
     *
     * @return Cantidad de notas en el panel
     */
    public void actualizarResumen() {
        int countToDo = contarNotas(paneToDo);
        int countDoing = contarNotas(panelDoing);
        int countDone = contarNotas(panelDone);

        String resumen = "Por hacer: " + countToDo + "\n" + "En proceso: " + countDoing + "\n" + "Terminado: " + countDone;

        resumenTareas.setText(resumen);
    }
}

