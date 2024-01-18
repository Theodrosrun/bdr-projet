package ch.heigvd.components;

import ch.heigvd.utils.freemarker.FreeMarkerConfig;
import ch.heigvd.utils.view.HoraireCoursView;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.*;

public class TimeTable {
    public static String doGet(HoraireCoursView hr) {
        try {
            Template template = FreeMarkerConfig.getConfig().getTemplate("time_table.ftlh");
            StringWriter out = new StringWriter();
            Map<String, Object> data = new HashMap<>();
            List<List<String>> cours = hr.getCours();
            data.put("jours", HoraireCoursView.JOURS);
            data.put("rows", cours);
            List<String> typeOfCours = new ArrayList<>();
            for (List<String> cour : cours) {
                for (String s : cour) {
                    if (!Objects.isNull(s) && s.contains(" - ")){
                        String tmp = s.split(" - ")[0];
                        if (!typeOfCours.contains(tmp)) {
                            typeOfCours.add(tmp);
                        }
                    }
                }
            }
            data.put("typeOfCours", typeOfCours);
            template.process(data, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la génération du contenu : " + e.getMessage();
        }
    }
}
