package ArchLegends;

import java.util.Random;

class Goblin extends Monster{
   
    Goblin(){
        super(1,100);
    }
    @Override
    public String toString() {
        return "Goblin, Level 1";
    }
}

class Zombie extends Monster{
    Zombie(){
        super(2,150);
    }
    @Override
    public String toString() {
        return "Zombie, Level 2";
    }

}

class Fiend extends Monster{
    Fiend(){
        super(3,200);

    }
    @Override
    public String toString() {
        return "Fiend, Level 3";
    }

}

class Lionfang extends Monster{
    Lionfang(){
        super(4,250);

    }
    
    @Override
    public float attack(float opponentHP) {
        Random IntegeRandom = new Random();
        int rnd = IntegeRandom.nextInt(10);
        if(rnd==0){
            return (int)(opponentHP/2);
        }
        else{
            return super.attack(opponentHP);
        }
    }

    @Override
    public String toString() {
        return "Leofang, The BOSS!!";
    }
}