package fr.uvsq.cprog.collex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class AdresseIPTest {

    @Test
    public void testValidIP() {
        AdresseIP ip1 = new AdresseIP("192.168.0.1");
        AdresseIP ip2 = new AdresseIP("10.0.0.255");

        assertEquals("192.168.0.1", ip1.getIp());
        assertEquals("10.0.0.255", ip2.getIp());

        AdresseIP ip3 = new AdresseIP("192.168.0.1");
        assertTrue(ip1.equals(ip3));
        assertEquals(ip1.hashCode(), ip3.hashCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidIP_format() {
        new AdresseIP("invalide_ip");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidIP_values() {
        new AdresseIP("999.999.999.999");
    }

    @Test
    public void testToString() {
        AdresseIP ip = new AdresseIP("8.8.8.8");
        assertEquals("8.8.8.8", ip.toString());
    }
}
