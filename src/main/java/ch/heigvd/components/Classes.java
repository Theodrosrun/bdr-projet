package ch.heigvd.components;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classes {
    public static String doGet(List<Class> classes) {
        try {
            // Configuration de FreeMarker
            Configuration cfg = FreeMarkerConfig.getConfig();

            // Préparer les données pour le template
            Map<String, Object> templateData = new HashMap<>();
            templateData.put("classes", classes);

            // Charger le template et générer le HTML
            Template template = cfg.getTemplate("html/about.ftlh");
            StringWriter out = new StringWriter();
            template.process(templateData, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
