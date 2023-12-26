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

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la fermeture de la connexion à la base de données", e);
        }
    }

    private String createSelectQuery(String table,
                                     List<String> columns,
                                     List<String> innerJoin,
                                     String where,
                                     String orderBy
    ) {
        StringBuilder query = new StringBuilder("SELECT ");
        for (String column : columns) {
            query.append(column).append(", ");
        }
        query.delete(query.length() - 2, query.length());
        query.append(" FROM ").append(table);
        if (where != null) {
            query.append(" WHERE ").append(where);
        }
        if (orderBy != null) {
            query.append(" ORDER BY ").append(orderBy);
        }
        return query.toString();
    }

    private ResultSet executeQuery(String query) {
        try {
            return connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'exécution de la requête", e);
        }
    }

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

}
