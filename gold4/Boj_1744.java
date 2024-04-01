package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
    수 묶기
    https://www.acmicpc.net/problem/1744
*/
public class Boj_1744 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> negative = new PriorityQueue<>();
        PriorityQueue<Integer> positive = new PriorityQueue<>((o1, o2) -> o2 - o1);
        boolean zero = false;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) positive.offer(num);
            else if (num < 0) negative.offer(num);
            else zero = true;
        }
        while (negative.size() >= 2) {
            sum += (negative.poll() * negative.poll());
        }
        if (!negative.isEmpty()) {
            if (!zero) {
                if (positive.contains(Math.abs(negative.peek()))) {
                    positive.remove(Math.abs(negative.poll()));
                }
            } else negative.poll();
            if (!negative.isEmpty()) sum += negative.poll();
        }
        while (positive.size() >= 2) {
            int num = positive.poll();
            if (num > 1) {
                if (positive.peek() > 1) sum += num * positive.poll();
                else sum += num;
            } else sum += num;
        }
        if (!positive.isEmpty()) sum += positive.poll();
        System.out.println(sum);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
