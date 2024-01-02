package ch.heigvd.utils.controller;

import ch.heigvd.utils.db.SQLManager;
import ch.heigvd.utils.structure.Table;
import ch.heigvd.utils.structure.View;

import java.util.HashMap;
import java.util.List;

/***
 * Classe permettant de s'associer à la la BDD
 */
public class GeneralController {
    /***
     * Indicatifs de provenance de la BDD
     */
    private final SQLManager sqlManager = new SQLManager(
            "bdr",
            "bdr",
            "jdbc:postgresql://localhost:5432/bdr",
            "my_amazing_fitness");

    public GeneralController() {}

    /***
     * Récupération des informations liées au compte d'utilisateur du pseudo renseigné
     * @param userName nom d'utilisateur renseigné
     * @return
     */
    public HashMap<String, String> getAccount(String userName) {
        List<HashMap<String, String>> accounts = SQLManager.toList(sqlManager.select(View.AccountView.name(),
                "username = '" + userName + "'"));
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    /***
     * Récupération des informations liées aux personnes
     * @param personId
     * @return
     */
    public HashMap<String, String> getPerson(int personId) {
        List<HashMap<String, String>> persons = SQLManager.toList(sqlManager.select(Table.Personne.name(),
                "id = '" + personId + "'"));
        return persons.isEmpty() ? null : persons.get(0);
    }

    public List<HashMap<String, String>> getSubscriptions(int memberId) {
        return SQLManager.toList(sqlManager.select(View.MembreAbonnementView.name(),
                "membre_id = '" + memberId + "'"));
    }

    public List<HashMap<String, String>> getBills(int memberId, String ... columns) {
        return SQLManager.toList(sqlManager.select(View.MembreFactureView.name(),
                "membre_id = '" + memberId + "'",
                columns));
    }

    public List<HashMap<String, String>> getMembersUnpaid() {
        return SQLManager.toList(sqlManager.select(View.MembreFactureView.name(),
                "payment_id IS NULL AND date_echeance < CURRENT_DATE"));
    }

    public List<HashMap<String, String>> getMemberCourses(int memberId, String ... columns) {
        return SQLManager.toList(sqlManager.select(View.MemberCourseWeekView.name(),
                "membre_id = '" + memberId + "'", columns));
    }

    public List<HashMap<String, String>> getInstructorWeekCourses(int memberId, String ... columns) {
        return SQLManager.toList(sqlManager.select(View.CourseWeekView.name(),
                "instructeur_id = '" + memberId + "'", columns));
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
                "type_abonnement = 'Gym' AND disponibilite = true"));
    }

    public List<HashMap<String, String>> getCurrentAbosCours() {
        return SQLManager.toList(sqlManager.select(Table.Abonnement.name(),
                "type_abonnement = 'Course' AND disponibilite = true"));
    }

    public HashMap<String, String> getPayingMethods(int payingMethodId) {
        List<HashMap<String, String>> methods = SQLManager.toList(sqlManager.select(Table.MoyenPaiement.name(),
                "moyen_paiement_id = '" + payingMethodId + "'"));
        return methods.isEmpty() ? null : methods.get(0);
    }
}
