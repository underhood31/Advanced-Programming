import java.io.*;


class  CompressList{
    private class Node{
        int num;
        char ch;
		Node next;
		Node(int d){
			num=d;
            ch='0';
            next=null;
        }
        Node(char d){
			num=0;
            ch=d;
            next=null;
        }
        
    }
    private Node StartChar;
    private Node StartCount;
    private Node endChar;
    private Node endCount;
    CompressList(){
        StartChar=null;
        endChar=StartChar;
		StartCount=null;
        endCount=StartCount;
    }
    void addData(String d){
        char init = d.charAt(0);
        int count =1 ;
        for(int i =1; i<d.length();++i){
            if(d.charAt(i)==init){
                ++count;
            }
            else{
                if(StartChar==null){
                    this.StartChar = new Node(init);
                    this.endChar = this.StartChar;
                    this.StartCount = new Node(count);
                    this.endCount = this.StartCount;
                }
                else{
                    this.endChar.next = new Node(init);
                    this.endChar=this.endChar.next;
                    this.endCount.next = new Node(count);
                    this.endCount = this.endCount.next;
                     
                }
                init = d.charAt(i);
                count=1;
            }
        }
        if(StartChar==null){
            this.StartChar = new Node(init);
            this.endChar = this.StartChar;
            this.StartCount = new Node(count);
            this.endCount = this.StartCount;
        }
        else{
            this.endChar.next = new Node(init);
            this.endChar=this.endChar.next;
            this.endCount.next = new Node(count);
            this.endCount = this.endCount.next;
             
        }
    }
    void printList(){
        Node tempChar = this.StartChar;
        Node tempCount = this.StartCount;
        while(tempCount!=null){
            System.out.print(tempChar.ch);
            System.out.print(tempCount.num);
            tempChar=tempChar.next;
            tempCount=tempCount.next;

        }
        System.out.print("\n");
    }
    
}

public class q2{
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            CompressList comp = new CompressList();
            comp.addData(s);
            comp.printList();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}