package ch.heigvd.components;

import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import ch.heigvd.utils.structure.UserType;
import ch.heigvd.utils.web.CookieManager;
import freemarker.template.Template;
import jakarta.servlet.http.HttpServletRequest;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Menu {
    public static String doGet(HttpServletRequest req) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("isConnected", CookieManager.isLogged(req));
            data.put("isAdmin", CookieManager.isLogged(req) && Objects.requireNonNull(CookieManager
                    .getCookie(req, "userType")).getValue().equals(UserType.Administrateur.name()));
            Template template = FreeMarkerConfig.getConfig().getTemplate("menu.ftlh");
            StringWriter out = new StringWriter();
            template.process(data, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
