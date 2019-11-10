package lb7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Handler {
    private static volatile Queue<Fibonacci> pending = new LinkedList<Fibonacci>();
    private static volatile Queue<Result> results = new LinkedList<Result>();
    private Producer producer;
    private static ArrayList<Thread> threads;

    public void handleCalls(int n) throws IOException{
        switch (n){
            case 1:         // Creates Consumer threads
                System.out.print("Enter the number of consumers: ");
                threads = new ArrayList<Thread>();
                int cns = Integer.parseInt(Main.br.readLine().trim().split("\\s+")[0]);
                for (int i = 0; i < cns; i++) {
                    threads.add(new Thread(new Consumer(Handler.pending, Handler.results)));
                }
                break;
            case 2:           //Starts consumer threads
                for (Thread th : threads) {
                    th.start();
                }
                break;
            case 3:   //Create Producer threads
                producer = new Producer(Handler.pending, Handler.results);
                break;
            case 4:     //runs producer threads
                System.out.println("Enter the number to get output==>");

                try{
                    producer.run();
                }catch (Exception e){
                    System.out.println("producer not initialised");
                }
                break;
            case 5:
                System.out.println("Terminating...");
                boolean cont =true;
                while(cont){
                    cont = !pending.isEmpty();
                }
                while(!results.isEmpty()){
                    System.out.println(results.poll());
                }
                System.exit(0);

        }
    }
//    public static void main(String[] args) throws IOException {
//        System.out.print("Enter the number of consumers: ");
//        threads = new ArrayList<Thread>();
//        int cns = Integer.parseInt(Main.br.readLine().trim().split("\\s+")[0]);
//        for (int i = 0; i < cns; i++) {
//            threads.add(new Thread(new Consumer(Handler.pending, Handler.results)));
//        }
//        Producer producer = new Producer(Handler.pending, Handler.results);
//        for (Thread th : threads) {
//            th.start();
//        }
//        System.out.println("Enter the number to get output==>");
//        producer.run();
//    }
//    public static void terminate(){
//        System.out.println("Terminating...");
//        boolean cont =true;
//        while(cont){
//            cont = !pending.isEmpty();
//        }
//        while(!results.isEmpty()){
//            System.out.println(results.poll());
//        }
//        System.exit(0);
//    }
}
