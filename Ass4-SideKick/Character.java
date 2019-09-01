package ArchLegends;

abstract class Character implements Cloneable{
    protected float HP;
    private boolean isAlive;
    protected float maxHP;
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
    protected void setAlive(){
        isAlive=true;
    }


  
    public boolean getLifeStatus(){
        if(this.HP<=0)
            this.isAlive=false;
        return this.isAlive;
    }

}