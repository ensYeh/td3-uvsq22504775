package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class NomMachineTest {

    @Test
    public void testValidNomMachine() {
        NomMachine nom = new NomMachine("www.uvsq.fr");
        assertEquals("www.uvsq.fr", nom.getNomComplet());
        assertEquals("www", nom.getNom());
        assertEquals("uvsq.fr", nom.getDomaine());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNomMachine() {
        new NomMachine("machineSansPoint");
    }

    @Test
    public void testEqualsAndHashCode() {
        NomMachine nom1 = new NomMachine("ecampus.uvsq.fr");
        NomMachine nom2 = new NomMachine("ecampus.uvsq.fr");
        NomMachine nom3 = new NomMachine("poste.uvsq.fr");

        assertEquals(nom1, nom2);
        assertNotEquals(nom1, nom3);
        assertEquals(nom1.hashCode(), nom2.hashCode());
    }

    @Test
    public void testToString() {
        NomMachine nom = new NomMachine("poste.uvsq.fr");
        assertEquals("poste.uvsq.fr", nom.toString());
    }
}
