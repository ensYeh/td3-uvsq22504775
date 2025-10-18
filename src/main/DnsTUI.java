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
