package ch.heigvd.utils.view;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class MembreAbonnementView {
    private int membre_id;
    private int contrat_id;
    private String date_debut;
    private String date_fin;
    private String abo_id;
    private String prix;
    private String typeAbonnement;

    public MembreAbonnementView(Object[] obj) {
        this.membre_id = Integer.parseInt((String) obj[0]);
        this.contrat_id = Integer.parseInt((String) obj[1]);
        this.date_debut = (String) obj[2];
        this.date_fin = (String) obj[3];
        this.abo_id = (String) obj[4];
        this.prix = (String) obj[5];
        this.typeAbonnement = (String) obj[6];
    }


}

