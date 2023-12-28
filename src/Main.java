import argumente.*;
import predicate.PredPlace;
import reguli.Regula1;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Elev elev1 = new Elev("Elev1");
        Materie matematica = new Materie("Matematica");

        Regula1 regula1 = new Regula1();
        PredPlace rezultatRegula1 = regula1.verificaRegula1(elev1, matematica);

        System.out.println(rezultatRegula1);

    }
}