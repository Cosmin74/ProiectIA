package predicate;

import argumente.Elev;
import argumente.Materie;
import java.util.function.BiPredicate;

public class PredPlace {
    public BiPredicate<Elev, Materie> getPredicat(Elev elevNume, Materie materiePreferata) {
        return (elev, materie) -> elev.getNume().equals(elevNume.getNume())
                && materie.getMaterie().equals(materiePreferata.getMaterie());
    }
}