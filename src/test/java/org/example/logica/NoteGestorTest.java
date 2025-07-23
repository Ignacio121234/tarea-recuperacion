package org.example.logica;

import org.example.Vista.Note;
import org.example.Vista.PaneToDo;
import org.example.Vista.PanelDoing;
import org.example.Vista.PanelDone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class NoteGestorTest {

    private NoteGestor gestor;
    private PaneToDo paneToDo;
    private PanelDoing panelDoing;
    private PanelDone panelDone;
    private Note note;
    private Note note2;

    @BeforeEach
    void setup() {
        paneToDo = new PaneToDo();
        panelDoing = new PanelDoing();
        panelDone = new PanelDone();
        gestor = new NoteGestor(paneToDo, panelDoing, panelDone);
    }

    @Test
    void testContarNotas() {
        NoteData data1 = new NoteData("erherherh", "12314124asfasfasf", 1);
        NoteData data2 = new NoteData("erherherhrhe", "D123123123123afasfasfc", 1);
        note = new Note(data1, paneToDo, panelDoing, panelDone);
        note2 = new Note(data2, paneToDo, panelDoing, panelDone);
        paneToDo.add(note);
        paneToDo.add(note2);

        int cantidad = gestor.contarNotas(paneToDo);
        assertEquals(2, cantidad);
    }

    @Test
    void testGuardarNotas() {


        NoteData data1 = new NoteData("erjerjerjerjerj", "ererjerjerjejerj", 1);
        note = new Note(data1, paneToDo, panelDoing, panelDone);
        paneToDo.add(note);

        gestor.guardarNotas();

        File archivo = new File("notas.dat");
        assertTrue(archivo.exists());
        assertTrue(archivo.length() > 0);

        archivo.delete();
    }

    @Test
    void testCargarNotas() {
        NoteData data1 = new NoteData("erjerjerjerjerj", "ererjerjerjejerj", 1);
        note = new Note(data1, paneToDo, panelDoing, panelDone);
        panelDoing.add(note);
        gestor.guardarNotas();


        paneToDo.removeAll();
        panelDoing.removeAll();
        panelDone.removeAll();

        gestor.cargarNotas();

        assertEquals(1, panelDoing.getComponentCount());
        assertTrue(panelDoing.getComponent(0) instanceof Note);

        new File("notas.dat").delete();
    }
}