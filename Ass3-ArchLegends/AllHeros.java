package ArchLegends;

class Warrior extends Hero{
    private final String splPower = "Attack and defense attributes get boosted by 5 for the next 3 moves";
    
    Warrior(){
        attackVal=10;
        defenceVal=3;
        baseAttack = attackVal + level - 1;
        baseDefence = defenceVal + level - 1;
    }
    @Override
    public int specialAttack() {
        if(specialMoves==0){
            this.specialMoves=3;
            return 0;
        }    
        else{
            return -1;
        }

    }
    
    @Override
    public float attack() {
        if (specialMoves>0) {
            return super.attack() + 5;    
            --specialMoves;    
        }
        return super.attack();
    }
    @Override
    public void takeAttack(float attackValue) {
        if (specialMoves>0) {
            super.takeAttack(attackValue-5);
            --specialMoves;
        }
        super.takeAttack(attackValue);
    }
    

}

class Mage extends Hero{
    
    private final String splPower = "Cast a spell which reduces the opponent's HP by 5% for the next 3 moves";
    
    Mage(){
        attackVal=5;
        defenceVal=5;
        baseAttack = attackVal + level - 1;
        baseDefence = defenceVal + level - 1;
    }
    @Override
    public int specialAttack() {
        if(specialMoves==0){
            this.specialMoves=3;
            return 0;
        }    
        else{
            return -1;
        }

    }

    @Override
    public float attack(Monster opponent) {
        float change =0;
        if (specialMoves>0) {
            change = 0.05*opponent.getHP();
            --specialMoves;    
        }
        return super.attack() + change;
    }
    @Override
    public void takeAttack(float attackValue, Monster opponent) {
        if (specialMoves>0) {
            opponent.mutateHP(-0.05*opponent.getHP());
            --specialMoves;
        }
        super.takeAttack(attackValue);
    }
}

class Theif extends Hero{
    private final String splPower = "Steal 30% of opponents HP";
    Theif(){
        attackVal=6;
        defenceVal=4;
        baseAttack = attackVal + level - 1;
        baseDefence = defenceVal + level - 1;
    }
    @Override
    public int specialAttack(Monster opponent) {
        if(specialMoves==0){
            this.specialMoves=3;
            this.mutateHP(0.3*opponent.getHP());
            opponent.mutateHP(-0.3*opponent.getHP());
            return 0;
        }    
        else{
            return -1;
        }

    }
    @Override
    public float attack() {
        if (specialMoves>0) {
            --specialMoves;    
        }
        return super.attack();
    }
    @Override
    public void takeAttack(float attackValue) {
        if (specialMoves>0) {
            --specialMoves;
        }
        super.takeAttack(attackValue);
    }
}

class Healer extends Hero{
    private final String splPower = "Increase own HP by 5% for the next 3 moves";
    Healer(){
        attackVal=4;
        defenceVal=8;
        baseAttack = attackVal + level - 1;
        baseDefence = defenceVal + level - 1;
    }
    @Override
    public int specialAttack() {
        if(specialMoves==0){
            this.specialMoves=3;
            return 0;
        }    
        else{
            return -1;
        }

    }

    @Override
    public float attack() {
        if (specialMoves>0) {
            this.mutateHP(0.05*this.getHP());
            --specialMoves;    
        }
        return super.attack();
    }
    @Override
    public void takeAttack(float attackValue) {
        if (specialMoves>0) {
            this.mutateHP(0.05*this.getHP());
            --specialMoves;
        }
        super.takeAttack(attackValue);
    }
}