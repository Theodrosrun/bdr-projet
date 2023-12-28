package ch.heigvd.components;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plans {
    public static String doGet(String title, List<HashMap<String, String>> plans, boolean withButton) {
        try {
            // Configuration de FreeMarker
            Configuration cfg = FreeMarkerConfig.getConfig();

            // Préparer les données pour le template
            Map<String, Object> templateData = new HashMap<>();
            templateData.put("title", title);
            templateData.put("plans", plans);
            templateData.put("withButton", withButton);

            // Charger le template et générer le HTML
            Template template = cfg.getTemplate("html/plans.ftlh");
            StringWriter out = new StringWriter();
            template.process(templateData, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
