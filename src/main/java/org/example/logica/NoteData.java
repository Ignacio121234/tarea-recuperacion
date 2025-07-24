package org.example.logica;


import java.io.Serializable;

/**
 * Clase que representa los datos de una nota.
 * Contiene título, descripción y posicion .
 */
public class NoteData implements Serializable {
    private String title;
    private String description;
    private int position;


    /**
     * Constructor para crear una nota con título, descripción y posición.
     *
     * @param title      Título de la nota
     * @param description Descripción de la nota
     * @param position    Panel asociado (1: ToDo, 2: Doing, 3: Done)
     */
    public NoteData(String title, String description, int position) {
        this.title = title;
        this.description = description;
        this.position = position;
    }


    // setters y getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setTitle(String title) {
        this.title = title;

    }

    public void setDescription(String description) {
        this.description = description;
    }
}