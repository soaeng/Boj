package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    회의실 배정
    https://www.acmicpc.net/problem/1931
*/
public class Boj_1931_pq {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.offer(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        int count = 0;
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            while (!pq.isEmpty() && pq.peek()[0] < current[1]) pq.poll();
            count++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
