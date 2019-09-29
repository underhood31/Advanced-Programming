package RacerMan;

import java.io.*;

class Main{
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

    public static void main(String[] args) throws Exception{
        int len=0;
        String uname="";
        String ch;
        while(true){
            try {
                System.out.println("Do you want to load saved user? (Y/n)");
                ch = br.readLine().trim().split("\\s+")[0];
                System.out.print("Enter the username: ");
                uname = br.readLine().trim().split("\\s+")[0];
                
                if(ch.equals("y")||ch.equals("Y")){
                    break;
                }
                System.out.print("Enter the length of the track: ");
                String[] s =br.readLine().trim().split("\\s+");
                len = Integer.parseInt(s[0]);
                
                break;    
            } catch (Exception e) {
                System.out.println("INVALID INPUT!!!");
            
            }
        }
        Game game=null;
        if(ch.equals("y")||ch.equals("Y")){
            game=deserialize(uname);
        }
        else{
            game = new Game(uname, len);
        }
        game.startGame();
    }
    private static Game deserialize(String uname) throws IOException, ClassNotFoundException{
        ObjectInputStream get = null;
        try{
            get = new ObjectInputStream(new FileInputStream(uname+".bin"));
            return (Game)get.readObject();
        }finally{

        }

    }
}