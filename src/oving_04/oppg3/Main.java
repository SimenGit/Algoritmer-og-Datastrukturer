package oving_04.oppg3;

import java.util.Date;
import java.util.Random;

public class Main {

    private static int[] GenerateRandom(int size) {
        Random random = new Random();
        int[] liste = new int[size];
        for(int i = 0; i < liste.length; i ++) {
            int midl = random.nextInt(500);
            liste[i] = midl;
        }
        return liste;
    }

    public static void main(String[] args) {

        Heapsort hs = new Heapsort();
        Quicksort qs = new Quicksort();
        Date endTime;

        int[] stor = GenerateRandom(100000000);

        qs.quickSort(stor, 0, stor.length - 1);
        Date startTime = new Date();
        hs.sort(stor);
        endTime = new Date();

        System.out.println("Tid valgt sortering: " + ((endTime.getTime() - startTime.getTime()) + "ms"));
    }
}
