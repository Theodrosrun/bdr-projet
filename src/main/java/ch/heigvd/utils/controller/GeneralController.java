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
        List<HashMap<String, String>> accounts = SQLManager.toHashMapList(sqlManager.select(View.AccountView.name(),
                "username = '" + userName + "'"));
        return accounts.isEmpty() ? null : accounts.get(0);
    }


    /***
     * Récupération des informations (columns) sur les cours relatifs à l'identifiant de l'instructeur (clause WHERE)
     * @param memberId identifiant de l'instructeur
     * @param columns colonnes dont on souhaite connaître les informations
     * @return les informations liées à l'identifiant
     */
    public List<HashMap<String, String>> getInstructorWeekCourses(int memberId, String ... columns) {
        // la liste de tous les cours de la semaine des instructeurs sur la vue CourseWeekView
        return SQLManager.toHashMapList(sqlManager.select(View.CourseWeekView.name(),
                "instructeur_id = '" + memberId + "'", columns));
    }


    public List<HashMap<String, String>> getInstructorsWithCoursesTypes() {
        return SQLManager.toHashMapList(sqlManager.select(View.IntructeurTypeCoursView.name()));
    }

    public List<HashMap<String, String>> getTodayAverageFrequency(int fitnessId) {
        return SQLManager.toHashMapList(sqlManager.select(View.MoyennePersonnesParHeureCeJourDeSemaineView.name(),
                "fitness_id = '" + fitnessId + "'"));
    }


    /***
     * Fonction qui pourrait être améliorée, elle remplit son rôle
     * @return la totalité des instructeurs avec plus que seulement leur identifiant (cf la table)
     */
    public List<HashMap<String, String>> getInstructors() {
        return SQLManager.toHashMapList(sqlManager.select(Table.Instructeur.name(),
                " INNER JOIN employe ON instructeur_id = id ", true));
    }

    /***
     * Obtention de la liste de tous les abonnements disponibles de Gym
     * @return liste de tous les abonnements de muscu
     */
    public List<HashMap<String, String>> getGymPlans() {
        return SQLManager.toHashMapList(sqlManager.select(Table.Abonnement.name(),
                "type_abonnement = 'Gym' AND disponibilite = true"));
    }

    public List<HashMap<String, String>> getTypePaymentMethods() {
        return SQLManager.toHashMapList(sqlManager.select(Table.TypeMoyenPaiement.name()));
    }
}