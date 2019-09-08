package RacerMan;

class Player(){
    private final String name;
    private int position;
    Player(name){
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