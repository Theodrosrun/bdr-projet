package ch.heigvd.components;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.StringWriter;

public class Slider {
    public static String doGet() {
        try {
            Configuration cfg = FreeMarkerConfig.getConfig();
            Template template = cfg.getTemplate("slider.ftlh");

            StringWriter out = new StringWriter();
            template.process(null, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
