package ch.heigvd.components;

import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Head {
    private final String pageName;
    private static final String TITLE = "My Amazing Fitness";
    private static final String DESCRIPTION = "Become the best version of yourself";
    private static final String CHARSET = "UTF-8";

    public Head(String pageName) {
        this.pageName = pageName;
    }

    public String doGet() {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("charset", CHARSET);
            data.put("description", DESCRIPTION);
            data.put("title", TITLE);
            data.put("pageName", pageName);

            Template template = FreeMarkerConfig.getConfig().getTemplate("head.ftlh");
            StringWriter out = new StringWriter();
            template.process(data, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
