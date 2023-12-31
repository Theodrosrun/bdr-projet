package ch.heigvd.utils.db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Getter
public class SQLManager {

    private final Connection connection;

    /***
     * Constructeur
     * @param user utlisateur
     * @param password mot de passe
     * @param url URL
     * @param schema schéma
     */
    public SQLManager(String user, String password, String url, String schema) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erreur lors du chargement du driver", e);
        }
        try {
            connection = DriverManager.getConnection(
                    url + "?user=" + user + "&password=" + password + "&currentSchema=" + schema
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données", e);
        }
    }

    /***
     * Surchage de l'utilisation de select
     * @param table table donnée
     * @return résultat du SELECT
     */
    public ResultSet select(String table) {
        return executeQuery(createSelectQuery(table, List.of("*"), null, null, null));
    }

    public ResultSet select(String table, String where) {
        return executeQuery(createSelectQuery(table, List.of("*"), null, where, null));
    }

    public ResultSet select(String table, String where, String orderBy) {
        return executeQuery(createSelectQuery(table, List.of("*"), null, where, orderBy));
    }

    public ResultSet select(String table, String... columns) {
        return executeQuery(createSelectQuery(table, List.of(columns), null, null, null));
    }

    public ResultSet select(String table, String where, String... columns) {
        return executeQuery(createSelectQuery(table, List.of(columns), null, where, null));
    }

    public ResultSet select(String table, String where, String orderBy, String... columns) {
        return executeQuery(createSelectQuery(table, List.of(columns), null, where, orderBy));
    }

    public ResultSet select(String table, List<String> columns) {
        return executeQuery(createSelectQuery(table, columns, null, null, null));
    }

    /***
     * Fonction qui pourrait être factorisée mais qui ne modifie pas celle ci-dessus
     * @param table table de base
     * @param inner écrire l'intégralité de l'inner join sous forme de string
     * @param utilisationInnerJoin avertir que l'on souhaite utiliser un innerjoin. Uniquement pour surchargé mais inutile
     * @return la selection avec inner join.
     */
    public ResultSet select(String table, String inner, boolean utilisationInnerJoin) {
        return executeQuery(createSelectQuery(table, List.of("*"), inner, null, null));
    }

    /***
     * Fermeture de la connexion à la BDD
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la fermeture de la connexion à la base de données", e);
        }
    }

    /***
     * Requête SQL à la BDD plus complexe
     * @param table table
     * @param columns colonnes désirées
     * @param innerJoin optionnellement la combinaison de tables --> pas utilisé jusqu'à mnt
     * @param where condition
     * @param orderBy triage
     * @return résultat de la requête sous forme de String
     */
    private String createSelectQuery(String table,
                                     List<String> columns,
                                     String innerJoin,
                                     String where,
                                     String orderBy
    ) {
        StringBuilder query = new StringBuilder("SELECT ");
        for (String column : columns) {
            query.append(column).append(", ");
        }
        query.delete(query.length() - 2, query.length());
        query.append(" FROM ").append(table);
        if (innerJoin != null)
            query.append(innerJoin);
        if (where != null) {
            query.append(" WHERE ").append(where);
        }
        if (orderBy != null) {
            query.append(" ORDER BY ").append(orderBy);
        }
        return query.toString();
    }

    /***
     * Exécution d'une requête
     * @param query string formé grâce à la fonction createSelectQuery()
     * @return un ResultSet
     */
    private ResultSet executeQuery(String query) {
        try {
            return connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'exécution de la requête", e);
        }
    }

    /***
     * Cette méthode crée une liste de HashMaps où chaque HashMap représente une ligne de données
     * avec un ensemble de colonnes (clé-valeur pour chaque colonne).
     * @param rs résultat de la requête de la fonction executeQuery()
     * @return une structure de deux éléments où les clés sont les noms des colonnes et les valeurs sont les valeurs
     * correspondantes de chaque colonne dans cette ligne.
     */
    public static List<HashMap<String, String>> toList(ResultSet rs) {
        List<HashMap<String, String>> list = new ArrayList<>();
        try (rs) {
            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    row.put(rs.getMetaData().getColumnName(i), rs.getString(i));
                }
                list.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la conversion du ResultSet en liste", e);
        }
        return list;
    }


    /***
     * Fonction servant à insérer des nouveaux attributs dans une table
     * @param table table en question
     * @param columns colonnes à renseigner
     * @param values attributs
     */
    public void insert(String table, List<String> columns, List<Object> values) {
        StringBuilder queryBuilder = new StringBuilder("INSERT INTO ").append(table).append(" (");

        for (String column : columns) {
            queryBuilder.append(column).append(", ");
        }

        queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
        queryBuilder.append(") VALUES (");

        for (Object value : values) {
            if (value instanceof Number) {
                // Si la valeur est un nombre, on l'ajoute tel quel
                queryBuilder.append(value);
            } else {
                // Sinon, on entoure la valeur de guillemets simples
                queryBuilder.append("'").append(value).append("'");
            }
            queryBuilder.append(", ");
        }

        queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
        queryBuilder.append(")");

        String query = queryBuilder.toString();

        try {
            connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'insertion des données dans la table", e);
        }
    }
}
