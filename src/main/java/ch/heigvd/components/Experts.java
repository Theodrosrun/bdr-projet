package ch.heigvd.components;

import ch.heigvd.utils.controller.GeneralController;
import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Experts {
    public static String doGet() {
        try {
            Template template = FreeMarkerConfig.getConfig().getTemplate("experts.ftlh");
            Map<String, Object> data = new HashMap<>();
            List<HashMap<String, String>> experts = new GeneralController().getInstructorsWithCoursesTypes();
            data.put("title", "EXPERT TRAINERS");
            data.put("experts", experts);
            StringWriter out = new StringWriter();
            template.process(data, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
