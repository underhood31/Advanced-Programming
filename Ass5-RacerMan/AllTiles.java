package RacerMan;
//make sound!!!
//Throw exception
class Snake extends Tile{

    private final String type= "Snake";
    private final String sound="Hiss";
    
    Snake(int totalTiles){
        super(-1, totalTiles);
    }
    
    @Override
    public void shake() {
        throw new SnakeBiteException(sound+"... "+"You stepped upon a "+type+" position will change by "+this.getMoveTiles());
    }
}
class Vulture extends Tile{
    private final String type= "Vulture";
    private final String sound="Yapping";
    Vulture(int totalTiles){
        super(-1, totalTiles);
    }

    @Override
    public void shake() {
        throw new VultureBiteException(sound+"... "+"You stepped upon a "+type+" position will change by "+this.getMoveTiles());
    }
}
class Cricket extends Tile{
    private final String type= "Cricket";
    private final String sound="Chirp";
    Cricket(int totalTiles){
        super(-1, totalTiles);
    }
   
    @Override
    public void shake() {
        throw new CricketBiteException(sound+"... "+"You stepped upon a "+type+" position will change by "+this.getMoveTiles());
    }
}
class Trampolene extends Tile{
    private final String type= "Trampolene";
    private final String sound="PingPong";
    Trampolene(int totalTiles){
        super(1, totalTiles);    
    }

    @Override
    public void shake() {
        throw new TrampoleneException(sound+"... "+"You stepped upon a "+type+" position will change by "+this.getMoveTiles());
    }
}
class White extends Tile{
    private final String type= "White Tile";
    private final String sound="Blank";
    White(int totalTiles){
        super(0, totalTiles);
    }
    @Override
    public void shake() {
        throw new TrampoleneException("White tile, you can rest here");
    }
    
}