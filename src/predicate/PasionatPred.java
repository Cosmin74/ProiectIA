package predicate;

import argumente.Domeniu;
import argumente.Elev;

import java.util.function.BiPredicate;

public class PasionatPred {
    public BiPredicate<Elev, Domeniu> getPredicat(Elev elevNume, Domeniu domeniuInteres) {
        return (elev, domeniu) -> elev.getNume().equals(elevNume.getNume())
                && domeniu.getDomeniu().equals(domeniuInteres.getDomeniu());
    }
}
