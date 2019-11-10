package lb7;

import java.util.HashMap;

public class Fibonacci {                //FlyWeight


    private static HashMap<Integer, Fibonacci> instances = new HashMap<Integer, Fibonacci>();
    private final int n;
    private int result;
    private long timeTaken;
    private boolean print;
    private boolean calculated;
    private Fibonacci(int num, boolean pr){
        n = num;
        print = pr;
    }
    public static Fibonacci getInstance(int num, boolean pr){
        if(!Fibonacci.instances.containsKey(num)){
            instances.put(num,new Fibonacci(num,false));
        }
        instances.get(num).setPrint(pr);
        return instances.get(num);
    }
    private static int calculateFib(int n){
        if(n<2){
            return  n;
        }
        else{
            return calculateFib(n-1) + calculateFib(n-2);
        }
    }

    public void run() {
        long start = System.currentTimeMillis();
        if(!calculated){
            calculated=true;
            result=calculateFib(n);
        }
        long end = System.currentTimeMillis();
        timeTaken = end-start;
        if (print)
            System.out.println(this.getResult());
    }
    public  Result getResult(){
        return  new Result(this.n, this.result, timeTaken);
    }
    public void setPrint(boolean pr){
        print=pr;
    }
    @Override
    public String toString() {
        return Integer.toString(this.n);
    }
}
