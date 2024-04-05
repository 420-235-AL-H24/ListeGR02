import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListeTest {
    Liste<Integer> vide, data;

    @BeforeEach
    void setUp() {
        vide = new Liste<>();
        data = new Liste<>();
        data.ajouter(1);
        data.ajouter(2);
        data.ajouter(3);
        data.ajouter(4);
        data.ajouter(5);
    }

    @Test
    void getNbElements() {
        assertEquals(0, vide.getNbElements());
        assertEquals(5, data.getNbElements());
    }

    @Test
    void estVide() {
        assertTrue(vide.estVide());
        assertFalse(data.estVide());
    }

    @Test
    void getElementAt() {
        assertEquals(1, data.getElementAt(0));
        assertEquals(3, data.getElementAt(2));
        assertEquals(5, data.getElementAt(4));
    }

    @Test
    void ajouter() {
        data.ajouter(95);
        data.ajouter(96);
        data.ajouter(97);
        assertEquals(8, data.getNbElements());
        assertEquals(95, data.getElementAt(5));
        assertEquals(96, data.getElementAt(6));
        assertEquals(97, data.getElementAt(7));
    }

    @Test
    void inserer() {
        assertTrue(data.ajouter(90, 0));
        assertTrue(data.ajouter(94, 4));
        assertTrue(data.ajouter(97, 7));
        assertEquals(8, data.getNbElements());
        assertEquals(90, data.getElementAt(0));
        assertEquals(94, data.getElementAt(4));
        assertEquals(97, data.getElementAt(7));
    }

    @Test
    void insererPositionsInvalides() {
        // Ces ajouts devraient être refusés
        assertFalse(data.ajouter(91, -9));
        assertFalse(data.ajouter(99, 9));
        assertEquals(5, data.getNbElements());
    }

    @Test
    void ajouterPlusieurs() {
        Liste<Integer> autre = new Liste<>();
        autre.ajouter(95);
        autre.ajouter(96);
        autre.ajouter(97);
        data.ajouter(autre);

        assertEquals(8, data.getNbElements());
        assertEquals(95, data.getElementAt(5));
        assertEquals(96, data.getElementAt(6));
        assertEquals(97, data.getElementAt(7));
    }

    @Test
    void trouver() {
        assertEquals(0, data.trouver(1));
        assertEquals(2, data.trouver(3));
        assertEquals(4, data.trouver(5));
    }

    @Test
    void trouverTout() {
        Liste<Integer> autre = new Liste<>();
        autre.ajouter(1);
        autre.ajouter(3);
        autre.ajouter(5);
        assertTrue(data.trouverTout(autre));

        autre.ajouter(9);
        assertFalse(data.trouverTout(autre));
    }

    @Test
    void effacerAt() {
        assertTrue(data.effacerAt(4));
        assertTrue(data.effacerAt(2));
        assertTrue(data.effacerAt(0));
        assertEquals(2, data.getNbElements());
        assertEquals(2, data.getElementAt(0));
        assertEquals(4, data.getElementAt(1));
    }

    @Test
    void effacerTout() {
        Liste<Integer> autre = new Liste<>();
        autre.ajouter(1);
        autre.ajouter(3);
        autre.ajouter(5);
        assertTrue(data.effacerTout(autre));

        assertEquals(2, data.getNbElements());
        assertEquals(2, data.getElementAt(0));
        assertEquals(4, data.getElementAt(1));

        autre.ajouter(9);
        assertFalse(data.effacerTout(autre));

        assertEquals(2, data.getNbElements());
        assertEquals(2, data.getElementAt(0));
        assertEquals(4, data.getElementAt(1));
    }

    @Test
    void testEffacerTout() {
        data.effacerTout();
        assertEquals(0, data.getNbElements());
    }
}
