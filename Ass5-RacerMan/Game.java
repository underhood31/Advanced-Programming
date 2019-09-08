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
        this.totalTiles = totalTiles;
    }
    public void StartGame(){
        System.out.println("Starting game with "+ player.getName()+" at Tile 1");
        System.out.println("Hit [ENTER] to start the game");
        System.in.read();
        System.out.println("GAME STARTED =============>");
        int rollNo=0;
        boolean inCage=true;
        while(player.getPosition()>totalTiles){
            ++rollNo;
            int roll = Dice.roll(6);
            if(inCage){
                System.out.print(player.getName()+" rolled "+Integer.toString(roll)+" at Tile-"+player.getPosition());
                if(roll!=6){
                    System.out.print(", OOPs you need 6 to start\n");
                }
                else if(roll==6){
                    inCage=false;
                    System.out.print(",  You are out of the cage! You get a free roll")
                }
            }
            if(!inCage){
                System.out.print(player.getName()+" rolled "+Integer.toString(roll)+" at Tile-"+player.getPosition());
                
                try {
                    this.movePlayer(roll);
                    Tile currentTile=Track.getTile(player.getPosition());
                    currentTile.shake();
                } 
                catch (TileExceptions e) {
                    System.out.println(e.getMessage())
                    this.movePlayer(currentTile.getMoveTiles())
                    String ty =currentTile.getClass().toString();
                    if(ty.equals("Snake")){
                        ++snakeBites;
                    }
                    else if(ty.equals("Vulture")){
                        ++vultureBites;
                    }
                    else if(ty.equals("Cricket")){
                        ++cricketBites;
                    }
                    else if(ty.equals("Trampolene")){
                        ++trampolenes;
                    }

                }
                catch (GameWinnerException e){
                    System.out.println(player.getName()+" won the race in "+Integer.toString(rollNo)+" rolls!");
                    System.out.println("Total Snake Bits "+Integer.toString(snakeBites));
                    System.out.println("Total Vulture Bits "+Integer.toString(vultureBites));
                    System.out.println("Total Cricket Bits "+Integer.toString(cricketBites));
                    System.out.println("Total Trampolenes "+Integer.toString(trampolenes));
                }
            }
        }
    }
    private movePlayer(int roll){
        if(roll+player.getPosition()>totalTiles){
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