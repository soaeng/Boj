package gold2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    불켜기
    https://www.acmicpc.net/problem/11967
*/
public class Boj_11967 {
    static int N;
    static ArrayList<Room>[][] switches;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        switches = new ArrayList[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                switches[r][c] = new ArrayList<>();
            }
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            switches[x][y].add(new Room(a, b));
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Room> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        boolean[][] light = new boolean[N][N];
        queue.offer(new Room(0, 0));
        visited[0][0] = true;
        light[0][0] = true;
        int count = 1;
        while (!queue.isEmpty()) {
            Room room = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = room.r + deltas[d][0];
                int nc = room.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (visited[nr][nc]) continue;
                if (!light[nr][nc]) continue;
                queue.offer(new Room(nr, nc));
                visited[nr][nc] = true;
            }
            for (Room r : switches[room.r][room.c]) {
                if (!light[r.r][r.c]) count++;
                light[r.r][r.c] = true;
                if (visited[r.r][r.c]) continue;
                for (int d = 0; d < 4; d++) {
                    int nr = r.r + deltas[d][0];
                    int nc = r.c + deltas[d][1];
                    if (isOutOfRange(nr, nc)) continue;
                    if (visited[nr][nc]) {
                        visited[r.r][r.c] = true;
                        queue.offer(new Room(r.r, r.c));
                        break;
                    }
                }
            }
        }
        return count;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static class Room {
        int r, c;

        public Room(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
