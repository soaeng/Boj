package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    연구소
    https://www.acmicpc.net/problem/14502
*/
public class Boj_14502_2 {
    static int N, M, area = Integer.MIN_VALUE;
    static char[][] map;
    static Node[] wall;
    static ArrayList<Node> virus;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        virus = new ArrayList<>();
        wall = new Node[3];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0, idx = 0; j < M; j++, idx += 2) {
                map[i][j] = input.charAt(idx);
                if (map[i][j] == '2') virus.add(new Node(i, j));
            }
        }

        comb(0, 0);
        System.out.println(area);
    }

    private static void comb(int depth, int start) {
        if (depth == 3) {
            area = Math.max(area, bfs(wall));
//            System.out.println(Arrays.toString(wall));
            return;
        }
        for (int i = start; i < N * M; i++) {
            int r = i / M;
            int c = i % M;
            if (map[r][c] == '0') {
                wall[depth] = new Node(r, c);
                comb(depth + 1, i + 1);
            }
        }
    }

    private static int bfs(Node[] wall) {
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        char[][] temp = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = map[i][j];
            }
        }
        for (Node node : wall) {
            temp[node.r][node.c] = '1';
        }
        for (Node node : virus) {
            queue.offer(new Node(node.r, node.c));
            visited[node.r][node.c] = true;
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = node.r + deltas[d][0];
                int nc = node.c + deltas[d][1];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visited[nr][nc]) continue;
                if (temp[nr][nc] != '0') continue;
                queue.offer(new Node(nr, nc));
                visited[nr][nc] = true;
                temp[nr][nc] = '2';
            }
        }

        return getSafeArea(temp);
    }

    private static int getSafeArea(char[][] temp) {
        int count = 0;
        for (char[] mm : temp) {
            for (char m : mm) {
                if (m == '0') count++;
            }
        }
        return count;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}

