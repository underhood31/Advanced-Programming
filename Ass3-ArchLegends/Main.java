package ArchLegends;

import java.io.BufferedReader;
import java.io.IOException;


public class Main{
    staic void printMainMenu(){
        System.out.println("Choose your option:");
        System.out.println("1) New User");
        System.out.println("2) Exit User");
        System.out.println("3) Exit");
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args)throws IOException {
        Game game = new Game();
        System.out.println("Welcome to ArchLegends");
        String[] s;
        boolean toContinue = true;
        while(toContinue){
            printMainMenu();        
            s = br.readLine().trim().split("\\s+");
            int choice = Integer.parseInt(s[0]);
            switch choice{
                case 1:
                    System.out.println("Enter username: ");
                    s = br.readLine().trim().split("\\s+");
                    String uname = s[0];
                    
                    System.out.println("Choose a hero: ");
                    System.out.println("1) Warrior");
                    System.out.println("2) Thief");
                    System.out.println("3) Mage ");
                    System.out.println("4) Healer");
                    s = br.readLine().trim().split("\\s+");
                    int heroChoice = Integer.parseInt(s[0]);

                    System.out.println(game.addPlayer(uname, heroChoice));
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    s = br.readLine().trim().split("\\s+");
                    String uname = s[0];
                    game.playGame(uname);
                    break;
                case 3:
                    System.out.println("May coward get blessed!\nGoodbye");
                    toContinue=false;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
        
    }
}