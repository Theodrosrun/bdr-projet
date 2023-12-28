package ch.heigvd.utils.structure;

import ch.heigvd.utils.controller.GeneralController;
import lombok.Getter;

import java.util.HashMap;
@Getter
public class Account {
    private final int id;
    private final String username;
    private final UserType userType;

    private Account(HashMap<String, String> account) {
        this.id = Integer.parseInt(account.get("id"));
        this.username = account.get("username");
        this.userType = UserType.valueOf(account.get("usertype"));
    }

    public static Account from(String username, String password) {
        HashMap<String, String> account = new GeneralController().getAccount(username);
        if (account == null || !account.get("motdepasse").equals(password)) {
            return null;
        }
        return new Account(account);
    }

}
