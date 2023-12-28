package ch.heigvd.components;

import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import freemarker.template.Template;
import java.io.StringWriter;

public class Slider {
    public static String doGet() {
        try {
            Template template = FreeMarkerConfig.getConfig().getTemplate("slider.ftlh");
            StringWriter out = new StringWriter();
            template.process(null, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
