package ArchLegends;
import java.util.Comparator;

class Sidekick extends Character{
    protected float XP;
    protected final float baseAttack;
    protected float extraAttack;
    private boolean canUsePower;
    private int type;
    Sidekick(float bAttack,float eAttack,int type){
        extraAttack=eAttack;
        baseAttack=bAttack;
        maxHP=100;
        HP=100;
        canUsePower=true;
        this.type=type;
    } 
    public float attack(){
        System.out.println("Sidekick attacked and infliceted "+(baseAttack+extraAttack+(int)(XP/5))+" to the monster.");
        return baseAttack+extraAttack+(int)(XP/5);
    }
    public float attackDamage(){
        return baseAttack+extraAttack+(int)(XP/5);
    }
    public void takeAttack(float attackValue) {

        this.mutateHP(-1*attackValue);
        System.out.println("Sidekick health: "+this.HP);
    }
    public float getXP(){
        return this.XP;
    }
    public void matchWon(float XPtoUpdate){
        this.XP+=XPtoUpdate;
        this.HP = this.maxHP;

    }
    public void usePower(){
        canUsePower=false;
    }
    public boolean canUsSpecialPower(){
        return canUsePower;
    }
    public int getType(){
        return this.type;
    }
    @Override
    protected Object clone(){
        try {
            return super.clone();

        } catch (CloneNotSupportedException e) {
            System.out.println("Cannot clone");
            return null;
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (obj!=null && this.getClass()==obj.getClass()) {
            Sidekick o=(Sidekick)(obj);
            return((this.XP==o.getXP())&&(this.attackDamage()==o.attackDamage())&&(this.type==o.getType()));
            
        } else {
            return false;
        }
    }
 
}
class SortbyXP implements Comparator<Sidekick> 
{ 
  
    public int compare(Sidekick a, Sidekick b) 
    { 
        if(a.getXP()>b.getXP()){
            return -11;
        } 
        else if(a.getXP()<b.getXP()){
            return 1;
        }else if(a.equals(b)){
            return 1;
        }
        return -1;
        
    } 
}
