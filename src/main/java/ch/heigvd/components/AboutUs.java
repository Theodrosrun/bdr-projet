package ch.heigvd.components;

import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import freemarker.template.Template;
import java.io.StringWriter;

public class AboutUs {

    /***
     * Cette méthode est utilisée pour gérer les requêtes HTTP de type GET.
     * Cela permet une réutilisation efficace du code et facilite la gestion des mises à jour de l'interface utilisateur.
     * @return un string
     */
    public static String doGet() {
        try {
            Template template = FreeMarkerConfig.getConfig().getTemplate("about_us.ftlh");
            StringWriter out = new StringWriter();
            template.process(null, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
