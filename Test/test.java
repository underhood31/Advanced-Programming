import java.io.*;
import java.util.Scanner;
class Manager implements Serializable {
    private String name;
    public Manager(String n) {
        name=n;
    }
}
   
public class test{
    public static void main(String[] args) {
        
        try{
            Manager m = new Manager("Amy");
            ObjectOutputStream out = null;
            try{
                out = new ObjectOutputStream(new FileOutputStream("ouf"));
                out.writeObject(m);
            }finally{
                out.close();
            }
        }
        catch(IOException e){
            System.out.println("The following IOException occured"+e.toString()+"\n"+e.getMessage());
        }
    }

}