package silver4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    체스판 다시 칠하기
    https://www.acmicpc.net/problem/1018
*/
public class Boj_1018 {
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] board = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                if (input.charAt(j) == 'W') board[i][j] = true;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int r = 0; r <= N - 8; r++) {
            for (int c = 0; c <= M - 8; c++) {
                int white = 0;
                int black = 0;
                loop:
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        boolean color = board[r + i][c + j];
                        if (i % 2 == 0) {
                            if (j % 2 == 0) {
                                if (!color) white++;
                                else black++;
                            } else {
                                if (color) white++;
                                else black++;
                            }
                        } else {
                            if (j % 2 == 0) {
                                if (color) white++;
                                else black++;
                            } else {
                                if (!color) white++;
                                else black++;
                            }
                        }
                        if (white >= min && black >= min) break loop;
                    }
                }
                min = Math.min(min, Math.min(white, black));
            }
        }
        System.out.println(min);
    }

    public static void main(String[] args) throws Exception {
        new Boj_1018().solution();
    }
}
