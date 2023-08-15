package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    영역 구하기
    https://www.acmicpc.net/problem/2583
*/
public class Boj_2583 {
    static int M, N, K;
    static int[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static Queue<Node> queue;
    static ArrayList<Integer> list = new ArrayList<>();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int r = y1; r < y2; r++) {
                for (int c = x1; c < x2; c++) map[r][c] = 1;
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) bfs(i, j);
            }
        }
        sb.append(list.size()).append("\n");
        Collections.sort(list);
        for (int n : list) sb.append(n).append(" ");
        System.out.println(sb);
    }

    private static void bfs(int r, int c) {
        queue = new LinkedList<>();
        queue.offer(new Node(r, c));
        map[r][c] = -1;
        int area = 1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr >= 0 && nr < M && nc >= 0 && nc < N && map[nr][nc] == 0) {
                    map[nr][nc] = -1;
                    area++;
                    queue.offer(new Node(nr, nc));
                }
            }
        }
        list.add(area);
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static void printMap() {
        for (int[] mm : map) {
            for (int m : mm) {
                System.out.print(m + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_2583().solution();
    }
}
