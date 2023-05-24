import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Integer, Integer> distribution;
    private static int sum;

    private static int amountGames = 20;
    private static int sampleSize = 1000000;
    private static double rtp = 6.9;

    public static void main(String[] args) {
        fillDistribution();
        double m = 0;
        for (int k = 0; k < sampleSize; k++) {
            if (getRTP() >= rtp) {
                m++;
            }
        }
        System.out.println(m / sampleSize);
    }

    private static void fillDistribution() {
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

    private static double getRTP() {
        double rtp = 0;
        for (int i = 0; i < amountGames; i++) {
            rtp += getGame();
        }
        rtp /= amountGames;
        return rtp;
    }
}