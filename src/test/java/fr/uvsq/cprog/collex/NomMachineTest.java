package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class NomMachineTest {

    // ✅ Test pour un nom valide
    @Test
    public void testValidNomMachine() {
        NomMachine nm = new NomMachine("machine1");
        assertEquals("machine1", nm.toString());
    }

    // ✅ Test pour un nom invalide (caractère interdit '@')
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNomMachine() {
        new NomMachine("machine@"); 
    }

    // ✅ Test toString
    @Test
    public void testToString() {
        NomMachine nm = new NomMachine("serveur01");
        assertEquals("serveur01", nm.toString());
    }

    // ✅ Test equals et hashCode
    @Test
    public void testEqualsAndHashCode() {
        NomMachine nm1 = new NomMachine("pc01");
        NomMachine nm2 = new NomMachine("pc01");
        NomMachine nm3 = new NomMachine("pc02");

        assertEquals(nm1, nm2);
        assertEquals(nm1.hashCode(), nm2.hashCode());
        assertNotEquals(nm1, nm3);
    }
}
