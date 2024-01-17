package ch.heigvd.utils.view;

import lombok.Getter;

@Getter
public class AccountView {
    private String username;
    private int moyen_paiement_pref_id;
    private int id;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String adresseMail;
    private String numeroTelephone;
    private String adresse;
    private String ville;
    private String NPA;
    private String pays;

    public AccountView(Object[] obj) {
        this.username = (String) obj[0];
        this.moyen_paiement_pref_id = Integer.parseInt((String) obj[1]);
        this.id = Integer.parseInt((String) obj[2]);
        this.nom = (String) obj[3];
        this.prenom = (String) obj[4];
        this.dateNaissance = (String) obj[5];
        this.adresseMail = (String) obj[6];
        this.numeroTelephone = (String) obj[7];
        this.adresse = (String) obj[8];
        this.ville = (String) obj[9];
        this.NPA = (String) obj[10];
        this.pays = (String) obj[11];
    }

}
