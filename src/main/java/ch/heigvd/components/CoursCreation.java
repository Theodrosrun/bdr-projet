package ch.heigvd.components;

import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import freemarker.template.Template;
import java.io.StringWriter;

public class CoursCreation {
    public static String doGet() {
        try {
            Template template = FreeMarkerConfig.getConfig().getTemplate("cours_creation.ftlh");
            StringWriter out = new StringWriter();
            template.process(null, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
