package ArchLegends;

class Minion extends Sidekick{

    private final float costXP=5;
    Minion(float bornFromXP){
        super(1, (bornFromXP-5)/2,1);
    }
    
    
    
}

class Knight extends Sidekick{
    private final float costXP=8;
    Knight(float bornFromXP){
        super(2, (bornFromXP-8)/2,2);
    }
}