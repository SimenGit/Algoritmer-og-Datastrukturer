package oving_01;

import java.util.Date;
import java.util.Random;

public class StockCalc {
    private int[] sChange;
    public int lowPlace;
    public int highPlace;
    private int diff;

    public StockCalc(int[] sChange){
        this.sChange = sChange;
    }

    private void setDiff(int[] stockChange) {
        for (int i = 0; i < stockChange.length; i++) {
            int value = 0;
            for (int j = i + 1; j < stockChange.length; j++) {

                value += stockChange[j - 1];
                int difference = stockChange[j] + value;
                if (difference > diff) {
                    diff = difference;
                    lowPlace = i;
                    highPlace = j;
                }
                //hvis det er en veldig stor forandring på slutten av tabellen.
                if (stockChange[j] > diff) {
                    diff = stockChange[j];
                    lowPlace = j;
                    highPlace = j;
                }
            }
        }

    }
    private int[] generateRandomValueArray(int minimumValue, int maximumValue, int size) {
        Random random = new Random();
        int[] valueArray = new int[size];
        for(int i = 0; i < size; i++) {
            valueArray[i] = random.nextInt(maximumValue - minimumValue) + minimumValue;
        }

        return valueArray;
    }

    public double getTime(int aLength, int runtimes){

        int[] rArray = generateRandomValueArray(-20, 20, aLength);
        Date start = new Date();
        double time;
        Date slutt;
        for (int i = 0; i < runtimes; i++){
            setDiff(rArray);
        }
        slutt = new Date();
        time = (double)(slutt.getTime()-start.getTime()) / (double)runtimes;
        return time;
    }


    public int getDiff(int [] stockChange){
        setDiff(stockChange);
        return diff;
    }

    public String toString(int[] stockChange){
        int res = getDiff(stockChange);
        String a = "Aksjene ble kjøpt på starten av dag: " + (lowPlace+1) + " og solgt på slutten av dag: " + (highPlace+1) + ". Differansen var: " + res;
        return a;
    }
}
