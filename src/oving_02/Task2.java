package oving_02;

import java.util.Date;

public class Task2 {

    public static void main(String[] args) {

        double base = 1.001;
        double exp = 100;

        double lokke = 1000000;
        Date startTime = new Date();
        Date endTime;

        for(int i = 0; i < lokke; i++) {
            calculateExponential(base, exp);
        }
        endTime = new Date();
        System.out.println("Tid oppg 1: " + (endTime.getTime() - startTime.getTime()) / lokke + "ms");

        startTime = new Date();

        for(int i = 0; i < lokke; i++) {
            calculateExponential2(base, exp);
        }
        endTime = new Date();
        System.out.println("Tid oppg 2: " + (endTime.getTime() - startTime.getTime()) / lokke + "ms");
        System.out.println("math.pow: " + Math.pow(base, exp));

        System.out.println("\n1: " + calculateExponential(base, exp));
        System.out.println("2: " + calculateExponential2(base, exp));

    }


    //første eksponentialutregning
    private static double calculateExponential(double base, double exponent) {
        if (exponent > 0) {
            return base * calculateExponential(base, exponent - 1);
        } else {
            return 1;
        }
    }

    //andre eksponentialutregning
    private static double calculateExponential2(double base, double exponent) {
        if (exponent > 0) {
            //sjekker om eksponenten er partall eller oddetall.
            if (exponent%2 == 0) {
                //Partall
                double newExponent = exponent / 2;
                return calculateExponential2((base * base), newExponent);
            } else {
                //Oddetall
                double nyExponent = (exponent - 1) / 2;
                return base * calculateExponential2((base * base), nyExponent);
            }
        }
        return 1;
    }
}
