import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Runner{
    public static  final String MARATHON_CATEGORY1 = "Great Delhi Run";
    public static  final String MARATHON_CATEGORY2 = "Open 10K Run";
    public static  final String MARATHON_CATEGORY3 = "Half Marathon";
    
    public static  final String MONEY_CATEGORY3P1 = "Rs. 2,80,000/-";
    public static  final String MONEY_CATEGORY3P2 = "Rs. 2,10,000/-";
    public static  final String MONEY_CATEGORY2P1 = "Rs. 1,90,000/-";
    public static  final String MONEY_CATEGORY2P2 = "Rs. 1,50,000/-";
    public static  final String MONEY_CATEGORY1P1 = "Rs. 1,35,000/-";
    public static  final String MONEY_CATEGORY1P2 = "Rs. 1,15,000/-";
    
    private String name;
    private int minutes;
    private String category;
    Runner(String name, int minutes, String category){
        this.name = name;
        this.minutes = minutes;
        this.category = category;
    }
    public String getName(){
        return this.name;
    }
    public String getCategory(){
        return this.category;
    }
    public int getMinutes(){
        return this.minutes;
    }
}
class  LinkedList{
    private class Node{
        private Runner data;
		private Node next;
		Node(Runner d){
			this.data=d;
			this.next=null;
        }
        public Node getNext(){
            return this.next;
        }
        public void setNext(Node n){
            this.next=n;
        }
        public Runner getData(){
            return this.data;
        }
    }
    private Node start;
    private Node end;
    private int size;
    private  Runner winner1Cat1;
    private  Runner winner1Cat2;
    private  Runner winner1Cat3;
    private  Runner winner2Cat1;
    private  Runner winner2Cat2;
    private  Runner winner2Cat3;    
    LinkedList(){
        start=null;
        end=start;
		size=0;
    }
    
    public Runner getWinner1Cat1(){
        return this.winner1Cat1;
    }
    public Runner getWinner2Cat1(){
        return this.winner2Cat1;
    }
    public Runner getWinner1Cat2(){
        return this.winner1Cat2;
    }
    public Runner getWinner2Cat2(){
        return this.winner2Cat2;
    }
    public Runner getWinner1Cat3(){
        return this.winner1Cat3;
    }
    public Runner getWinner2Cat3(){
        return this.winner2Cat3;
    }


    public void addData(Runner d){
        if(this.size==0){
            this.start = new Node(d);
            this.end =this.start;
            ++this.size;

            if(d.getCategory() == Runner.MARATHON_CATEGORY1){
                this.winner1Cat1 = d;
            }
            else if(d.getCategory()==Runner.MARATHON_CATEGORY2){
                this.winner1Cat2 = d;
            }
            else{
                this.winner1Cat2 = d;
            }
        }
        else{
            this.end.setNext(new Node(d));
            this.end = this.end.getNext();
            ++this.size;

            if(d.getCategory() == Runner.MARATHON_CATEGORY1){
                if(this.winner1Cat1==null){
                    this.winner1Cat1 = d;
                }
                else{
                    if(d.getMinutes()<this.winner1Cat1.getMinutes()){
                        this.winner2Cat1 = this.winner1Cat1;
                        this.winner1Cat1 = d;
                    }    
                    else{
                        if(this.winner2Cat1 == null){
                            this.winner2Cat1=d;
                        }
                        else if(d.getMinutes()<this.winner2Cat1.getMinutes()){
                            this.winner2Cat1 = d;
                        } 
                    }
                }
            }
            else if(d.getCategory()==Runner.MARATHON_CATEGORY2){
                if(this.winner1Cat2==null){
                    this.winner1Cat2 = d;
                }
                else{
                    if(d.getMinutes()<this.winner1Cat2.getMinutes()){
                        this.winner2Cat2 = this.winner1Cat2;
                        this.winner1Cat2 = d;
                    }    
                    else{
                        if(this.winner2Cat2 == null){
                            this.winner2Cat2=d;
                        }
                        else if(d.getMinutes()<this.winner2Cat2.getMinutes()){
                            this.winner2Cat2 = d;
                        } 
                    }
                }

            }
            else{
                if(this.winner1Cat3==null){
                    this.winner1Cat3 = d;
                }
                else{
                    if(d.getMinutes()<this.winner1Cat3.getMinutes()){
                        this.winner2Cat3 = this.winner1Cat3;
                        this.winner1Cat3 = d;
                    }    
                    else{
                        if(this.winner2Cat3 == null){
                            this.winner2Cat3=d;
                        }
                        else if(d.getMinutes()<this.winner2Cat3.getMinutes()){
                            this.winner2Cat3 = d;
                        } 
                    }
                }

            }
        }
        
    }
}

class assignment{

    private static JTextField nameField, minuteField;
    private static JPanel namePanel, minutePanel, categoryPanel, controlPanel;
    private static JRadioButton cat1,cat2,cat3;
    private static ButtonGroup allCategories;
    private static JButton bCancel, bNext, bWinners;
    private static LinkedList runners;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Final Assignment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel p_main = new JPanel();
        p_main.setLayout(new BoxLayout(p_main,BoxLayout.Y_AXIS));
        ///////////////////////////////////////////////
        namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        nameField = new JTextField(15);
        nameField.setText("");
        namePanel.add(new Label("Enter Name: "));
        namePanel.add(nameField);
        ///////////////////////////////////////////////
        minutePanel = new JPanel();
        minutePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        minuteField = new JTextField(15);
        minuteField.setText("");        
        minutePanel.add(new Label("Enter time(in minutes): "));
        minutePanel.add(minuteField);
        ///////////////////////////////////////////////
        categoryPanel = new JPanel();
        categoryPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        categoryPanel.add(new Label("Select Category"));
        cat1 = new JRadioButton(Runner.MARATHON_CATEGORY1);
        cat1.setSelected(true);
        cat2 = new JRadioButton(Runner.MARATHON_CATEGORY2);
        cat3 = new JRadioButton(Runner.MARATHON_CATEGORY3);
        allCategories = new ButtonGroup();
        allCategories.add(cat1);
        allCategories.add(cat2);
        allCategories.add(cat3);
        categoryPanel.add(cat1);
        categoryPanel.add(cat2);
        categoryPanel.add(cat3);
        ///////////////////////////////////////////////
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        runners = new LinkedList();
        bNext = new JButton("Next");
        bNext.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                if (nameField.getText().equals("") || minuteField.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Empety Fields not allowed");
                } else {
                    try {
                        String category;
                        if (cat1.isSelected()) {
                            category = Runner.MARATHON_CATEGORY1;
                        } 
                        else if(cat2.isSelected()) {
                            category = Runner.MARATHON_CATEGORY2;
                        }
                        else{
                            category = Runner.MARATHON_CATEGORY3;
                        }
                        Runner newEntry = new Runner(nameField.getText(), Integer.parseInt(minuteField.getText()), category);
                        nameField.setText("");
                        minuteField.setText("");
                        cat1.setSelected(true);
                        runners.addData(newEntry);
                        
                    } catch (Exception f) {
                        JOptionPane.showMessageDialog(frame, "Error: "+f.toString());
                    }
                    
                }
                // Runner newEntry = new Runner(name, minutes, category)
            }
        });
        bWinners = new JButton("Calculate Winners");
        bWinners.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    System.out.println(runners.getWinner1Cat1().getName()+"\n"+runners.getWinner1Cat2().getName()+"\n"+runners.getWinner1Cat3().getName());
                    JFrame  Fwinners = new JFrame();
                    ///////////////////
                    JPanel Pwinners =new JPanel();
                    Pwinners.setLayout(new BoxLayout(Pwinners,BoxLayout.Y_AXIS));
                    
                    JPanel Pcat1W1 = new JPanel();
                    Pcat1W1.setLayout(new FlowLayout(FlowLayout.CENTER));
                    Pcat1W1.add(new Label(Runner.MARATHON_CATEGORY1+" winner "+runners.getWinner1Cat1().getName() + " won prize money of " + Runner.MONEY_CATEGORY1P1));
                    Pwinners.add(Pcat1W1);

                    if(runners.getWinner2Cat1()!=null){
                        JPanel Pcat1W2 = new JPanel();
                        Pcat1W1.setLayout(new FlowLayout(FlowLayout.CENTER));
                        Pcat1W2.add(new Label(Runner.MARATHON_CATEGORY1+" runner up "+runners.getWinner2Cat1().getName() + " won prize money of " + Runner.MONEY_CATEGORY1P2));
                        Pwinners.add(Pcat1W2);
                    }
                    
                    JPanel Pcat2W1 = new JPanel();
                    Pcat2W1.setLayout(new FlowLayout(FlowLayout.CENTER));
                    Pcat2W1.add(new Label(Runner.MARATHON_CATEGORY2+" winner "+runners.getWinner1Cat2().getName() + " won prize money of " + Runner.MONEY_CATEGORY2P1));
                    Pwinners.add(Pcat2W1);

                    if(runners.getWinner2Cat2()!=null){
                        JPanel Pcat2W2 = new JPanel();
                        Pcat2W2.setLayout(new FlowLayout(FlowLayout.CENTER));
                        Pcat2W2.add(new Label(Runner.MARATHON_CATEGORY2+" runner up  "+runners.getWinner2Cat2().getName() + " won prize money of " + Runner.MONEY_CATEGORY2P2));
                        Pwinners.add(Pcat2W2);
                    }

                    JPanel Pcat3W1 = new JPanel();
                    Pcat3W1.setLayout(new FlowLayout(FlowLayout.CENTER));
                    Pcat3W1.add(new Label(Runner.MARATHON_CATEGORY3+" winner "+runners.getWinner1Cat3().getName() + " won prize money of " + Runner.MONEY_CATEGORY3P1));
                    Pwinners.add(Pcat3W1);

                    if(runners.getWinner2Cat3()!=null){
                        JPanel Pcat3W2 = new JPanel();
                        Pcat3W2.setLayout(new FlowLayout(FlowLayout.CENTER));
                        Pcat3W2.add(new Label(Runner.MARATHON_CATEGORY3+" runner up "+runners.getWinner2Cat3().getName() + " won prize money of " + Runner.MONEY_CATEGORY3P2));
                        Pwinners.add(Pcat3W2);
                    }


                    JButton close = new JButton("Close");
                    close.addActionListener(new ActionListener(){
                    
                        @Override
                        public void actionPerformed(ActionEvent f) {
                            Fwinners.dispose();
                        }
                    });
                    Pwinners.add(close);
                    
                    //////////////////
                    Fwinners.add(Pwinners);
                    Fwinners.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    Fwinners.setSize(400,400);
                    Fwinners.setVisible(true);    
                } catch (Exception f) {
                    JOptionPane.showMessageDialog(frame, "More Data Required: " );                    
                }
                
            }
        });
        bCancel = new JButton("Cancel");
        bCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });

        controlPanel.add(bWinners);
        controlPanel.add(bNext);
        controlPanel.add(bCancel);
        //////////////////////////////////////////////
        p_main.add(namePanel);
        p_main.add(minutePanel);
        p_main.add(categoryPanel);
        p_main.add(controlPanel);
        //////////////////////////////////////////////
        frame.add(p_main);
        frame.setSize(600,400);
        frame.setVisible(true); 
    }
}