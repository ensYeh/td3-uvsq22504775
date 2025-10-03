package fr.uvsq.cprog.collex;

import java.util.Objects;

public class DnsItem {

    private final AdresseIP ip;
    private final NomMachine machine;

    public DnsItem(AdresseIP ip, NomMachine machine) {
        this.ip = ip;
        this.machine = machine;
    }

    public AdresseIP getIp() {
        return ip;
    }

    public NomMachine getMachine() {
        return machine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DnsItem)) return false;
        DnsItem other = (DnsItem) o;
        return ip.equals(other.ip) && machine.equals(other.machine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, machine);
    }

    @Override
    public String toString() {
        return ip + " " + machine;
    }
}
