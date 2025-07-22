package org.example;

import java.io.Serializable;

public class NoteData implements Serializable {
    private String title;
    private String description;
    private int position;

    public NoteData(String title, String description, int position) {
        this.title = title;
        this.description = description;
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int posi) {
        this.position = posi;
    }
}