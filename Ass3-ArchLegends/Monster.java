package ArchLegends;
import java.util.Random;
import java.math.*;
class Monster extends Character{
    protected final int levelToAffect;
    protected final float MAX_HP;
    protected float currentHP;
    @Override
    public int attack() {
        Random distribution = new Random();
        return Math.abs((int)distribution.nextGaussian()+this.currentHP/8);
    }
}