package gold5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    로봇 청소기
    https://www.acmicpc.net/problem/14503
*/
public class Boj_14503 {
    static int N, M, ans = 0;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북동남서
    static Robot robot;
    static Queue<Robot> queue = new LinkedList<>();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(br.readLine());
        robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
        System.out.println(ans);
    }

    static void bfs() {
        queue.add(robot);
        while (!queue.isEmpty()) {
            Robot current = queue.poll();
            if (map[current.r][current.c] == 0) {
                map[current.r][current.c] = -1;
                ans++;
            }

            if (!checkBlank(current.r, current.c)) {
                int nr = current.r + deltas[(current.d + 2) % 4][0];
                int nc = current.c + deltas[(current.d + 2) % 4][1];
                if (map[nr][nc] == 1) break;
                else queue.offer(new Robot(nr, nc, current.d));
            } else {
                while (true) {
                    current.d = changeDirection(current.d);
                    int nr = current.r + deltas[current.d][0];
                    int nc = current.c + deltas[current.d][1];
                    if (map[nr][nc] == 0) {
                        queue.add(new Robot(nr, nc, current.d));
                        break;
                    }
                }
            }
        }
    }

    static boolean checkBlank(int r, int c) {
        return (map[r - 1][c] == 0 || map[r + 1][c] == 0 || map[r][c - 1] == 0 || map[r][c + 1] == 0);
    }

    static public int changeDirection(int d) {
        return d - 1 >= 0 ? d - 1 : 3;
    }

    static class Robot {
        int r, c, d;

        public Robot(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_14503().solution();
    }
}
