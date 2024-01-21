package ch.heigvd.utils.controller;

import ch.heigvd.utils.db.SQLManager;
import ch.heigvd.utils.structure.Table;
import ch.heigvd.utils.structure.View;

import java.sql.ResultSet;
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

    public int executeUpdate(String query, Object... params) {
        return sqlManager.executeUpdate(query, params);
    }

    public List<HashMap<String, String>> getInstructorsWithCoursesTypes() {
        return SQLManager.toHashMapList(sqlManager.select(View.IntructeurTypeCoursView.name()));
    }

    public List<HashMap<String, String>> getTodayAverageFrequency(int fitnessId) {
        return SQLManager.toHashMapList(sqlManager.select(View.MoyennePersonnesParHeureCeJourDeSemaineView.name(),
                "fitness_id = '" + fitnessId + "'"));
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

    public List<HashMap<String, String>> getTypeCours() {
        return SQLManager.toHashMapList(sqlManager.select(Table.TypeCours.name()));
    }

    public List<HashMap<String, String>> getAccountView() {
        return SQLManager.toHashMapList(sqlManager.select(View.AccountView.name()));
    }
}
