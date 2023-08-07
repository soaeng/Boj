package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    스타트링크
    https://www.acmicpc.net/problem/5014
*/
public class Boj_5014 {
    static int F, S, G, U, D, ans = -1;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        if (S == G) ans = 0;
        else bfs(S);
        System.out.println(ans == -1 ? "use the stairs" : ans);
    }

    static void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[F + 1];
        queue.offer(s);
        visited[s] = 1;
        int n;
        while (!queue.isEmpty()) {
            n = queue.poll();
            if (n == G) {
                ans = visited[n] - 1;
                return;
            }
            if (n + U <= F && visited[n + U] == 0) {
                queue.offer(n + U);
                visited[n + U] = visited[n] + 1;
            }
            if (n - D > 0 && visited[n - D] == 0) {
                queue.offer(n - D);
                visited[n - D] = visited[n] + 1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_5014().solution();
    }
}
