package fr.uvsq.cprog.collex;

import java.util.Objects;

public class AdresseIP {

    private final String ip;  

   
    public AdresseIP(String ip) {
        if (!ip.matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
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
    public int hashCode() {
        return Objects.hash(ip);
    }

    // toString() 
    @Override
    public String toString() {
        return ip;
    }
}