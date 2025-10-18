package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Représente un serveur DNS simple.
 * Permet de stocker des machines avec leur nom et adresse IP,
 * de les rechercher et d'ajouter de nouvelles entrées.
 */
public class Dns {

    private final Map<String, DnsItem> parNom = new HashMap<>();
    private final Map<String, DnsItem> parIP = new HashMap<>();
    private final Path fichierBD;

    /**
     * Charge la base de données comme déja indiqué dans l'exercice .
     *
     * @param fichierBD chemin du fichier de la base de données
     * @throws IOException si le fichier ne peut pas être lu
     */
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

    /**
     * Retourne l'entrée DNS correspondant à un nom de machine.
     *
     * @param nom NomMachine recherché
     * @return DnsItem ou null si non trouvé
     */
    public DnsItem getItem(NomMachine nom) {
        return parNom.get(nom.getNomComplet());
    }

    /**
     * Retourne l'entrée DNS correspondant à une adresse IP.
     *
     * @param ip AdresseIP recherchée
     * @return DnsItem ou null si non trouvé
     */
    public DnsItem getItem(AdresseIP ip) {
        return parIP.get(ip.getIp());
    }

    /**
     * Retourne la liste des machines d'un domaine triée par nom de machine.
     *
     * @param domaine nom du domaine
     * @return liste d'items
     */
    public List<DnsItem> getItems(String domaine) {
        return parNom.values().stream()
                .filter(item -> item.getNomMachine().getDomaine().equals(domaine))
                .sorted(Comparator.comparing(i -> i.getNomMachine().getNomComplet()))
                .collect(Collectors.toList());
    }

    /**
     * Retourne la liste des machines d'un domaine triée par adresse IP.
     *
     * @param domaine nom du domaine
     * @return liste d'items
     */
    public List<DnsItem> getItemsByIP(String domaine) {
        return parNom.values().stream()
                .filter(item -> item.getNomMachine().getDomaine().equals(domaine))
                .sorted(Comparator.comparing(i -> i.getAdresseIP().getIp()))
                .collect(Collectors.toList());
    }

    /**
     * Ajoute une nouvelle machine dans la base DNS et met à jour le fichier.
     *
     * @param ip  AdresseIP de la machine
     * @param nom NomMachine de la machine
     * @throws IOException              si le fichier ne peut pas être écrit
     * @throws IllegalArgumentException si le nom ou l'IP existe déjà
     */
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

        // Mettre à jour le fichier
        List<String> lignes = new ArrayList<>();
        for (DnsItem i : parNom.values()) {
            lignes.add(i.getNomMachine().getNomComplet() + " " + i.getAdresseIP().getIp());
        }
        Files.write(fichierBD, lignes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}