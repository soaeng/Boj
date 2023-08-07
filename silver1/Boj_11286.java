package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
    절댓값 힙
    https://www.acmicpc.net/problem/11286
*/
public class Boj_11286 {
    static int N;
    static PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> {
        if (Math.abs(o1) == Math.abs(o2)) return o1 - o2;
        else return Math.abs(o1) - Math.abs(o2);
    });

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        int n;
        for (int i = 0; i < N; i++) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) {
                if (heap.isEmpty()) sb.append(0).append("\n");
                else sb.append(heap.poll()).append("\n");
            } else {
                heap.offer(n);
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_11286().solution();
    }
}
