package gold1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    구슬 탈출 3
    https://www.acmicpc.net/problem/15644
*/
public class Boj_15644 {
    static int N, M;
    static char[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        Marble red = null, blue = null;
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                switch (map[i][j]) {
                    case 'R':
                        red = new Marble(i, j);
                        break;
                    case 'B':
                        blue = new Marble(i, j);
                        break;
                }
            }
        }
        Info result = bfs(red, blue);
        StringBuilder sb = new StringBuilder();
        if (result != null) {
            sb.append(result.count).append("\n");
            sb.append(result.dir);
        } else sb.append(-1);
        System.out.println(sb);
    }

    private static Info bfs(Marble r, Marble b) {
        Queue<Info> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][N][M];
        String dir = "UDLR";
        queue.offer(new Info(r, b, 0, ""));
        while (!queue.isEmpty()) {
            Info info = queue.poll();
            // 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력
            if (info.count > 10) return null;
            // 파란 구슬이 구멍에 빠지면 실패
            if (map[info.blue.r][info.blue.c] == 'O') continue;
            // 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지
            if (map[info.red.r][info.red.c] == 'O') return info;
            for (int d = 0; d < 4; d++) {
                Marble red = moveMarble(info.red, d);
                Marble blue = moveMarble(info.blue, d);
                // 빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다
                if (red.r == blue.r && red.c == blue.c && !red.goal) {
                    if (red.count < blue.count) {
                        blue.r -= deltas[d][0];
                        blue.c -= deltas[d][1];
                    } else {
                        red.r -= deltas[d][0];
                        red.c -= deltas[d][1];
                    }
                }
                if (visited[red.r][red.c][blue.r][blue.c]) continue;
                queue.offer(new Info(red, blue, info.count + 1, info.dir + dir.charAt(d)));
                visited[red.r][red.c][blue.r][blue.c] = true;
            }
        }
        return null;
    }

    private static Marble moveMarble(Marble marble, int d) {
        int nr = marble.r;
        int nc = marble.c;
        int count = 0;
        while (true) {
            count++;
            nr += deltas[d][0];
            nc += deltas[d][1];
            if (map[nr][nc] == 'O') return new Marble(nr, nc, count, true);
            if (map[nr][nc] == '#') return new Marble(nr - deltas[d][0], nc - deltas[d][1], count, false);
        }
    }

    private static class Marble {
        int r, c, count;
        boolean goal;

        public Marble(int r, int c, int count, boolean goal) {
            this.r = r;
            this.c = c;
            this.count = count;
            this.goal = goal;
        }

        public Marble(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + count + ", " + goal + ")";
        }
    }

    private static class Info {
        Marble red, blue;
        int count;
        String dir;

        public Info(Marble red, Marble blue, int count, String dir) {
            this.red = red;
            this.blue = blue;
            this.count = count;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return red + ", " + blue + ", " + count;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_15644().solution();
    }
}
