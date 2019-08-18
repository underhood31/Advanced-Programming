import java.io.*;
import java.util.*;
public interface AppUser{
    public void diplayDetails();
    public void showRewards();
    public void searchForItems();
    public void showMenu();

}

class Merchant implements AppUser{
    private static int IDCOUNT=1;
    private String name;
    private int id;
    private String address;
    private float totalContribution;
    private float money;
    private int maxItems;
    private int extraSlots;
    private Hashtable<Integer,Item> items;

    Merchant(String n, String addr){
        this.name = n;
        this.address = addr;
        this.totalContribution = 0;
        this.money=0;
        this.id = ++Merchant.IDCOUNT; 
        this.maxItems =10;
        this.extraSlots=0;
        items = new Hashtable<Integer, Item>();
    }

    private void addItem(String n, int q, float p, String c, int off){
        if(items.size()<this.maxItems+this.extraSlots){
            Item temp=new Item(n, q, p, this, c, off);
            this.items.put(temp.getID(), temp);
            System.out.println(temp);
        }
        else{
            System.out.println("Error: Can't add more items");
        }
    }
    public buyItem(int itemID){
        try {
            Item itm=items.get(itemID);
            this.money += itm.getPrice()-0.005*itm.getPrice();
            this.totalContribution += 0.01*itm.getPrice();
            itm.buyMe();
            if(itm.getQuantity()==0){
                items.remove(itemID);
                Item.removeFromCategory(itm.getCategory(),itemID);
            }
            this.extraSlots = (int)(this.totalContribution/100);
        } catch (Exception e) {
            System.out.println("Item not found");
        }
        
    }
    public void addOffer(int itemID, int offer){
        try {
            Item temp = items.get(itemID);            
            temp.setOffer(offer);
            System.out.println(temp);
        } catch (Exception e) {
            System.out.println("Error: Invalid input");
        } 
    }
    public void editItem(int itemID, int quantity, float price){
        try {
            Item temp = items.get(itemID);            
            temp.setPrice(price);
            temp.setQuantity(quantity);
            System.out.println(temp);
        } catch (Exception e) {
            System.out.println("Error: Invalid input");
        }
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String toString() {
        return "[ID: "+ Integer.toString(this.id) + " Name: "+this.name+" Addr: "+this.address+"\nContributions: "+Float.toString(this.totalContribution)+" ";
    }
    @Override
    public void diplayDetails(){
        System.out.println(this);
    }
    @Override
    public void showMenu(){
        boolean going=true;
        String s[];
        int choice;
        while(going){
            System.out.println("Welcome " + this.name);
            System.out.println("Merchant Menu");
            System.out.println("1) Add Item");
            System.out.println("2) Edit Item");
            System.out.println("3) Search by category Item");
            System.out.println("4) Add offer");
            System.out.println("5) Rewards won");

            s=program.br.readLine().trim().split("\\s+");
            choice = Integer.parseInt(s[0]);

            switch (choice){
                case 1:
                    System.out.print("Enter name: ");
                    s=program.br.readLine().trim().split("\\s+");
                    String n=s[0];
                    System.out.print("Enter Price: ");
                    s=program.br.readLine().trim().split("\\s+");
                    float p=Float.parseFloat(s[0]);
                    System.out.print("Enter Quatity: ");
                    s=program.br.readLine().trim().split("\\s+");
                    int q=Integer.parseInt(s[0]);
                    System.out.print("Enter Category: ");
                    s=program.br.readLine().trim().split("\\s+");
                    String c=s[0];
                    System.out.print("Enter Offer(0 if none): ");
                    s=program.br.readLine().trim().split("\\s+");
                    int off=Integer.parseInt(s[0]);
                    this.addItem(n, q, p, c, off);
                    break;
                case 2:
                    System.out.println("Choose item by code");
                    items.forEach((k, v) -> { 
                        System.out.println(k+ " : " +v);
                    }); 
                    s=program.br.readLine().trim().split("\\s+");
                    int itID=Integer.parseInt(s[0]);
                    
                    System.out.println("Enter Edit Details");
                    System.out.print("New Price: ");
                    s=program.br.readLine().trim().split("\\s+");
                    float pr=Float.parseFloat(s[0]);
                    System.out.print("Quantity: ");
                    s=program.br.readLine().trim().split("\\s+");
                    int qt=Integer.parseInt(s[0]);
                    editItem(itID, qt, pr);
                    break;
                case 3:
                    Item.printCategories();
                    s=program.br.readLine().trim().split("\\s+");
                    String cat = s[0];
                    Item.printFromCategory(cat);                    
                    break;
                case 4:
                    System.out.println("Choose item by code");
                    items.forEach((k, v) -> { 
                        System.out.println(k+ " : " +v);
                    }); 
                    s=program.br.readLine().trim().split("\\s+");
                    int itIDForOff=Integer.parseInt(s[0]);
                    System.out.println("Select offer");
                    System.out.println("1: Buy 1 get 1 free");
                    System.out.println("2: 25% off");
                    s=program.br.readLine().trim().split("\\s+");
                    int selectedOff=Integer.parseInt(s[0]);
                    
                    this.addOffer(itIDforOff, selectedOff);
                    break;
                case 5:
                    System.out.println("Reward(extra slots): " + this.extraSlots );
                    break;
                case 6:
                    going=false;
                    break;
                default:
                    System.out.println("Invalid Input");
            }

        }

    }

}

class Costumer implements AppUser{
    public static int IDCOUNT=1;    
    private String name;
    private int id;
    private String address;
    private int numOfOrders;
    private LinkedList<Item> orders;
    private float mainAccount;
    private float rewardAccount;
    private Queue<Item> cart;
    Costumer(String n, String addr){
        this.name = n;
        this.id = ++Costumer.IDCOUNT;
        this.address = addr;
        this.numOfOrders = 0;
        this.mainAccount = 100;
        this.orders=new LinkedList<Item>();
    }
    public void addToCart(Item item){
        cart.add(Item);
    }

    public void checkout(){
        while(cart.size()>0){
            Item temp = cart.peek();
            float billAmount = temp.getPrice()+0.005*temp.getPrice();
            if(billAmount>mainAccount+rewardAccount){
                System.out.println("Insufficient funds");
            }
            else if(billAmount>mainAccount){
                
                rewardAccount=temp.getPrice()-mainAccount;
                mainAccount=0;
                temp.getMerchant().buyItem(temp.getID());
                ++this.numOfOrders;
                this.orders.push(temp);
                if(numOfOrders%5==0){
                    this.rewardAccount+=10;
                }
            }
            else{
                rewardAccount=temp.getPrice()-mainAccount;
                mainAccount=0;
                temp.getMerchant().buyItem(temp.getID());
                this.orders.push(temp);
                ++this.numOfOrders;
                if(numOfOrders%5==0){
                    this.rewardAccount+=10;
                }
            }
        }
    }
    public void buy(Item toBuy){

    }
    public void listRecentOrders(){
        System.out.println("Order History:");
        for(int i =0;i<10 && i<orders.size();++i){
            system.out.println(orders.get(i));
        }
    }
    @Override
    public String toString() {
        return "[ID: "+ Integer.toString(this.id) + " Name: "+this.name+" Addr: "+this.address+" Number of orders: "+Integer.toString(this.numOfOrders);
    }
    @Override
    public void diplayDetails(){
        System.out.println(this);
    }
    @Override
    public void showMenu() {
        boolean going=true;
        String s[];
        int choice;
        while(going){
            System.out.println("Welcome " + this.name);
            System.out.println("Customer Menu");
            System.out.println("1) Add Item");
            System.out.println("2) Edit Item");
            System.out.println("3) Search by category Item");
            System.out.println("4) Add offer");
            System.out.println("5) Rewards won");

            s=program.br.readLine().trim().split("\\s+");
            choice = Integer.parseInt(s[0]);

            switch (choice){
                case 1:
                    Item.printCategories();
                    s=program.br.readLine().trim().split("\\s+");
                    String cat = s[0];
                    Item.printFromCategory(cat);  
                    System.out.print("Enter Item Code: ");
                    s=program.br.readLine().trim().split("\\s+");
                    int buyID= Integer.parseInt(s[0]);
                    Item toBuy = Item.getItem(cat,buyID);
                    System.out.println("Enter quantity: ");
                    s=program.br.readLine().trim().split("\\s+");
                    int qToBuy= Integer.parseInt(s[0]);
                    System.out.println("Choose Method of transaction");
                    System.out.println("1) Buy Item");
                    System.out.println("2) Add to cart");
                    System.out.println("3) Exit");
                    s=program.br.readLine().trim().split("\\s+");
                    int method= Integer.parseInt(s[0]);
                    switch (method){
                        case 1:
                            this.buy(toBuy);
                            break;
                        case 2:
                            this.addToCart(toBuy);
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                    break;
                case 2:
                    this.checkout();
                    break;
                case 3:
                    System.out.println("Rewards won: " + this.rewardAccount);
                    break;
                case 4:
                    this.listRecentOrders();
                    break;
                case 5:
                    going=false;
                    break;
                default:                
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }
}


class Item{
    private static int IDCOUNT=1;
    private static Hashtable<String, ArrayList<Item>> categories = new Hashtable<String, ArrayList<Item>>();
    private String name;
    private int id;
    private int category;
    private int quantity;
    private float price;
    private int offer;
    private Merchant merchant;
    Item(String name, int quantity, float price, Merchant merchant,String category, int offer){
        this.name = name;
        this.id=++IDCOUNT;
        this.quantity = quantity;
        this.price = price;
        this.merchant = merchant;
        this.category = category;
        this.offer = offer;
        
        if(categories.containsKey(category)){
            ArrayList<Item> temp = categories.get(category);
            temp.add(this);
        }
        else{
            ArrayList<Item> temp= new ArrayList<Item>();
            temp.add(this);
            categories.put(category, temp);
        }
    }
   //-------Static function
   
    public static void printCategories(){
        Set s = categories.keySet();
        for (String v : s) {
            System.out.println(v);
        }
    }
    public static void removeFromCategory(String cat, int itemID){
        ArrayList<Item> temp=  categories.get(cat);
        for (int i = 0; i < temp.size(); ++i){
            Item itm = temp.get(i);
            if (itm.getID()==itemID) {
                temp.remove(itm);
            }
        }  
            
    }
    public static void printFromCategory(String category){
        ArrayList<Item> temp = category.get(category);
        for (int i = 0; i < temp.size(); ++i)  
            System.out.println(temp.get(i));
    }
    public static Item getItem(String cat, int itemID){
        ArrayList<Item> temp=  categories.get(cat);
        Item toRet = null;
        for (int i = 0; i < temp.size(); ++i){
            Item itm = temp.get(i);
            if (itm.getID()==itemID) {
                toRet = itm;
            }
        }  
        return toRet;
    }

   //-------Non static function
   public void buyMe(int n){
        this.quantity-=n;
   } 
   public int getQuantity(){
       return this.quantity;
   }
    public String getCategory(){
        return this.category;
    }
    public int getID(){
        return this.id;
    }
    public Merchant getMerchant(){
        return this.merchant;
    }
    public void setOffer(int of){
        this.offer = of;
    }
    public void setPrice(float p){
        this.price = p;
    }
    public float getPrice(){
        return this.price;
    }
    public void setQuantity(int q){
        this.quantity = q;
    }
    @Override
    public String toString() {
        
    }
}


public class program{
    private static void printhomeMenu(){
        System.out.println("HOME");
        System.out.println("1. Enter as merchant");
        System.out.println("2. Enter as Customer");
        System.out.println("3. See user details");
        System.out.println("4. Company account Balance");
        System.out.println("5. Exit");
    }
    private static void executeMenu(AppUser au){

    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String[][] merchantNames = {{"Arya","Winterfell"},{"Jon","The Wall"},{"Ramsy","Bolton house"},{"Drogon","Velariya"},{"Jamie","Casterly Rock"}};
        String[][] costumerNames = {{"Frodo","Shire"},{"Bilbo","Elven home"},{"Thorin","Mountain"},{"Gandalf","The Unknown"},{"Sam","Shire with Frodo"}};
        ArrayList<Merchant> merchants = new ArrayList<Merchants>();
        ArrayList<Costumer> costumers = new ArrayList<Costumer>();

        for (String[] s : merchantNames) {
            Merchant temp = new Merchant(s[0], s[1]);
            merchants.add(temp);
        }
        for (String[] s : costumerNames) {
            Costumer temp = new Costumer(s[0], s[1]);
            costumers.add(temp);
        }
        printhomeMenu();
        String s[] ;
        int choice ;
        boolean going=true;
        while (going){
            s = br.readLine().trim().split("\\s+");
            choice = Integer.parseInt(s[0]);
            switch (choice){
                case 1:
                    System.out.println("Choose Merchant:");
                    for(int i=0; i<merchants.size();++i){
                        System.out.println((i+1) +"  "+merchants.get(i).getName() );

                    }
                    s = br.readLine().trim().split("\\s+");
                    choice = Integer.parseInt(s[0]);
                    executeMenu(merchants.get(choice-1));
                    
                case 2:
                    System.out.println("Choose Costumer:");
                    for(int i=0; i<costumers.size();++i){
                        System.out.println((i+1) +"  "+costumers.get(i).getName() );

                    }
                    s = br.readLine().trim().split("\\s+");
                    choice = Integer.parseInt(s[0]);
                    executeMenu(costumers.get(choice-1));
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Goodbye");
                    going=false;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
        
        
    }
}