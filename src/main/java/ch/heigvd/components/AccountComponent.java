package ch.heigvd.components;

import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import ch.heigvd.utils.structure.Account;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class AccountComponent {
    public static String doGet(Account account) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("account", account);

            Template template = FreeMarkerConfig.getConfig().getTemplate("accountComponent.ftlh");
            StringWriter out = new StringWriter();
            template.process(data, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
