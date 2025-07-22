package org.example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Note extends JPanel implements ActionListener {
    private String title;
    private String description;
    private JLabel tituloLabel;
    private JTextArea descripcionNota;
    private JButton btnElim;
    private JButton btnIzq;
    private JButton btnDer;
    private int posicion = 1;
    private PaneToDo panelToDo;
    private PanelDoing panelDoing;
    private PanelDone panelDone;


    public Note(String title, String description,
                PaneToDo panelToDo, PanelDoing panelDoing, PanelDone panelDone, int posicion) {
        this.title = title;
        this.description = description;
        this.panelToDo = panelToDo;
        this.panelDoing = panelDoing;
        this.panelDone = panelDone;
        this.posicion = posicion;


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setPreferredSize(new Dimension(250, 300));
        setMaximumSize(new Dimension(250, 250));
        setBackground(new Color(255, 255, 200));
        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.DARK_GRAY, 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        tituloLabel = new JLabel(title);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(tituloLabel);

        descripcionNota = new JTextArea(description, 5, 15);
        descripcionNota.setBackground(new Color(255, 255, 150));
        descripcionNota.setLineWrap(true);
        descripcionNota.setWrapStyleWord(true);
        descripcionNota.setEditable(false);
        descripcionNota.setAlignmentX(Component.CENTER_ALIGNMENT);

        JScrollPane scroll = new JScrollPane(descripcionNota);
        scroll.setPreferredSize(new Dimension(230, 150));
        scroll.setMaximumSize(new Dimension(230, 150));
        scroll.setMinimumSize(new Dimension(230, 150));
        add(scroll);

        btnElim = new JButton("eliminar");
        btnElim.addActionListener(this);
        btnIzq = new JButton("←");
        btnIzq.addActionListener(this);
        btnDer = new JButton("→");
        btnDer.addActionListener(this);

        JPanel panelMotaBtns = new JPanel();

        panelMotaBtns.setPreferredSize(new Dimension(250, 65));
        panelMotaBtns.setMaximumSize(new Dimension(250, 65));
        panelMotaBtns.setMinimumSize(new Dimension(250, 65));

        panelMotaBtns.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelMotaBtns.setBackground(new Color(255, 255, 200));

        panelMotaBtns.add(btnIzq);
        panelMotaBtns.add(btnElim);
        panelMotaBtns.add(btnDer);

        add(panelMotaBtns);


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnElim) {
            Container parent = getParent();
            if (parent != null) {
                parent.remove(this);
                parent.revalidate();
                parent.repaint();
            }

        }
        if (e.getSource() == btnIzq) {
            if (posicion > 1) {
                Container parent = getParent();
                if (parent != null) {
                    parent.remove(this);
                    parent.revalidate();
                    parent.repaint();
                }
                if (posicion == 2) {
                    panelToDo.add(this);
                    posicion = 1;
                } else if (posicion == 3) {
                    panelDoing.add(this);
                    posicion = 2;
                }

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
                    parent.revalidate();
                    parent.repaint();
                }
                if (posicion == 1) {
                    panelDoing.add(this);
                    posicion = 2;
                    revalidate();
                    repaint();
                    getParent().revalidate();
                    getParent().repaint();
                } else if (posicion == 2) {
                    panelDone.add(this);
                    posicion = 3;
                    revalidate();
                    repaint();
                    getParent().revalidate();
                    getParent().repaint();
                }
                revalidate();
                repaint();
                getParent().revalidate();
                getParent().repaint();
            }
        }
    }

    public int getPosicion(){
        return posicion;
    }
}