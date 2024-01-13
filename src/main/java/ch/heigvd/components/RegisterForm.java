package ch.heigvd.components;

import ch.heigvd.utils.controller.GeneralController;
import ch.heigvd.utils.form.Field;
import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import ch.heigvd.utils.web.CookieManager;
import freemarker.template.Template;
import jakarta.servlet.http.HttpServletRequest;

import java.io.StringWriter;
import java.util.*;

/***
 * Cette classe sert à récupérer les paramètres correctement lors de l'inscription d'un nouvel utilisateur
 */
public class RegisterForm {
    public static final List<String> DURATIONS = List.of("1", "3", "6", "12");
    public static final List<String> FREQUENCIES = List.of("1", "3", "6", "12");
    private static final List<Field> fields = new ArrayList<>(){{
        add(new Field("Last Name", "lastname", "text"));
        add(new Field("First Name", "name", "text"));
        add(new Field("Date of Birth", "dateOfBirth", "date"));
        add(new Field("Email address", "email", "email"));
        add(new Field("Mobile", "mobile", "text"));
        add(new Field("Street", "street", "text"));
        add(new Field("Number", "number", "text"));
        add(new Field("City", "city", "text"));
        add(new Field("Zip Code", "zipCode", "number"));
        add(new Field("Country", "country", "text"));
        add(new Field("Plan", "plan", "select"));
        add(new Field("Start Date", "startDate", "date"));
        add(new Field("Duration", "duration", "select", DURATIONS));
        add(new Field("Frequency", "frequency", "select", FREQUENCIES));
        add(new Field("Payment Method", "paymentMethod", "select"));
        add(new Field("Payment Information", "paymentInformation", "text"));
    }};

    // Créez une liste statique de paramètres de personne à partir des dix premiers champs
    // Utilisation de map pour convertir chaque field en récupérant le label (ex de label "DateOfBirth")
    public static final List<String> PERSON_PARAM_NAMES = fields.subList(0, 10).stream().map(Field::getInputName).toList();
    public static final List<String> MEMBER_CONTRACT_PARAM_NAMES = fields.subList(10, 11).stream().map(Field::getInputName).toList();
    public static final List<String> CONTRACT_PARAM_NAMES = fields.subList(11, 14).stream().map(Field::getInputName).toList();
    public static final List<String> PAYMENT_METHOD_PARAM_NAMES = fields.subList(14, 16).stream().map(Field::getInputName).toList();

    /***
     * Affichage des informations
     */
    public static String doGet(HttpServletRequest req) {
        try {
            if(CookieManager.isLogged(req)) {
                return "";
            }
            Map<String, Object> data = new HashMap<>();
            List<String> plans = new GeneralController()
                    .getGymPlans()
                    .stream()
                    .map(plan -> plan.get("abo_id"))
                    .toList();
            fields.get(10).setOptions(plans);

            List<String> paymentMethods = new GeneralController()
                    .getTypePaymentMethods()
                    .stream()
                    .map(paymentMethod -> paymentMethod.get("nom"))
                    .toList();
            fields.get(14).setOptions(paymentMethods);

            Template template = FreeMarkerConfig.getConfig().getTemplate("register_form.ftlh");
            data.put("plans", plans);
            data.put("paymentMethods", paymentMethods);
            data.put("fields", fields);
            data.put("durations", DURATIONS);
            data.put("frequencies", FREQUENCIES);
            StringWriter out = new StringWriter();
            template.process(data, out);
            return out.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
