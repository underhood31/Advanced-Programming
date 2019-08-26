package ArchLegends;
import java.util.Hashtable;

public class Game{
    Hashtable<String, Hero> players;
    Hashtable<Integer,Monster> levels;
    Game(){
        players = new Hashtable<String, Hero>();
    }
    void generateGraph(){
        
    }
    public addPlayer(String uname, Hero h){
        players.put(uname, h);
    }
    private void changeUser(){

    }
    public void exitGame(){
        
    }
}