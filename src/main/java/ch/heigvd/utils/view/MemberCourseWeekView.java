package ch.heigvd.utils.view;

import lombok.Getter;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class MemberCourseWeekView {
    private String jour;
    private String heure;
    private String description;
    private String instructeur_id;
    private String typecours;
    private String fitness_id;
    private String salle_id;
    private String abo_id;
    private String membre_id;

    public MemberCourseWeekView(Object[] obj) {
        this.jour = (String) obj[0];
        this.heure = (String) obj[1];
        this.description = (String) obj[2];
        this.instructeur_id = (String) obj[3];
        this.typecours = (String) obj[4];
        this.fitness_id = (String) obj[5];
        this.salle_id = (String) obj[6];
        this.abo_id = (String) obj[7];
        this.membre_id = (String) obj[8];
    }
}
