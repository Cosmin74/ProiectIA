import argumente.*;
import predicate.CarieraPred;
import predicate.InteresPred;
import predicate.PredPlace;
import reguli.Regula1;
import reguli.Regula2;
import reguli.Regula3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String caleFisier = "C:\\Users\\User\\Desktop\\ProiectIA\\src\\input\\seturi-date.txt";
        String numeElev = "Ion";
        String materieInput = "Limba franceză";
        String domeniuInput = "Diplomatie";

        Elev elev = new Elev(numeElev);
        Materie materie = new Materie(materieInput);
        Domeniu domeniu = new Domeniu(domeniuInput);

        Regula1 regula1 = new Regula1();
        PredPlace rezultatRegula1 = regula1.verificaRegula1(elev, materie);

        if(rezultatRegula1!=null){
            Regula2 regula2 = new Regula2();
            InteresPred rezultatRegula2 = regula2.verificaRegula2(elev, materie, domeniu);

            if(rezultatRegula2!=null) {
                Regula3 regula3 = new Regula3();
                Boolean rezultatRegula3 = regula3.verificaRegula3(elev, domeniu);

                if(rezultatRegula3){
                    String regex = materieInput + " - " + domeniuInput + " - (.+)";

                    byte[] continutBytes = new byte[0];
                    try {
                        continutBytes = Files.readAllBytes(Paths.get(caleFisier));
                        String continutString = new String(continutBytes);

                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(continutString);

                        if (matcher.find()) {
                            String[] cuvinte = matcher.group().split("\\s*-\\s*");
                            String carieraInput = cuvinte[2];

                            Cariera cariera = new Cariera(carieraInput);
                            CarieraPred carieraPred = new CarieraPred();

                            System.out.println("Elevului "+ numeElev+ " i se potriveste o cariera de "+ cariera.getCariera().toLowerCase()+".");
                        } else {
                            System.out.println("Nu s-a gasit nicio linie care să corespundă cu regex-ul.");
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            else{
                System.out.println("Domeniul ales nu sunt potrivite,alege din nou");
            }
        }
        else{
            System.out.println("Materia aleasa nu se potriveste,aleg din nou");
        }
    }
}