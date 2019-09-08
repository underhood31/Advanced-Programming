package RacerMan;

import java.util.HashMap;
import java.util.Random;

class Track{
    // make a hast table to store values <int, tile>
    private HashMap<Integer,Tile> TileOnRoute;
    private final int totalTiles;
    private int snakes;
    private int vultures;
    private int crickets;
    private int trampolenes;
    private final Tile snakeTile;
    private final Tile vultureTile;
    private final Tile cricketTile;
    private final Tile trampoleneTile;
    private final Tile WhiteTile;
    
    Track(int totalTiles){
        System.out.println("Setting up race track...");
        this.totalTiles=totalTiles;
        TileOnRoute=new HashMap<>();
        //0-3 types 4-11 white
        snakeTile = new Snake(totalTiles);
        vultureTile = new Vulture(totalTiles);
        cricketTile = new Cricket(totalTiles);
        trampoleneTile = new Trampolene(totalTiles);
        WhiteTile = new White(totalTiles);
        Random rnd = new Random();
        for (int i = 2; i <= totalTiles; i++) {
            int decide = rnd.nextInt(12);
            if(decide<4){
                switch (decide){
                    case 0:
                        TileOnRoute.put(i,snakeTile);
                        ++snakes;
                        break;
                    case 1:
                        TileOnRoute.put(i,vultureTile);
                        ++vultures;
                        break;
                    case 2:
                        TileOnRoute.put(i,cricketTile);
                        ++crickets;
                        break;
                    case 3:
                        TileOnRoute.put(i,trampoleneTile);
                        ++trampolenes;
                        break;
                
                }
                
            }
            else{

                TileOnRoute.put(i,WhiteTile);
            }
        }
        
    }

    public Tile getTile(int pos){
        return TileOnRoute.get(pos);
    }

    @Override
    public String toString(){
        String retString="";
        retString+=("[Danger] There are "+ Integer.toString(snakes)+", "+Integer.toString(vultures)+" and "+Integer.toString(crickets)+" snakes, vultures and crickets respectively.\n");
        retString+=("[Danger] Each snake, vulture and cricket can move make change of "+Integer.toString(snakeTile.getMoveTiles())+", "+Integer.toString(vultureTile.getMoveTiles())+" and "+Integer.toString(cricketTile.getMoveTiles())+" respectively to your current position.\n");
        retString+=("[Good News] There are "+Integer.toString(trampolenes)+" trampolene tiles\n");                
        retString+=("[Good News] Each trampolene tile will make a change of "+Integer.toString(trampoleneTile.getMoveTiles())+" to your current position");
        return retString;
    }   
}