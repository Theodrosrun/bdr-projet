package ch.heigvd.utils.view;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class HoraireCoursView {
    public final static List<String> JOURS = new ArrayList<>() {{
        add("Dimanche");
        add("Lundi");
        add("Mardi");
        add("Mercredi");
        add("Jeudi");
        add("Vendredi");
        add("Samedi");
    }};
    List<String> heures;
    List<List<String>> cours;

    public HoraireCoursView(List<Object[]> obj) {
        heures = new ArrayList<>();
        cours = new ArrayList<>();
        for (Object[] objects : obj) {
            heures.add((String) objects[0]);
        }
        for (int i = 0; i < heures.size(); i++) {
            List<String> tmp = new ArrayList<>();
            for (int j = 0; j < obj.get(0).length; j++) {
                tmp.add((String) obj.get(i)[j]);
            }
            cours.add(tmp);
        }
    }
}
