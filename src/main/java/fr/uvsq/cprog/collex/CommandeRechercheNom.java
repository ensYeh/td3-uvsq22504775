package fr.uvsq.cprog.collex;

public class CommandeRechercheNom implements Commande {
    private final Dns dns;
    private final String adresseIP;

    public CommandeRechercheNom(Dns dns, String adresseIP) {
        this.dns = dns;
        this.adresseIP = adresseIP;
    }

    @Override
    public void execute() {
        AdresseIP ip = new AdresseIP(adresseIP);
        DnsItem item = dns.getItem(ip);
        if (item != null) {
            System.out.println(item.getMachine());
        } else {
            System.out.println("Aucun nom trouvé pour " + adresseIP);
        }
    }
}
