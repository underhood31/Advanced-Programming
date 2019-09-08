package RacerMan;

import java.util.Random;

class Dice{
    public static int roll(int maxLim){
        Random rnd = new Random();
        return rnd.nextInt(maxLim)+1;
    } 
}