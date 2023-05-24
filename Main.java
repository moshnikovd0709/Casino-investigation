import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Integer, Integer> distribution;
    private static int sum;
    private static double rtp = 6.9;

    public static void main(String[] args) {
        distribution = new HashMap<>();
        distribution.put(0, 1403);
        distribution.put(1, 321);
        distribution.put(2, 113);
        distribution.put(5, 80);
        distribution.put(10, 24);
        distribution.put(20, 11);
        distribution.put(50, 8);
        distribution.put(100, 1);
        sum = 0;
        for (int i : distribution.keySet()) {
            sum += distribution.get(i);
        }
        ArrayList<Integer> statistics = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            statistics.add(getGame());
        }
        double good = 0;
        for (int k = 0; k < 1000000; k++) {
            ArrayList<Integer> indexes = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                int rnd;
                do {
                    rnd = (int) (Math.random() * 1000000);
                } while (indexes.contains(rnd));
                indexes.add(rnd);
            }
            if (getRTP(statistics, indexes) >= rtp) {
                good++;
            }
        }
        System.out.println(good / 1000000);
    }

    private static int getGame() {
        int rnd = (int) (Math.random() * sum);
        int cur = 0;
        for (int i : distribution.keySet()) {
            cur += distribution.get(i);
            if (rnd < cur) {
                return i;
            }
        }
        return 0;
    }

    private static double getRTP(ArrayList<Integer> statistics, ArrayList<Integer> indexes) {
        double rtp = 0;
        for (int i : indexes) {
            rtp += statistics.get(i);
        }
        rtp /= 20;
        return rtp;
    }
}