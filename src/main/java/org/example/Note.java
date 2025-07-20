package org.example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Note extends JPanel {
    private String title;
    private String description;
    private JLabel tituloLabel;
    private JTextArea descripcionNota;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 200));
        setMaximumSize(new Dimension(200, 200)); // No se expande
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
        add(scroll);



    }


}