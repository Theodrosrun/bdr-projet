package ch.heigvd.components;

import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import ch.heigvd.utils.view.MembreAbonnementView;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plans {
    public static String doGet(String title, List<?> plans, boolean withInfo) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("title", title);       // titre du composant
            data.put("plans", plans);       // Liste d'informations
            data.put("withInfo", withInfo); // informations supplémentaires à afficher

            Template template = FreeMarkerConfig.getConfig().getTemplate("plans.ftlh");
            StringWriter out = new StringWriter();
            template.process(data, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
