package fr.uvsq.cprog.collex;

import java.io.IOException;

public class CommandeSupprimerIP implements Commande {

    private final Dns dns;
    private final String ip;

    public CommandeSupprimerIP(Dns dns, String ip) {
        this.dns = dns;
        this.ip = ip;
    }

    @Override
    public void execute() {
        try {
            AdresseIP adresseIP = new AdresseIP(ip);
            dns.removeItem(adresseIP);
            System.out.println("Adresse IP supprimée : " + ip);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
