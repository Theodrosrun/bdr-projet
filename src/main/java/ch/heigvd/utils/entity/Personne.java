package ch.heigvd.utils.entity;

import jakarta.persistence.Column;
import java.util.Date;

public class Personne {
    private int id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String adresseMail;
    private String numeroTelephone;
    private String rue;
    private String numero;
    private String ville;
    private int npa;
    private String pays;

    public Personne(String nom, String prenom, Date dateNaissance, String adresseMail,
                    String numeroTelephone, String numero, String rue, String ville, int npa, String pays) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresseMail = adresseMail;
        this.numeroTelephone = numeroTelephone;
        this.numero = numero;
        this.rue = rue;
        this.ville = ville;
        this.npa = npa;
        this.pays = pays;
    }

    public void submit() {}

}

