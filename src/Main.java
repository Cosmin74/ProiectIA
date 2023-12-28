import argumente.*;
import predicate.InteresPred;
import predicate.PredPlace;
import reguli.Regula1;
import reguli.Regula2;

public class Main {
    public static void main(String[] args) {
        Elev elev = new Elev("Ion");
        Materie materie = new Materie("Matematica");
        Domeniu domeniu = new Domeniu("Cercetare");

        Regula1 regula1 = new Regula1();
        PredPlace rezultatRegula1 = regula1.verificaRegula1(elev, materie);
        System.out.println(rezultatRegula1);

        Regula2 regula2 = new Regula2();
        InteresPred rezultatRegula2 = regula2.verificaRegula2(elev, materie, domeniu);
        System.out.println(rezultatRegula2);
    }
}