package ch.heigvd.components;

import ch.heigvd.utils.controller.GeneralController;
import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountList {
    public static String doGet() {
        try {
            Template template = FreeMarkerConfig.getConfig().getTemplate("account_list.ftlh");
            Map<String, Object> data = new HashMap<>();
            List<HashMap<String, String>> accountList = new GeneralController().getAccountView();
            data.put("comptes", accountList);
            StringWriter out = new StringWriter();
            template.process(data, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
