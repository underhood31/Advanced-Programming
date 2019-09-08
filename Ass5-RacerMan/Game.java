package RacerMan;
class Game{
    private final Player player;
    private final Track track;
    private final int totalTiles;
    private int snakeBites;
    private int vultureBites;
    private int cricketBites;
    private int trampolenes;

    Game(String name, int totalTiles){
        player = new Player(name);
        track = new Track(totalTiles);
        System.out.println(track);
        this.totalTiles = totalTiles;
    }
    public void startGame() throws Exception{
        System.out.println("Starting game with "+ player.getName()+" at Tile 1");
        System.out.println("Hit [ENTER] to start the game");
        System.in.read();
        System.out.println("GAME STARTED =============>");
        int rollNo=0;
        boolean inCage=true;
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
                    System.out.print(",  You are out of the cage! You get a free roll");
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
                    String ty =currentTile.getClass().toString();
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
            }
        }
    }
    private void movePlayer(int roll){
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
}