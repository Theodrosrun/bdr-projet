package ch.heigvd.components;

import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class PayingMethod {
    public static String doGet(HashMap<String, String> method) {
        try {
            Template template = FreeMarkerConfig.getConfig().getTemplate("paying_methods.ftlh");
            StringWriter out = new StringWriter();
            Map<String, Object> data = new HashMap<>();
            data.put("payment_m", method);
            template.process(data, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
