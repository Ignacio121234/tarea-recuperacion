package org.example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Note extends JPanel implements ActionListener {
    private NoteData data;
    private JLabel tituloLabel;
    private JTextArea descripcionNota;
    private JButton btnElim;
    private JButton btnIzq;
    private JButton btnDer;
    private PaneToDo panelToDo;
    private PanelDoing panelDoing;
    private PanelDone panelDone;
    private int posicion;



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

        tituloLabel = new JLabel(data.getTitle());
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(tituloLabel);

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

        JPanel panelBtns = new JPanel();
        panelBtns.setPreferredSize(new Dimension(250, 65));
        panelBtns.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBtns.setBackground(new Color(255, 255, 200));

        panelBtns.add(btnIzq);
        panelBtns.add(btnElim);
        panelBtns.add(btnDer);

        add(panelBtns);
    }

    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == btnElim) {
            Container parent = getParent();
            if (parent != null) {
                parent.remove(this);
                parent.revalidate();
                parent.repaint();
            }
            PanelMenu.panelgobal.actualizarResumen();
        }

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
                PanelMenu.panelgobal.actualizarResumen();
                revalidate();
                repaint();
                getParent().revalidate();
                getParent().repaint();
            }
        }
    }



    public NoteData getData() {
        return data;
    }
}
