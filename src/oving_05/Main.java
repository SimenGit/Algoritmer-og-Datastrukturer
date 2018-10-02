package oving_05;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Hash hash = new Hash(15485867);


        //LESER INN FRA FIL
        String alleNavn = lesFil("src/oving_05/navn.txt");
        String[] liste = alleNavn.split("\n");


        System.out.println("Prøver å sette inn liste fra fil:");
        String settInn = hash.settInnListe(liste);
        System.out.println(settInn);
        System.out.println("\n" + "Printer alle indekser i hashtabellen: ");
        System.out.println(hash.getIndekserString());

        System.out.println("\n\nPrøver å legge til filen på nytt: ");
        String settInnIgjen = hash.settInnListe(liste);
        System.out.println(settInnIgjen);
        System.out.println("\n");

        System.out.println("\n\nPrinter ut alle indekser på nytt etter alle navnene er lagt inn 2 ganger: ");
        System.out.println(hash.getIndekserString());


        //SETTER INN EKSISTERENDE NAVN I TABELLEN:
        System.out.println("\nPrøver nå å sette inn ett eksisterende navn i tabellen: ");
        String settInnEksisterende = hash.settInnEnkel("Haugum,Terje");
        System.out.println(settInnEksisterende);
        int key = hash.getFirstKeyByString("Haugum,Terje");
        System.out.println("\n");
        System.out.println("Key 1 på dette navnet var: " + key);
        int key2 = hash.getSecondKeyByFirstKey(key);
        System.out.println("Key 2 på dette navnet ved bruk av 2. hash funksjon: " + key2);
        System.out.println("ble satt inn på plass: " + key + key2);

        System.out.println("\nPrøver å sette inn samme person for 3. gang: ");
        String settInnEksisterende2 = hash.settInnEnkel("Haugum,Terje");
        System.out.println(settInnEksisterende2);

        System.out.println("Søker oppslag: ");
        boolean ey = hash.sokOppslag("Haugum,Terje");
        System.out.println(ey);



        //STATESTIKK TIL NÅ:
        int antallBrukt = hash.getAntallBrukt();
        int antallKoll = hash.getAntallKollisjoner();

        System.out.println("\n\nTotalt antall kolisjoner: " + antallKoll);
        System.out.println("Totalt antall plasser brukt: " + antallBrukt);

        double mod = antallKoll % antallBrukt;
        System.out.println("Gjennomsnitlig kollisjoner per pers: " + (antallKoll / antallBrukt) + "." + (int)mod);


        //TIDTAKING:

        //Henter fylt String tabell på 5 000 000 plasser:
        Generer gen = new Generer();
        String[] tab = gen.getTabell();

        Date startTime = new Date();
        //hash.settInnListe(tab);
        Date endTime = new Date();

        System.out.println("Tid sette inn hash: " + ((endTime.getTime() - startTime.getTime()) + "ms"));








    }


    private static String lesFil(String filnavn) {
        String res = "";
        StringBuilder sb = new StringBuilder();
        try {
            FileReader reader = new FileReader(filnavn);
            BufferedReader buffReader = new BufferedReader(reader);
            String linje = buffReader.readLine();

            while(linje != null) {
                sb.append(linje);
                sb.append("\n");
                linje = buffReader.readLine();
            }
            return sb.toString();
        }catch (IOException ioe) {
            System.out.println("IOException");
        }
        return res;
    }


}
