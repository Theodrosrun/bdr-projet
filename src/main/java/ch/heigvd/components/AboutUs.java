package ch.heigvd.components;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;

public class AboutUs {
    public static String doGet() {
        try {
            // Configuration de FreeMarker
            Configuration cfg = FreeMarkerConfig.getConfig();

            // Charger le template et générer le HTML
            Template template = cfg.getTemplate("html/about.ftlh");
            StringWriter out = new StringWriter();
            template.process(null, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
