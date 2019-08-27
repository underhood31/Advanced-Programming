package ArchLegends;

class Warrior extends Hero{
    private final String splPower = "Attack and defence attributes get boosted by 5 for the next 3 moves";
    
    Warrior(String uname){
        super(uname,10,3);
       
        baseAttack = attackVal + level - 1;
        baseDefence = defenceVal + level - 1;
    }
    @Override
    public int specialAttack(Monster monster) {
        System.out.println(splPower);
        if(specialMoves==0){
            this.specialMoves=4;
            return 0;
        }    
        else{
            return -1;
        }

    }
    
    @Override
    public float attack(Monster opponent) {
        if (specialMoves>0) {
            return super.attack(opponent) + 5;    
        }
        
        return super.attack(opponent);
    }
    @Override
    public void defend(float attackValue, Monster opponent) {
        if (specialMoves>0) {
            float damage =attackValue-5;
            if(damage<0){
                damage=0;
            }
            System.out.println("Attack value is reduced from "+attackValue +" to "+damage+" because of special power");
            
            super.defend(damage,opponent);
        }
        else{
            super.defend(attackValue,opponent);
        }
        
    }
    @Override
    public void takeAttack(float attackValue, Monster opponent) {
        if(specialMoves>0){
            --specialMoves;
        }
        super.takeAttack(attackValue,opponent);
    }

}

class Mage extends Hero{
    
    private final String splPower = "Cast a spell which reduces the opponent's HP by 5% for the next 3 moves";
    
    Mage(String uname){
        super(uname,5,5);

        baseAttack = attackVal + level - 1;
        baseDefence = defenceVal + level - 1;
    }
    @Override
    public int specialAttack(Monster monster) {
        System.out.println(splPower);
        if(specialMoves==0){
            this.specialMoves=4;
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
            change = (float)(0.05*opponent.getHP());
            System.out.println("Special power, Extra damage added: "+ change);
        }
        return super.attack(opponent) + change;
    }
    @Override
    public void takeAttack(float attackValue, Monster opponent) {
        if (specialMoves>0) {
            
            --specialMoves;
        }
        super.takeAttack(attackValue,opponent);
    }
    @Override
    public void defend(float attackValue, Monster opponent) {
        if (specialMoves>0) {
            float change = (float)(0.05*opponent.getHP());
            opponent.mutateHP(-1*change);
            System.out.println("Special power, Extra damage added: "+ change);
        }
        super.defend(attackValue, opponent);
    }
}

class Thief extends Hero{
    private final String splPower = "Steal 30% of opponents HP";
    Thief(String uname){
        super(uname,6,4);
     
        baseAttack = attackVal + level - 1;
        baseDefence = defenceVal + level - 1;
    }
    @Override
    public int specialAttack(Monster opponent) {
        System.out.println(splPower);
        if(specialMoves==0){
            this.specialMoves=4;
            this.mutateHP((float)(0.3*opponent.getHP()));
            opponent.mutateHP((float)(-0.3*opponent.getHP()));
            return 0;
        }    
        else{
            return -1;
        }

    }
    
    @Override
    public void takeAttack(float attackValue, Monster opponent) {
        if (specialMoves>0) {
            --specialMoves;
        }
        super.takeAttack(attackValue,opponent);
    }
    
}

class Healer extends Hero{
    private final String splPower = "Increase own HP by 5% for the next 3 moves";
    Healer(String uname){
        super(uname,4,8);
        baseAttack = attackVal + level - 1;
        baseDefence = defenceVal + level - 1;
    }
    @Override
    public int specialAttack(Monster monster) {
        System.out.println(splPower);
        if(specialMoves==0){
            this.specialMoves=4;
            return 0;
        }    
        else{
            return -1;
        }

    }

    @Override
    public float attack(Monster opponent) {
        if (specialMoves>0) {
            this.mutateHP((float)(0.05*this.getHP()));
        }
        return super.attack(opponent);
    }
    @Override
    public void takeAttack(float attackValue,Monster opponent) {
        if (specialMoves>0) {
            this.mutateHP((float)(0.05*this.getHP()));
            --specialMoves;
        }
        super.takeAttack(attackValue,opponent);
    }
    public void defend(float attackValue, Monster opponent) {
        if (specialMoves>0) {
            this.mutateHP((float)(0.05*this.getHP()));
        }
        super.defend(attackValue, opponent);
    }
}