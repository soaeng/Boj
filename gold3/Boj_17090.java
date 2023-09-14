package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    미로 탈출하기
    https://www.acmicpc.net/problem/17090
*/
public class Boj_17090 {
    static int N, M, count = 1, total = 0;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] maze, visited;
    static boolean[] escape;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new int[N][M];
        visited = new int[N][M];
        escape = new boolean[N * M + 1];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = str.charAt(j);
                switch (c) {
                    case 'U':
                        maze[i][j] = 0;
                        break;
                    case 'D':
                        maze[i][j] = 1;
                        break;
                    case 'L':
                        maze[i][j] = 2;
                        break;
                    case 'R':
                        maze[i][j] = 3;
                        break;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] != 0) continue;
                dfs(i, j, 0);
                count++;
            }
        }
        System.out.println(total);
    }

    private static void dfs(int r, int c, int cnt) {
        if (r < 0 || r >= N || c < 0 || c >= M) {
            escape[count] = true;
            total += cnt;
            return;
        }
        if (visited[r][c] != 0) {
            if (escape[visited[r][c]]) {
                escape[count] = true;
                total += cnt;
            }
            return;
        }
        visited[r][c] = count;
        dfs(r + deltas[maze[r][c]][0], c + deltas[maze[r][c]][1], cnt + 1);
    }

    public static void main(String[] args) throws Exception {
        new Boj_17090().solution();
    }
}
