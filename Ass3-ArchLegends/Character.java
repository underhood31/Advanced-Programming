package ArchLegends;

abstract class Character{
    protected float HP;
    private boolean isAlive;
    Character(){
        isAlive=true;
    }
    public float getHP(){
        return HP;
    }
    protected void mutateHP(float n){
        this.HP+=n;
        if(this.HP<0){
            this.HP=0;
            isAlive=false;
        }
    }

    public void takeAttack(float attackValue){
        mutateHP(-1*attackValue);
        
    }
    public boolean getLifeStatus(){
        return this.isAlive;
    }

    public abstract float attack();
    public abstract int specialAttack();
}