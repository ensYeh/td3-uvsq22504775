package fr.uvsq.cprog.collex;

import java.io.IOException;

public class CommandeSupprimerIP implements Commande {
    private final Dns dns;
    private final String ipStr;

    public CommandeSupprimerIP(Dns dns, String ipStr) {
        this.dns = dns;
        this.ipStr = ipStr;
    }

    @Override
    public void execute() {
        try {
            AdresseIP ip = new AdresseIP(ipStr);
            boolean ok = dns.supprimerItem(ip);
            if (ok) {
                System.out.println("Machine supprimée pour l'adresse IP : " + ipStr);
            } else {
                System.out.println("Aucune machine trouvée pour l'adresse IP : " + ipStr);
            }
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
