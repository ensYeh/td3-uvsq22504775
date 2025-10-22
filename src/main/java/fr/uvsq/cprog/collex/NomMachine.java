package fr.uvsq.cprog.collex;

import java.util.Objects;

public class NomMachine {

    private final String nomComplet; // ex : machine.local, poste.uvsq.fr

    public NomMachine(String nomComplet) {
        if (nomComplet == null || !nomComplet.matches("[a-zA-Z0-9]+([-.][a-zA-Z0-9]+)*")) {
            throw new IllegalArgumentException("NomMachine invalide : " + nomComplet);
        }
        this.nomComplet = nomComplet;
    }

    // renvoie le nom complet
    public String getNomComplet() {
        return nomComplet;
    }

    // renvoie le domaine (tout après le premier '.')
    public String getDomaine() {
        int idx = nomComplet.indexOf('.');
        if (idx == -1 || idx == nomComplet.length() - 1) {
            return ""; // pas de domaine
        }
        return nomComplet.substring(idx + 1);
    }

    @Override
    public String toString() {
        return nomComplet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NomMachine)) return false;
        NomMachine that = (NomMachine) o;
        return nomComplet.equals(that.nomComplet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomComplet);
    }
}
