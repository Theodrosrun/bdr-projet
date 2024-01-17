package ch.heigvd.utils.view;

import lombok.Getter;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class CourseWeekView {
    private String jour;
    private Time heure;
    private String description;
    private int instructeur_id;
    private String typecours;
    private int fitness_id;
    private String salle_id;
    private String abo_id;


    public CourseWeekView(Object[] obj) {
        this.jour = (String) obj[0];
        this.heure = Time.valueOf((String) obj[1]);
        this.description = (String) obj[2];
        this.instructeur_id = Integer.parseInt((String) obj[3]);
        this.typecours = (String) obj[4];
        this.fitness_id = Integer.parseInt((String) obj[5]);
        this.salle_id = (String) obj[6];
        this.abo_id = (String) obj[7];
    }
}
