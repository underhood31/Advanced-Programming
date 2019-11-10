package lb7;

public class Result {
    private int result;
    private long time;
    private int num;
    public Result(int position, int calculatedResult, long timeTaken){
        result =calculatedResult;
        time =timeTaken;
        num=position;
    }

    @Override
    public String toString() {
        return Integer.toString(num)+" has result: "+Integer.toString(result) + " in " + Long.toString(time) + " milliseconds";

    }
}
