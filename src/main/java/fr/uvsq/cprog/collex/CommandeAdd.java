package fr.uvsq.cprog.collex;



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
        System.out.println("Ajout de la machine : " + nom + " / " + ip);
    }
}
