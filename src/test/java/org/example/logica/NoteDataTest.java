package org.example.logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NoteDataTest {

    @Test
    void testConstructor() {
        NoteData data = new NoteData("wegwegewgew", "wegwegwegweg", 2);

        assertEquals("wegwegewgew", data.getTitle());
        assertEquals("wegwegwegweg", data.getDescription());
        assertEquals(2, data.getPosition());
    }

    @Test
    void testSetAndGetTitulo() {
        NoteData data = new NoteData("", "", 1);
        data.setTitle("gwgwegw");
        assertEquals("gwgwegw", data.getTitle());
    }

    @Test
    void testSetterYGetterDescrip() {
        NoteData data = new NoteData("", "", 1);
        data.setDescription("awfawfwafawf");
        assertEquals("awfawfwafawf", data.getDescription());
    }

    @Test
    void testSetterYGetterPosit() {
        NoteData data = new NoteData("", "", 1);
        data.setPosition(3);
        assertEquals(3, data.getPosition());
    }
}