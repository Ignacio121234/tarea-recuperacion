package org.example.Vista;

import org.example.logica.NoteData;
import org.example.logica.NoteGestor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Clase que representa el panel lateral del menú.
 * Incluye botones para crear, guardar y cargar notas, así como un resumen de las tareas.
 */

public class PanelMenu extends JPanel implements ActionListener {

    private NoteGestor gestor;


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
        this.gestor = new NoteGestor(paneToDo, panelDoing, panelDone);

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
        menubtn3 = new JButton("agregar notas guardadas");



        menubtn1.setPreferredSize(new Dimension(190,50));
        menubtn1.addActionListener(this);
        menubtn2.setPreferredSize(new Dimension(190,50));
        menubtn2.addActionListener(this);
        menubtn3.setPreferredSize(new Dimension(190,50));
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
            gestor.guardarNotas();

        }
        if (e.getSource() == menubtn3) {
            gestor.cargarNotas();

            actualizarResumen();
        }



    }




    /**
     * Cuenta cuantas notas hay en un panel dado.
     *
     * @return Cantidad de notas en el panel
     */
    public void actualizarResumen() {
        int countToDo = gestor.contarNotas(paneToDo);
        int countDoing = gestor.contarNotas(panelDoing);
        int countDone = gestor.contarNotas(panelDone);

        String resumen = "Por hacer: " + countToDo + "\n" + "En proceso: " + countDoing + "\n" + "Terminado: " + countDone;

        resumenTareas.setText(resumen);
    }
}

