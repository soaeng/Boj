package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    요세푸스 문제
    https://www.acmicpc.net/problem/1158
 */
public class Boj_1158 {
    static int N, K;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Queue<Integer> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                if (j == K - 1) sb.append(queue.poll()).append(", ");
                else queue.offer(queue.poll());
            }
        }
        sb.delete(sb.length() - 2, sb.length()).append(">");
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Boj_1158().solution();
    }
}
