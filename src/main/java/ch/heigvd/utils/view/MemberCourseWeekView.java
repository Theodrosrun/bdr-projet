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
    private String cours;
    private String fitness;
    private String salle;
    private String abonnement;

    public MemberCourseWeekView(Object[] obj) {
        this.jour = (String) obj[0];
        this.heure = (String) obj[1];
        this.description = (String) obj[2];
        this.cours = (String) obj[4];
        this.fitness = (String) obj[5];
        this.salle = (String) obj[6];
        this.abonnement = (String) obj[7];
    }
}
