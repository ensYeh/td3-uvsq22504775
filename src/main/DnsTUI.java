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

