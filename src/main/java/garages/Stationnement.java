package garages;

import java.util.Date;

public class Stationnement {
    private Voiture voiture;
    private Garage garage;
    private Date entree;
    private Date sortie;

    public Stationnement(Voiture v, Garage g) {
        this.voiture = v;
        this.garage = g;
        this.entree = new Date();
        this.sortie = null;
    }

    public void terminer() {
        this.sortie = new Date();
    }

    public boolean estEnCours() {
        return sortie == null;
    }

    public Garage getGarage() {
        return this.garage;
    }

    @Override
    public String toString() {
        if (estEnCours()) {
            return "Stationnement{" + "entree=" + entree + ", en cours}";
        } else {
            return "Stationnement{" + "entree=" + entree + ", sortie=" + sortie + '}';
        }
    }
}
