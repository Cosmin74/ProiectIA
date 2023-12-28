package predicate;

import argumente.Materie;

import java.util.function.Predicate;

public class PredMateriePreferata {
    public Predicate<Materie> getPredicat(Materie materiePreferata) {
        String materieDorita = materiePreferata.getMaterie();

        return materie -> materie.getMaterie().equals(materieDorita);
    }
}