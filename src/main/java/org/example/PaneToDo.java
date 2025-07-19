package org.example;

import javax.swing.*;
import java.awt.*;

public class PaneToDo extends JPanel {


    public PaneToDo(){

    setLayout(new GridLayout(0,1));
    setBackground(Color.GREEN);
    Note note1 = new Note("xx");
    Note note2 = new Note("");
    Note note3 = new Note("");
    Note note4 = new Note("");

    add(note1);//add(note2);add(note3);//add(note4);





    }


}
