package ch.heigvd.components;

import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import ch.heigvd.utils.structure.Account;
import ch.heigvd.utils.web.CookieManager;
import freemarker.template.Template;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.StringWriter;

public class RegisterForm {
    public static String doGet(HttpServletRequest req) {
        try {
            if(CookieManager.isLogged(req)) {
                return "";
            }
            Template template = FreeMarkerConfig.getConfig().getTemplate("register_form.ftlh");
            StringWriter out = new StringWriter();
            template.process(null, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
