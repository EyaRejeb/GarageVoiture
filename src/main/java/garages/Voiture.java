package garages;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Voiture {
    private String immatriculation;
    private Set<Stationnement> myStationnements;

    public Voiture(String immatriculation) {
        this.immatriculation = immatriculation;
        this.myStationnements = new HashSet<>();
    }

    public boolean estDansUnGarage() {
        return myStationnements.stream().anyMatch(Stationnement::estEnCours);
    }

    public Set<Garage> garagesVisites() {
        Set<Garage> garages = new HashSet<>();
        for (Stationnement s : myStationnements) {
            garages.add(s.getGarage());
        }
        return garages;
    }

    public void entreAuGarage(Garage garage) throws IllegalStateException {
        if (estDansUnGarage()) {
            throw new IllegalStateException("La voiture est déjà dans un garage.");
        }
        myStationnements.add(new Stationnement(this, garage));
    }

    public void sortDuGarage() throws IllegalStateException {
        Stationnement stationnementEnCours = myStationnements.stream()
                .filter(Stationnement::estEnCours)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("La voiture n'est pas dans un garage."));
        stationnementEnCours.terminer();
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