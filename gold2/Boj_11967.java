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
    static Map<Integer, ArrayList<Integer>> map;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int key = (Integer.parseInt(st.nextToken()) - 1) * N + (Integer.parseInt(st.nextToken()) - 1);
            ArrayList<Integer> list = map.getOrDefault(key, new ArrayList<>());
            list.add((Integer.parseInt(st.nextToken()) - 1) * N + (Integer.parseInt(st.nextToken()) - 1));
            map.put(key, list);
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Room> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][N][N];
        queue.offer(new Room(0, 0));
        visited[1][0][0] = true;
        int count = 1;
        while (!queue.isEmpty()) {
            Room room = queue.poll();
            // 불 다 켜기
            if (map.get(room.id) != null) {
                visited[0] = new boolean[N][N];
                visited[0][room.r][room.c] = true;
                for (int id : map.get(room.id)) {
                    int r = id / N;
                    int c = id % N;
                    if (visited[1][r][c]) continue;
                    visited[1][r][c] = true;
                    count++;
                }
                map.remove(room.id);
            }
            for (int d = 0; d < 4; d++) {
                int nr = room.r + deltas[d][0];
                int nc = room.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (visited[0][nr][nc]) continue;
                if (!visited[1][nr][nc]) continue;
                queue.offer(new Room(nr, nc));
                visited[0][nr][nc] = true;
            }
        }
        return count;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static class Room {
        int id, r, c;

        public Room(int r, int c) {
            this.id = r * N + c;
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "(" + id + ", " + r + ", " + c + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
