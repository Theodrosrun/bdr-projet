package ch.heigvd.utils.controller;

import ch.heigvd.utils.db.SQLManager;
import ch.heigvd.utils.structure.Table;
import ch.heigvd.utils.structure.View;

import java.util.HashMap;
import java.util.List;

public class GeneralController {
    private final SQLManager sqlManager = new SQLManager(
            "bdr",
            "bdr",
            "jdbc:postgresql://localhost:5432/bdr",
            "my_amazing_fitness");

    public GeneralController() {}

    public HashMap<String, String> getAccount(String userName) {
        List<HashMap<String, String>> accounts = SQLManager.toList(sqlManager.select(View.AccountView.name(),
                "username = '" + userName + "'"));
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    public HashMap<String, String> getMember(String userName) {
        List<HashMap<String, String>> members = SQLManager.toList(sqlManager.select(Table.Membre.name(),
                "compte_id = '" + userName + "'"));
        if (members.isEmpty()) {
            return null;
        }
        return members.get(0);
    }

    public  HashMap<String, String> getEmployee(String userName) {
        List<HashMap<String, String>> employees = SQLManager.toList(sqlManager.select(Table.Employe.name(),
                "compte_id = '" + userName + "'"));
        if (employees.isEmpty()) {
            return null;
        }
        return employees.get(0);
    }

    public List<HashMap<String, String>> getMembers() {
        return SQLManager.toList(sqlManager.select(Table.Membre.name()));
    }

    public List<HashMap<String, String>> getEmployees() {
        return SQLManager.toList(sqlManager.select(Table.Employe.name()));
    }

    public List<HashMap<String, String>> getAccounts() {
        return SQLManager.toList(sqlManager.select(Table.Compte.name()));
    }

    public List<HashMap<String, String>> getAbosOf(String userName) {
        return SQLManager.toList(sqlManager.select(Table.Abonnement.name(),
                "membre_id = '" + userName + "'"));
    }

    public List<HashMap<String, String>> getCurrentAbosMuscu() {
        return SQLManager.toList(sqlManager.select(Table.Abonnement.name(),
                "typeAbonnement = 'Gym' AND disponibilite = true"));
    }

    public List<HashMap<String, String>> getCurrentAbosCours() {
        return SQLManager.toList(sqlManager.select(Table.Abonnement.name(),
                "typeAbonnement = 'Course' AND disponibilite = true"));
    }
}
