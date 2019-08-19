import java.io.*;
import java.math.BigInteger;
import java.lang.Math;


class  LinkedList{
    private class Node{
        String data;
		Node next;
		Node(String d){
			data=d;
			next=null;
		}
    }
    private Node start;
    private Node end;
    private int size;
    LinkedList(){
        start=null;
        end=start;
		size=0;
    }
    void addData(String d){
        if(this.size==0){
            this.start = new Node(d);
            this.end =this.start;
            ++this.size;
        }
        else{
            this.end.next=new Node(d);
            this.end = this.end.next;
            ++this.size;
        }
    }
    void printList(){
        Node temp = this.start;
        String out = "";
        while(temp!=null){
            out+=(temp.data+"x");
            temp=temp.next;
        }
        out = out.substring(0,out.length()-1);
        System.out.println(out);
    }
  
}

class primeF{
    private String data;
    private LinkedList factors = new LinkedList();
    primeF(String s){
        this.data=s;
        calFacts();
    }
    private void calFacts(){
        BigInteger initLen = BigInteger.valueOf(this.data.length());
        BigInteger len = BigInteger.valueOf(this.data.length());
        while(len.mod(BigInteger.TWO)==BigInteger.ZERO){
            len = len.divide(BigInteger.TWO);
            this.factors.addData("2");
        }
        BigInteger fact = BigInteger.valueOf(3);

        while(fact.compareTo(initLen.sqrt())<=0){
        
            while(len.mod(fact)==BigInteger.ZERO){
                this.factors.addData(fact.toString());
                len = len.divide(fact);
            }
            fact = fact.add(BigInteger.TWO);
        }

        if(len.compareTo(BigInteger.TWO)>0){
            this.factors.addData(len.toString());
        }

    }
    void printFs(){
       factors.printList();
    }
}

public class q3{
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            primeF str = new primeF(s);
            str.printFs();      
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    }
}