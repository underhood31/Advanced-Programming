package ArchLegends;
import java.util.Hashtable;

public class Game{
    Hashtable<String, Hero> players;
    Hashtable<Integer,Monster> levels;
    Game(){
        players = new Hashtable<String, Hero>();
        generateGraph();
    }
    private void generateGraph(){
        
    }
    public String addPlayer(String uname, int heroType){
        if(players.containsKey(uname)){
            return "User already exists";
        }
        else{
            if (heroType==1) {
                players.put(uname, new Warrior());
                return "User Creation Successful. Username: "+uname+". Hero type: Warrior. Log in to play..."        
            }
            else if (heroType==2) {
                players.put(uname, new Thief());
                return "User Creation Successful. Username: "+uname+". Hero type: Thief. Log in to play..."        
            }
            else if (heroType==3) {
                players.put(uname, new Mage());
                return "User Creation Successful. Username: "+uname+". Hero type: Mage. Log in to play..."        
            }
            else if (heroType==4) {
                players.put(uname, new Healer());
                return "User Creation Successful. Username: "+uname+". Hero type: Healer. Log in to play..."        
            }
        }
        
    }
    private void changeUser(){

    }
    public void exitGame(){
        
    }

    public void playGame(String uname){
        if(players.containsKey(uname)){
            System.out.println("Player not found");
            return;
        }

        // TODO implement playing method
        System.out.println("User Found. Logged in as " + uname);
        int stage;
        stage = selectPath();
    }
}