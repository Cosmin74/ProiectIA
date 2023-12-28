import argumente.*;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Elev elev1 = new Elev("Elev1");
        MateriePreferata matematica = new MateriePreferata("Matematica");
        Domeniu domeniuPasionat = new Domeniu("Algebra");
        Cariera carieraPotrivita = new Cariera("Matematician");

        Predicate<Place> predElev = p -> p.getElev().equals(elev1);
        Predicate<Place> predMaterie = p -> p.getMaterie().equals(matematica);
        Place loc1 = null;
        Predicate<Place> regulaCompusa1 = predElev.and(predMaterie);

        if (regulaCompusa1.test(new Place(elev1, matematica))) {
            loc1 = new Place(elev1, matematica);

            System.out.println(elev1.getNume() + " ii place materia " + matematica.getMaterie());
            Place finalLoc = loc1;

            BiPredicate<Elev, MateriePreferata> predPlace = (elev, materie) ->
                    elev.getNume().equals(finalLoc.getElev().getNume()) && materie.getMaterie().equals(finalLoc.getMaterie().getMaterie());

            BiPredicate<Elev, Domeniu> predPasionat = (elev, domeniu) ->
                    elev.getNume().equals(finalLoc.getElev().getNume()) && domeniu.getDomeniu().equals(domeniuPasionat.getDomeniu());

            if (predPlace.test(loc1.getElev(), loc1.getMaterie()) && predPasionat.test(loc1.getElev(), domeniuPasionat)) {
                System.out.println("Regula pentru Place, Pasionat și Interes este satisfăcută.");
                BiPredicate<Elev, Domeniu> predInteres = (elev, domeniu) ->
                        elev.getNume().equals(elev1.getNume()) && domeniu.getDomeniu().equals(domeniuPasionat.getDomeniu());
            }
            else {
                System.out.println("Regula pentru Place, Pasionat și Interes nu este satisfăcută.");
            }
        }


    }
}