package gold4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    QUENTO
    https://www.acmicpc.net/problem/10429
*/
public class Boj_10429 {
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int N, M;
    static char[][] map;
    static boolean isFinished;

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[3][3];
        isFinished = false;
        for (int i = 0; i < 3; i++) {
            map[i] = br.readLine().toCharArray();
        }
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if ((r + c) % 2 != 0) continue;
                boolean[][] selected = new boolean[3][3];
                ArrayList<Node> positions = new ArrayList<>();
                selected[r][c] = true;
                positions.add(new Node(r, c));
                dfs(r, c, selected, positions);
                selected[r][c] = false;
            }
        }
        if (!isFinished) System.out.println("0");
    }

    private static int calcPerm(ArrayList<Node> perm) {
        int result = map[perm.get(0).r][perm.get(0).c] - '0';
        for (int i = 1; i < perm.size() - 1; i++) {
            Node node = perm.get(i);
            int num = map[perm.get(i + 1).r][perm.get(i + 1).c] - '0';
            switch (map[node.r][node.c]) {
                case '+':
                    result += num;
                    break;
                case '-':
                    result -= num;
                    break;
            }
        }
        return result;
    }

    private static void dfs(int r, int c, boolean[][] selected, ArrayList<Node> positions) {
        if (isFinished) return;
        if (positions.size() == M * 2 - 1) {
            if (calcPerm(positions) == N) {
                StringBuilder sb = new StringBuilder();
                sb.append("1").append("\n");
                for (Node node : positions) {
                    sb.append(node.r).append(" ").append(node.c).append("\n");
                }
                System.out.println(sb);
                isFinished = true;
            }
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = r + deltas[d][0];
            int nc = c + deltas[d][1];
            if (!isInRange(nr, nc)) continue;
            if (selected[nr][nc]) continue;
            selected[nr][nc] = true;
            positions.add(new Node(nr, nc));
            dfs(nr, nc, selected, positions);
            positions.remove(positions.size() - 1);
            selected[nr][nc] = false;
        }
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < 3 && c >= 0 && c < 3;
    }

    private static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        new Boj_10429().solution();
    }
}
