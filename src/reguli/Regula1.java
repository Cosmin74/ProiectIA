package reguli;

import argumente.Elev;
import argumente.Materie;
import predicate.PredElev;
import predicate.PredMateriePreferata;
import predicate.PredPlace;

import java.util.function.Predicate;

public class Regula1 {
    public PredPlace verificaRegula1(Elev elev, Materie materiePreferata) {
        PredElev predElev = new PredElev();
        PredMateriePreferata predMaterie = new PredMateriePreferata();
        PredPlace predPlace = new PredPlace();

        var predElevDaca = predElev.getPredicat();

        var predMateriePreferataDaca = predMaterie.getPredicat(materiePreferata);

        if (predElevDaca.test(elev) && predMateriePreferataDaca.test(materiePreferata)) {
            return predPlace;
        } else {
            return null;
        }
    }
}
