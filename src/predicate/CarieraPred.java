package predicate;

import argumente.Cariera;
import argumente.Domeniu;
import argumente.Elev;

import java.util.function.BiPredicate;

public class CarieraPred {
    public BiPredicate<Elev, Cariera> getPredicat(Elev elevNume, Cariera carieraPotrivita) {
        return (elev, cariera) -> elev.getNume().equals(elevNume.getNume())
                && cariera.getCariera().equals(carieraPotrivita.getCariera());
    }
}
