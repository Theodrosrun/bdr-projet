package ch.heigvd.utils.view;

import lombok.Getter;

@Getter
public class MembreFactureView {
    private String membre_id;
    private String abonnement;
    private String facture;
    private String montant;
    private String echeance;
    private String payment_id;

    public MembreFactureView(Object[] obj) {
        this.membre_id = (String) obj[0];
        this.abonnement = (String) obj[1];
        this.facture = (String) obj[2];
        this.montant = (String) obj[3];
        this.echeance = (String) obj[4];
        if (obj[5] != null)
            this.payment_id = (String) obj[5];
        else
            this.payment_id = "";
    }

}
