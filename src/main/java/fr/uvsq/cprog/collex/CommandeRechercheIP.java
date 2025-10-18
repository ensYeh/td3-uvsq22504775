package fr.uvsq.cprog.collex;

public class CommandeRechercheIP implements Commande {
    private final Dns dns;
    private final String nomMachine;

    public CommandeRechercheIP(Dns dns, String nomMachine) {
        this.dns = dns;
        this.nomMachine = nomMachine;
    }

    @Override
    public void execute() {
        NomMachine nom = new NomMachine(nomMachine);
        DnsItem item = dns.getItem(nom);
        if (item != null) {
            System.out.println(item.getIp());
        } else {
            System.out.println("Aucune IP trouvée pour " + nomMachine);
        }
    }
}