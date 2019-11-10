package lb7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public  class  Producer implements Runnable{
    private Queue<Fibonacci> pending;
    private Queue<Result> results;

    public Producer(Queue<Fibonacci> pending, Queue<Result> results){
        this.pending = pending;
        this.results = results;
    }
    public void getNums() throws IOException {
        String s[] = {""};
        try{
//            System.out.print("\nEnter the number: ");
            s = Main.br.readLine().trim().split("\\s+");
            int num = Integer.parseInt(s[0]);
            if (num<0){
                Main.h.handleCalls(5);
            }
            boolean pr = false;
            try {
                pr = Boolean.parseBoolean(s[1]);
            }catch (Exception e){
                pr=false;
            }
            synchronized (pending){
                pending.add(Fibonacci.getInstance(num,pr));
            }

        }catch (Exception ex){
            if("pending".equals(s[0])){
                System.out.println("Pending: " + pending);
            }
            else
                System.out.println("Invalid Input");
        }

    }
    @Override
    public void run() {
        while (true){
            try {
                getNums();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
