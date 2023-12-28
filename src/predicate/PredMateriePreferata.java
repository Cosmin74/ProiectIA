package predicate;

import argumente.Elev;
import argumente.Materie;

import java.util.function.Predicate;

public class PredMateriePreferata {
    public Predicate<Materie> getPredicat(Materie materiePreferata) {
        return materie -> materie.getMaterie().equals(materiePreferata.getMaterie());
    }
}