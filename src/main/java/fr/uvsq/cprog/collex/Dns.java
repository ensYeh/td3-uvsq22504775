package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Dns {

    private final Map<String, DnsItem> parNom = new HashMap<>();
    private final Map<String, DnsItem> parIP = new HashMap<>();
    private final Path fichierBD;

    public Dns(Path fichierBD) throws IOException {
        this.fichierBD = fichierBD;
        if (Files.exists(fichierBD)) {
            List<String> lignes = Files.readAllLines(fichierBD);
            for (String ligne : lignes) {
                String[] parts = ligne.split("\\s+");
                if (parts.length != 2) {
                    continue;
                }
                AdresseIP ip = new AdresseIP(parts[1]);
                NomMachine nom = new NomMachine(parts[0]);
                DnsItem item = new DnsItem(ip, nom);
                parNom.put(nom.getNomComplet(), item);
                parIP.put(ip.getIp(), item);
            }
        }
    }


    public DnsItem getItem(NomMachine nom) {
        return parNom.get(nom.getNomComplet());
    }

    
    public DnsItem getItem(AdresseIP ip) {
        return parIP.get(ip.getIp());
    }
    public List<DnsItem> getItems(String domaine) {
        return parNom.values().stream()
                .filter(item -> item.getMachine().getDomaine().equals(domaine))
                .sorted(Comparator.comparing(i -> i.getMachine().getNomComplet()))
                .collect(Collectors.toList());
    }


    public List<DnsItem> getItemsByIP(String domaine) {
        return parNom.values().stream()
                .filter(item -> item.getMachine().getDomaine().equals(domaine))
                .sorted(Comparator.comparing(i -> i.getIp().getIp()))
                .collect(Collectors.toList());
    }

    
    public void addItem(AdresseIP ip, NomMachine nom) throws IOException {
        if (parNom.containsKey(nom.getNomComplet())) {
            throw new IllegalArgumentException("ERREUR : Le nom de machine existe déjà !");
        }
        if (parIP.containsKey(ip.getIp())) {
            throw new IllegalArgumentException("ERREUR : L'adresse IP existe déjà !");
        }
        DnsItem item = new DnsItem(ip, nom);
        parNom.put(nom.getNomComplet(), item);
        parIP.put(ip.getIp(), item);

        
        List<String> lignes = new ArrayList<>();
        for (DnsItem i : parNom.values()) {
            lignes.add(i.getMachine().getNomComplet() + " " + i.getIp().getIp());
        }
        Files.write(fichierBD, lignes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}