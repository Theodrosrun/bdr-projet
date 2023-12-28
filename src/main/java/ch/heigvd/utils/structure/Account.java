package ch.heigvd.utils.structure;

import ch.heigvd.utils.controller.GeneralController;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
@Getter
public class Account {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final Date birthDate;
    private final String email;
    private final String phoneNumber;
    private final String IBAN;
    private final String addressNumberStreet;
    private final String addressCity;
    private final String addressNPA;
    private final String addressCountry;
    private final String username;
    private final UserType userType;

    private Account(HashMap<String, String> account) {
        this.id = Integer.parseInt(account.get("id"));
        this.username = account.get("username");
        this.userType = UserType.valueOf(account.get("usertype"));
        HashMap<String, String> infos = new GeneralController().getPerson(id);
        this.firstName = infos.get("prenom");
        this.lastName = infos.get("nom");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.birthDate = sdf.parse(infos.get("datenaissance"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.email = infos.get("adressemail");
        this.phoneNumber = infos.get("numerotelephone");
        this.IBAN = infos.get("iban");
        this.addressNumberStreet = infos.get("rue") + " " + infos.get("numero");
        this.addressCity = infos.get("ville");
        this.addressNPA = infos.get("npa");
        this.addressCountry = infos.get("pays");
    }

    public static Account from(String username, String password) {
        HashMap<String, String> account = new GeneralController().getAccount(username);
        if (account == null || !account.get("motdepasse").equals(password)) {
            return null;
        }
        return new Account(account);
    }

}
