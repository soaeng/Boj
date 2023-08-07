package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
    최대 힙
    https://www.acmicpc.net/problem/11279
*/
public class Boj_11279 {
    static int N;
    static int[] numbers;
    static PriorityQueue<Integer> heap = new PriorityQueue<>(((o1, o2) -> o2 - o1));

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        int n;
        for (int i = 0; i < N; i++) {
            n = Integer.parseInt(br.readLine());
            if (n > 0) heap.offer(n);
            else {
                if (heap.size() == 0) sb.append(0).append("\n");
                else sb.append(heap.poll()).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_11279().solution();
    }
}
