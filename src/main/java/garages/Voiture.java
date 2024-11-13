package garages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Voiture {
	@Getter
	@NonNull
	private final String immatriculation;
	@ToString.Exclude // On ne veut pas afficher les stationnements dans toString
	private final List<Stationnement> myStationnements = new LinkedList<>();

    public Voiture(String immatriculation) {
        if (immatriculation == null || immatriculation.isEmpty()) {
            throw new IllegalArgumentException("L'immatriculation ne peut pas être vide.");
        }
        this.immatriculation = immatriculation;
        this.myStationnements = new ArrayList<>();
    }

    public void entreAuGarage(Garage g) throws IllegalStateException {
        if (estDansUnGarage()) {
            throw new IllegalStateException("La voiture est déjà dans un garage.");
        }
        myStationnements.add(new Stationnement(this, g));
    }

    public void sortDuGarage() throws IllegalStateException {
        if (!estDansUnGarage()) {
            throw new IllegalStateException("La voiture n'est pas actuellement dans un garage.");
        }
        myStationnements.get(myStationnements.size() - 1).terminer();
    }

    public Set<Garage> garagesVisites() {
        Set<Garage> garages = new HashSet<>();
        for (Stationnement s : myStationnements) {
            garages.add(s.getGarage());
        }
        return garages;
    }

    public boolean estDansUnGarage() {
        return !myStationnements.isEmpty() && myStationnements.get(myStationnements.size() - 1).estEnCours();
    }

    public void imprimeStationnements(PrintStream out) {
        Set<Garage> garages = garagesVisites();
        for (Garage garage : garages) {
            out.println("Garage " + garage.getName() + ":");
            for (Stationnement s : myStationnements) {
                if (s.getGarage().equals(garage)) {
                    out.println("\t" + s);
                }
            }
        }
    }
}
