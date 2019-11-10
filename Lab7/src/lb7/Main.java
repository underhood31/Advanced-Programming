package lb7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static Handler h;
    public static void main(String[] args) throws IOException {
        h = new Handler();
        h.handleCalls(1);
        h.handleCalls(3);
        h.handleCalls(2);
        h.handleCalls(4);
    }




}
