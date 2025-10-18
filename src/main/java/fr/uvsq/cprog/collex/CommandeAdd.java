package fr.uvsq.cprog.collex;

import java.io.IOException;

public class CommandeAdd implements Commande {
    private final Dns dns;
    private final String ip;
    private final String nom;

    public CommandeAdd(Dns dns, String ip, String nom) {
        this.dns = dns;
        this.ip = ip;
        this.nom = nom;
    }

    @Override
    public void execute() {
        try {
            AdresseIP adresseIP = new AdresseIP(ip);
            NomMachine nomMachine = new NomMachine(nom);
            dns.addItem(adresseIP, nomMachine);
            System.out.println("Ajout de la machine : " + nom + " / " + ip);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }
}
