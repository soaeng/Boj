package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    환승
    https://www.acmicpc.net/problem/5214
*/
public class Boj_5214 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + M];
        for (int i = 0; i < N + M; i++) graph.add(new ArrayList<>());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                int index = Integer.parseInt(st.nextToken()) - 1;
                graph.get(index).add(N + m);
                graph.get(N + m).add(index);
            }
        }

        queue.offer(0);
        visited[0] = true;
        int time = 0;
        N--;
        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                if (node == N) {
                    System.out.println(time / 2 + 1);
                    return;
                }
                for (int next : graph.get(node)) {
                    if (visited[next]) continue;
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
