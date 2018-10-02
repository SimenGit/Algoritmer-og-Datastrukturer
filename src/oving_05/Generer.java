package oving_05;

import java.util.Random;

public class Generer {

    public String[] getTabell() {
        String[] tab = new String[5000000];
        Random random = new Random();
        for(int i = 0; i < tab.length; i++) {
            int rand = random.nextInt(15485867);
            String randomString = String.valueOf(rand);
            tab[i] = randomString;
        }
        return tab;
    }

}
