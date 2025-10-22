package fr.uvsq.cprog.collex;

import java.util.Objects;

public class AdresseIP {

    private final String ip;  

    public AdresseIP(String ip) {
        // Regex stricte pour valider qu'une adresse IP est correcte (0-255 pour chaque octet)
        if (!ip.matches("((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)")) {
            throw new IllegalArgumentException("Adresse IP invalide : " + ip);
        }
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    // equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdresseIP)) return false;
        AdresseIP other = (AdresseIP) o;
        return ip.equals(other.ip);
    }

    // hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }

    // toString()
    @Override
    public String toString() {
        return ip;
    }
}
