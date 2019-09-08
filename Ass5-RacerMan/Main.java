package RacerMan;

import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        int len;
        String uname;
        while(true){
            try {
                System.out.print("Enter the length of the track: ");
                String[] s =br.readLine().trim().split("\\s+");
                len = Integer.parseInt(s[0]);
                System.out.print("Enter the username: ");
                uname = br.readLine().trim().split("\\s+")[0];
                break;    
            } catch (Exception e) {
                System.out.println("INVALID INPUT!!!");
            
            }
        }
        Game game = new Game(uname, len);
        game.startGame();
    }
}