
import java.io.Serializable;
import java.util.Random;

abstract class Tile implements Serializable{
    protected final int moveFactor;
    protected final int moveTiles;
    protected final int totalTiles;

    Tile(int moveFactor, int totalTiles){
        this.moveFactor=moveFactor;
        this.totalTiles=totalTiles;
        this.moveTiles=calMoveTiles();
    }

    private int calMoveTiles(){
        Random calc = new Random();
        if(totalTiles>10){
            return calc.nextInt(10)+1;
        }
        else{
            return calc.nextInt(totalTiles)+1;
        }
    }
    
    public int getMoveTiles(){
        return moveTiles*moveFactor;
    }
    
    abstract public void shake();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}