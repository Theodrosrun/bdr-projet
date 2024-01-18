package ch.heigvd.utils.view;

import lombok.Getter;

@Getter
public class MembreFactureView {
    private String abonnement;
    private String facture;
    private String montant;
    private String echeance;
    private String payment;

    public MembreFactureView(Object[] obj) {
        this.abonnement = (String) obj[1];
        this.facture = (String) obj[2];
        this.montant = (String) obj[3];
        this.echeance = (String) obj[4];
        this.payment = (obj[5] != null) ? (String) obj[5] : "Impayé";
    }

}
