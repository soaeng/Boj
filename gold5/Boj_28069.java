package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    김밥천국의 계단
    https://www.acmicpc.net/problem/28069
*/
public class Boj_28069 {
    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new ArrayDeque<>();
        int[] visited = new int[N + 1];
        queue.offer(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == N && visited[node] <= K) {
                System.out.println("minigimbob");
                return;
            }
            if (visited[node] == K) continue;
            int nn = node + 1;
            if (nn <= N && visited[nn] == 0) {
                queue.offer(nn);
                visited[nn] = visited[node] + 1;
            }
            nn = node + node / 2;
            if (nn <= N && visited[nn] == 0) {
                queue.offer(nn);
                visited[nn] = visited[node] + 1;
            }
        }
        System.out.println("water");
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
