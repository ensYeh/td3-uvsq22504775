package fr.uvsq.cprog.collex;

import java.util.Scanner;

/**
 * Classe responsable de l'interface utilisateur textuelle.
 * Elle lit les commandes, les interprète et crée l'objet Commande correspondant.
 */
public class DnsTUI {

    private final Scanner scanner = new Scanner(System.in);
    private final Dns dns;

    public DnsTUI(Dns dns) {
        this.dns = dns;
    }

    /**
     * Lit la ligne saisie par l'utilisateur et renvoie l'objet Commande correspondant.
     */
    public Commande nextCommande() {
        System.out.print("> ");
        String ligne = scanner.nextLine().trim();

        // commande pour quitter
        if (ligne.equalsIgnoreCase("quit") || ligne.equalsIgnoreCase("exit")) {
            return new CommandeQuitter();
        }

        // commande pour ajouter : add <ip> <nom.qualifie>
        if (ligne.startsWith("add ")) {
            String[] parts = ligne.split("\\s+");
            if (parts.length == 3) {
                return new CommandeAdd(dns, parts[1], parts[2]);
            } else {
                System.out.println("Usage : add <ip> <nom.machine>");
                return null;
            }
        }

        // commande pour lister : ls [-a] <domaine>
        if (ligne.startsWith("ls")) {
            String[] parts = ligne.split("\\s+");
            boolean triParIP = parts.length > 2 && parts[1].equals("-a");
            String domaine = triParIP ? parts[2] : parts.length > 1 ? parts[1] : null;

            if (domaine != null) {
                return new CommandeListe(dns, domaine, triParIP);
            } else {
                System.out.println("Usage : ls [-a] <domaine>");
                return null;
            }
        }

        // si l'entrée est une adresse IP
        if (ligne.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
            return new CommandeRechercheNom(dns, ligne);
        }

        // si l'entrée est un nom de machine
        if (ligne.contains(".")) {
            return new CommandeRechercheIP(dns, ligne);
        }

        System.out.println("Commande inconnue : " + ligne);
        return null;
    }

    /**
     * Affiche le message passé en paramètre.
     */
    public void affiche(String message) {
        System.out.println(message);
    }
    
}
