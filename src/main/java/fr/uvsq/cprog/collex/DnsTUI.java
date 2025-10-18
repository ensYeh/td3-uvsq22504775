package fr.uvsq.cprog.collex;

import java.util.Scanner;

public  class DnsTUI {

    private final Scanner scanner = new Scanner(System.in);
    private final Dns dns;

    public DnsTUI(Dns dns) {
        this.dns = dns;
    
    }
        public Commande nextCommande() {
        System.out.print("> ");
        String ligne = scanner.nextLine().trim();

        // commande pour quitter
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