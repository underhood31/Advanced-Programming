package ArchLegends;

import java.util.Random;

class Goblin extends Monster{
   
    Goblin(){
        levelToAffect=1;
        MAX_HP=100;
    }
}

class Zombies extends Monster{
    Zombies(){
        levelToAffect=2;
        MAX_HP=150;
    }

}

class Fiends extends Monster{
    Fiends(){
        levelToAffect=3;
        MAX_HP=200;
    }

}

class Lionfang extends Monster{
    Lionfang(){
        levelToAffect=4;
        MAX_HP=250;
    }
    
    @Override
    public int attack(float opponentHP) {
        Random IntegeRandom = new Random();
        int rnd = IntegeRandom.nextInt(10);
        if(rnd==0){
            return (int)(opponentHP/2);
        }
        else{
            return super.attack();
        }
    }
}