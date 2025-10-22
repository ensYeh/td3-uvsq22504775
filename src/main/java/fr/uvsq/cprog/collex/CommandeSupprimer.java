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
            dns.removeItem(nom);
            System.out.println("Machine supprimée : " + nomMachine);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
