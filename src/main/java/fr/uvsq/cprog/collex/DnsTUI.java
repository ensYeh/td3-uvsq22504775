package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsTUI {

    private final Scanner scanner = new Scanner(System.in);
    private final Dns dns;

    public DnsTUI(Dns dns) {
        this.dns = dns;
    }

    public Commande nextCommande() {
        System.out.print("> ");
        String ligne = scanner.nextLine().trim();

        if (ligne.equalsIgnoreCase("quit") || ligne.equalsIgnoreCase("exit")) {
            return new CommandeQuitter();
        }

        if (ligne.startsWith("add ")) {
            String[] parts = ligne.split("\\s+");
            if (parts.length == 3) {
                return new CommandeAdd(dns, parts[1], parts[2]);
            } else {
                System.out.println("Usage : add <ip> <nom.machine>");
                return null;
            }
        }

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

        // Supprimer par IP ou par nom
        if (ligne.startsWith("delete ")) {
            String[] parts = ligne.split("\\s+");
            if (parts.length == 2) {
                String target = parts[1];
                if (target.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
                    return new CommandeSupprimerIP(dns, target);
                } else if (target.contains(".")) {
                    return new CommandeSupprimerNom(dns, target);
                } else {
                    System.out.println("NomMachine ou IP invalide");
                    return null;
                }
            } else {
                System.out.println("Usage : delete <ip|nom.machine>");
                return null;
            }
        }

        if (ligne.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
            return new CommandeRechercheNom(dns, ligne);
        }

        if (ligne.contains(".")) {
            return new CommandeRechercheIP(dns, ligne);
        }

        System.out.println("Commande inconnue : " + ligne);
        return null;
    }

    public void affiche(String message) {
        System.out.println(message);
    }
}
