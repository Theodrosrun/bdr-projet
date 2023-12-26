package ch.heigvd.utils.controller;

import ch.heigvd.utils.db.SQLManager;
import ch.heigvd.utils.structure.Table;
import ch.heigvd.utils.structure.UserType;

import java.util.HashMap;
import java.util.List;

public class UserController {
    private final int id;
    private final String userName;
    private final UserType userType;
    private final SQLManager sqlManager = new SQLManager(
            "bdr",
            "bdr",
            "jdbc:postgresql://localhost:5432/bdr",
            "my_amazing_fitness");

    public UserController(String userName) {
        HashMap<String, String> member = new GeneralController().getMember(userName);
        HashMap<String, String> employee = new GeneralController().getEmployee(userName);
        this.userName = userName;
        this.id = getId(member, employee);
        this.userType = getUserType(member, employee);
    }


    public List<HashMap<String, String>> getCurrentAbos() {
        return SQLManager.toList(sqlManager.select(Table.Abonnement.name(),
                "typeAbonnement = 'Gym' AND disponibilite = true"));
    }

    private static int getId(HashMap<String, String> member, HashMap<String, String> employee) {
        if (member != null) {
            return Integer.parseInt(member.get("member_id"));
        } else if (employee != null) {
            return Integer.parseInt(employee.get("employe_id"));
        } else {
            throw new RuntimeException("User does not exist");
        }
    }

    private static UserType getUserType(HashMap<String, String> member, HashMap<String, String> employee) {
        if (member != null) {
            return UserType.Membre;
        } else if (employee != null) {
            return UserType.Admin;
        } else {
            throw new RuntimeException("User does not exist");
        }
    }
}
