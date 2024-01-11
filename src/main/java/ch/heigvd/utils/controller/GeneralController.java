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
     * Récupération des informations liées au compte d'utilisateur du pseudo renseigné (clause WHERE)
     * @param userName nom d'utilisateur renseigné
     * @return les informations liées au compte d'utilisateur
     */
    public HashMap<String, String> getAccount(String userName) {
        // la liste de tous les utilisateurs visibles sur la vue AccountView
        List<HashMap<String, String>> accounts = SQLManager.toList(sqlManager.select(View.AccountView.name(),
                "username = '" + userName + "'"));
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    /***
     * Récupération des informations liées à l'identifiant de la personne (clause WHERE)
     * @param personId identifiant de la personne
     * @return les informations liées à l'identifiant
     */
    public HashMap<String, String> getPerson(int personId) {
        List<HashMap<String, String>> persons = SQLManager.toList(sqlManager.select(Table.Personne.name(),
                "id = '" + personId + "'"));
        return persons.isEmpty() ? null : persons.get(0);
    }

    /***
     * Récupération des informations liées à l'identifiant du membre (clause WHERE)
     * @param memberId identifiant du membre
     * @return les informations liées à l'identifiant
     */
    public List<HashMap<String, String>> getSubscriptions(int memberId) {
        // la liste de tous les abonnements des membres sur la vue MembreAbonnementView
        return SQLManager.toList(sqlManager.select(View.MembreAbonnementView.name(),
                "membre_id = '" + memberId + "'"));
    }

    /***
     * Récupération des informations (columns) sur les factures relatives à l'identifiant du membre (clause WHERE)
     * @param memberId identifiant du membre
     * @param columns colonnes dont on souhaite connaître les informations
     * @return les informations liées à l'identifiant
     */
    public List<HashMap<String, String>> getBills(int memberId, String ... columns) {
        // la liste de toutes les factures des membres sur la vue MembreFactureView
        return SQLManager.toList(sqlManager.select(View.MembreFactureView.name(),
                "membre_id = '" + memberId + "'",
                columns));
    }

    /***
     * Récupération des informations sur les membres n'ayant pas payé
     * @return la liste des informations sur tous les employés n'ayant pas payé
     */
    public List<HashMap<String, String>> getMembersUnpaid() {
        // la liste de toutes les factures des membres sur la vue MembreFactureView
        return SQLManager.toList(sqlManager.select(View.MembreFactureView.name(),
                "payment_id IS NULL AND date_echeance < CURRENT_DATE"));
    }

    /***
     * Récupération des informations (columns) sur les cours relatifs à l'identifiant du membre (clause WHERE)
     * @param memberId identifiant du membre
     * @param columns colonnes dont on souhaite connaître les informations
     * @return les informations liées à l'identifiant
     */
    public List<HashMap<String, String>> getMemberCourses(int memberId, String ... columns) {
        // la liste de tous les cours de la semaine des membres sur la vue MemberCourseWeekView
        return SQLManager.toList(sqlManager.select(View.MemberCourseWeekView.name(),
                "membre_id = '" + memberId + "'", columns));
    }

    /***
     * Récupération des informations (columns) sur les cours relatifs à l'identifiant de l'instructeur (clause WHERE)
     * @param memberId identifiant de l'instructeur
     * @param columns colonnes dont on souhaite connaître les informations
     * @return les informations liées à l'identifiant
     */
    public List<HashMap<String, String>> getInstructorWeekCourses(int memberId, String ... columns) {
        // la liste de tous les cours de la semaine des instructeurs sur la vue CourseWeekView
        return SQLManager.toList(sqlManager.select(View.CourseWeekView.name(),
                "instructeur_id = '" + memberId + "'", columns));
    }

    /***
     * Récupération du membre grâce à l'identifiant du compte
     * @param compte_id identifiant du compte
     * @return les informations sur le membre
     */
    public HashMap<String, String> getMember(int compte_id) {
        List<HashMap<String, String>> members = SQLManager.toList(sqlManager.select(Table.Membre.name(),
                "compte_id = '" + compte_id + "'"));
        if (members.isEmpty()) {
            return null;
        }
        return members.get(0);
    }

    public List<HashMap<String, String>> getInstructorsWithCoursesTypes() {
        return SQLManager.toList(sqlManager.select(View.IntructeurTypeCoursView.name()));
    }

    public List<HashMap<String, String>> getTodayAverageFrequency(int fitnessId) {
        return SQLManager.toList(sqlManager.select(View.MoyennePersonnesParHeureCeJourDeSemaineView.name(),
                "fitness_id = '" + fitnessId + "'"));
    }

    /***
     * Récupération des informations de l'employée grâce à l'identifiant du compte
     * @param compte_id identifiant du compte
     * @return les informations de l'employée
     */
    public  HashMap<String, String> getEmployee(int compte_id) {
        List<HashMap<String, String>> employees = SQLManager.toList(sqlManager.select(Table.Employe.name(),
                "compte_id = '" + compte_id + "'"));
        if (employees.isEmpty()) {
            return null;
        }
        return employees.get(0);
    }

    /***
     * Liste tous les membres
     * @return la liste de tous les membres
     */
    public List<HashMap<String, String>> getMembers() {
        return SQLManager.toList(sqlManager.select(Table.Membre.name()));
    }

    /***
     * Liste tous les employés
     * @return la liste de tous les employés
     */
    public List<HashMap<String, String>> getEmployees() {
        return SQLManager.toList(sqlManager.select(Table.Employe.name()));
    }

    /***
     * Fonction qui pourrait être améliorée, elle remplit son rôle
     * @return la totalité des instructeurs avec plus que seulement leur identifiant (cf la table)
     */
    public List<HashMap<String, String>> getInstructors() {
        return SQLManager.toList(sqlManager.select(Table.Instructeur.name(), " INNER JOIN employe ON instructeur_id = id ", true));
    }

    /**
     * Liste tous les comptes
     * @return la liste de tous les comptes
     */
    public List<HashMap<String, String>> getAccounts() {
        return SQLManager.toList(sqlManager.select(Table.Compte.name()));
    }

    /***
     * Obtention de la liste de tous les abonnements de l'identifiant du membre
     * @param memberId identifiant du membre
     * @return la liste de tous les abonnements
     */
    public List<HashMap<String, String>> getAbosOf(int memberId) {
        return SQLManager.toList(sqlManager.select(Table.Abonnement.name(),
                "membre_id = '" + memberId + "'"));
    }

    /***
     * Obtention de la liste de tous les abonnements disponibles de Gym
     * @return liste de tous les abonnements de muscu
     */
    public List<HashMap<String, String>> getCurrentAbosMuscu() {
        return SQLManager.toList(sqlManager.select(Table.Abonnement.name(),
                "type_abonnement = 'Gym' AND disponibilite = true"));
    }

    /***
     * Obtention de la liste de tous les abonnements disponibles de cours
     * @return liste de tous les abonnements disponibles
     */
    public List<HashMap<String, String>> getCurrentAbosCours() {
        return SQLManager.toList(sqlManager.select(Table.Abonnement.name(),
                "type_abonnement = 'Course' AND disponibilite = true"));
    }

    /***
     * Donne le moyen de payement en fonction de son identifiant
     * @param payingMethodId idenfiant du moyen de payement
     * @return le moyen de payement
     */
    public HashMap<String, String> getPayingMethods(int payingMethodId) {
        List<HashMap<String, String>> methods = SQLManager.toList(sqlManager.select(Table.MoyenPaiement.name(),
                "moyen_paiement_id = '" + payingMethodId + "'"));
        return methods.isEmpty() ? null : methods.get(0);
    }

    public void insert(String table, List<String> columns, List<Object> values) {
        SQLManager.insert(table, columns, values);
    }
}
