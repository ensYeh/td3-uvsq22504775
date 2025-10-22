package fr.uvsq.cprog.collex;

import java.io.IOException;

public class CommandeSupprimer implements Commande {
    private final Dns dns;
    private final String nomMachine;

    public CommandeSupprimer(Dns dns, String nomMachine) {
        this.dns = dns;
        this.nomMachine = nomMachine;
    }

    @Override
    public void execute() {
        try {
            NomMachine nom = new NomMachine(nomMachine);
            boolean ok = dns.supprimerItem(nom);
            if (ok) {
                System.out.println("Machine supprimée : " + nomMachine);
            } else {
                System.out.println("Machine non trouvée : " + nomMachine);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Erreur lors de la mise à jour du fichier : " + e.getMessage());
        }
    }
}
