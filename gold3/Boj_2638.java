package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    치즈
    https://www.acmicpc.net/problem/2638
*/
public class Boj_2638 {
    static int N, M, time, count;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;
    static ArrayList<Node> cheese;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        cheese = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) cheese.add(new Node(i, j));
            }
        }
        count = cheese.size();
        while (count > 0) {
            visited = new boolean[N][M];
            checkOutside(0, 0);
            meltCheese();
            time++;
        }
        System.out.println(time);
    }

    private static void meltCheese() {
        int size = cheese.size();
        for (int i = 0; i < size; i++) {
            Node node = cheese.get(i);
            if (checkMelt(node.r, node.c)) {
                map[node.r][node.c] = 3;
                cheese.remove(node);
                count--;
                i--;
                size--;
            }
        }
    }

    private static boolean checkMelt(int r, int c) {
        int count = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (checkRange(nr, nc) && map[nr][nc] == 2) count++;
        }
        return count >= 2;
    }

    private static void checkOutside(int r, int c) {
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (checkRange(nr, nc) && !visited[nr][nc] && map[nr][nc] != 1) {
                map[nr][nc] = 2;
                checkOutside(nr, nc);
            }
        }
    }

    private static boolean checkRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2638().solution();
    }
}
