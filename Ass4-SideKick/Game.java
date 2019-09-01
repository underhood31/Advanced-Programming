package ArchLegends;
import java.io.IOException;
import java.util.Hashtable;

public class Game{
    Hashtable<String, Hero> players;
    Hashtable<Integer,Monster> levels;
    Game(){
        players = new Hashtable<String, Hero>();
    }
    
    public String addPlayer(String uname, int heroType){
        if(players.containsKey(uname)){
            return "User already exists";
        }
        else{
            if (heroType==1) {
                final Hero h = new Warrior(uname);
                players.put(uname,h) ;
                return "User Creation Successful. Username: "+uname+". Hero type: Warrior. Log in to play...";  
            }
            else if (heroType==2) {
                final Hero h = new Thief(uname);
                players.put(uname, h);
                return "User Creation Successful. Username: "+uname+". Hero type: Thief. Log in to play...";     
            }
            else if (heroType==3) {
                final Hero h = new Mage(uname);
                players.put(uname, h);
                return "User Creation Successful. Username: "+uname+". Hero type: Mage. Log in to play...";      
            }
            else if (heroType==4) {
                final Hero h = new Healer(uname);
                players.put(uname, h);
                return "User Creation Successful. Username: "+uname+". Hero type: Healer. Log in to play...";        
            }
        }
        return "Cannot add";
        
    }

    public void playGame(String uname) throws IOException{
        if(!players.containsKey(uname)){
            System.out.println("Player not found");
            return;
        }
        Hero player = players.get(uname);
        System.out.println("User Found. Logged in as " + uname + ".You can enter -1 to exit");
        boolean exit =false;
        while(!exit){
            if(player.getPos()==0){
                System.out.println("You are at initial position");
            }
            System.out.println("Choose an option:");
            System.out.println("1) Go Forward");
            if(player.getPos()!=0 && player.getPos()!=1)
                System.out.println("2) Go Backward");

            int choice=Integer.parseInt(Main.br.readLine().trim().split("\\s+")[0]);

            switch (choice){
                case -1:
                    exit=true;
                    break;
                case 1:
                    int res = player.moveForward();
                    if(res==5){
                        exit=true;
                    }
                    break;
                case 2:
                    player.moveBackward();
                    break;
            }
        }
    }
}