package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DnsItemTest {

    @Test
    public void testDnsItemCreation() {
        AdresseIP ip = new AdresseIP("192.168.1.1");
        NomMachine nom = new NomMachine("machine.local");

        DnsItem item = new DnsItem(ip, nom);

        assertEquals(ip, item.getIp());
        assertEquals(nom, item.getMachine());
        assertEquals("192.168.1.1 machine.local", item.toString());
    }
}
