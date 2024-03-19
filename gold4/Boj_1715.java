package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
    카드 정렬하기
    https://www.acmicpc.net/problem/1715
*/
public class Boj_1715 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }
        int ans = 0;
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            ans += a + b;
            pq.offer(a + b);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
