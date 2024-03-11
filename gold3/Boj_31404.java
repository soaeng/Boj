package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    아리스, 청소합니다! (Easy)
    https://www.acmicpc.net/problem/31404
*/
public class Boj_31404 {
    static int H, W, count;
    static Node aris;
    static boolean[][] map;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        int[][][] rule = new int[2][H][W];
        map = new boolean[H][W];
        int[][][] visited = new int[4][H][W];
        count = 0;
        st = new StringTokenizer(br.readLine());
        aris = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        for (int i = 0; i < 2; i++) {
            for (int r = 0; r < H; r++) {
                String input = br.readLine();
                for (int c = 0; c < W; c++) {
                    rule[i][r][c] = Character.getNumericValue(input.charAt(c));
                }
            }
        }
        int move = 0;
        int temp = 0;
        while (count <= H * W && !isOutOfRange(aris.r, aris.c)) {
            int type = isCleaned() ? 0 : 1;
            if (type == 1) {
                if (visited[aris.d][aris.r][aris.c] == count) break;
                visited[aris.d][aris.r][aris.c] = count;
                temp++;
            } else temp = 0;
            aris.d = (aris.d + rule[type][aris.r][aris.c]) % 4;
            aris.r += deltas[aris.d][0];
            aris.c += deltas[aris.d][1];
            move++;
        }
        System.out.println(move - temp);
    }

    private static boolean isCleaned() {
        if (!map[aris.r][aris.c]) {
            map[aris.r][aris.c] = true;
            count++;
            return true;
        }
        return false;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W;
    }

    private static class Node {
        int r, c, d;

        public Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        @Override
        public String toString() {
            return "(" + r + ", " + c + ", " + d + ")";
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
