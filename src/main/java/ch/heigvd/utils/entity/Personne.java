package ch.heigvd.utils.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jdk.jfr.Name;
import lombok.Getter;

import java.util.Date;
@Entity
@Getter
@Table(name = "Personne")
public class Personne {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private int id;

        @Column(name = "nom", nullable = false)
        private String nom;

        @Column(name = "prenom", nullable = false)
        private String prenom;

        @Column(name = "datenaissance", nullable = false, columnDefinition = "DATE")
        private Date dateNaissance;

        @Column(name = "adressemail", nullable = false)
        private String adresseMail;

        @Column(name = "numerotelephone", nullable = false)
        private String numeroTelephone;

        @Column(name = "rue", nullable = false)
        private String rue;

        @Column(name = "numero", nullable = false)
        private String numero;

        @Column(name = "ville", nullable = false)
        private String ville;

        @Column(name = "npa", nullable = false)
        private int npa;

        @Column(name = "pays", nullable = false)
        private String pays;

        public Personne() {}

        public Personne(@NotBlank String nom,
                        @NotBlank String prenom,
                        @PastOrPresent Date dateNaissance,
                        @Email String adresseMail,
                        @NotBlank String numeroTelephone,
                        @NotBlank String rue,
                        @NotBlank String numero,
                        @NotBlank String ville,
                        @Positive int npa,
                        @NotBlank String pays) {
                this.nom = nom;
                this.prenom = prenom;
                this.dateNaissance = dateNaissance;
                this.adresseMail = adresseMail;
                this.numeroTelephone = numeroTelephone;
                this.rue = rue;
                this.numero = numero;
                this.ville = ville;
                this.npa = npa;
                this.pays = pays;
        }
}

