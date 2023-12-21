package ch.heigvd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        // Paramètres de connexion à la base de données
        String url = "jdbc:postgresql://localhost:5432/bdr";
        String user = "bdr";
        String password = "bdr";

        try {
            // Connexion à la base de données
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion à la base de données réussie.");

            // Création d'une requête
            Statement stmt = conn.createStatement();
            String query = "SELECT 1;"; // Exemple de requête simple

            // Exécution de la requête
            ResultSet rs = stmt.executeQuery(query);

            // Traitement des résultats
            if (rs.next()) {
                System.out.println("Requête exécutée avec succès : " + rs.getInt(1));
            } else {
                System.out.println("Pas de résultat pour la requête.");
            }

            // Fermeture des ressources
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }
}
