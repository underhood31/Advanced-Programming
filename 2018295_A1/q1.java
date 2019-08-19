import java.io.*;


class  LinkedList{
    private class Node{
        int data;
		Node next;
		Node(int d){
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
    void addData(int d){
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
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
        System.out.print("\n");
    }
    void findLoop(int node){
        if(node<this.size && node!=0){
            System.out.print("True" + "\n" + (this.size-node+1) + "\n");
        }
        else{
            System.out.print("False" + "\n" + (0) + "\n");
        }
    }
    void deleteGiven(int d){
        if(d==0){
            return;
        }
        else if (d==1){
            this.start=this.start.next;
        }
        else{
            int pos=2;
            Node prev = this.start;
            Node now = this.start.next;
            while(now!=null){
                if(pos==d){
                    prev.next = now.next;
                    break;
                }
                prev=now;
                now=now.next;
                ++pos;
            }
        }
        
    }

}

public class q1 {
    public static void main(String[] args)  {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String[] s = br.readLine().trim().split("\\s+");
            int n = Integer.parseInt(s[0]);              
            for(int i = 0; i<n;++i){
                
                s = br.readLine().trim().split("\\s+");
                int size = Integer.parseInt(s[0]);              
                s = br.readLine().trim().split("\\s+");
                
                LinkedList list = new LinkedList();
                for(int j=0; j<size; ++j){
                    list.addData(Integer.parseInt(s[j]));
                }
                
                s = br.readLine().trim().split("\\s+");
                int loop = Integer.parseInt(s[0]);              
                list.findLoop(loop);
                list.deleteGiven(loop);
                list.printList();
                
            }      
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}