package ArchLegends;
import java.util.Random;
import java.math.*;
class Monster extends Character{
    protected final int levelToAffect;
    protected final float MAX_HP;
  
    Monster(int l,float hp){
        levelToAffect = l;
        MAX_HP = hp;
    }
    public float attack(float oh) {
        Random distribution = new Random();
        return (int)Math.abs(distribution.nextGaussian()*this.HP/16+this.HP/8);
    }
    public void revive(){
        this.setAlive();
        this.HP = MAX_HP;
    }
    public void takeAttack(float attackValue) {
        System.out.println("You attacked, and inflicted "+attackValue+" damage to the monster");
        this.mutateHP(-1*attackValue);
    }
    public float getMaxHp(){
        return MAX_HP;
    }
    public int getLevel(){
        return levelToAffect;
    }
}