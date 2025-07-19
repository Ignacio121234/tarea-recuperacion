package org.example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Note extends JPanel {

    public Note(String description){

        setPreferredSize(new Dimension(200,200));
        setBackground(new Color(255, 255, 200));
        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.DARK_GRAY, 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));


    }

}
