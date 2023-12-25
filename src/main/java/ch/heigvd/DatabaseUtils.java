package ch.heigvd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

    public static void executeSqlScript(Connection conn, String scriptPath) throws IOException, SQLException {
        InputStream inputStream = DatabaseUtils.class.getResourceAsStream(scriptPath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        Statement stmt = conn.createStatement();

        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                stmt.execute(line);
            }
        }

        stmt.close();
        reader.close();
    }
}
