package ch.heigvd.utils.db;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String user = "bdr";
        String password = "bdr";
        String schema = "pagila";
        String url = "jdbc:postgresql://localhost:5432/bdr";
        SQLManagement management = new SQLManagement(user, password, url, schema);
        ResultSet rs = management.getTable("actor");
        List<HashMap<String, String> > table = SQLManagement.toList(rs);
        System.out.println(Arrays.toString(table.toArray()));
    }
}
