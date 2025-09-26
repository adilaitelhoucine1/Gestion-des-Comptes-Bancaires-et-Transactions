package model;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Personne {
    private int idGestionnaire;
    private String departement;
    private List<Client> listeClients;

    public Manager(String nom, String prenom, String email, String motDePasse, String departement) {
        super(nom, prenom, email, motDePasse);
       // this.idGestionnaire = idGestionnaire;
        this.departement = departement;
        this.listeClients = new ArrayList<>();
    }

    public int getIdGestionnaire() { return idGestionnaire; }
    public void setIdGestionnaire(int idGestionnaire) { this.idGestionnaire = idGestionnaire; }

    public String getDepartement() { return departement; }
    public void setDepartement(String departement) { this.departement = departement; }

    public List<Client> getListeClients() { return listeClients; }
    public void setListeClients(List<Client> listeClients) { this.listeClients = listeClients; }
}
