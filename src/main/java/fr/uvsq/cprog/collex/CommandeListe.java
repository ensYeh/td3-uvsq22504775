package fr.uvsq.cprog.collex;

import java.util.List;

public class CommandeListe implements Commande {
    private final Dns dns;
    private final String domaine;
    private final boolean triParIP;

    public CommandeListe(Dns dns, String domaine, boolean triParIP) {
        this.dns = dns;
        this.domaine = domaine;
        this.triParIP = triParIP;
    }

    @Override
    public void execute() {
        List<DnsItem> items = triParIP ? dns.getItemsByIP(domaine) : dns.getItems(domaine);
        if (items.isEmpty()) {
            System.out.println("Aucune machine trouvée pour le domaine : " + domaine);
        } else {
            for (DnsItem i : items) {
                System.out.println(i.getIp() + " " + i.getMachine());
            }
        }
    }
}
