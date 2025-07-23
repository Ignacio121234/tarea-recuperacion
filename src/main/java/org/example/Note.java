package org.example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Clase que representa una nota visual dentro de el panel Kanban.
 * Contiene titulo, descripcion, y botones para eliminar o mover la nota entre lso paneles.
 */
public class Note extends JPanel implements ActionListener {

    //datos de la nota
    private NoteData data;

    //label que muestra el titulo de la nota
    private JLabel tituloLabel;

    //area de texto de la descripcion
    private JTextArea descripcionNota;


    //botones para controlar las notas
    private JButton btnElim;
    private JButton btnIzq;
    private JButton btnDer;


    //Panel de tareas por hacer
    private PaneToDo panelToDo;

    //Panel de tareas en proceso
    private PanelDoing panelDoing;

    //Panel de tareas completadas
    private PanelDone panelDone;

    //int que representa la posicion de la nota entre los 3 paneles
    private int posicion;


    /**
     * Constructor que crea una nueva nota visual a partir de sus datos y los paneles.
     *
     * @param data        Datos de la nota
     * @param panelToDo    Panel "Por hacer"
     * @param panelDoing  Panel "En proceso"
     * @param panelDone   Panel "Terminado"
     */
    public Note(NoteData data, PaneToDo panelToDo, PanelDoing panelDoing, PanelDone panelDone) {
        this.data = data;
        this.panelToDo = panelToDo;
        this.panelDoing = panelDoing;
        this.panelDone = panelDone;
        this.posicion = data.getPosition();


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setPreferredSize(new Dimension(250, 300));
        setMaximumSize(new Dimension(250, 250));
        setBackground(new Color(255, 255, 200));
        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.DARK_GRAY, 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));


        // Se crea y agrega el titulo de la nota
        tituloLabel = new JLabel(data.getTitle());
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(tituloLabel);

        // Area de texto para la descripcion
        descripcionNota = new JTextArea(data.getDescription(), 5, 15);
        descripcionNota.setBackground(new Color(255, 255, 150));
        descripcionNota.setLineWrap(true);
        descripcionNota.setWrapStyleWord(true);
        descripcionNota.setEditable(false);
        descripcionNota.setAlignmentX(Component.CENTER_ALIGNMENT);

        JScrollPane scroll = new JScrollPane(descripcionNota);
        scroll.setPreferredSize(new Dimension(230, 150));
        add(scroll);

        btnElim = new JButton("eliminar");
        btnElim.addActionListener(this);
        btnIzq = new JButton("←");
        btnIzq.addActionListener(this);
        btnDer = new JButton("→");
        btnDer.addActionListener(this);


        // Panel para botones inferiores
        JPanel panelBtns = new JPanel();
        panelBtns.setPreferredSize(new Dimension(250, 65));
        panelBtns.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBtns.setBackground(new Color(255, 255, 200));

        // se agregan los botones
        panelBtns.add(btnIzq);
        panelBtns.add(btnElim);
        panelBtns.add(btnDer);

        add(panelBtns);
    }

    /**
     * Maneja eventos de botones: eliminar, mover izquierda y mover derecha
     *
     * @param e Evento de accion generado por un boton
     */
    public void actionPerformed(ActionEvent e) {


        // Eliminar nota del panel actual
        if (e.getSource() == btnElim) {
            Container parent = getParent();
            if (parent != null) {
                parent.remove(this);
                parent.revalidate();
                parent.repaint();
            }
            PanelMenu.panelgobal.actualizarResumen();
        }

        // Mover nota a la izquierda
        if (e.getSource() == btnIzq) {
            if (posicion > 1) {
                Container parent = getParent();
                if (parent != null) {
                    parent.remove(this);
                    PanelMenu.panelgobal.actualizarResumen();
                    parent.revalidate();
                    parent.repaint();

                }
                if (posicion == 2) {
                    panelToDo.add(this);
                    posicion = 1;
                    data.setPosition(1);

                } else if (posicion == 3) {
                    panelDoing.add(this);
                    posicion = 2;
                    data.setPosition(2);
                }
                PanelMenu.panelgobal.actualizarResumen();
                revalidate();
                repaint();
                getParent().revalidate();
                getParent().repaint();
            }
        }

        // Mover nota a la derecha
        if (e.getSource() == btnDer) {
            if (posicion < 3) {
                Container parent = getParent();
                if (parent != null) {
                    parent.remove(this);
                    PanelMenu.panelgobal.actualizarResumen();
                    parent.revalidate();
                    parent.repaint();
                }
                if (posicion == 1) {
                    panelDoing.add(this);
                    posicion = 2;
                    data.setPosition(2);
                    PanelMenu.panelgobal.actualizarResumen();
                    revalidate();
                    repaint();
                    getParent().revalidate();
                    getParent().repaint();
                } else if (posicion == 2) {
                    panelDone.add(this);
                    posicion = 3;
                    data.setPosition(3);
                    PanelMenu.panelgobal.actualizarResumen();
                    revalidate();
                    repaint();
                    getParent().revalidate();
                    getParent().repaint();
                }

                // Actualiza el resumen visual de las tareas

                PanelMenu.panelgobal.actualizarResumen();
                revalidate();
                repaint();
                getParent().revalidate();
                getParent().repaint();
            }
        }
    }


    /**
     * Retorna los datos del modelo asociados a esta nota.
     *
     * @return NoteData con titulo, descripcion y posicion.
     */
    public NoteData getData() {
        return data;
    }
}
