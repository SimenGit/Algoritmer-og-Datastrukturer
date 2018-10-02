package oving_05;

import java.util.ArrayList;

public class Hash {

    private final int TABELL_STORRELSE;
    private String[] hashTabell;
    private int antallBrukt = 0;
    private int antallKollisjoner = 0;

    public Hash(int storrelse) {
        TABELL_STORRELSE = storrelse;
        hashTabell = new String[storrelse];
    }

    public int getAntallBrukt() {
        return antallBrukt;
    }

    public int getAntallKollisjoner() {
        return antallKollisjoner;
    }

    private int genererIndeksFraStreng(String streng) {
        int verdi = -1;
        for(int i = 0; i < streng.length(); i++) {
            verdi += (streng.charAt(i) * (13 * (i + 1)));
        }
        return verdi;
    }

    public String settInnListe(String[] navneliste) {

        int index;
        int kollisjonerDenneInnsetting = 0;
        String kollisjoner = "";

        for(int i = 0; i < navneliste.length; i++) {
            boolean sattInn = false;
            index = genererIndeksFraStreng(navneliste[i]);
            if(hashTabell[index] == null) {
                hashTabell[index] = navneliste[i];
                antallBrukt++;
            }else{
                antallKollisjoner++;
                kollisjonerDenneInnsetting++;
                kollisjoner += navneliste[i] + "\n";
                int secondHashAmount = 1;
                int secondHash = secondHashFunction(index);

                while(sattInn == false) {

                    int hashverdien = index + (secondHash * secondHashAmount);
                    //forsikrer om at den ikke går over tabell-lengden.
                    if(hashverdien > hashTabell.length) {
                        hashverdien -= hashTabell.length;
                    }

                    if(hashTabell[hashverdien] == null) {
                        hashTabell[hashverdien] = navneliste[i];
                        antallBrukt++;
                        sattInn = true;
                    }else{
                        secondHashAmount++;
                    }
                }
            }
        }
        if(kollisjoner.equals("")) {
            return "Innsettingen skjedde uten kollisjoner.";
        }else {
            return "Det skjedde " + String.valueOf(kollisjonerDenneInnsetting) + " kollisjoner. \nKollisjoner skjedde på navn: \n\n" + kollisjoner;
        }
    }

    public String settInnEnkel(String navn) {

        int index = genererIndeksFraStreng(navn);
        if(hashTabell[index] == null) {
            hashTabell[index] = navn;
            antallBrukt++;
            return "satt inn";
        }else{
            antallKollisjoner++;
            int secondHashAmount = 1;
            int secondHash = secondHashFunction(index);
            boolean sattInn = false;

            while(sattInn == false) {

                int hashverdien = index + (secondHash * secondHashAmount);
                //forsikrer om at den ikke går over tabell-lengden.
                if(hashverdien > hashTabell.length) {
                    hashverdien -= hashTabell.length;
                }

                if(hashTabell[hashverdien] == null) {
                    hashTabell[hashverdien] = navn;
                    sattInn = true;
                    antallBrukt++;
                }else{
                    secondHashAmount++;
                }
            }
            return "satt inn med: " + String.valueOf(secondHashAmount) + " kollisjoner.";
        }
    }


    // hash2(key) = PRIME – (key % PRIME) where PRIME is a prime smaller than the TABLE_SIZE.
    private int secondHashFunction(int key) {
        int hopp;
        hopp = 43 - (key % 43);
        return hopp;
    }


    private ArrayList<Integer> getIndekser() {
        ArrayList<Integer> liste = new ArrayList<Integer>();
        for(int i = 0; i < hashTabell.length; i++) {
            if(hashTabell[i] == null) {
                continue;
            }else {
                liste.add(i);
            }
        }
        return liste;
    }

    public String getIndekserString() {

        ArrayList<Integer> indekserList = getIndekser();
        String[] indekser = new String[indekserList.size()];
        for(int i = 0; i < indekser.length; i++) {
            indekser[i] = String.valueOf(indekserList.get(i));
        }
        String print = "Indekser: ";
        for(int i = 0; i < indekser.length; i++) {
            print += indekser[i];
            print += ", ";
        }
        return print;
    }

    public int getFirstKeyByString(String navn) {
        int index = genererIndeksFraStreng(navn);
        return index;
    }
    public int getSecondKeyByFirstKey(int key) {
        int jump = secondHashFunction(key);
        return jump;
    }

    public boolean sokOppslag(String navn) {
        int index = genererIndeksFraStreng(navn);

        boolean funnet = false;
        if(hashTabell[index] == navn) {
            return true;
        }else{
            int faktor = 1;
            int hopp = getSecondKeyByFirstKey(index);

            while(!funnet) {
                if(faktor > 1000) {
                    return false;
                }

                int hashverdien = index + (hopp * faktor);
                if(hashverdien > hashTabell.length) {
                    hashverdien -= hashTabell.length;
                }

                if(hashTabell[index + (faktor * hopp)] == navn) {
                    funnet = true;
                }else{
                    faktor++;
                }
            }
        }
        return true;
    }








}
