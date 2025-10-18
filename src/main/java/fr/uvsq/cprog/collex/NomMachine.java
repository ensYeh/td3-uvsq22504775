package fr.uvsq.cprog.collex;

import java.util.Objects;

public class NomMachine {

    private final String nomComplet;
    private final String nom;
    private final String domaine;

    public NomMachine(String nomComplet) {
        if (!nomComplet.contains(".")) {
            throw new IllegalArgumentException("NomMachine invalide : " + nomComplet);
        }
        this.nomComplet = nomComplet;
        String[] parts = nomComplet.split("\\.", 2);
        this.nom = parts[0];
        this.domaine = parts[1];
    }

    public String getNom() {
        return nom;
    }

    public String getDomaine() {
        return domaine;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NomMachine)) return false;
        NomMachine other = (NomMachine) o;
        return nomComplet.equals(other.nomComplet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomComplet);
    }

    @Override
    public String toString() {
        return nomComplet;
    }
}
