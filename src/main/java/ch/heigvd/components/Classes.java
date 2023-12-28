package ch.heigvd.components;

import freemarker.template.Template;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classes {
    public static String doGet(List<Class> classes) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("classes", classes);

            Template template = FreeMarkerConfig.getConfig().getTemplate("classes.ftlh");
            StringWriter out = new StringWriter();
            template.process(data, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
