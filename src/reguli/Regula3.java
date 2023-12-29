package reguli;

import argumente.Cariera;
import argumente.Domeniu;
import argumente.Elev;
import argumente.Materie;
import predicate.CarieraPred;
import predicate.InteresPred;

public class Regula3 {

    InteresPred interesPred = new InteresPred();
    CarieraPred carieraPred = new CarieraPred();

    public Boolean verificaRegula3(Elev elev, Domeniu domeniu) {

        var interesPredDaca = interesPred.getPredicat(elev, domeniu);

        if (interesPredDaca.test(elev, domeniu)) {
            return true;
        } else {
            return false;
        }

    }
}
