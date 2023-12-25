package ch.heigvd;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/database")
public class DatabaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://postgresql:5432/bdr", "bdr", "bdr");

            DatabaseUtils.executeSqlScript(conn, "scripts/import_schema.sql");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT 1;");

            if (rs.next()) {
                out.println("<p>Connexion et requête réussies : " + rs.getInt(1) + "</p>");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            out.println("<p>Erreur : " + e.getMessage() + "</p>");
        }
    }

    // Chargement manuel du pilote
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
