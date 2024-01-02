package ch.heigvd.utils.structure;

import ch.heigvd.utils.controller.GeneralController;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/***
 * Classe représentant toutes informations relatives à l'utilisateur
 */
@Getter
public class Account {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final Date birthDate;
    private final String email;
    private final String phoneNumber;
    private final String addressNumberStreet;
    private final String addressCity;
    private final String addressNPA;
    private final String addressCountry;
    private final String username;
    private final Integer payingMethodId;
    private final UserType userType;

    /***
     * Constructeur
     * @param info une structure de deux éléments (association : clef - information)
     */
    private Account(HashMap<String, String> info) {
        this.id = Integer.parseInt(info.get("id"));
        this.username = info.get("username");
        this.userType = UserType.valueOf(info.get("usertype"));
        this.firstName = info.get("prenom");
        this.lastName = info.get("nom");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.birthDate = sdf.parse(info.get("datenaissance"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.email = info.get("adressemail");
        this.phoneNumber = info.get("numerotelephone");
        this.addressNumberStreet = info.get("adresse");
        this.addressCity = info.get("ville");
        this.addressNPA = info.get("npa");
        this.addressCountry = info.get("pays");
        if (info.get("moyen_paiement_pref_id") == null) {
            this.payingMethodId = null;
        }else{
            this.payingMethodId = Integer.parseInt(info.get("moyen_paiement_pref_id"));
        }
    }


    /***
     * comparaison du compte associé
     * @param username nom d'utilisateur
     * @param password mdp
     * @return création d'une nouvelle instance de la classe Compte
     */
    public static Account from(String username, String password) {
        HashMap<String, String> information = new GeneralController().getAccount(username);
        if (information == null || !information.get("mot_de_passe").equals(password)) {
            return null;
        }
        return new Account(information);
    }

}
