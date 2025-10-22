package fr.uvsq.cprog.collex;
import java.io.IOException;
import java.nio.file.Paths;

import fr.uvsq.cprog.collex.Commande;
import fr.uvsq.cprog.collex.CommandeQuitter;
import fr.uvsq.cprog.collex.Dns;
import fr.uvsq.cprog.collex.DnsTUI;

public class DnsApp {
    public static void main(String[] args) {
        try {
            Dns dns = new Dns(Paths.get("dns.txt"));  
            DnsTUI tui = new DnsTUI(dns);            
            boolean continuer = true;
            while (continuer) {
                Commande cmd = tui.nextCommande();
                if (cmd != null) {
                    cmd.execute();
                    if (cmd instanceof CommandeQuitter) {
                        continuer = false;
                    }
                }
            }
        } catch (IOException e) {
             System.err.println("Erreur : " + e.getMessage());
        }
    }
}