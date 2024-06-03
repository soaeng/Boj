package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    탈출
    https://www.acmicpc.net/problem/16397
*/
public class Boj_16397 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        System.out.println(bfs(N, T, G));
    }

    private static String bfs(int N, int T, int G) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[100000];
        queue.offer(N);
        visited[N] = true;
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int node = queue.poll();
                if (node == G) return Integer.toString(time);
                for (int b = 0; b < 2; b++) {
                    int next = node;
                    if (b == 0) next += 1;
                    else {
                        next *= 2;
                        if (next > 99999) break;
                        int length = (int) Math.log10(next);
                        next -= Math.pow(10, length);
                    }
                    if (next > 99999) continue;
                    if (visited[next]) continue;
                    queue.offer(next);
                    visited[next] = true;
                }
            }
            if (++time > T) return "ANG";
        }
        return "ANG";
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
