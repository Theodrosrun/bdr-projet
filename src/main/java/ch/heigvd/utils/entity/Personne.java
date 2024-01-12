package ch.heigvd.utils.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Personne")
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "dateNaissance", nullable = false)
    private Date dateNaissance;

    @Column(name = "adresseMail", nullable = false)
    private String adresseMail;

    @Column(name = "numeroTelephone")
    private String numeroTelephone;

    @Column(name = "numero", nullable = false)
    private int numero;

    @Column(name = "rue", nullable = false)
    private String rue;

    @Column(name = "ville", nullable = false)
    private String ville;

    @Column(name = "NPA", nullable = false)
    private int npa;

    @Column(name = "pays", nullable = false)
    private String pays;

    public Personne() {
    }

}

