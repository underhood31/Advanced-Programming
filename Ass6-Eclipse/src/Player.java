//package RacerMan;

import java.io.Serializable;

class Player implements Serializable{
    private final String name;
    private int position;
    Player(String name){
        this.name=name;
        this.position=1;
    }
    public int getPosition(){
        return this.position;
    }
    public void setPosition(int n){
        position=n;
    }
    public String getName(){
        return name;
    }
}