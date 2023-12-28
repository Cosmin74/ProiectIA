package predicate;

import argumente.Elev;

import java.util.function.Predicate;

public class PredElev {
    public Predicate<Elev> getPredicat() {
        return elev -> elev.getNume().equals(elev.getNume());
    }
}