package fr.uvsq.cprog.collex;

import java.io.IOException;

public class CommandeSupprimerNom implements Commande {
    private final Dns dns;
    private final String nom;

    public CommandeSupprimerNom(Dns dns, String nom) {
        this.dns = dns;
        this.nom = nom;
    }

    @Override
    public void execute() {
        try {
            NomMachine nm = new NomMachine(nom);
            boolean ok = dns.supprimerItem(nm);
            if (ok) {
                System.out.println("Machine supprimée : " + nom);
            } else {
                System.out.println("Aucune machine trouvée pour : " + nom);
            }
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
