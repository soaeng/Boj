package silver3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
    시리얼 번호
    https://www.acmicpc.net/problem/1431
*/
public class Boj_1431 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.length() == o2.length()) {
                if (getSum(o1) == getSum(o2)) return o1.compareTo(o2);
                else return getSum(o1) - getSum(o2);
            } else {
                return o1.length() - o2.length();
            }
        });
        for (int i = 0; i < N; i++) {
            pq.offer(br.readLine());
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }
        System.out.print(sb);
    }


    private static int getSum(String serial) {
        int count = 0;
        for (char ch : serial.toCharArray()) {
            if (Character.isDigit(ch)) count += Character.getNumericValue(ch);
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
