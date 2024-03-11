package gold3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
    소코반
    https://www.acmicpc.net/problem/4577
*/
public class Boj_4577 {
    static int R, C, r, c;
    static char[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static ArrayList<int[]> target;

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = 0;
        while (true) {
            t++;
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (R == 0 && C == 0) break;
            map = new char[R][C];
            target = new ArrayList<>();
            for (int r = 0; r < R; r++) {
                String input = br.readLine();
                for (int c = 0; c < C; c++) {
                    map[r][c] = input.charAt(c);
                    switch (map[r][c]) {
                        case '+':
                        case 'B':
                            target.add(new int[]{r, c});
                            break;
                        case 'w':
                            map[r][c] = '.';
                            Boj_4577.r = r;
                            Boj_4577.c = c;
                            break;
                        case 'W':
                            map[r][c] = '+';
                            Boj_4577.r = r;
                            Boj_4577.c = c;
                            target.add(new int[]{r, c});
                            break;
                    }
                } // end of c
            } // end of r
            boolean isComplete = false;
            for (char cmd : br.readLine().toCharArray()) {
                int d;
                if (cmd == 'U') d = 0;
                else if (cmd == 'D') d = 1;
                else if (cmd == 'L') d = 2;
                else d = 3;
                move(d);
                if (isFinished()) {
                    isComplete = true;
                    break;
                }
            } // end of command

            map[r][c] = map[r][c] == '+' ? 'W' : 'w';
            sb.append("Game ").append(t).append(": ").append(isComplete ? "complete" : "incomplete").append("\n");
            for (char[] cc : map) {
                for (char c : cc) {
                    sb.append(c);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean isFinished() {
        for (int[] pos : target) {
            if (map[pos[0]][pos[1]] == '+') return false;
        }
        return true;
    }

    private static void printMap() {
        for (char[] cc : map) {
            for (char c : cc) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static void move(int d) {
        int nr = r + deltas[d][0];
        int nc = c + deltas[d][1];

        if (map[nr][nc] == '.' || map[nr][nc] == '+') {
            r = nr;
            c = nc;
        } else if (map[nr][nc] == 'b' || map[nr][nc] == 'B') {
            int bnr = nr + deltas[d][0];
            int bnc = nc + deltas[d][1];
            switch (map[bnr][bnc]) {
                case '.':
                    if (map[nr][nc] == 'B') map[nr][nc] = '+';
                    else map[nr][nc] = '.';
                    map[bnr][bnc] = 'b';
                    r = nr;
                    c = nc;
                    break;
                case '+':
                    if (map[nr][nc] == 'B') map[nr][nc] = '+';
                    else map[nr][nc] = '.';
                    map[bnr][bnc] = 'B';
                    r = nr;
                    c = nc;
                    break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
