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
    LinkedList(int data){
        this.start = new Node(data);
        this.end =this.start;
    }
    LinkedList(LinkedList l){
        this.start = l.start;
        this.end = l.end;
    }
    void addData(int d){
  
        this.end.next=new Node(d);
        this.end = this.end.next;
    }
    void display(){
        Node temp = this.start;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
        }
        System.out.print("\n");
    }

}
class q2{
    public static void main(String[] args) {
        LinkedList l1 = new LinkedList(1);
        l1.display();
        LinkedList l2 = new LinkedList(l1);
        l1.addData(2);
        l2.display();
    }   
}