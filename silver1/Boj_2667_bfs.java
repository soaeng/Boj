package silver1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    단지번호붙이기
    https://www.acmicpc.net/problem/2667
*/
public class Boj_2667_bfs {
    static int N;
    static boolean[][] map;
    static boolean[][] visited;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                if (input.charAt(j) == '1') map[i][j] = true;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                if (!map[i][j]) continue;
                bfs(i, j);
            }
        }
        Collections.sort(list);
        sb.append(list.size()).append("\n");
        for (int l : list) {
            sb.append(l).append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(int r, int c) {
        Queue<Node> queue = new ArrayDeque<>();
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int count = 0;
        queue.offer(new Node(r, c));
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            count++;
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (isOutOfRange(nr, nc)) continue;
                if (visited[nr][nc] || !map[nr][nc]) continue;
                queue.offer(new Node(nr, nc));
                visited[nr][nc] = true;
            }
        }
        list.add(count);
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }
}

class Node {
    int r, c;

    public Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}