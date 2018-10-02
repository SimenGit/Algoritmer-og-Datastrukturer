package oving_01;

public class Main{

    static int[] sChange = {-3, -5, 1, 4, 5, -5, -7, 10, 10, -9, 10, -10, 10, 10};

    public static void main(String[] args){

        StockCalc stock = new StockCalc(sChange);
        System.out.println(stock.toString(sChange));

        System.out.println("Tid - 1000 lengde: " + stock.getTime(1000, 1000) + " ms");

        System.out.println("Tid - 10 x 1000 lengde:" + stock.getTime(10000, 1000) + "ms.");

        //kompleksitet: n^2, øvre og nedre grense C * n^2 funker som nedre grense og som øvre grense: K * n^2.




    }
}
