package reguli;

import argumente.Domeniu;
import argumente.Elev;
import argumente.Materie;
import predicate.InteresPred;
import predicate.PasionatPred;
import predicate.PredPlace;

public class Regula2 {
    PredPlace predPlace = new PredPlace();
    PasionatPred pasionatPred = new PasionatPred();
    InteresPred interesPred = new InteresPred();

    public InteresPred verificaRegula2(Elev elev, Materie materiePreferata, Domeniu domeniu) {

        var predPlaceDaca = predPlace.getPredicat(elev, materiePreferata);
        var pasionatPredDaca = pasionatPred.getPredicat(elev, domeniu);

        if (predPlaceDaca.test(elev, materiePreferata) && pasionatPredDaca.test(elev, domeniu)) {
            return interesPred;
        } else {
            return null;
        }

    }
}