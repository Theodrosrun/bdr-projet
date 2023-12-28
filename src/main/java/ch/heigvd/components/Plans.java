package ch.heigvd.components;

import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plans {
    public static String doGet(String title, List<HashMap<String, String>> plans, boolean withButton) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("title", title);
            data.put("plans", plans);
            data.put("withButton", withButton);

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
