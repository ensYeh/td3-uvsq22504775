package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class DnsTest {

    private Path tempDnsFile;
    private Dns dns;

    @Before
    public void setUp() throws IOException {
        tempDnsFile = Files.createTempFile("dns_test", ".txt");
        dns = new Dns(tempDnsFile);
    }

    @Test
    public void testAddMachine() throws IOException {
        NomMachine nom = new NomMachine("pc1.local");
        AdresseIP ip = new AdresseIP("192.168.1.10");

        dns.addItem(ip, nom); // attention ici méthode s'appelle addItem dans ta classe Dns

        String contenu = Files.readString(tempDnsFile);
        assertTrue(contenu.contains("pc1.local"));
        assertTrue(contenu.contains("192.168.1.10"));
    }

    @After
    public void tearDown() throws IOException {
        Files.deleteIfExists(tempDnsFile);
    }
}
