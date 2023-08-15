package silver2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    연결 요소의 개수
    https://www.acmicpc.net/problem/11724
    bfs: 117224KB / 576ms
    dfs: 115880KB / 576ms
*/
public class Boj_11724 {
    static int N, M, ans = 0;
    static int[][] map;
    static Queue<Integer> queue;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            map[u][v]++;
            map[v][u]++;
        }
        for (int i = 1; i <= N; i++) {
            if (map[i][i] != -1) {
                dfs(i);
                ans++;
            }
        }
        System.out.println(ans);
    }

//    static void bfs(int u) {
//        queue = new LinkedList<>();
//        queue.offer(u);
//        map[u][u] = -1;
//        while (!queue.isEmpty()) {
//            int n = queue.poll();
//            for (int i = 1; i <= N; i++) {
//                if (map[n][i] == 1 && map[i][i] != -1) {
//                    queue.offer(i);
//                    map[i][i] = -1;
//                }
//            }
//        }
//    }

    static void dfs(int u) {
        map[u][u] = -1;
        for (int i = 1; i <= N; i++) {
            if (map[u][i] == 1 && map[i][i] != -1) dfs(i);
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_11724().solution();
    }
}
