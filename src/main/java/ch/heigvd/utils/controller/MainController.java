package ch.heigvd.utils.controller;

import ch.heigvd.utils.view.HoraireCoursView;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MainController {
    static final EntityManager em = AppContextListener.createEntityManager();

    public static HoraireCoursView getHoraireCoursView() {
        List result = em
                .createNativeQuery("SELECT hc FROM my_amazing_fitness.horairecoursview hc ORDER BY hc.heure")
                .getResultList();
        return new HoraireCoursView(result);
    }
}
