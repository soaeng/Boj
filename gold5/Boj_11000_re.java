package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    강의실 배정
    https://www.acmicpc.net/problem/11000
*/
public class Boj_11000_re {
    static int N;
    static int[][] list;
    static PriorityQueue<Integer> pq;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new int[N][2];
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            list[i] = new int[]{s, t};
        }
        Arrays.sort(list, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });

        for (int[] nn : list) {
            if (!pq.isEmpty() && pq.peek() <= nn[0]) pq.poll();
            pq.offer(nn[1]);
        }
        System.out.println(pq.size());
    }

    public static void main(String[] args) throws Exception {
        new Boj_11000_re().solution();
    }
}
