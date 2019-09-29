package RacerMan;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Game implements Serializable {
    private final Player player;
    private final Track track;
    private final int totalTiles;
    private int snakeBites;
    private int vultureBites;
    private int cricketBites;
    private int trampolenes;
    private boolean firstPass, secondPass, thirdPass;
    private boolean inCage;
    private int rollNo;
    Game(String name, int totalTiles){
        player = new Player(name);
        track = new Track(totalTiles);
        System.out.println(track);
        this.totalTiles = totalTiles;
        inCage=true;
        firstPass=secondPass=thirdPass=false;
        rollNo=0;
    }
    public void startGame() throws Exception{
        System.out.println("Starting game with "+ player.getName()+" at Tile "+player.getPosition());
        System.out.println("Hit [ENTER] to start the game");
        System.in.read();
        System.out.println("GAME STARTED =============>");
        
        while(true){
            ++rollNo;
            int roll = Dice.roll(6);
            if(inCage){
                System.out.print(player.getName()+" rolled "+Integer.toString(roll)+" at Tile-"+player.getPosition());
                if(roll!=6){
                    System.out.print(", OOPs you need 6 to start\n");
                }
                else if(roll==6){
                    inCage=false;
                    System.out.print(",  You are out of the cage! You get a free roll\n");
                }
            }
            if(!inCage){
                System.out.print(player.getName()+" rolled "+Integer.toString(roll)+" at Tile-"+player.getPosition());
                
                try {
                    this.movePlayer(roll);
                    Tile currentTile=track.getTile(player.getPosition());
                    currentTile.shake();
                } 
                catch (TileExceptions e) {
                    Tile currentTile=track.getTile(player.getPosition());
                    System.out.println(e.getMessage());
                    try {
                        this.movePlayer(currentTile.getMoveTiles());
                        
                    } catch (GameWinnerException f) {
                        System.out.println(player.getName()+" won the race in "+Integer.toString(rollNo)+" rolls!");
                        System.out.println("Total Snake Bits "+Integer.toString(snakeBites));
                        System.out.println("Total Vulture Bits "+Integer.toString(vultureBites));
                        System.out.println("Total Cricket Bits "+Integer.toString(cricketBites));
                        System.out.println("Total Trampolenes "+Integer.toString(trampolenes));
                        break;
                    }
                    catch(GameSaveException f){
                        System.out.println("\n"+f.getMessage());
                        System.out.println("Tile: "+player.getPosition()+", Do you want to save game?(Y/n)");                    
                        String ch = Main.br.readLine().trim().split("\\s+")[0];
                        if (ch.equals("y")||ch.equals("Y")){
                            serialize();
                            break;
                        }
                    }
                    if(currentTile.getClass()==(new Snake(1)).getClass()){
                        ++snakeBites;
                    }
                    else if(currentTile.getClass()==(new Vulture(1)).getClass()){
                        ++vultureBites;
                    }
                    else if(currentTile.getClass()==(new Cricket(1)).getClass()){
                        ++cricketBites;
                    }
                    else if(currentTile.getClass()==(new Trampolene(1)).getClass()){
                        ++trampolenes;
                    }

                }
                catch (GameWinnerException e){
                    System.out.println(player.getName()+" won the race in "+Integer.toString(rollNo)+" rolls!");
                    System.out.println("Total Snake Bits "+Integer.toString(snakeBites));
                    System.out.println("Total Vulture Bits "+Integer.toString(vultureBites));
                    System.out.println("Total Cricket Bits "+Integer.toString(cricketBites));
                    System.out.println("Total Trampolenes "+Integer.toString(trampolenes));
                    break;
                }
                catch(GameSaveException f){
                    System.out.println(f.getMessage());
                    System.out.println("Tile: "+player.getPosition()+", Do you want to save game?(Y/n)");
                    String ch = Main.br.readLine().trim().split("\\s+")[0];
                    if (ch.equals("y")||ch.equals("Y")){
                        serialize();
                        break;
                    }
                }
            }
        }
    }
    private void movePlayer(int roll){
        if((player.getPosition()>=(int)(0.25*totalTiles))&&!firstPass){
            firstPass=true;
            throw new GameSaveException("25% path completed");
        }
        else if((player.getPosition()>=(int)(0.5*totalTiles))&&!secondPass){
            secondPass=true;
            throw new GameSaveException("50% path completed");
        }
        else if((player.getPosition()>=(int)(0.75*totalTiles))&&!thirdPass){
            thirdPass=true;
            throw new GameSaveException("75% path completed");

        }
        if(roll+player.getPosition()>totalTiles){
            player.setPosition(player.getPosition()+roll);
            System.out.println(": Landed on tile "+ player.getPosition()+"\n");
            throw new GameWinnerException(player.getName()+ " Won!!!");
        }
        else if(roll+player.getPosition()<1){
            player.setPosition(1);
            System.out.print("> Landed on tile 1, can't go further back \n");
            
        }
        else{
            player.setPosition(player.getPosition()+roll);
            System.out.println(": Landed on tile "+ player.getPosition()+"\n");
        }
    }
    private void serialize() throws IOException{
        ObjectOutputStream save=null;
        try{
            save=new ObjectOutputStream(new FileOutputStream(this.player.getName()+".bin"));
            save.writeObject(this);
        }
        finally{
            save.close();
        }
    }
}