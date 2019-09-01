package ArchLegends;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Location{
    final private Monster monster;
    final private int locNo;
    Location(Monster m, int loc){
        this.monster = m;
        this.locNo = loc;
    }
    public int getLocNo(){
        return locNo;
    }
    public Monster getMonster(){
        monster.revive();
        return monster;
    }
    @Override
    public String toString() {
        return "Go to Location " + this.locNo;
    }
}
class Route{
    private Location[] route;
    private int pos;
    Route(ArrayList<Location> locs){
        route = new Location[122];

        for (int i = 0; i < locs.size(); ++i) {
            route[i+1]=locs.get(i);
        }
        route[0] = null;
        for(int i=41;i<122;i+=10){
            route[i] = new Location(new Lionfang(), -7);
        }
        pos=0;
    }
    public Monster moveForward() throws IOException{
        if (3*pos+1<route.length) {
            if(route[3*pos+1]!=null)
                System.out.println("1)" + route[3*pos+1]);            
        }
        if (3*pos+2<route.length) {
            if(route[3*pos+2]!=null)
                System.out.println("2)" + route[3*pos+2]);
        }
        if (3*pos+3<route.length) {
            if(route[3*pos+3]!=null)
                System.out.println("3)" + route[3*pos+3]);
        }
        if(route[3*pos+1]==null && route[3*pos+2]==null && route[3*pos+3]==null){
            System.out.println("Dead End, going bacwards...");
            return this.moveBackward();

        }
        else if(route[3*pos+1]!=null && route[3*pos+2]!=null && route[3*pos+3]!=null){
            Location smallest;
            if (route[3*pos+1].getMonster().getLevel() <= route[3*pos+2].getMonster().getLevel() && route[3*pos+1].getMonster().getLevel() <= route[3*pos+3].getMonster().getLevel()) {
                smallest = route[3*pos+1];
            } else if (route[3*pos+2].getMonster().getLevel() <= route[3*pos+3].getMonster().getLevel() && route[3*pos+2].getMonster().getLevel() <= route[3*pos+1].getMonster().getLevel()) {
                smallest = route[3*pos+2];
            } else {
                smallest = route[3*pos+3];
            }
            System.out.println("(Bonus part)Recommended: "+smallest.getLocNo());
        }
        String[] s = Main.br.readLine().trim().split("\\s+");
        int ch = Integer.parseInt(s[0]);
        this.pos=3*pos+ch;
        return route[pos].getMonster();

    }
    public Monster moveBackward(){
        pos=(pos-1)/3;
        return route[pos].getMonster();
    }
    public int getPos(){
        return this.pos;
    }
    public void setToInitial(){
        this.pos=0;
    }
}
class Hero extends Character{
    
    private ArrayList<Sidekick> sidekicks;
    protected float XP;
    protected int level;    
    protected final String name;
    protected final float attackVal;
    protected final float defenceVal;
    protected float baseAttack;
    protected float baseDefence;
    protected float currentDefence;
    protected int specialMoves;
    protected Route route;
    private int movesDone;
    Hero(String username, int att, int def){
        this.name = username;
        this.attackVal =att;
        this.defenceVal =def;
        specialMoves=0;
        movesDone = 0;
        level=1;
        updateHP();
        ArrayList<Location> locations = new ArrayList<Location>();
        for(int i=0; i<40;++i){
            Random rnd = new Random();
            int monsterNo = rnd.nextInt(3);
            Monster montemp;
            switch(monsterNo){
                case 0:
                    montemp = new Goblin();
                    break;
                case 1:
                    montemp = new Zombie();
                    break;
                case 2: 
                    montemp = new Fiend();
                    break;
                default:
                    montemp = new Goblin();

            }
            locations.add(new Location(montemp, i));
            
        }
        Collections.shuffle(locations);
        route = new Route(locations); 
        sidekicks = new ArrayList<Sidekick>();       
    }
    public int getPos(){
        return route.getPos();
    }
    public int getNoOfSideKicks(){
        return this.sidekicks.size();
    }
    public void addSidekick() throws IOException{
        System.out.print("Do you want to Buy a Sidekick(YES/NO): ");
        String in = Main.br.readLine().trim().split("\\s+")[0];
        if(in.equals("NO")||in.equals("no")){
            return;
        }
        if(this.sidekicks.size()<4){
            System.out.println("Current XP: " + this.XP);
            System.out.println("Select new sidekick: ");
            System.out.println("1) Minion");
            System.out.println("2) Knight");
            int ch = Integer.parseInt(Main.br.readLine().trim().split("\\s+")[0]);
            System.out.print("XP to spend: ");
            int spend = Integer.parseInt(Main.br.readLine().trim().split("\\s+")[0]);
            switch (ch){
                case 1:
                    if(this.XP>5){
                        this.XP-=spend;
                        Sidekick temp = new Minion(spend);
                        this.sidekicks.add(temp);
                        System.out.println("You added sideckick Minion");
                    }
                    else{
                        System.out.println("Not enough XP to buy Sidekick");
                    }
                    
                    break;
                case 2:
                    if(this.XP>8){
                        this.XP-=spend;
                        sidekicks.add(new Knight(spend));
                        System.out.println("You added sideckick Knight");
                    }
                    else{
                        System.out.println("Not enough XP to buy Sidekick");
                    }
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    } 

    // public Sidekick getSideKick(){
    //     sidekicks.sort(new SortbyXP());
    //     return sidekicks.get(0);
    // }
    public int  moveForward() throws IOException{
        if(XP>=5)
            this.addSidekick();
        selectLevel();
        updateHP();
        this.removeDead(sidekicks);
        sidekicks.sort(new SortbyXP());
        Monster mons = route.moveForward();
        int result = fight(mons);
        if (result==-1){
            this.route.setToInitial();
            this.level=1;
            updateHP();
            return 0;
        }
        else{
            
            if(mons.levelToAffect==4){
                System.out.println("You won!!");
                this.route.setToInitial();
                this.level=1;
                updateHP();
                return 5;
            }
            return 0;
        }
    } 
    public void moveBackward() throws IOException{
        if(XP>=5)
            this.addSidekick();
        selectLevel();
        updateHP();        
        this.removeDead(sidekicks);
        sidekicks.sort(new SortbyXP());
        int result = fight(route.moveBackward());
        
        if (result==-1){
            this.route.setToInitial();
            this.level=1;
            updateHP();
        }
        else{
            updateHP();
        }
    }
    private void removeDead(ArrayList<Sidekick> lst){
        for (int i = 0; i < lst.size(); i++) {
            if(!lst.get(i).getLifeStatus()){
                lst.remove(i);
            }
        }
    }
    private int fight(Monster monster) throws IOException{
        System.out.println("Fight started, you are fighting a "+ monster);
        ArrayList<Sidekick> helpers = new ArrayList<Sidekick>();
        
        if(sidekicks.size()>0){
            System.out.println("Do you want to use a Sidekick(YES/NO)");
            String choiceString=Main.br.readLine().trim().split("\\s+")[0];
            String rep;
            if((choiceString.equals("YES") || choiceString.equals("yes") || choiceString.equals("Yes") )&& sidekicks.size()>0){
                helpers.add(sidekicks.get(0));
                float attack=helpers.get(0).attackDamage();
                if(helpers.get(0).getType()==1){
                    System.out.println("Your sidekick is Minion. Sidekick attack is "+attack);
                    if(helpers.get(0).canUsSpecialPower()){
                        System.out.println("Press 'c' to use clonning ability, press 'f to fight'");         
                        rep  =  Main.br.readLine().trim().split("\\s+")[0];
                        if(rep.equals("c")){
                            helpers.get(0).usePower();
                            helpers.add((Sidekick)helpers.get(0).clone());
                            helpers.add((Sidekick)helpers.get(0).clone());
                            helpers.add((Sidekick)helpers.get(0).clone());
                        }
                    }
                    
                }

                else{
                    System.out.println("Your sidekick is Knight. Sidekick attack is "+attack);
                    
                }
            }
            else{
                System.out.println("Cannot use sidekick");
            }
        }
        
      
        while(this.getLifeStatus() && monster.getLifeStatus()){
            System.out.println("1) Attack");
            System.out.println("2) Defence");
        
            if(specialMoves<=0 && movesDone>2)
                System.out.println("3) Special Attack");
            String[] s = Main.br.readLine().trim().split("\\s+");
            int choice = Integer.parseInt(s[0]);
            switch(choice){
                case 1:
                    System.out.println("You choose to attack!");
                    monster.takeAttack(this.attack(monster));
                    for (Sidekick var : helpers) {
                        monster.takeAttack(var.attack());
                    }
                    System.out.println("Your HP: "+this.getHP()+"/"+this.maxHP+" ;Monster's HP: "+monster.getHP()+"/"+monster.getMaxHp());
                    if(monster.getLifeStatus()){
                        System.out.println("Monster Attack!!!");
                        float monAttack = monster.attack(this.getHP());
                        this.takeAttack(monAttack, monster);
                        System.out.println("Your HP: "+this.getHP()+"/"+this.maxHP+" ;Monster's HP: "+monster.getHP()+"/"+monster.getMaxHp());
                        for (Sidekick var : helpers) {
                            var.takeAttack((float)1.5*monAttack);
                        }
                    }
                    break;
                case 2:
                    System.out.println("You choose to Defend!");
                    System.out.println("Monster attack!!!");
                    float monAttackD = monster.attack(this.getHP()); 
                    if(helpers.size()>0)
                        if(monster.getLevel()==2 && helpers.get(0).getType()==2){
                            System.out.println("Zombie's attacks reduced by 5 units because of knight");
                            monAttackD-=5;
                        }                        
                    this.defend(monAttackD,monster);
                    System.out.println("Your HP: "+this.getHP()+"/"+this.maxHP+" ;Monster's HP: "+monster.getHP()+"/"+monster.getMaxHp());
                    for (Sidekick var : helpers) {
                        var.takeAttack((float)1.5*monAttackD);
                    }
                    break;
                case 3:
                    System.out.println("Special attack activated"); 
                    this.specialAttack(monster);
                    System.out.println("Your HP: "+this.getHP()+"/"+this.maxHP+" ;Monster's HP: "+monster.getHP()+"/"+monster.getMaxHp());
                    float monAttackS;
                    if(monster.getLifeStatus()){
                        System.out.println("Monster Attack!!!");
                        monAttackS = monster.attack(this.getHP());
                        this.takeAttack(monAttackS,monster);
                        System.out.println("Your HP: "+this.getHP()+"/"+this.maxHP+" ;Monster's HP: "+monster.getHP()+"/"+monster.getMaxHp());
                        for (Sidekick var : helpers) {
                            var.takeAttack((float)1.5*monAttackS);
                        }
                    }
                    break;
            }
            ++movesDone;
        }
        if(!monster.getLifeStatus()){
            System.out.println("Monster dead!!!");
            if(helpers.size()>0){
                helpers.get(0).matchWon(2*monster.getLevel());
            }
            this.XP+=(20*monster.getLevel());
            return 1;
        }
        if(!this.getLifeStatus()){
            System.out.println("You died, game restart");
            sidekicks.clear();
            return -1;
        }
        return 1;

    }
    private void updateHP(){
        this.setAlive();
        switch (this.level){
            case 1:
                HP=100;
                maxHP=100;
                break;
            case 2:
                maxHP=150;
                HP=150;
                break;
            case 3:
                maxHP=200;
                HP=200;
                break;
            case 4:
                maxHP=250;
                HP=250;
                break; 
        }
    }
    private void selectLevel(){
        switch (this.level){
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

    
    public float attack(Monster opponent)
    {
        selectLevel();
        System.out.println("You attacked, and inflicted "+(this.baseAttack+level-1)+" damage to the monster");        
        return this.baseAttack+level-1;    
    }

    
    public void takeAttack(float attackValue, Monster opponent) {
        attackValue=attackValue-currentDefence;
        if(attackValue<0){
            attackValue=0;
        }
        System.out.println("Monster attacked and inflicted damage of "+attackValue+" to you after having a defence of "+currentDefence);
        this.mutateHP(-1*(attackValue));
    }
    public void defend(float attackValue, Monster opponent){
        selectLevel();
        this.currentDefence = this.baseDefence +level-1;
        if(attackValue<0){
            attackValue=0;
        }
        takeAttack(attackValue, opponent);
        currentDefence=0;
    }
    public int specialAttack(Monster monster){
        return 0;
    }

    
}