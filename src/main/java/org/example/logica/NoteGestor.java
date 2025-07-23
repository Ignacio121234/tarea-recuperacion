package org.example.logica;

import org.example.Vista.Note;
import org.example.Vista.PaneToDo;
import org.example.Vista.PanelDoing;
import org.example.Vista.PanelDone;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NoteGestor {
    private  List<NoteData> NotasLista;
    private  PaneToDo paneToDo;
    private  PanelDoing panelDoing;
    private  PanelDone panelDone;

    public NoteGestor(PaneToDo paneToDo, PanelDoing panelDoing, PanelDone panelDone) {
        this.NotasLista = new ArrayList<>();
        this.paneToDo = paneToDo;
        this.panelDoing = panelDoing;
        this.panelDone = panelDone;
    }


    public int contarNotas(JPanel panel) {
        int contador = 0;
        for (Component comp : panel.getComponents()) {
            if (comp instanceof Note) {
                contador++;
            }
        }
        return contador;
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
        }}


