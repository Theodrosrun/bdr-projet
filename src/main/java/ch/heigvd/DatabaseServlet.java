package ch.heigvd;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/database")
public class DatabaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String url = "jdbc:postgresql://localhost:5432/bdr";
        String user = "bdr";
        String password = "bdr";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            out.println("<p>Connexion à la base de données réussie.</p>");

            Statement stmt = conn.createStatement();
            String query = "SELECT 1;";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                out.println("<p>Requête exécutée avec succès : " + rs.getInt(1) + "</p>");
            } else {
                out.println("<p>Pas de résultat pour la requête.</p>");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            out.println("<p>Erreur lors de la connexion à la base de données : " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }
}
