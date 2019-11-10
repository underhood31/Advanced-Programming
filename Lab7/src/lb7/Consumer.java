package lb7;

import java.util.Queue;

public  class Consumer implements  Runnable{
    private Queue<Fibonacci> pending;
    private Queue<Result> results;

    public Consumer(Queue<Fibonacci> pending, Queue<Result> results){
        this.pending = pending;
        this.results = results;
    }

    @Override
    public void run() {
        while (true){
            CalculateResult();
        }
    }

    public void CalculateResult(){
        Fibonacci toGive;

        try{
            synchronized (pending) {
                toGive = pending.remove();
            }
            toGive.run();
            results.add(toGive.getResult());
        }
        catch (Exception ex){
        }

    }
}
