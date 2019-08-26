package ArchLegends;

class Hero extends Character{
    protected float XP;
    protected int level;    
    protected final String name;
    private final float attackVal;
    private final float defenceVal;
    private float baseAttack;
    private float baseDefence;
    private float currentDefence;
    protected int specialMoves;
    Hero(String username){
        this.name = username;
        level=1;;
        updateHP();
    }

    private void updateHP(){
        switch this.level{
            case 1:
                HP=100
                break;
            case 2:
                HP=150
                break;
            case 3:
                HP=200
                break;
            case 4:
                HP=250
                break; 
        }
    }
    private void selectLevel(){
        switch this.level{
            case 1:
                if (XP>=20) {
                    level+=1;
                }
                break;
            case 2:
                if (XP>=40) {
                    level+=1;
                }
                break;
            case 3:
                if (XP>=60) {
                    level+=1;
                }    
                break;
            case 4:
                break;
        }
    }

    @Override
    public float attack()
    {
        
        selectLevel();
        return this.baseAttack+level-1;    
    }

    @Override
    public void takeAttack(float attackValue) {
        this.mutateHP(-1*(attackValue-currentDefence));
        currentDefence=0;
    }
    
    public void setUpDefence(){
        selectLevel();
        currentDefence = baseDefence+level-1;
    }


}