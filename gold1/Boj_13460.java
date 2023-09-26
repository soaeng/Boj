package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    구슬 탈출 2
    https://www.acmicpc.net/problem/Boj_13460
*/
public class Boj_13460 {
    static int N, M, ans;
    static char[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][][][] visited;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        int[] red = {}, blue = {};
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    red = new int[]{i, j};
                    map[i][j] = '.';
                }
                if (map[i][j] == 'B') {
                    blue = new int[]{i, j};
                    map[i][j] = '.';
                }
            }
        }
        bfs(red, blue);
        System.out.println(ans);
    }

    private static void bfs(int[] red, int[] blue) {
        Queue<Info> queue = new LinkedList<>();
        queue.offer(new Info(red, blue, 0));
        while (!queue.isEmpty()) {
            Info info = queue.poll();
            if (map[info.blue[0]][info.blue[1]] == 'O') continue;
            if (info.count > 10) {
                ans = -1;
                return;
            }
            if (map[info.red[0]][info.red[1]] == 'O') {
                ans = info.count;
                return;
            }
            for (int d = 0; d < 4; d++) {
                int[] nb = moveMarble(info.blue, d);
                int[] nr = moveMarble(info.red, d);
                if (nb[0] == nr[0] && nb[1] == nr[1] && nr[2] < 0) {
                    if (nr[3] < nb[3]) {
                        nb[0] -= deltas[d][0];
                        nb[1] -= deltas[d][1];
                    } else {
                        nr[0] -= deltas[d][0];
                        nr[1] -= deltas[d][1];
                    }
                }
                if (visited[nr[0]][nr[1]][nb[0]][nb[1]]) continue;
                queue.offer(new Info(nr, nb, info.count + 1));
                visited[nr[0]][nr[1]][nb[0]][nb[1]] = true;
            }
        }
        ans = -1;
    }

    private static int[] moveMarble(int[] marble, int d) {
        int nr = marble[0];
        int nc = marble[1];
        int goal = -1;
        int cnt = 0;
        while (true) {
            cnt++;
            nr += deltas[d][0];
            nc += deltas[d][1];
            if (map[nr][nc] == 'O') {
                goal = 1;
                break;
            }
            if (map[nr][nc] == '#') {
                nr -= deltas[d][0];
                nc -= deltas[d][1];
                break;
            }
        }
        return new int[]{nr, nc, goal, cnt};
    }

    private static class Info {
        int[] red, blue;
        int count;

        public Info(int[] red, int[] blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_13460().solution();
    }
}
