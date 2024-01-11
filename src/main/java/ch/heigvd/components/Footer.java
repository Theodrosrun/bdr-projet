package ch.heigvd.components;

import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Footer {

    private static final String PHONE = "+41 (0) 24 557 63 30";
    private static final String ADDRESS = "Rte de Cheseaux 1, 1400 Yverdon-les-Bains";
    private static final String MAIL = "contact@heig-vd.ch";

    public static String doGet() {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("phone", PHONE);
            data.put("address", ADDRESS);
            data.put("mail", MAIL);

            Template template = FreeMarkerConfig.getConfig().getTemplate("footer.ftlh");
            StringWriter out = new StringWriter();
            template.process(data, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
