package ArchLegends;
import java.util.Random;
import java.math.*;
class Monster extends Character{
    protected final int levelToAffect;
    protected final float MAX_HP;
    @Override
    public int attack() {
        Random distribution = new Random();
        return Math.abs((int)distribution.nextGaussian()+this.HP/8);
    }
}